package control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import control.biglietto.ControlloDataServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;



public class ControlloDataServletTC {
  private ControlloDataServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  /**
   * Test su  controlloDataServlet.
   */
  
  @BeforeEach
  public void setUp() {
    servlet = new ControlloDataServlet();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void ControlloDataServletTest() throws ServletException, IOException {
    request.addParameter("day", "15");
    request.addParameter("month", "06");
    request.addParameter("year", "2020");
    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }

  @Test
  public void ControlloDataServletTestMeseLunghezza1() throws ServletException, IOException {
    request.addParameter("day", "15");
    request.addParameter("month", "6");
    request.addParameter("year", "2020");
    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }

  @Test
  public void ControlloCodiceTestGiornoLunghezza1() throws ServletException, IOException {
    request.addParameter("day", "5");
    request.addParameter("month", "06");
    request.addParameter("year", "2020");
    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType()); 
  }

  @Test
  public void ControlloCodiceTestBigliettiFiniti() throws ServletException, IOException {
    request.addParameter("day", "15");
    request.addParameter("month", "06");
    request.addParameter("year", "2019");
    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }

  @Test
  public void ControlloCodiceTestException() throws ServletException, IOException {
    request.addParameter("day", "a");
    request.addParameter("month", "a");
    request.addParameter("year", "a");
    servlet.doGet(request, response);
  }
  /**
   * teardown.
   */
  
  @AfterEach
  public void tearDown() {
    servlet = null;
    request = null;    
    response = null;
  }

}
