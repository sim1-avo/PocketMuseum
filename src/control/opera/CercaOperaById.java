package control.opera;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.biglietto.BigliettoBean;
import model.biglietto.BigliettoModelDM;
import model.opera.OperaBean;
import model.opera.OperaModelDM;
import model.opera.RecensioneBean;
import model.opera.RecensioneModelDM;


// Servlet implementation class CercaOperaById

@WebServlet("/CercaOperaById")
public class CercaOperaById extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static OperaModelDM operaModel = new OperaModelDM();
  private static RecensioneModelDM recensioneModel = new RecensioneModelDM();
  private static BigliettoModelDM bigliettoModel = new BigliettoModelDM();

  public CercaOperaById() {
    super();
  }
  /**
   * Servlet ricerca opera in base al codice identificatore.
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    OperaBean opera = null;
    ArrayList<RecensioneBean> recensioniOpere = null;
    int id = Integer.parseInt(request.getParameter("id"));
    
    try {
      if (request.getSession().getAttribute("guida") == null) {
        BigliettoBean biglietto;
        String codice = (String) request.getSession().getAttribute("codiceValido");
        biglietto = bigliettoModel.doRetrieveBigliettoByKey(codice);
        Timestamp data = new Timestamp(System.currentTimeMillis());
        Timestamp fine = biglietto.getFineTurno();
        if (data.after(fine)) {
          request.getSession().removeAttribute("codiceValido");
          response.setContentType("text/html");
          RequestDispatcher rd = request.getRequestDispatcher(
              response.encodeRedirectURL("./EventiHomePageServlet?fineTurno=true"));
          rd.forward(request, response);
          return;
        }
      } 
      opera = operaModel.doRetrieveByKey(id);
      recensioniOpere = (ArrayList<RecensioneBean>) recensioneModel.doRetrieveById(id);
      if  (request.getParameter("json") != null && request.getParameter("json").equals("true")) {
        Gson gson = new Gson();
        String json = gson.toJson(opera);
        response.setContentType("application/json");
        response.getWriter().write(json);
      } else {
        request.setAttribute("opera", opera);
        int totale = 0;
        int i = 0;
        for (RecensioneBean rb: recensioniOpere) {
          totale += rb.getValutazione();
          i++;
        }
        Integer media;
        if (i != 0) {
          media = totale / i;
        } else {
          media = 0;
        }
        request.setAttribute("media", media);
        RequestDispatcher rd;
        response.setContentType("text/html");
        rd = request.getRequestDispatcher(response.encodeRedirectURL("./PageOpera.jsp?id=" + id));
        rd.forward(request, response);
      }  
    } catch (SQLException e) {
      e.printStackTrace();
      RequestDispatcher rd;
      response.setContentType("text/html");
      rd = request.getRequestDispatcher(response.encodeRedirectURL("./PageOpera.jsp?id=" + 1));
      rd.forward(request, response);
    }
  }
  /**
   * doPost calcolo recensione opera .
   */
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    OperaBean opera = null;
    ArrayList<RecensioneBean> recensioniOpere = null;
    int id = Integer.valueOf(request.getParameter("id"));
    try {
      opera = operaModel.doRetrieveByKey(id);
      recensioniOpere = (ArrayList<RecensioneBean>) recensioneModel.doRetrieveById(id);
    
      if (request.getParameter("json") != null && request.getParameter("json").equals("true")) {
        Gson gson = new Gson();
        opera.setImmagine("");
        opera.setCopertina("");
        String json = gson.toJson(opera);
        response.setContentType("application/json");
        response.getWriter().write(json);
      } else {
        request.setAttribute("opera", opera);
        int totale = 0;
        int i = 0;
        for (RecensioneBean rb: recensioniOpere) {
          totale += rb.getValutazione();
          i++;
        }
        Integer media;
        if (i != 0) {
          media = totale / i;
        } else {
          media = 0;
        }
        request.setAttribute("media", media);
        RequestDispatcher rd;
        response.setContentType("text/html");
        rd = request.getRequestDispatcher(response.encodeRedirectURL("./PageOpera.jsp?id=" + id));
        rd.forward(request, response);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }




}
