package control.opera;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.opera.OperaBean;
import model.opera.OperaModelDM;

/**
 * Servlet implementation class GuidaPageServlet.
 */
@WebServlet("/GuidaPageServlet")
public class GuidaPageServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private OperaModelDM modelOpere = new OperaModelDM();
    

  public GuidaPageServlet() {
    super();
  }
  /**
   * Servlet pagina guida.
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
       throws  IOException, ServletException {
    ArrayList<OperaBean> opere = null;
    if (request.getSession().getAttribute("NoDbConnection") != null) {
      modelOpere = null;
    }
        
    try {
      opere = (ArrayList<OperaBean>) modelOpere.doRetrieveAll("ASC");
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("opere", opere);
    RequestDispatcher rd = request.getRequestDispatcher("/GuidaPersonalPage.jsp");
    rd.forward(request, response);
  }
}


       
