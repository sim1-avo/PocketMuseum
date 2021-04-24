package control.utente;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TemplateDM;
import model.utente.UtenteBean;
import model.utente.UtenteModelDM;

@WebServlet("/ModificaDati")
public class ModificaDati extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ModificaDati() {
    super();
  }
  /**
   * Servlet modifica dati.
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    UtenteBean utente = new UtenteBean();
    if (request.getSession().getAttribute("utente") != null) {
      utente = (UtenteBean) request.getSession().getAttribute("utente");
    }
    if (request.getSession().getAttribute("guida") != null) {
      utente = (UtenteBean) request.getSession().getAttribute("guida");
    }
    if (request.getSession().getAttribute("biglietteria") != null) {
      utente = (UtenteBean) request.getSession().getAttribute("biglietteria");
    }    
    String richiesta = request.getParameter("richiesta");
    if (richiesta.equals("psw")) {
      String vecchio = request.getParameter("vecchio");
      System.out.println("PW utente vecchia " + vecchio);
      System.out.println("PW " + utente.getPassword());
      if (vecchio.equals(utente.getPassword())) {
        utente.setPassword(request.getParameter("nuovo"));
        try {
          TemplateDM model = new UtenteModelDM();
          try {
            System.out.println("utente");
            model.modifica(utente);
          } catch (SQLException e) {
            e.printStackTrace();
          }
        } finally {
          RequestDispatcher dispatcher = request
                    .getRequestDispatcher(response.encodeRedirectURL("./PersonalPage.jsp"));
          dispatcher.forward(request, response);
        }
      } else {
        request.getSession().setAttribute("error",  Boolean.TRUE);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(response.encodeRedirectURL("./PersonalPage.jsp"));
        dispatcher.forward(request, response);
      }
    }
  }


}
