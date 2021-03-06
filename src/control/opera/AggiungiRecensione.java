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
    String commento = request.getParameter("commento");

    if (numStelle < 1 || numStelle > 5) {
      throw new IllegalArgumentException("Numero stelle non rispettato");
    }
    if (commento.length() > 100) {
      throw new IllegalArgumentException("La lunghezza del commento non è rispettata");
    }
    RecensioneBean esiste = new RecensioneBean();
    if (email.length() == 0) {
      RequestDispatcher rd;
      rd = request.getRequestDispatcher(response.encodeRedirectURL("CercaOpera?search="));
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
      RecensioneBean recensione = new RecensioneBean(numStelle, email, operaId, commento);
      try {
        model.doSave(recensione);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if(!email.equals("guida@pocketmuseum.it") && !email.equals("biglietteria@pocketmuseum.it")
            && esiste != null){
      RecensioneBean recensione2 = new RecensioneBean(numStelle, email, operaId, commento);
      try {
        model.doUpdate(recensione2);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
    RequestDispatcher rd;
    rd = request.getRequestDispatcher(response.encodeRedirectURL("CercaOpera?search="));
    rd.forward(request, response);
  }
}