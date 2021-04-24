package control;


import control.utente.LoginServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;




class LoginServletTC {

  private LoginServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  
  @BeforeEach
  public void setUp() {
    servlet = new LoginServlet();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void LoginGuida() throws ServletException, IOException {
    request.setParameter("email", "guida@pocketmuseum.it");
    request.setParameter("Password", "admin1234");
    servlet.doGet(request, response);
  }

  @Test
  public void LoginUtenteErrato() throws ServletException, IOException {
    request.setParameter("email", "gui@pocketmuseum.it");
    request.setParameter("Password", "admin");
    servlet.doGet(request, response);
  }

  @Test
  public void LoginUtente() throws ServletException, IOException {
    request.setParameter("email", "mariorossi@gmail.com");
    request.setParameter("Password", "12345678");
    servlet.doGet(request, response);
  }

  @Test
  public void LoginBiglietteria() throws ServletException, IOException {
    request.setParameter("email", "biglietteria@pocketmuseum.it");
    request.setParameter("Password", "admin1234");
    servlet.doGet(request, response);
  }

  @Test
  public void LoginUtenteNonEsistente() throws ServletException, IOException {
    request.setParameter("email", "bigliet");
    request.setParameter("Password", "admin");
    servlet.doGet(request, response);
  }

  @AfterEach
  public void tearDown() {
    servlet = null;
    request = null;    
    response = null;
  }

}
