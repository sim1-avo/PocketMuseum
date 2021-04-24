package control;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import control.utente.LoginServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


class TC_1_2 extends Mockito {
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
 public void TC_1_2_1() throws ServletException, IOException {
    request.addParameter("email", "");
    request.addParameter("Password", "12345678");
    String message = "L'accesso non va a buon fine poichè il campo e-mail è vuoto";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
 
  @Test
 public void TC_1_2_2() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "");
    String message = "L'accesso non va a buon fine poichè il campo password è vuoto";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
 
  @Test
 public void TC_1_2_3() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "1234567");
    String message = "L'accesso non va a buon fine poichè il campo password è errato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  /*
  @Disabled
  @Test
 public void TC_1_2_4() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    String message = "";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }*/
 
  @AfterEach
 public void tearDown() {
    servlet = null;
    request = null;    
    response = null;
  }
}
