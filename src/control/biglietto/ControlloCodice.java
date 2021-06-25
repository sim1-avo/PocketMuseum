package control.biglietto;

import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.biglietto.BigliettoModelDM;

/**
 * Servlet implementation class CodiceServlet.
 */
@WebServlet("/CodiceServlet")
public class ControlloCodice extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static BigliettoModelDM ticket = new BigliettoModelDM(null);
  public ControlloCodice() {
    super();
  }
  /**
   * Servlet controllo codice.
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
    HttpSession session = request.getSession();
    String str = request.getParameter("data");
    String codice = request.getParameter("codice");
    if (codice.length() < 12 || codice.length() > 12) {
      throw new IllegalArgumentException("Lunghezza codice non valida");
    }
    
    if (!codice.matches("[a-zA-Z]{4}[0-9]{4}[a-zA-Z]{4}")) {
      throw new IllegalArgumentException("Formato codice non valido");
    }
    
    try {
      Timestamp data = java.sql.Timestamp.valueOf(str);
      Timestamp inizio = ticket.doRetrieveBigliettoByKey(codice).getInizioTurno();
      Timestamp fine = ticket.doRetrieveBigliettoByKey(codice).getFineTurno();
      if (ticket.doRetrieveByKey(codice) && (data.before(fine) && (data.after(inizio)))) {
        session.setAttribute("codiceValido", codice);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher(response.encodeRedirectURL("./OpereTour.jsp"));
        rd.forward(request, response);
      } else {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("non trovato");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
