package control;


import static org.junit.jupiter.api.Assertions.assertEquals;

import control.evento.InserisciEventoServlet;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import model.evento.EventoBean;
import model.evento.EventoModelDM;
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
  public void InserisciEventoServletTestConImmagine()
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
    EventoBean bean = model.doRetrieveByName("Evento di test");
    model.doDelete(bean);
  }

  @Test
  public void InserisciEventoServletTestSenzaImmagine() 
      throws ServletException, IOException, SQLException {
    request.addParameter("Nome", "Evento di test");
    request.addParameter("Descrizione", "Descrizione di prova per evento di test");
    request.addParameter("data_inizio", "2021-01-01");
    request.addParameter("data_fine", "2021-02-02");
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
    EventoBean bean = model.doRetrieveByName("Evento di test");
    model.doDelete(bean);
  }

  @AfterEach
  void tearDown() throws Exception {
    servlet = null;
    request = null;    
    response = null;
  }

}
