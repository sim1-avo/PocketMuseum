package control.biglietto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.biglietto.BigliettoBean;
import model.biglietto.BigliettoModel;
import model.biglietto.BigliettoModelDM;


@WebServlet("/ControlloDataServlet")
public class ControlloDataServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public ControlloDataServlet() {
    super();
  }
  
  /**
   * Servlet controllo codice.
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
   
    BigliettoModel<BigliettoBean> model = new BigliettoModelDM();
    
    String day = request.getParameter("day");
    String month = request.getParameter("month");
    String year = request.getParameter("year");
    if (month.length() == 1) {
      month = "0" + month;
    }
    if (day.length() == 1) {
      day = "0" + day;
    }
    int numBiglietti1;
    int numBiglietti2;
    int numBiglietti3;
    int numBiglietti4;
    java.sql.Timestamp ts1;
    java.sql.Timestamp ts2;
    java.sql.Timestamp ts3;
    java.sql.Timestamp ts4;
    String input1 = year + "-" + month + "-" + day + " 09:00:00";
    String input2 = year + "-" + month + "-" + day + " 11:15:00";
    String input3 = year + "-" + month + "-" + day + " 13:30:00";
    String input4 = year + "-" + month + "-" + day + " 15:45:00";
    
    try {
      ts1 = java.sql.Timestamp.valueOf(input1);
      ts2 = java.sql.Timestamp.valueOf(input2);
      ts3 = java.sql.Timestamp.valueOf(input3);
      ts4 = java.sql.Timestamp.valueOf(input4);
      numBiglietti1 = model.controlloDisponibilita(ts1);
      numBiglietti2 = model.controlloDisponibilita(ts2);
      numBiglietti3 = model.controlloDisponibilita(ts3);
      numBiglietti4 = model.controlloDisponibilita(ts4);
      
      String risultato = "";
      
      if (numBiglietti1 < 10) {
        risultato = "true";
      } else {
        risultato = "false";
      }
      if (numBiglietti2 < 10) {  
        risultato += "&true";
      } else {
        risultato += "&false";
      }
      if (numBiglietti3 < 10) {
        risultato += "&true";
      } else {
        risultato += "&false";
      }
      if (numBiglietti4 < 10) {
        risultato += "&true";
      } else {
        risultato += "&false";
      }
      response.setContentType("text/html;charset=UTF-8");
      response.getWriter().write(risultato);

    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      request.setAttribute("error", e.getMessage());
    }
  }

  
}
