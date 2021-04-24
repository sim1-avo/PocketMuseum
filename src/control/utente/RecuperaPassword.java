package control.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Utility;
import model.utente.UtenteBean;
import model.utente.UtenteModel;
import model.utente.UtenteModelDM;


@WebServlet("/RecuperaPassword")
public class RecuperaPassword extends HttpServlet {
  private static final long serialVersionUID = 1L;
  /**
   * Servletrecupero password.
   */
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String email = request.getParameter("email");
    UtenteModel<UtenteBean> model = new UtenteModelDM();
    UtenteBean utente = null;
    try {
      utente = model.doRetrieveByKey(email);
      if (utente.getNome() != null) {
        String password = utente.getPassword();
        Utility.sendMail(email, password);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
      
    response.sendRedirect("Log.jsp");
     
  }
}