package control.homepage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomePageServlet.
 */
@WebServlet("/HomePageServlet")
public class HomePageServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
  public HomePageServlet() {
    super();
  }
  /**
   * Servlet recupero eventi per homepage.
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws  IOException, ServletException {
   
    RequestDispatcher rd = request.getRequestDispatcher("/Index.jsp");
    rd.forward(request, response);
  }
  
  
}
