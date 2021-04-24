package control.opera;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.opera.RecensioneBean;
import model.opera.RecensioneModelDM;
 
/* Servlet implementation class AggiungiRecensione */
@WebServlet("/AggiungiRecensione")
public class AggiungiRecensione extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static RecensioneModelDM model = new RecensioneModelDM();
       
  public AggiungiRecensione() {
    super();
  }
  /**
   * Servlet aggiunta recensione.
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    String email = request.getParameter("email");
    int operaId = Integer.parseInt(request.getParameter("operaId"));
    int numStelle = Integer.parseInt(request.getParameter("stelle"));
    
    if (numStelle < 1 || numStelle > 5) {
      throw new IllegalArgumentException("Numero stelle non rispettato");
    }
    RecensioneBean esiste = new RecensioneBean();
    if (email.length() == 0) {
      RequestDispatcher rd;
      rd = request.getRequestDispatcher(response.encodeRedirectURL("CercaOperaById?id=" + operaId));
      rd.forward(request, response);
    }
    try {
      esiste = model.doRetrieveByKey(operaId, email);
    } catch (SQLException e) {
      e.printStackTrace();
      esiste = null;
    }
    if (!email.equals("guida@pocketmuseum.it") && !email.equals("biglietteria@pocketmuseum.it")
        && esiste == null) {
      RecensioneBean recensione = new RecensioneBean(numStelle, email, operaId);
      try {
        model.doSave(recensione);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    RequestDispatcher rd;
    rd = request.getRequestDispatcher(response.encodeRedirectURL("CercaOperaById?id=" + operaId));
    rd.forward(request, response);
  }
}
