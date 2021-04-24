package control.utente;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**Servlet per efftuare il logout.
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public LogoutServlet() {
    super();
  }
  /**
   * Servlet gestione logout.
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getSession().invalidate();
    RequestDispatcher rd = request.getRequestDispatcher("./Log.jsp");
    rd.forward(request, response);
  }
  
  
}
