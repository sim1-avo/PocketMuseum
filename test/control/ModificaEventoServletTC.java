package control;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void TC5_2_1() throws ServletException, IOException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("Nome", "");
        request.addParameter("Descrizione", "Descrizione di prova per modifica evento di test");
        request.addParameter("data_inizio", "2021-01-01");
        request.addParameter("data_fine", "2021-02-02");
        request.addParameter("idEvent", "50");
        byte[] b = new byte[20];
        MockPart part = new MockPart("Immagine", "Immagine.jpg", b);
        request.addPart(part);
        String message = "L'aggiunta dell'evento non va a buon fine poiche il campo Nome e vuoto";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void TC5_2_2() throws ServletException, IOException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("Nome", "evento");
        request.addParameter("Descrizione", "Descrizione di prova per evento di test");
        request.addParameter("data_inizio", "2021-01-01");
        request.addParameter("data_fine", "2021-02-02");
        String message = "L'aggiunta dell'evento non va a buon fine poiche il campo Nome non rispetta la lunghezza minima";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
        assertEquals(message, exception.getMessage());
    }
    @Test
    public void TC5_2_3() throws ServletException, IOException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("Nome", "eventooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        request.addParameter("Descrizione", "Descrizione di prova per evento di test");
        request.addParameter("data_inizio", "2021-01-01");
        request.addParameter("data_fine", "2021-02-02");
        String message = "L'aggiunta dell'evento non va a buon fine poiche il campo Nome non rispetta la lunghezza massima";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
        assertEquals(message, exception.getMessage());
    }
    @Test
    public void TC5_2_4() throws ServletException, IOException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("Nome", "Evento di test");
        request.addParameter("Descrizione", "");
        request.addParameter("data_inizio", "2021-01-01");
        request.addParameter("data_fine", "2021-02-02");
        String message = "L'aggiunta dell'evento non va a buon fine poiche il campo descrizione e vuoto";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void TC5_2_5() throws ServletException, IOException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("Nome", "Evento di test");
        request.addParameter("Descrizione", "descriz");
        request.addParameter("data_inizio", "2021-01-01");
        request.addParameter("data_fine", "2021-02-02");
        String message = "L'aggiunta dell'evento non va a buon fine poiche il campo descrizione non rispetta la lunghezza minima.";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
        assertEquals(message, exception.getMessage());
    }

  @Test
  public void TC5_2_6() throws ServletException, IOException {
    String descrizione="a";
    for(int i=0; i <=5000 ;i++){descrizione+="a";}
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
    request.addParameter("Nome", "Evento di test");
    request.addParameter("Descrizione", descrizione);
    request.addParameter("data_inizio", "2021-01-01");
    request.addParameter("data_fine", "2021-02-02");
    String message = "L'aggiunta dell'evento non va a buon fine poiche il campo descrizione non rispetta la lunghezza massima";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }


    @Test
    public void  TC5_2_7()
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
    }

    @Test
    public void TC5_2_8()
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
    }

    @Test
    public void  TC5_2_9()
            throws ServletException, IOException, SQLException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("Nome", "Modifica Evento di test");
        request.addParameter("Descrizione", "Descrizione di prova per modifica evento di test");
        request.addParameter("data_inizio", "2021-01-01");
        request.addParameter("data_fine", "2021-02-02");
        request.addParameter("idEvent", "50");
        byte[] b = new byte[20];
        MockPart part = new MockPart("Immagine", "Immagine.pdf", b);
        request.addPart(part);
        String message = "L'aggiunta dell'evento non va a buon fine poiche il campo immagine contiene un file di formato diverso da jpg o png";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
        assertEquals(message, exception.getMessage());
    }
    @Test
    public void  TC5_2_9_1()
            throws ServletException, IOException, SQLException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("Nome", "Modifica Evento di test");
        request.addParameter("Descrizione", "Descrizione di prova per modifica evento di test");
        request.addParameter("data_inizio", "2021-01-01");
        request.addParameter("data_fine", "2021-02-02");
        request.addParameter("idEvent", "50");
        byte[] b = new byte[20000000];
        MockPart part = new MockPart("Immagine", "Immagine.png", b);
        request.addPart(part);
        String message = "L'aggiunta dell'evento non va a buon fine poiche il campo immagine contiene file di dimensioni superiori a 1MB";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void TC5_2_10() throws ServletException, IOException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("Nome", "Evento di test");
        request.addParameter("Descrizione", "Descrizione");
        request.addParameter("data_inizio", "");
        request.addParameter("data_fine", "2021-02-02");
        String message = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
        assertEquals(message, exception.getMessage());
    }
    @Test
    public void TC5_2_11()
            throws ServletException, IOException, SQLException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("Nome", "Evento di test");
        request.addParameter("Descrizione", "Descrizione di prova per evento di test");
        request.addParameter("data_inizio", "2021-01-01");
        request.addParameter("data_fine", "");
        String message = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
        assertEquals(message, exception.getMessage());
    }
    @Test
    public void TC5_2_12()
            throws ServletException, IOException, SQLException {
        UtenteBean guida = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
        request.getSession().setAttribute("guida", guida);
        request.addParameter("Nome", "Evento di test");
        request.addParameter("Descrizione", "Descrizione di prova per evento di test");
        request.addParameter("data_inizio", "2021-01-01");
        request.addParameter("data_fine", "2020-01-01");
        String message = "L'aggiunta dell'evento non va a buon fine poiche il campo data fine contiene una data antecedente a data inizio";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servlet.doPost(request, response);
        });
        assertEquals(message, exception.getMessage());
    }


    @AfterEach
    void tearDown() throws Exception {
        servlet = null;
        request = null;
        response = null;
    }

}
