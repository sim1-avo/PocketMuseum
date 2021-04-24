package control.biglietto;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.biglietto.AcquistaBean;
import model.biglietto.AcquistaModel;
import model.biglietto.AcquistaModelDM;
import model.utente.UtenteBean;

/*
 * Servlet implementation class PageBigliettiServlet
 */
@WebServlet("/PageBigliettiServlet")
public class PageBigliettiServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private AcquistaModel<AcquistaBean> model = new AcquistaModelDM();

  public PageBigliettiServlet() {
    super();
  }
  /**
   * Servlet recupero biglietti utente .
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    try {
      UtenteBean utente = new UtenteBean();
      utente = (UtenteBean) session.getAttribute("utente");
      ArrayList<AcquistaBean> acquista = new ArrayList<AcquistaBean>();
      acquista = (ArrayList<AcquistaBean>) model.doRetrieveAllByEmail(utente.getEmail());
      request.setAttribute("acquista", acquista);
    } catch (Exception e) {
      e.printStackTrace();
      response.sendRedirect("./Log.jsp");
      return;
    }
    
    RequestDispatcher rd = request.getRequestDispatcher("./PersonalPage.jsp");
    rd.forward(request, response);
  }

  

}
