package control;





import control.biglietto.PageBigliettiServlet;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import model.utente.UtenteBean;
import model.utente.UtenteModelDM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;


public class PageBigliettiServletTC {
  private PageBigliettiServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private MockHttpSession session;
  
  @BeforeEach
  void setUp() throws Exception {
    servlet = new PageBigliettiServlet();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void PageBigliettiServletTestPositivo() 
      throws ServletException, IOException, SQLException {
    UtenteBean utente = null;
    UtenteModelDM model = new UtenteModelDM();
    utente = model.doRetrieveByKey("mariorossi@gmail.com");
    session = (MockHttpSession) request.getSession();
    session.setAttribute("utente", utente);
    servlet.doGet(request, response);
  }

  @Test
  public void PageBigliettiServletTestUtenteNonEsistente() 
      throws ServletException, IOException {
    UtenteBean utente = null;
    session = (MockHttpSession) request.getSession();
    session.setAttribute("utente", utente);
    servlet.doGet(request, response);
  }

  @AfterEach
  void tearDown() throws Exception {
    servlet = null;
    request = null;    
    response = null;
  }
}
