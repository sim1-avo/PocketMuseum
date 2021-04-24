package control.biglietto;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.biglietto.AcquistaBean;
import model.biglietto.AcquistaModelDM;
import model.biglietto.BigliettoBean;
import model.biglietto.BigliettoModelDM;
import model.biglietto.CodeGenerator;
 
@WebServlet("/AcquistaBigliettoServlet")
public class AcquistaBigliettoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public AcquistaBigliettoServlet() {
    super();
  }
  
  /**
   * Servlet Acquista Biglietto.
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    AcquistaModelDM model1 = new AcquistaModelDM();
    BigliettoModelDM model2 = new BigliettoModelDM();
    
    String numeroCarta = request.getParameter("numerocarta");
    if (numeroCarta.length() != 12) {
      throw new IllegalArgumentException("Lunghezza numero carta non rispettata");
    }
    if (!numeroCarta.matches("^[0-9]{12}")) {
      throw new IllegalArgumentException("Formato numero carta non rispettato");
    }
    
    String scadenza = request.getParameter("scadenza");
    if (scadenza.length() != 5) {
      throw new IllegalArgumentException("Lunghezza scadenza non rispettata");
    }
    if (!scadenza.matches("^[0-1]{1}[0-9]{1}\\/[0-9]{2}")) {
      throw new IllegalArgumentException("Formato scadenza non rispettato");
    }
    
    String cvv = request.getParameter("cvv");
    if (cvv.length() != 3) {
      throw new IllegalArgumentException("Lunghezza cvv non rispettata");
    }
    if (!cvv.matches("^[0-9]{3}")) {
      throw new IllegalArgumentException("Formato cvv non rispettato");
    }
    
    if (request.getParameter("mode").equals("registrato")) {
      try {
        String codice = model1.doRetrieveByLastDate("CodiceBiglietto").substring(4, 8);
        int lastCode = Integer.parseInt(codice);
        CodeGenerator codeGenerator = new CodeGenerator(lastCode + 1);
        Date dataInizio = Date.valueOf(request.getParameter("data_inizio"));
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
        String strDate = formatter.format(dataInizio);
        String turno = request.getParameter("turno");
        String dataInizioTurno = strDate + " " + turno + ".0";
        Timestamp ts = Timestamp.valueOf(dataInizioTurno);
        String oraTurno = turno.substring(0, 2);
        int fineTurno = Integer.parseInt(oraTurno) + 2;
        oraTurno = String.valueOf(fineTurno);
        String dataFineTurno = strDate + " " + oraTurno + turno.substring(2) + ".0";
        Timestamp ts2 = Timestamp.valueOf(dataFineTurno);
        String nuovoCodice = codeGenerator.nextCode();
        BigliettoBean beanB = new BigliettoBean();
        beanB.setInizioTurno(ts);
        beanB.setFineTurno(ts2);
        beanB.setCosto(12.5f);
        beanB.setCodice(nuovoCodice);
        model2.doSave(beanB);
        Date dataAcquisto =  new Date(System.currentTimeMillis()); 
        String email = request.getParameter("email");
        AcquistaBean beanA = new AcquistaBean();
        beanA.setDataAcquisto(dataAcquisto);
        beanA.setCodice(nuovoCodice);
        beanA.setEmail(email);
        
        model1.doSave(beanA);
        
        
      } catch (SQLException  e) {
        System.out.println("Error: " + e.getMessage());
        request.setAttribute("error", e.getMessage());
      }
    } else if (request.getParameter("mode").equals("biglietteria")) {
      String nomeTitolare = request.getParameter("nometitolare");
      if (nomeTitolare.length() < 5 || nomeTitolare.length() > 36) {
        throw new IllegalArgumentException("Lunghezza nome titolare non rispettata");
      }
      if (!nomeTitolare.matches("^(?![ .]+$)[a-zA-Z .]*$")) {
        throw new IllegalArgumentException("Formato nome titolare non rispettato");
      }

      try {
        String codice = model1.doRetrieveByLastDate("CodiceBiglietto").substring(4, 8);
        int lastCode = Integer.parseInt(codice);
        CodeGenerator codeGenerator = new CodeGenerator(lastCode + 1);
        Date dataInizio = Date.valueOf(request.getParameter("data_inizio"));
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
        String strDate = formatter.format(dataInizio);
        String turno = request.getParameter("turno");
        String dataInizioTurno = strDate + " " + turno + ".0";
        Timestamp ts = Timestamp.valueOf(dataInizioTurno);
        String oraTurno = turno.substring(0, 2);
        int fineTurno = Integer.parseInt(oraTurno) + 2;
        oraTurno = String.valueOf(fineTurno);
        String dataFineTurno = strDate + " " + oraTurno + turno.substring(2) + ".0";
        Timestamp ts2 = Timestamp.valueOf(dataFineTurno);
        String nuovoCodice = codeGenerator.nextCode();
        BigliettoBean beanB = new BigliettoBean();
        beanB.setInizioTurno(ts);
        beanB.setFineTurno(ts2);
        beanB.setCosto(12.5f);
        beanB.setCodice(nuovoCodice);
        model2.doSave(beanB);
      } catch (Exception  e) {
        System.out.println("Error: " + e.getMessage());
        request.setAttribute("error", e.getMessage());
      }
    }
    
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(response.encodeRedirectURL("./Homepage.jsp"));
    dispatcher.forward(request, response);
  }

}
