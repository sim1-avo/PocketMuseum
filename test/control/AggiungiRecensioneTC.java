package control;


import control.opera.AggiungiRecensione;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import model.opera.RecensioneModelDM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;



class AggiungiRecensioneTC {
  private AggiungiRecensione servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
 
  @BeforeEach
  public void setUp() {
    servlet = new AggiungiRecensione();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }
  
  @Test
  public void RecensioneNuova() throws ServletException, IOException, SQLException {
    request.addParameter("email", "mariorossi@gmail.com");
    RecensioneModelDM model = new RecensioneModelDM();
    model.doDelete(2, "mariorossi@gmail.com");
    request.addParameter("operaId", "2");
    request.addParameter("stelle", "4");
    request.addParameter("commento", "molto interessante");
    servlet.doGet(request, response);
  }
  
  @Test
  public void RecensioneEsistente() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("operaId", "2");
    request.addParameter("stelle", "4");
    request.addParameter("commento", "molto interessante");
    servlet.doGet(request, response); 
  }
  
  @Test
  public void RecensioneDaGuida() throws ServletException, IOException {
    request.addParameter("email", "guida@pocketmuseum.it");
    request.addParameter("operaId", "2");
    request.addParameter("stelle", "4");
    request.addParameter("commento", "molto interessante");
    servlet.doGet(request, response); 
  }
  
  @Test
  public void RecensioneDaBiglietteria() throws ServletException, IOException {
    request.addParameter("email", "biglietteria@pocketmuseum.it");
    request.addParameter("operaId", "2");
    request.addParameter("stelle", "4");
    request.addParameter("commento", "molto interessante");
    servlet.doGet(request, response); 
  }
  
  @Test
  public void RecensioneErrata() throws ServletException, IOException {
    request.addParameter("email", "");;
    request.addParameter("operaId", "1");
    request.addParameter("stelle", "5");
    request.addParameter("commento", "molto interessante");
    servlet.doGet(request, response); 
  }
  
  @Test
  public void NonEsisteRecensioneUtenteRegistrato() throws ServletException, IOException {
    request.addParameter("email", "pasqualerossi@gmail.com");
    request.addParameter("operaId", "2");
    request.addParameter("stelle", "5");
    request.addParameter("commento", "molto interessante");
    servlet.doGet(request, response); 
  }

  @AfterEach
  public void tearDown() {
    servlet = null;
    request = null;    
    response = null;
  }
}
