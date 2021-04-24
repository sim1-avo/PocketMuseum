package control;

import control.utente.RecuperaPassword;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
 

class RecuperaPasswordTC {

  private RecuperaPassword servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @BeforeEach
  public void setUp() {
    servlet = new RecuperaPassword();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void recuperaPasswordTestError() throws ServletException, IOException {
    request.addParameter("email", "guida@pocketmuseum.it");
    servlet.doPost(request, response);
  }
  
  @Test
  public void recuperaPasswordTestError1() throws ServletException, IOException {
    request.addParameter("email", "ciaociao");
    servlet.doPost(request, response);
  }

  @Test
  public void recuperaPasswordTestErrorSuccess() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    servlet.doPost(request, response);
  }

  @AfterEach
  public void tearDown() {
    servlet = null;
    request = null;    
    response = null;
  }
}
