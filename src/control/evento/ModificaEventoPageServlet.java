package control.evento;

import model.evento.EventoBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ModificaEventoPage")
public class ModificaEventoPageServlet extends HttpServlet {

    public ModificaEventoPageServlet() {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Controllo se qualche utente non 'guida' provi ad eliminare un evento

        if(request.getParameter("idEvent") == null || request.getParameter("idEvent")==""){
            throw new IllegalArgumentException("Il campo EventId è vuoto");
        }

        int idEvento = Integer.parseInt((String) request.getParameter("idEvent"));
        try {
            EventoBean evento = new EventoBean(idEvento);
                request.setAttribute("evento", evento);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.setContentType("text/html");
        ModificaEventoPageServlet.forwardRequest(request, response, "./ModificaEvento.jsp");


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