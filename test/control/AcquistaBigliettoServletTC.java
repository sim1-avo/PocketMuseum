package control;



import control.biglietto.AcquistaBigliettoServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;



public class AcquistaBigliettoServletTC {
  private AcquistaBigliettoServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  /**
   * Testing acquisto biglietto.
   * istanziamento. 
   */
  
  @BeforeEach
  public void setUp() {
    servlet = new AcquistaBigliettoServlet();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void AcquistaBigliettoTestAcquistoDaUtente() throws ServletException, IOException {
    request.addParameter("data_inizio", "2030-01-01");
    request.addParameter("turno", "09:00:00");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("scadenza", "05/22");
    request.addParameter("cvv", "123");
    request.addParameter("mode", "registrato");
    request.addParameter("email", "mariorossi@gmail.com");
    servlet.doGet(request, response);
  }
  
  @Test
  public void AcquistaBigliettoTestAcquistoDaBiglietteria() throws ServletException, IOException {
    request.addParameter("data_inizio", "2030-01-01");
    request.addParameter("turno", "09:00:00");
    request.addParameter("nometitolare", "Mario Rossi");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("scadenza", "05/22");
    request.addParameter("cvv", "123");
    request.addParameter("mode", "biglietteria");
    servlet.doGet(request, response);
  }

  @Test
  public void AcquistaBigliettoTestSql1() throws ServletException, IOException {
    request.addParameter("scadenza", "05/22");
    request.addParameter("data_inizio", "2021-01-26");
    request.addParameter("turno", "09:00:00");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("cvv", "123");
    request.addParameter("mode", "registrato");
    request.addParameter("email", "mariorossi");
    request.addParameter("nometitolare", "Mario Rossi");
    servlet.doGet(request, response);
  }

  @Test
  public void AcquistaBigliettoTestSql2() throws ServletException, IOException {
    request.addParameter("scadenza", "05/22");
    request.addParameter("data_inizio", "00");
    request.addParameter("turno", "09:00:00");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("cvv", "123");
    request.addParameter("mode", "biglietteria");
    request.addParameter("email", "mariorossi");
    request.addParameter("nometitolare", "Mario Rossi");
    servlet.doGet(request, response);
  }
  
  @Test
  public void AcquistaBigliettoTestMissingMode() throws ServletException, IOException {
    request.addParameter("scadenza", "05/22");
    request.addParameter("data_inizio", "00");
    request.addParameter("turno", "09:00:00");
    request.addParameter("numerocarta", "123456789123");
    request.addParameter("cvv", "123");
    request.addParameter("mode", "");
    request.addParameter("email", "mariorossi");
    request.addParameter("nometitolare", "Mario Rossi");
    servlet.doGet(request, response);
  }
  /**
   * tearDown.
   */
  
  @AfterEach
  public void tearDown() {
    servlet = null;
    request = null;    
    response = null;
  }

}
