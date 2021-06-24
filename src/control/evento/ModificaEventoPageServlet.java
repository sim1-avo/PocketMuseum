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
        if(request.getSession().getAttribute("guida") == null ) {
            response.sendRedirect("Login.jsp");
            return;
        }

        if ( ! ModificaEventoPageServlet.checkParameters(request)) {
            ModificaEventoPageServlet.forwardRequest(request, response, "./GuidaPageServlet");
        }

        int idEvento = Integer.parseInt((String) request.getParameter("idEvent"));
        try {
            EventoBean evento = new EventoBean(idEvento);
            if (evento == null) {
                ModificaEventoPageServlet.forwardRequest(request, response, "./GuidaPageServlet");
            } else {
                request.setAttribute("evento", evento);
                ModificaEventoPageServlet.forwardRequest(request, response, "./ModificaEvento.jsp");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.setContentType("text/html");


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /** Check if the parameters are setted
     * @param req
     * */
    private static boolean checkParameters(HttpServletRequest req) {

        //add here new check on parameters
        return (req.getParameter("idEvent") != null) && (!((String)req.getParameter("idEvent")).equals(""));
    }

    /** Forward request
     * @param req
     * @param res
     * @param destination the destination where you want to send the datas. */
    private static void forwardRequest(HttpServletRequest req, HttpServletResponse res, String destination) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(destination);
        rd.forward(req, res);
    }

    /** Set the type and the message of alert on the attributes of the
     *  request.
     *  @param req the request of the servlet
     *  @param type the type of alert: is accepted only 'error'. */

}