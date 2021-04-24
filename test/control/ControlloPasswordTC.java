package control;

import control.utente.ControlloPassword;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class ControlloPasswordTC {
  private ControlloPassword servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  
  @BeforeEach
  void setUp() throws Exception {
    servlet = new ControlloPassword();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void passwordCorretta() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("password", "12345678");
    servlet.doGet(request, response);
  }
  
  @Test
  public void passwordSbagliata() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("password", "12345678910");
    servlet.doGet(request, response);
  }
  
  @Test
  public void emailInesistente() throws ServletException, IOException {
    request.addParameter("email", "mariorossiiiiiiii@gmail.com");
    request.addParameter("password", "12345678910");
    servlet.doGet(request, response);
  }

  @AfterEach
  void tearDown() throws Exception {
    servlet = null;
    request = null;    
    response = null;
  }
}