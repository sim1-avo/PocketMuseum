package control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import control.biglietto.RecuperaBiglietti;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;



public class RecuperaBigliettiServletTC {
  private RecuperaBiglietti servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  /**
   * Test sulla servlet RecuperaBiglietti.
   */
  
  @BeforeEach
  public void setUp() {
    servlet = new RecuperaBiglietti();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }


  @Test
  public void RecuperaBigliettiTestPositivo() throws ServletException, IOException {
    request.addParameter("inizio", "2019-06-15");
    request.addParameter("fine", "2020-06-15");
    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }

  @Test
  public void RecuperaBigliettiTestSqlExc() throws ServletException, IOException {
    request.addParameter("inizio", "a");
    request.addParameter("fine", "a");
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
