package control.utente;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.utente.UtenteBean;
import model.utente.UtenteModel;
import model.utente.UtenteModelDM;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public LoginServlet() {
    super();
  }
  /**
   * Servlet gestione login al sistema.
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String redirect = "";
    UtenteModel<UtenteBean> model = new UtenteModelDM();
    UtenteBean utente = null;
    String email = request.getParameter("email");
    String password = request.getParameter("Password");
    if (email.length() == 0) {
      throw new IllegalArgumentException("L'accesso non va a buon fine poichè"
          + " il campo e-mail è vuoto");
    }
    if (password.length() == 0) {
      throw new IllegalArgumentException("L'accesso non va a buon fine poichè"
          + " il campo password è vuoto");
    }
    try {
      utente = model.doRetrieveByKey(email);
      if (password.equals(utente.getPassword())) {
        if  (email.equals("guida@pocketmuseum.it")) {
          request.getSession(true).setAttribute("guida", utente);
          redirect = "Opere.jsp";
        } else if (email.equals("biglietteria@pocketmuseum.it")) {
          request.getSession(true).setAttribute("biglietteria", utente);
          redirect = "Tour.jsp";
        } else {
          request.getSession(true).setAttribute("utente", utente);
          redirect = "Tour.jsp";
        }
      } else {
        throw new IllegalArgumentException("L'accesso non va a buon fine poichè"
            + " il campo password è errato");
      }   
      response.sendRedirect(redirect);
    } catch (SQLException e) {
      e.printStackTrace();
      response.sendRedirect(redirect);
    }
  }
}
