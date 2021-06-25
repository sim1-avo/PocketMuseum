package control;


import static org.junit.jupiter.api.Assertions.assertEquals;

import control.evento.InserisciEventoServlet;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;

import control.evento.ModificaEventoServlet;
import model.evento.EventoBean;
import model.evento.EventoModelDM;
import model.utente.UtenteBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPart;


class ModificaEventoServletTC {
    private ModificaEventoServlet servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private static EventoModelDM model = new EventoModelDM();


    @BeforeEach
    void setUp() throws Exception {
        servlet = new ModificaEventoServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void ModificaEventoServletTestConImmagine()
            throws ServletException, IOException, SQLException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("Nome", "Modifica Evento di test");
        request.addParameter("Descrizione", "Descrizione di prova per modifica evento di test");
        request.addParameter("data_inizio", "2021-01-01");
        request.addParameter("data_fine", "2021-02-02");
        request.addParameter("idEvent", "50");
        byte[] b = new byte[20];
        MockPart part = new MockPart("Immagine", "Immagine.jpg", b);
        request.addPart(part);
        servlet.doPost(request, response);
        assertEquals("text/html", response.getContentType());
        EventoBean evento = model.doRetrieveByName("Modifica Evento di test");
        model.doUpdate(evento);
        model.doDelete(evento);
    }

    @Test
    public void ModificaEventoServletTestSenzaImmagine()
            throws ServletException, IOException, SQLException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("Nome", "Modifica Evento di test");
        request.addParameter("Descrizione", "Descrizione di prova per modifica evento di test");
        request.addParameter("data_inizio", "2021-01-01");
        request.addParameter("data_fine", "2021-02-02");
        request.addParameter("idEvent", "50");
        byte[] b = new byte[20];
        MockPart part = new MockPart("Immagine", "", b);
        request.addPart(part);
        servlet.doPost(request, response);
        assertEquals("text/html", response.getContentType());
        EventoBean evento = model.doRetrieveByName("Modifica Evento di test");
        model.doUpdate(evento);
        model.doDelete(evento);
    }

    @AfterEach
    void tearDown() throws Exception {
        servlet = null;
        request = null;
        response = null;
    }

}
