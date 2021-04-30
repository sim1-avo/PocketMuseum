package control.evento;

import model.evento.EventoBean;
import model.evento.EventoModel;
import model.evento.EventoModelDM;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/ModificaEvento")
@MultipartConfig
public class ModificaEventoServlet extends HttpServlet {

    public ModificaEventoServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Controllo se qualche utente non 'guida' provi a modificare un evento
        if(request.getSession().getAttribute("guida") == null ) {
            response.sendRedirect("Log.jsp");
            return;
        }

        if ( ! ModificaEventoServlet.checkParameters(request)) {
            ModificaEventoServlet.getAlert("error", request);
            ModificaEventoServlet.forwardRequest(request, response, "./GuidaPageServlet");
        }

        //Validate parameters
        ModificaEventoServlet.validateParametersServerSide(request);

        EventoBean evento = new EventoBean();
        Date dataInizio = Date.valueOf(request.getParameter("data_inizio"));
        Date dataFine = Date.valueOf(request.getParameter("data_fine"));;

        evento.setId(Integer.parseInt(request.getParameter("idEvent")));
        evento.setNome(request.getParameter("Nome"));
        evento.setDescrizione(request.getParameter("Descrizione"));
        evento.setDataInizio(new Timestamp(dataInizio.getTime()));
        evento.setDataFine(new Timestamp(dataFine.getTime()));
        if( ! request.getPart("Immagine").getSubmittedFileName().equals(""))
            evento.setImmaginePart(request.getPart("Immagine"));

        EventoModelDM modelEvent = new EventoModelDM();
        try {
            boolean resultUpdate = modelEvent.doUpdate(evento);
            if(resultUpdate) {
                ModificaEventoServlet.getAlert("success", request);
                ModificaEventoServlet.forwardRequest(request, response, "./GuidaPageServlet");
            } else {
                ModificaEventoServlet.getAlert("error", request);
                ModificaEventoServlet.forwardRequest(request, response, "./GuidaPageServlet");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /** Check if the parameters are setted
     * @param req
     * */
    private static boolean checkParameters(HttpServletRequest req) throws ServletException, IOException {

        //add here new check on parameters
        return (req.getParameter("Nome") != null) &&
                (req.getParameter("data_inizio") != null) &&
                (req.getParameter("data_fine") != null) &&
                (req.getParameter("Descrizione") != null) &&
                (req.getPart("Immagine") != null) &&
                (req.getParameter("idEvent") != null);
    }

    /** Forward request
     * @param req
     * @param res
     * @param destination the destination where you want to send the datas. */
    private static void forwardRequest(HttpServletRequest req, HttpServletResponse res, String destination) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(destination);
        rd.forward(req, res);
    }

    /** Set the type and the message of alert on the attributes of the
     *  request.
     *  @param req the request of the servlet
     *  @param type the type of alert: for now is accepted only 'success' and 'error'. */
    private static void getAlert(String type, HttpServletRequest req) {
        if(type.equals("success")) {
            String messageAlert = "<strong>Fatto!</strong> L'evento è stato modificato con successo.";
            req.setAttribute("alertSuccess", true);
            req.setAttribute("messageAlert", messageAlert);
        } else if (type.equals("error")) {
            String messageAlert = "<strong>Errore.</strong> Si è verificato un problema durante la modifica dell'evento.";
            req.setAttribute("alertError", true);
            req.setAttribute("messageAlert", messageAlert);
        }
    }

    /** Validation parameters Server Side */
    private static void validateParametersServerSide(HttpServletRequest req) throws ServletException, IOException {
        if( ! ModificaEventoServlet.checkParameters(req))
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche uno o più parametri non sono stati passati o sono vuoti.");

        String nome = req.getParameter("Nome");
        String descrizione = req.getParameter("Descrizione");
        Part immagine = req.getPart("Immagine");
        Date dataInizio = Date.valueOf(req.getParameter("data_inizio"));
        Date dataFine = Date.valueOf(req.getParameter("data_fine"));

        //validate nome
        if (nome.length() == 0) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo Nome e vuoto");
        } else if (nome.length() < 10) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo Nome non rispetta la lunghezza minima");
        } else if (nome.length() > 100) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo Nome non rispetta la lunghezza massima");
        }


        //validate descrizione
        if (descrizione.length() == 0) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo descrizione e vuoto");
        } else if (descrizione.length() < 10) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo descrizione non rispetta la lunghezza minima.");
        } else if (descrizione.length() > 5000) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo descrizione non rispetta la lunghezza massima");
        }

        //validate immagine
        if (immagine != null && !(immagine.getSubmittedFileName().equals("")) ) {
            Pattern p1 = Pattern.compile("\\.jpg$|\\.png$|\\.JPG$|\\.PNG$");
            Matcher m1 = p1.matcher(immagine.getSubmittedFileName());

            if (m1.find() == false) {
                throw new IllegalArgumentException("L'aggiunta dell'evento non va a buon "
                        + "fine poiche il campo immagine contiene un file di formato diverso da jpg o png");
            } else if (immagine.getSize() > 1048576) {
                throw new IllegalArgumentException("L'aggiunta dell'evento non va a buon "
                        + "fine poiche il campo immagine contiene file di dimensioni superiori a 1MB");
            }
        }

        //validate data inizio
        if ((req.getParameter("data_inizio")).length() == 0) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo data inizio e vuoto");
        }

        //validate data fine
        if ((req.getParameter("data_fine")).length() == 0) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non "
                    + "va a buon fine poiche il campo data fine e vuoto");
        }

        //Compare data inizio e fine
        if ( dataFine.before(dataInizio) && dataFine.compareTo(dataInizio) != 0) {
            throw new IllegalArgumentException("L'aggiunta dell'evento non va a buon fine poiche"
                    + " il campo data fine contiene una data antecedente a data inizio");
        }
    }
}