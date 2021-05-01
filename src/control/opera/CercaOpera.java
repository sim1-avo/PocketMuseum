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


@WebServlet("/CercaOpera")
public class CercaOpera extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private OperaModelDM model = new OperaModelDM();
  
  public CercaOpera() {
    super();
  }
  /**
   * Servlet ricerca opera .
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String cerca = request.getParameter("search");
    ArrayList<OperaBean> opere = null;
    try {
      if (request.getSession().getAttribute("NoDbConnection") != null) {
        model = null;
      }
      opere = (ArrayList<OperaBean>) model.doRetrieveName(cerca);
      ArrayList<OperaBean> listOpereVisibili = new ArrayList<OperaBean>();
      for (OperaBean op : opere) {
        if (op.getStato().equals("visibile")) {
          listOpereVisibili.add(op);
        }
      }
      request.setAttribute("opere", listOpereVisibili);
      response.setContentType("text/html");
      RequestDispatcher rd = request.getRequestDispatcher("./OpereTour.jsp");
      rd.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
