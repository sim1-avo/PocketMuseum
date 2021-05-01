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

        //Controllo se qualche utente non 'guida' provi ad eliminare un evento
        if(request.getSession().getAttribute("guida") == null ) {
            response.sendRedirect("Log.jsp");
            return;
        }

        if ( ! EliminaEventoServlet.checkParameters(request)) {
            EliminaEventoServlet.getAlert("error", request);
            EliminaEventoServlet.forwardRequest(request, response, "./GuidaPageServlet");
        }

        int idEvent = Integer.parseInt(request.getParameter("idEvent"));

        try {
            EventoBean evento = new EventoBean(idEvent);
            EventoModelDM modelEvent = new EventoModelDM();
            boolean resultDelete = modelEvent.doDelete(evento);

            /* Setto gli alert che l'eliminazione è andata a buon fine se
             *  se resultDelete = 1
             *  Se resultDelete = 0, setto gli alert error
             */
            if(resultDelete)
                EliminaEventoServlet.getAlert("success", request);
            else
                EliminaEventoServlet.getAlert("error", request);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        EliminaEventoServlet.forwardRequest(request, response, "./GuidaPageServlet");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /** Check if the parameters are setted
     * @param req
     * */
    private static boolean checkParameters(HttpServletRequest req) {

        //add here new check on parameters
        return (req.getParameter("idEvent") != null) && ( !(req.getParameter("idEvent").equals("")) );
    }

    /** Forward request
     * @param req
     * @param res
     * @param destination the destination where you want to send the datas. */
    private static void forwardRequest(HttpServletRequest req, HttpServletResponse res, String destination) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(destination);
        rd.forward(req, res);
    }

    /** Set the type and the message of alert on the attributes of the re
     *  request.
     *  @param req the request of the servlet
     *  @param type the type of alert: for now is accepted only 'success' and 'error'. */
    private static void getAlert(String type, HttpServletRequest req) {
        if(type.equals("success")) {
            String messageAlert = "<strong>Fatto!</strong> L'evento è stato eliminato con successo.";
            req.setAttribute("alertSuccess", true);
            req.setAttribute("messageAlert", messageAlert);
        } else if (type.equals("error")) {
            String messageAlert = "<strong>Errore.</strong> Si è verificato un problema durante l'eliminazione dell'evento.";
            req.setAttribute("alertError", true);
            req.setAttribute("messageAlert", messageAlert);
        }
    }

}
