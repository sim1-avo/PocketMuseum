package control;


import static org.junit.jupiter.api.Assertions.assertEquals;

import control.opera.GestioneOperaServlet;
import control.opera.RimuoviOpera;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import model.opera.OperaBean;
import model.opera.OperaModelDM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPart;




class GestioneOperaServletTC {

  private GestioneOperaServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private RimuoviOpera servletRimuovi;
  private static OperaModelDM model = new OperaModelDM();
  
  @BeforeEach
  void setUp() throws Exception {
    servlet = new GestioneOperaServlet();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }
  
  @Test
  public void GestioneOperaTestAggiunta() 
      throws ServletException, IOException, SQLException {
    request.addParameter("id", "0");
    request.addParameter("nome", "Opera di prova");
    request.addParameter("autore", "Autore di prova");
    request.addParameter("stato", "visibile");
    request.addParameter("descrizione", "Descrizione di prova");
    byte[] b = new byte[20];
    MockPart part = new MockPart("immagine", "Immagine.jpg", b);
    request.addPart(part);
    byte[] b2 = new byte[20];
    MockPart part2 = new MockPart("copertina", "Copertina.jpg", b2);
    request.addPart(part2);
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
    ArrayList<OperaBean> list = (ArrayList<OperaBean>) model.doRetrieveName("Opera di prova");
    OperaBean bean = list.get(0);
    MockHttpServletRequest newRequest = new MockHttpServletRequest();
    MockHttpServletResponse newResponse = new MockHttpServletResponse();
    newRequest.addParameter("id", "" + bean.getId());
    servletRimuovi = new RimuoviOpera();
    servletRimuovi.doPost(newRequest, newResponse);
  }

  @Test
  public void GestioneOperaTestImmagineCopertinaPng() 
      throws ServletException, IOException, SQLException {
    request.addParameter("id", "0");
    request.addParameter("nome", "Opera di prova");
    request.addParameter("autore", "Autore di prova");
    request.addParameter("stato", "visibile");
    request.addParameter("descrizione", "Descrizione di prova");
    byte[] b = new byte[20];
    MockPart part = new MockPart("immagine", "immagine.png", b);
    request.addPart(part);
    byte[] b2 = new byte[20];
    MockPart part2 = new MockPart("copertina", "copertina.png", b2);
    request.addPart(part2);
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
    ArrayList<OperaBean> list = (ArrayList<OperaBean>) model.doRetrieveName("Opera di prova");
    OperaBean bean = list.get(0);
    MockHttpServletRequest newRequest = new MockHttpServletRequest();
    MockHttpServletResponse newResponse = new MockHttpServletResponse();
    newRequest.addParameter("id", "" + bean.getId());
    servletRimuovi = new RimuoviOpera();
    servletRimuovi.doPost(newRequest, newResponse);
  }

  @Test
  public void GestioneOperaTestImmagineCopertinaJpeg() 
      throws ServletException, IOException, SQLException {
    request.addParameter("id", "0");
    request.addParameter("nome", "Opera di prova");
    request.addParameter("autore", "Autore di prova");
    request.addParameter("stato", "visibile");
    request.addParameter("descrizione", "Descrizione di prova");
    byte[] b = new byte[20];
    MockPart part = new MockPart("immagine", "immagine.jpeg", b);
    request.addPart(part);
    byte[] b2 = new byte[20];
    MockPart part2 = new MockPart("copertina", "copertina.jpeg", b2);
    request.addPart(part2);
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
    ArrayList<OperaBean> list = (ArrayList<OperaBean>) model.doRetrieveName("Opera di prova");
    OperaBean bean = list.get(0);
    MockHttpServletRequest newRequest = new MockHttpServletRequest();
    MockHttpServletResponse newResponse = new MockHttpServletResponse();
    newRequest.addParameter("id", "" + bean.getId());
    servletRimuovi = new RimuoviOpera();
    servletRimuovi.doPost(newRequest, newResponse);
  }

  @Test
  public void GestioneOperaTestImmagineCopertinaSenzaNome() 
      throws ServletException, IOException, SQLException {
    request.addParameter("id", "0");
    request.addParameter("nome", "Opera di prova");
    request.addParameter("autore", "Autore di prova");
    request.addParameter("stato", "visibile");
    request.addParameter("descrizione", "Descrizione di prova");
    byte[] b = new byte[20];
    MockPart part = new MockPart("immagine", "", b);
    request.addPart(part);
    byte[] b2 = new byte[20];
    MockPart part2 = new MockPart("copertina", "", b2);
    request.addPart(part2);
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
    ArrayList<OperaBean> list = (ArrayList<OperaBean>) model.doRetrieveName("Opera di prova");
    OperaBean bean = list.get(0);
    MockHttpServletRequest newRequest = new MockHttpServletRequest();
    MockHttpServletResponse newResponse = new MockHttpServletResponse();
    newRequest.addParameter("id", "" + bean.getId());
    servletRimuovi = new RimuoviOpera();
    servletRimuovi.doPost(newRequest, newResponse);
  }

