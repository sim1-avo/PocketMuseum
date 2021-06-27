package control.evento;

import model.evento.EventoBean;
import model.evento.EventoModelDM;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EliminaEvento")
public class EliminaEventoServlet extends HttpServlet {

    public EliminaEventoServlet() {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        if(request.getParameter("idEvent") == null || request.getParameter("idEvent").equals("") ) {
            throw new IllegalArgumentException("Il campo EventId è vuoto.");
        }
        int idEvent = Integer.parseInt(request.getParameter("idEvent"));


        try {
            EventoBean evento = new EventoBean(idEvent);
            EventoModelDM modelEvent = new EventoModelDM();
            modelEvent.doDelete(evento);

            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.setContentType("text/html");
        EliminaEventoServlet.forwardRequest(request, response, "./Eventi.jsp");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /** Forward request
     * @param req
     * @param res
     * @param destination the destination where you want to send the datas. */
    private static void forwardRequest(HttpServletRequest req, HttpServletResponse res, String destination) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(destination);
        rd.forward(req, res);
    }

}
