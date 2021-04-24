package control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import control.biglietto.ControlloCodice;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class TC_3_3 extends Mockito {
  private ControlloCodice servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  
  /**
   * Metodo di settaggio.
   */
  @BeforeEach
  public void setUp() {
    servlet = new ControlloCodice();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }
   
  @Test
  public void TC3_1_01() throws ServletException, IOException {
    request.addParameter("codice", "ABCD1234ABC");
    String message = "Lunghezza codice non valida";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
 
  @Test
  public void TC3_1_02() throws ServletException, IOException {
    request.addParameter("codice", "ABCD1234EFGH5678");
    String message = "Lunghezza codice non valida";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Test
  public void TC3_1_03() throws ServletException, IOException {
    request.addParameter("codice", "1234ABCD5678");
    String message = "Formato codice non valido";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  /**
   * AfterEach.
   */
  
  @AfterEach
  public void tearDown() {
    servlet = null;
    request = null;    
    response = null;
  }
}
