package control.evento;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.evento.EventoBean;
import model.evento.EventoModelDM;

/**
 * Servlet implementation class InserisciEventoServlet.
 */
@WebServlet("/InserisciEventoServlet")
@MultipartConfig
public class InserisciEventoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InserisciEventoServlet() {
        super();
    }

    /**
     * Servlet inserimento utente .
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        EventoModelDM model = new EventoModelDM();
        String nome = request.getParameter("Nome");
        String descrizione = request.getParameter("Descrizione");
        Part immagine = request.getPart("Immagine");
        EventoBean bean = new EventoBean();
        Date dataInizio;
        Date dataFine;
        if (nome.length() == 0) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo Nome e vuoto");
        } else if (nome.length() < 10) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo Nome non rispetta la lunghezza minima");
        } else if (nome.length() > 100) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo Nome non rispetta la lunghezza massima");
        } else {
            bean.setNome(nome);
        }
        if (descrizione.length() == 0) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo descrizione e vuoto");
        } else if (descrizione.length() < 10) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo descrizione non rispetta la lunghezza minima.");
        } else if (descrizione.length() > 5000) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo descrizione non rispetta la lunghezza massima");
        } else {
            bean.setDescrizione(descrizione);
        }
        if (immagine != null) {
            Pattern p1 = Pattern.compile("\\.jpg$|\\.png$|\\.JPG$|\\.PNG$");
            Matcher m1 = p1.matcher(immagine.getSubmittedFileName());


            if (immagine.getSubmittedFileName().equals("")) {
                throw new IllegalArgumentException("L'aggiunta dell'evento non "
                        + "va a buon fine poiche il campo immagine e vuoto");
            } else if (m1.find() == false) {
                throw new IllegalArgumentException("L'aggiunta dell'evento non va a buon "
                        + "fine poiche il campo immagine contiene un file di formato diverso da jpg o png");
            } else if (immagine.getSize() > 1048576) {
                throw new IllegalArgumentException("L'aggiunta dell'evento non va a buon "
                        + "fine poiche il campo immagine contiene file di dimensioni superiori a 1MB");
            } else {
                bean.setImmaginePart(immagine);
            }
        }

        if ((request.getParameter("data_inizio")).length() == 0) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo data inizio e vuoto");
        } else {
            dataInizio = Date.valueOf(request.getParameter("data_inizio"));
            Timestamp ts1  = new Timestamp(dataInizio.getTime());
            bean.setDataInizio(ts1);
        }
        if ((request.getParameter("data_fine")).length() == 0) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo data fine e vuoto");
        } else {
            dataFine = Date.valueOf(request.getParameter("data_fine"));
            Timestamp ts2 = new Timestamp(dataFine.getTime());
            bean.setDataFine(ts2);
        }
        try {
            if (dataFine.after(dataInizio) || dataFine.compareTo(dataInizio) == 0) {
                model.doSave(bean);
            } else {
                throw new Exception();
            }
        } catch (Exception  e) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non va a buon fine poiche"
                    + " il campo data fine contiene una data antecedente a data inizio");
        }
        response.setContentType("text/html");
        RequestDispatcher rd = request.getRequestDispatcher("./Homepage.jsp");
        rd.forward(request, response);
    }
}
