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


@WebServlet("/UtenteDuplicatoControllo")
public class UtenteDuplicatoControllo extends HttpServlet {
  private static final long serialVersionUID = 1L;
  /**
   * Servlet verifica esistenza utente già registrato.
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    String email = request.getParameter("email");
    UtenteModelDM model = new UtenteModelDM();
    UtenteBean utente = null;
    try {
      utente = model.doRetrieveByKey(email);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (utente != null) {
      out.print("false");
    } else {
      out.print("true");
    }
  }
}
