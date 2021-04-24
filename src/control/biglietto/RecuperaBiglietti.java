package control.biglietto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.biglietto.BigliettoBean;
import model.biglietto.BigliettoModel;
import model.biglietto.BigliettoModelDM;

/**
 * Servlet implementation class RecuperaBiglietti.
 */
@WebServlet("/RecuperaBiglietti")
public class RecuperaBiglietti extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /**
   * Servlet recupero biglietti.
   */
  

  public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    String inizio = request.getParameter("inizio");
    String fine = request.getParameter("fine");
    
    ArrayList<ArrayList> biglietti = new ArrayList<ArrayList>();
    BigliettoModel<BigliettoBean> model = new BigliettoModelDM();
    try {
      biglietti = (ArrayList<ArrayList>) model.doRetrievePurchasedTicketByDate(inizio, fine);
      for (int i = 0; i < biglietti.get(0).size(); i++) {
        for (int j = 0; j < 7; j++) {
          if (j == 0) {
            out.println("<tr id=" + biglietti.get(j).get(i) + " <td></td><td>" + (i + 1) + "</td>");
          } else {
            out.println("<td>" + biglietti.get(j - 1).get(i) + "</td>");
          }
        }
      }
      response.setContentType("text/html;charset=UTF-8");
      out.println("</tr>");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
