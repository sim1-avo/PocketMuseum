package control.opera;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.opera.OperaBean;
import model.opera.OperaModelDM;

@WebServlet("/RecuperaOpere")
public class RecuperaOpere extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static OperaModelDM model = new OperaModelDM();

  public RecuperaOpere() {
    super();
  }
  /**
   * Servlet recupero dati opera.
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    ArrayList<OperaBean> opere = null;
    try {
      if (request.getSession().getAttribute("NoDbConnection") != null) {
        model = null; 
      }
      opere = (ArrayList<OperaBean>) model.doRetrieveAll("ASC");
      opere.sort(new Comparator<OperaBean>() {
        public int compare(OperaBean o1, OperaBean o2) {
          return o1.getNome().compareTo(o2.getNome());
        }
      });
      response.setContentType("text/html");
      request.setAttribute("opere", opere);
      RequestDispatcher rd = request.getRequestDispatcher("./GestioneOpera.jsp");
      rd.forward(request, response);
    } catch (Exception e) { 
      e.printStackTrace();
    }
  }
  
  public void doPost(HttpServletRequest request, HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }

}
