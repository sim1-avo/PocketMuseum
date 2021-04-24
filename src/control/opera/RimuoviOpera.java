package control.opera;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.opera.OperaModelDM;

@WebServlet("/RimuoviOpera")
public class RimuoviOpera extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public RimuoviOpera() {
    super();
  }
  /**
   * Servlet rimozione opera .
   */
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    OperaModelDM  model = new OperaModelDM();
    try {
      model.doDelete(model.doRetrieveByKey(id));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    response.setContentType("text/html");
    request.setAttribute("opere", null);
    RequestDispatcher rd = request.getRequestDispatcher("RecuperaOpere");
    rd.forward(request, response);
  }
}