  @Test
  public void GestioneOperaTestAggiuntaSenzaFoto() 
      throws ServletException, IOException, SQLException {
    request.addParameter("id", "0");
    request.addParameter("nome", "Opera di prova");
    request.addParameter("autore", "Autore di prova");
    request.addParameter("stato", "visibile");
    request.addParameter("descrizione", "Descrizione di prova");
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
    ArrayList<OperaBean> list = (ArrayList<OperaBean>) model.doRetrieveName("Opera di prova");
    OperaBean bean = list.get(0);
    model.doDelete(bean);
  }

  @Test
  public void GestioneOperaTestAggiuntaSenzaCopertina() 
      throws ServletException, IOException, SQLException {
    request.addParameter("id", "0");
    request.addParameter("nome", "Opera di prova");
    request.addParameter("autore", "Autore di prova");
    request.addParameter("stato", "visibile");
    request.addParameter("descrizione", "Descrizione di prova");
    byte[] b = new byte[20];
    MockPart part = new MockPart("immagine", "Immagine.jpg", b);
    request.addPart(part);
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
    ArrayList<OperaBean> list = (ArrayList<OperaBean>) model.doRetrieveName("Opera di prova");
    OperaBean bean = list.get(0);
    model.doDelete(bean);
  }

  @Test
  public void GestioneOperaTestAggiuntaSenzaImmagine()  
      throws ServletException, IOException, SQLException {
    request.addParameter("id", "0");
    request.addParameter("nome", "Opera di prova");
    request.addParameter("autore", "Autore di prova");
    request.addParameter("stato", "visibile");
    request.addParameter("descrizione", "Descrizione di prova");
    byte[] b = new byte[20];
    MockPart part = new MockPart("copertina", "Copertina.jpg", b);
    request.addPart(part);
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
    ArrayList<OperaBean> list = (ArrayList<OperaBean>) model.doRetrieveName("Opera di prova");
    OperaBean bean = list.get(0);
    model.doDelete(bean);
  }

  @Test
  public void GestioneOperaTestModifica() 
      throws ServletException, IOException, SQLException {
    OperaModelDM model = new OperaModelDM();
    ArrayList<OperaBean> opere = (ArrayList<OperaBean>) model
        .doRetrieveName("Il pagamento del tributo");
    String id = "" + opere.get(0).getId();
    request.addParameter("id", id);
    request.addParameter("nome", "Il pagamento del tributo");
    request.addParameter("autore", "Masaccio");
    request.addParameter("stato", "visibile");
    request.addParameter("descrizione", "Il Pagamento del tributo è"
        + " un affresco di Masaccio facente parte della decorazione della Cappella Brancacci "
        + "nella chiesa di Santa Maria del Carmine a Firenze. Si tratta di uno dei dipinti più "
        + "belli di tutti i tempi. L\'opera, databile al 1425 circa (255x598 cm), ritrae una scena "
        + "delle storie di San Pietro in cui Gesu\' lo invita a pagare il tributo chiesto da un"
        + " gabelliere per entrare nella citta\' di Cafarnao. Si tratta della scena universalmente"
        + " riconosciuta come una delle piu\' alte espressioni dell\'arte masaccesca e del primo"
        + " Rinascimento in generale.");
    byte[] b = new byte[20];
    MockPart part = new MockPart("immagine", "Immagine.jpg", b);
    request.addPart(part);
    byte[] b2 = new byte[20];
    MockPart part2 = new MockPart("copertina", "Copertina.jpg", b2);
    request.addPart(part2);
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }
  
  @Test
  public void GestioneOperaTestModificaIdErrato()
      throws ServletException, IOException, SQLException {
    request.addParameter("id", "15000");
    request.addParameter("nome", "Il pagamento del tributo");
    request.addParameter("autore", "Masaccio");
    request.addParameter("stato", "visibile");
    request.addParameter("descrizione", "Il Pagamento del tributo è "
        + "un affresco di Masaccio facente parte della decorazione"
        + " della Cappella Brancacci nella chiesa di Santa Maria del "
        + "Carmine a Firenze. Si tratta di uno dei dipinti più belli di"
        + " tutti i tempi. L\'opera, databile al 1425 circa (255x598 cm),"
        + " ritrae una scena delle storie di San Pietro in cui Gesu\' lo invita a "
        + "pagare il tributo chiesto da un gabelliere per entrare nella citta\' di Cafarnao."
        + " Si tratta della scena universalmente riconosciuta come una delle piu\' alte "
        + "espressioni dell\'arte masaccesca e del primo Rinascimento in generale.");
    byte[] b = new byte[20];
    MockPart part = new MockPart("immagine", "Immagine.jpg", b);
    request.addPart(part);
    byte[] b2 = new byte[20];
    MockPart part2 = new MockPart("copertina", "Copertina.jpg", b2);
    request.addPart(part2);
    servlet.doPost(request, response);
  }
  
