package control;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import control.evento.InserisciEventoServlet;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import model.evento.EventoBean;
import model.evento.EventoModelDM;
import model.utente.UtenteBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPart;


class InserisciEventoServletTC {
  private InserisciEventoServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private static EventoModelDM model = new EventoModelDM();
  
  @BeforeEach
  void setUp() throws Exception {
    servlet = new InserisciEventoServlet();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }



  @Test
  public void TC5_1_1() throws ServletException, IOException {
    request.addParameter("Nome", "");
    request.addParameter("Descrizione", "Descrizione di prova per evento di test");
    request.addParameter("data_inizio", "2021-01-01");
    request.addParameter("data_fine", "2021-02-02");
    String message = "L'aggiunta dell'evento non va a buon fine poiche il campo Nome e vuoto";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  @Test
  public void TC5_1_2() throws ServletException, IOException {
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
  public void TC5_1_3() throws ServletException, IOException {
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
  public void TC5_1_4() throws ServletException, IOException {
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
  public void TC5_1_5() throws ServletException, IOException {
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
  public void TC5_1_6() throws ServletException, IOException {
    String descrizione="a";
    for(int i=0; i <=5000 ;i++){descrizione+="a";}
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
  public void TC5_1_7()
      throws ServletException, IOException, SQLException {
    request.addParameter("Nome", "Evento di test");
    request.addParameter("Descrizione", "Descrizione di prova per evento di test");
    request.addParameter("data_inizio", "2021-01-01");
    request.addParameter("data_fine", "2021-02-02");
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }
  @Test
  public void  TC5_1_7_1()
          throws ServletException, IOException, SQLException {
    request.addParameter("Nome", "Modifica Evento di test");
    request.addParameter("Descrizione", "Descrizione di prova per modifica evento di test");
    request.addParameter("data_inizio", "2021-01-01");
    request.addParameter("data_fine", "2021-02-02");
    byte[] b = new byte[20];
    MockPart part = new MockPart("Immagine", "Immagine.jpg", b);
    request.addPart(part);
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @Test
  public void TC5_1_8()
  throws ServletException, IOException, SQLException {
    request.addParameter("Nome", "Evento di test");
    request.addParameter("Descrizione", "Descrizione di prova per evento di test");
    request.addParameter("data_inizio", "2021-01-01");
    request.addParameter("data_fine", "2021-02-02");
    byte[] b = new byte[20];
    MockPart part = new MockPart("Immagine", "Immagine.jpg", b);
    request.addPart(part);
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @Test
  public void  TC5_1_9()
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
  public void  TC5_1_9_1()
          throws ServletException, IOException, SQLException {
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
  public void TC5_1_10() throws ServletException, IOException {
    request.addParameter("Nome", "Evento di test");
    request.addParameter("Descrizione", "Descrizione");
    request.addParameter("data_inizio", "");
    request.addParameter("data_fine", "2021-02-02");
    String message = "L'aggiunta dell'evento non va a buon fine poiche il campo data inizio e vuoto";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  @Test
  public void TC5_1_11()
          throws ServletException, IOException, SQLException {
    request.addParameter("Nome", "Evento di test");
    request.addParameter("Descrizione", "Descrizione di prova per evento di test");
    request.addParameter("data_inizio", "2021-01-01");
    request.addParameter("data_fine", "");
    String message = "L'aggiunta dell'evento non va a buon fine poiche il campo data fine e vuoto";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  @Test
  public void TC5_1_12()
          throws ServletException, IOException, SQLException {
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
