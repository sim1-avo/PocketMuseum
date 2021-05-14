package control.evento;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.evento.EventoBean;
import model.evento.EventoModelDM;
import model.utente.UtenteBean;

/**
 * Servlet implementation class MostraEventiServlet.
 */
@WebServlet("/MostraEventiServlet")
public class MostraEventiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static EventoModelDM model = new EventoModelDM();

    public MostraEventiServlet() {
        super();
    }
    /**
     * Servlet mostra eventi .
     */


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ServletException {
        ArrayList<EventoBean> eventi = null;
        UtenteBean guida = (UtenteBean) request.getSession().getAttribute("guida");
        String secure = null;
        try {
            if (guida != null) {
                secure = guida.getPassword().substring(0);
            }
            eventi =  model.doRetrieveAll("DESC");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (guida != null && secure != null) {
            request.getSession(true).setAttribute("eventi", eventi);
            RequestDispatcher rd = request.getRequestDispatcher("/GuidaPP.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("eventi", eventi);
            RequestDispatcher rd = request.getRequestDispatcher("/UtentePP.jsp");
            rd.forward(request, response);
        }
    }



}
