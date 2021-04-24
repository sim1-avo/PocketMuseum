package control;


import control.utente.RegistrazioneServlet;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import model.TemplateDM;
import model.utente.UtenteModelDM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


class RegistrazioneServletTC {
  private RegistrazioneServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
 
  @BeforeEach
  public void setUp() {
    servlet = new RegistrazioneServlet();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }
  
  @Test
  public void RegistrazioneUtenteNonRegistrato() 
      throws ServletException, IOException, SQLException {
    request.addParameter("email", "giusepperossi@gmail.com");
    request.addParameter("Password", "Giuseppe75");
    request.addParameter("VerificaPassword", "Giuseppe75");
    request.addParameter("Nome", "Giuseppe");
    request.addParameter("Cognome", "Rossi");
    request.addParameter("CF", "RSIGPP68A15B745M");
    servlet.doGet(request, response); 
    TemplateDM dm = new UtenteModelDM();
    dm.elimina((String) "giusepperossi@gmail.com");
  }
  
  @AfterEach
  public void tearDown() {
    servlet = null;
    request = null;    
    response = null;
  }
}
