package control;

import control.evento.MostraEventiServlet;
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

class MostraEventiTC {
  private MostraEventiServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  
  @BeforeEach
  public void setUp() {
    servlet = new MostraEventiServlet();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }
  
  @Test
  public void MostraEventiServletTestGuida() 
      throws ServletException, IOException {
    UtenteBean utente = new UtenteBean();
    utente.setPassword("admin");
    utente.setTipo("guida");
    request.getSession().setAttribute("guida", utente);
    servlet.doGet(request, response);
  }



  @Test
  public void MostraEventiServletTestSuccess()
      throws ServletException, IOException {
    UtenteBean utente = new UtenteBean();
    utente.setTipo("utente");
    request.getSession().setAttribute("utente", utente);
    servlet.doGet(request, response);
  }

  @AfterEach
  public void tearDown() {
    servlet = null;
    request = null;    
    response = null;
  }
}
