package control;



import control.utente.UtenteDuplicatoControllo;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class UtenteDuplicatoTC {
  private UtenteDuplicatoControllo servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  
  @BeforeEach
  void setUp() throws Exception {
    servlet = new UtenteDuplicatoControllo();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void utenteDuplicato() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    servlet.doGet(request, response);
  }
  
  @Test
  public void utenteNuovo() throws ServletException, IOException {
    request.addParameter("email", "nuovoutentemail@gmail.com");
    servlet.doGet(request, response);
  }

  @AfterEach
  void tearDown() throws Exception {
    servlet = null;
    request = null;    
    response = null;
  }

}