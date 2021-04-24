package control.opera;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.opera.OperaBean;
import model.opera.OperaModelDM;

/**
 * Servlet implementation class RecuperaImmagini.
 */
@WebServlet("/RecuperaImmagini")
public class RecuperaImmagini extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static OperaModelDM model = new OperaModelDM();
  
  public RecuperaImmagini() {
    super();
  }
  /**
   * Servlet recupero immagini .
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    OperaBean opera = null;
    int id = Integer.valueOf(request.getParameter("id"));
    try {
      opera = model.doRetrieveByKey(id);
      response.setContentType("text/plain");
      response.getWriter().print(opera.getImmagine() + "ciao" + opera.getCopertina());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    doGet(request, response);
  }

}
