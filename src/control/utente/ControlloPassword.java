package control.utente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.utente.UtenteBean;
import model.utente.UtenteModelDM;


@WebServlet("/ControlloPassword")
public class ControlloPassword extends HttpServlet {
  /**
   * Servlet controllo password.
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    UtenteModelDM model = new UtenteModelDM();
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    UtenteBean utente = null;
    try {
      utente = model.doRetrieveByKey(email);
      if ((utente.getPassword()).equals(password)) {
        out.print("true");
      } else {
        out.print("false");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
