package control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import control.opera.CercaOperaById;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import model.opera.RecensioneBean;
import model.opera.RecensioneModelDM;
import model.utente.UtenteBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class CercaOperaByIdTC {
  private CercaOperaById servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @BeforeEach
  void setUp() throws Exception {
    servlet = new CercaOperaById();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void CercaOperaByIdTestJsonDoPost() throws ServletException, IOException {
    request.addParameter("json", "true");
    request.addParameter("id", "1");
    servlet.doPost(request, response);
    assertEquals("application/json", response.getContentType());
  }

  @Test
  public void CercaOperaByIdTestNormaleDoPost() throws ServletException, IOException {
    request.addParameter("id", "1");
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @Test
  public void CercaOperaByIdTestDoPostIdError() throws ServletException, IOException {
    request.addParameter("id", "0");   
    servlet.doPost(request, response);
  }

  @Test
  public void CercaOperaByIdTestDoPostNessunaRecensione() 
      throws ServletException, IOException, SQLException {
    RecensioneModelDM model = new RecensioneModelDM();
    model.doDelete(1, "mariorossi@gmail.com");
    request.addParameter("id", "1");
    servlet.doPost(request, response);
    model.doSave(new RecensioneBean(4, "mariorossi@gmail.com", 1));
  }

  @Test
  public void CercaOperaByIdTestJsonDoGetGuidaLoggata() throws ServletException, IOException {
    request.addParameter("json", "true");
    request.addParameter("id", "1");
    UtenteBean b = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
    request.getSession().setAttribute("guida", b); 
    servlet.doGet(request, response);
    assertEquals("application/json", response.getContentType());
  }

  @Test
  public void CercaOperaByIdTestDoPostJsonNotTrue() throws ServletException, IOException {
    request.addParameter("json", "false");
    request.addParameter("id", "1");
    UtenteBean b = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
    request.getSession().setAttribute("guida", b); 
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @Test
  public void CercaOperaByIdTestDoGetCodiceNonValido() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.getSession().setAttribute("codiceValido", "AAAA0000AAAA");
    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @Test
  public void CercaOperaByIdTestNormaleDoGet() throws ServletException, IOException {
    request.addParameter("id", "1");
    UtenteBean b = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
    request.getSession().setAttribute("guida", b); 
    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @Test
  public void CercaOperaByIdTestBigliettoValido() throws ServletException, IOException {
    request.getSession().setAttribute("codiceValido", "TTTT0000TTTT");
    request.addParameter("id", "1");
    UtenteBean b = new UtenteBean("mariorossi@gmail.com", "12345678", "", "", "reg", "");
    request.getSession().setAttribute("utente", b); 
    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @Test
  public void CercaOperaByIdTestIdNonEsistente() throws ServletException, IOException {
    request.addParameter("id", "0");
    UtenteBean b = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
    request.getSession().setAttribute("guida", b); 
    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @Test
  public void CercaOperaByIdTestJsonNotTrue() throws ServletException, IOException {
    request.addParameter("json", "false");
    request.addParameter("id", "1");
    UtenteBean b = new UtenteBean("guida@pocketmuseum.it", "admin", "", "", "guida", "");
    request.getSession().setAttribute("guida", b); 
    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @Test
  public void CercaOperaByIdTestNessunaRecensione() 
      throws ServletException, IOException, SQLException {
    request.getSession().setAttribute("codiceValido", "TTTT0000TTTT");
    RecensioneModelDM model = new RecensioneModelDM();
    model.doDelete(1, "mariorossi@gmail.com");
    request.addParameter("id", "1");
    UtenteBean b = new UtenteBean("mariorossi@gmail.com", "12345678", "", "", "reg", "");
    request.getSession().setAttribute("utente", b); 
    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
    model.doSave(new RecensioneBean(4, "mariorossi@gmail.com", 1));
  }

  @AfterEach
  void tearDown() throws Exception {
    servlet = null;
    request = null;    
    response = null;
  }
}
