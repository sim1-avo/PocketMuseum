package control;

import control.utente.ModificaDati;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import model.utente.UtenteBean;
import model.utente.UtenteModelDM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


class ModificaDatiTC {

  private ModificaDati servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @BeforeEach
  public void setUp() {
    servlet = new ModificaDati();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void ModificaDatiUtenteTestSuccess() 
      throws ServletException, IOException, SQLException {
    UtenteBean utente = null;
    UtenteModelDM model = new UtenteModelDM();
    utente = model.doRetrieveByKey("mariorossi@gmail.com");
    request.getSession().setAttribute("utente", utente);
    request.addParameter("richiesta", "psw");
    request.addParameter("vecchio", "12345678");
    request.addParameter("nuovo", "12345678");
    servlet.doGet(request, response);
  }
  
  @Test
  public void ModificaDatiGuidaTestSuccess() 
      throws ServletException, IOException, SQLException {
    UtenteBean utente = null;
    UtenteModelDM model = new UtenteModelDM();
    utente = model.doRetrieveByKey("guida@pocketmuseum.it");
    request.getSession().setAttribute("guida", utente);
    request.addParameter("richiesta", "psw");
    request.addParameter("vecchio", "admin1234");
    request.addParameter("nuovo", "admin1234");
    servlet.doGet(request, response);
  }
  
  @Test
  public void ModificaDatiBiglietteriaTestSuccess() 
      throws ServletException, IOException, SQLException {
    UtenteBean utente = null;
    UtenteModelDM model = new UtenteModelDM();
    utente = model.doRetrieveByKey("biglietteria@pocketmuseum.it");
    request.getSession().setAttribute("biglietteria", utente);
    request.addParameter("richiesta", "psw");
    request.addParameter("vecchio", "admin1234");
    request.addParameter("nuovo", "admin1234");
    servlet.doGet(request, response);
  }
  
  @Test
  public void ModificaDatiTestErrorPw()
      throws ServletException, IOException, SQLException {
    UtenteBean utente = null;
    UtenteModelDM model = new UtenteModelDM();
    utente = model.doRetrieveByKey("mariorossi@gmail.com");
    request.getSession().setAttribute("utente", utente);
    request.addParameter("richiesta", "psw");
    request.addParameter("vecchio", "123456789");
    request.addParameter("nuovo", "12345678");
    servlet.doGet(request, response);
  }

  @Test
  public void ModificaDatiTestErroreModifica() 
      throws ServletException, IOException {
    UtenteBean utente = new UtenteBean();
    utente.setPassword("123456789");
    request.getSession().setAttribute("utente", utente);
    request.addParameter("richiesta", "psw");
    request.addParameter("vecchio", "123456789");
    request.addParameter("nuovo", "12345678");
    servlet.doGet(request, response);
  }

  @Test
  public void ModificaDatiTestRichiestaErr() 
      throws ServletException, IOException, SQLException {
    UtenteBean utente = null;
    UtenteModelDM model = new UtenteModelDM();
    utente = model.doRetrieveByKey("mariorossi@gmail.com");
    request.getSession().setAttribute("utente", utente);
    request.addParameter("richiesta", "err");
    request.addParameter("vecchio", "123456789");
    request.addParameter("nuovo", "12345678");
    servlet.doGet(request, response);
  }

  @AfterEach
  public void tearDown() {
    servlet = null;
    request = null;    
    response = null;
  }
}
