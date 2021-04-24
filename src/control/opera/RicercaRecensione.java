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
import model.opera.RecensioneModelDM;

@WebServlet("/RicercaRecensione")
public class RicercaRecensione extends HttpServlet {
  private static final long serialVersionUID = 1L;
  OperaModelDM model = new OperaModelDM();
  RecensioneModelDM modelRecensione = new  RecensioneModelDM();
  /**
   * Servlet ricerca per recensione.
   */
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      int stelle = Integer.parseInt(request.getParameter("stelle"));
      ArrayList<OperaBean> listOpere = new ArrayList<OperaBean>();
      ArrayList<OperaBean> listPerRec = new ArrayList<OperaBean>();
      listOpere = (ArrayList<OperaBean>) model.doRetrieveAll("ASC");
      int media = 0;
      for (OperaBean op :listOpere) {
        media = modelRecensione.mediaRecensioni(op.getId());
        if (modelRecensione.mediaRecensioni(op.getId()) == stelle) {
          listPerRec.add(op);
        }
      }
      request.removeAttribute("opere");
      request.setAttribute("opere", listPerRec);
    } catch (Exception e) {
      e.printStackTrace();
    }
   
    RequestDispatcher rd = request.getRequestDispatcher("./ListOpere.jsp");
    rd.forward(request, response);
  }
}
