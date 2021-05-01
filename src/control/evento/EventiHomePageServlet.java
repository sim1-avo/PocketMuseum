package control.evento;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.evento.EventoBean;
import model.evento.EventoModelDM;

/**
 * Servlet implementation class EventiHomePageServlet.
 */
@WebServlet("/EventiHomePageServlet")
public class EventiHomePageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static EventoModelDM model = new EventoModelDM();

    public EventiHomePageServlet() {
        super();
    }
    /**
     * Servlet recupero eventi per homepage.
     */

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ServletException {
        ArrayList<EventoBean> eventi = null;
        try {
            if (request.getSession().getAttribute("NoDbConnection") != null) {
                model = null;
            }
            eventi =  model.doRetrieveAll("DESC");
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("eventi", eventi);
        RequestDispatcher rd = request.getRequestDispatcher("/Homepage.jsp");
        rd.forward(request, response);
    }


}
