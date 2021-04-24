package control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import control.opera.AggiungiRecensione;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


class TC_4_1 extends Mockito {
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
  public void TC4_1_01() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("operaId", "1");
    request.addParameter("stelle", "0");
    String message = "Numero stelle non rispettato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC4_1_02() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("operaId", "1");
    request.addParameter("stelle", "6");
    String message = "Numero stelle non rispettato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  /*
  @Disabled
  @Test
  public void TC4_1_03() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("operaId", "1");
    request.addParameter("stelle", "1");
    String message = "";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }*/
 
}
