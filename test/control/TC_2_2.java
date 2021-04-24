package control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import control.biglietto.AcquistaBigliettoServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;



class TC_2_2 extends Mockito {
  private AcquistaBigliettoServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @BeforeEach
  public void setUp() {
    servlet = new AcquistaBigliettoServlet();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void TC2_2_01() throws ServletException, IOException {
    request.addParameter("mode", "biglietteria");
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00");
    request.addParameter("nometitolare", "Mari");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("scadenza", "05/22");
    request.addParameter("cvv", "123");
    String message = "Lunghezza nome titolare non rispettata";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC2_2_02() throws ServletException, IOException {
    request.addParameter("mode", "biglietteria");
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00");
    request.addParameter("nometitolare", "Mario Rossi Rossi Rossi Rossi Rossi Rossi Rossi");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("scadenza", "05/22");
    request.addParameter("cvv", "123");
    String message = "Lunghezza nome titolare non rispettata";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Test
  public void TC2_2_03() throws ServletException, IOException {
    request.addParameter("mode", "biglietteria");
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00");
    request.addParameter("nometitolare", "Mario Rossi6789");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("scadenza", "05/22");
    request.addParameter("cvv", "123");
    String message = "Formato nome titolare non rispettato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC2_2_04() throws ServletException, IOException {
    request.addParameter("mode", "biglietteria");
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00");
    request.addParameter("nometitolare", "Mario Rossi");
    request.addParameter("numerocarta", "12345678");
    request.addParameter("scadenza", "05/22");
    request.addParameter("cvv", "123");
    String message = "Lunghezza numero carta non rispettata";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC2_2_05() throws ServletException, IOException {
    request.addParameter("mode", "biglietteria");
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00");
    request.addParameter("nometitolare", "Mario Rossi");
    request.addParameter("numerocarta", "1234567891234567");
    request.addParameter("scadenza", "05/22");
    request.addParameter("cvv", "123");
    String message = "Lunghezza numero carta non rispettata";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC2_2_06() throws ServletException, IOException {
    request.addParameter("mode", "biglietteria");
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00");
    request.addParameter("nometitolare", "Mario Rossi");
    request.addParameter("numerocarta", "12345678fdrt");
    request.addParameter("scadenza", "05/22");
    request.addParameter("cvv", "123");
    String message = "Formato numero carta non rispettato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC2_2_07() throws ServletException, IOException {
    request.addParameter("mode", "biglietteria");
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00");
    request.addParameter("nometitolare", "Mario Rossi");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("scadenza", "05/2");
    request.addParameter("cvv", "123");
    String message = "Lunghezza scadenza non rispettata";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC2_2_08() throws ServletException, IOException {
    request.addParameter("mode", "biglietteria");
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00");
    request.addParameter("nometitolare", "Mario Rossi");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("scadenza", "05/225");
    request.addParameter("cvv", "123");
    String message = "Lunghezza scadenza non rispettata";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC2_2_09() throws ServletException, IOException {
    request.addParameter("mode", "biglietteria");
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00");
    request.addParameter("nometitolare", "Mario Rossi");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("scadenza", "05-22");
    request.addParameter("cvv", "123");
    String message = "Formato scadenza non rispettato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC2_2_10() throws ServletException, IOException {
    request.addParameter("mode", "biglietteria");
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00");
    request.addParameter("nometitolare", "Mario Rossi");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("scadenza", "05/22");
    request.addParameter("cvv", "12");
    String message = "Lunghezza cvv non rispettata";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC2_2_11() throws ServletException, IOException {
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00");
    request.addParameter("nometitolare", "Mario Rossi");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("scadenza", "05/22");
    request.addParameter("cvv", "1234");
    String message = "Lunghezza cvv non rispettata";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC2_2_12() throws ServletException, IOException {
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00");
    request.addParameter("nometitolare", "Mario Rossi");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("scadenza", "05/22");
    request.addParameter("cvv", "12u");
    String message = "Formato cvv non rispettato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  /*
  @Disabled
  @Test
  public void TC2_2_13() throws ServletException, IOException {
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00");
    request.addParameter("nometitolare", "Mario Rossi");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("scadenza", "05/22");
    request.addParameter("cvv", "123");
    String message = "";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }*/


}