  @Test
  public void GestioneOperaTestModificaNomeImmagineCopertinaVuoto() 
      throws ServletException, IOException, SQLException {
    request.addParameter("id", "1");
    request.addParameter("nome", "Il pagamento del tributo");
    request.addParameter("autore", "Masaccio");
    request.addParameter("stato", "visibile");
    request.addParameter("descrizione", "Il Pagamento del tributo è un affresco "
        + "di Masaccio facente parte della decorazione della Cappella Brancacci "
        + "nella chiesa di Santa Maria del Carmine a Firenze. Si tratta di uno "
        + "dei dipinti più belli di tutti i tempi. L\'opera, databile al 1425"
        + " circa (255x598 cm), ritrae una scena delle storie di San Pietro in cui "
        + "Gesu\' lo invita a pagare il tributo chiesto da un gabelliere per entrare"
        + " nella citta\' di Cafarnao. Si tratta della scena universalmente riconosciuta"
        + " come una delle piu\' alte espressioni dell\'arte masaccesca e del "
        + "primo Rinascimento in generale.");
    byte[] b = new byte[20];
    MockPart part = new MockPart("immagine", "", b);
    request.addPart(part);
    byte[] b2 = new byte[20];
    MockPart part2 = new MockPart("copertina", "", b2);
    request.addPart(part2);
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }
  
  @Test
  public void GestioneOperaTestModificaSenzaCopertina() 
      throws ServletException, IOException, SQLException {
    OperaModelDM model = new OperaModelDM();
    ArrayList<OperaBean> opere = (ArrayList<OperaBean>) model
        .doRetrieveName("Il pagamento del tributo");
    String id = "" + opere.get(0).getId();
    request.addParameter("id", id);
    request.addParameter("nome", "Il pagamento del tributo");
    request.addParameter("autore", "Masaccio");
    request.addParameter("stato", "visibile");
    request.addParameter("descrizione", "Il Pagamento del tributo è un affresco "
        + "di Masaccio facente parte della decorazione della Cappella Brancacci nella"
        + " chiesa di Santa Maria del Carmine a Firenze. Si tratta di uno dei dipinti"
        + " più belli di tutti i tempi. L\'opera, databile al 1425 circa (255x598 cm),"
        + " ritrae una scena delle storie di San Pietro in cui Gesu\' lo invita a pagare"
        + " il tributo chiesto da un gabelliere per entrare nella citta\' di Cafarnao. "
        + "Si tratta della scena universalmente riconosciuta come una delle piu\' alte"
        + " espressioni dell\'arte masaccesca e del primo Rinascimento in generale.");
    byte[] b = new byte[20];
    MockPart part = new MockPart("immagine", "Immagine.jpg", b);
    request.addPart(part);
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }
  
  @Test
  public void GestioneOperaTestModificaSenzaImmagine() 
      throws ServletException, IOException, SQLException {
    OperaModelDM model = new OperaModelDM();
    ArrayList<OperaBean> opere = (ArrayList<OperaBean>) model
        .doRetrieveName("Il pagamento del tributo");
    String id = "" + opere.get(0).getId();
    request.addParameter("id", id);
    request.addParameter("nome", "Il pagamento del tributo");
    request.addParameter("autore", "Masaccio");
    request.addParameter("stato", "visibile");
    request.addParameter("descrizione", "Il Pagamento del tributo è un affresco di"
        + " Masaccio facente parte della decorazione della Cappella Brancacci"
        + "nella chiesa di Santa Maria del Carmine a Firenze. Si tratta di uno "
        + "dei dipinti più belli di tutti i tempi. L\'opera, databile al 1425 circa"
        + " (255x598 cm), ritrae una scena delle storie di San Pietro in cui Gesu\' "
        + "lo invita a pagare il tributo chiesto da un gabelliere per entrare nella "
        + "citta\' di Cafarnao. Si tratta della scena universalmente riconosciuta come "
        + "una delle piu\' alte espressioni dell\'arte masaccesca e del "
        + "primo Rinascimento in generale.");
    byte[] b = new byte[20];
    MockPart part = new MockPart("immagine", "Immagine.jpg", b);
    request.addPart(part);
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }
  
  @Test
  public void GestioneOperaTestModificaSenzaFoto() 
      throws ServletException, IOException, SQLException {
    OperaModelDM model = new OperaModelDM();
    ArrayList<OperaBean> opere = (ArrayList<OperaBean>) model
        .doRetrieveName("Il pagamento del tributo");
    String id = "" + opere.get(0).getId();
    request.addParameter("id", id);
    request.addParameter("nome", "Il pagamento del tributo");
    request.addParameter("autore", "Masaccio");
    request.addParameter("stato", "visibile");
    request.addParameter("descrizione", "Il Pagamento del tributo è un affresco di Masaccio "
        + "facente parte della decorazione della Cappella Brancacci nella chiesa di Santa Maria"
        + " del Carmine a Firenze. Si tratta di uno dei dipinti più belli di tutti i tempi. L\'"
        + "opera, databile al 1425 circa (255x598 cm), ritrae una scena delle storie di San Pietro "
        + "in cui Gesu\' lo invita a pagare il tributo chiesto da un gabelliere per entrare nella "
        + "citta\' di Cafarnao. Si tratta della scena universalmente riconosciuta come una delle "
        + "piu\' alte espressioni dell\'arte masaccesca e del primo Rinascimento in generale.");
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
