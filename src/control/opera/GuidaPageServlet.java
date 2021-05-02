package control.opera;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.evento.EventoBean;
import model.evento.EventoModelDM;
import model.opera.OperaBean;
import model.opera.OperaModelDM;

/**
 * Servlet implementation class GuidaPageServlet.
 */
@WebServlet("/GuidaPageServlet")
public class GuidaPageServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private EventoModelDM modelEventi = new EventoModelDM();
  private OperaModelDM modelOpere = new OperaModelDM();


  public GuidaPageServlet() {
    super();
  }
  /**
   * Servlet pagina guida.
   */

  public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws  IOException, ServletException {
    ArrayList<EventoBean> eventi = null;
    ArrayList<OperaBean> opere = null;
    if (request.getSession().getAttribute("NoDbConnection") != null) {
      modelEventi = null;
      modelOpere = null;
    }

    try {
      eventi = modelEventi.doRetrieveAll("DESC");
      opere = (ArrayList<OperaBean>) modelOpere.doRetrieveAll("ASC");
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("eventi", eventi);
    request.setAttribute("opere", opere);
    RequestDispatcher rd = request.getRequestDispatcher("/GuidaPersonalPage.jsp");
    rd.forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
          throws  IOException, ServletException {
    this.doGet(request, response);
  }
}


       
