package control;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import control.evento.InserisciEventoServlet;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;

import control.evento.ModificaEventoPageServlet;
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


class ModificaEventoPageServletTC {
    private ModificaEventoPageServlet servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private static EventoModelDM model = new EventoModelDM();


    @BeforeEach
    void setUp() throws Exception {
        servlet = new ModificaEventoPageServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void TC5_3_1() throws ServletException, IOException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("idEvent", "");
        String message = "Il campo EventId è vuoto";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
        assertEquals(message, exception.getMessage());
    }
    @Test
    public void TC5_3_2() throws ServletException, IOException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        String message = "Il campo EventId è vuoto";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void TC5_3_4() throws ServletException, IOException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("idEvent", "50");
        servlet.doPost(request, response);
        assertEquals("text/html", response.getContentType());

    }





    @AfterEach
    void tearDown() throws Exception {
        servlet = null;
        request = null;
        response = null;
    }

}
