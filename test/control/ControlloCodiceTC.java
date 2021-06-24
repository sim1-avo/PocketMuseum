package control;


import static org.junit.jupiter.api.Assertions.assertEquals;

import control.biglietto.ControlloCodice;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;



public class ControlloCodiceTC {
  private ControlloCodice servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  /**
   * Test sulla servlet controllocodice.
   */
  
  @BeforeEach
    public void setUp() {
    servlet = new ControlloCodice();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void ControlloCodiceTestAccesso() throws ServletException, IOException {
    request.addParameter("data", "2020-06-15 15:00:00");
    request.addParameter("codice", "AAAA0000AAAA");
    servlet.doGet(request, response);
  }

  @Test
  public void ControlloCodiceTestData() throws ServletException, IOException {
    request.addParameter("data", "2019-06-15 15:00:00");
    request.addParameter("codice", "AAAA0000AAAA");
    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }

  @Test
  public void ControlloCodiceTestCodiceErratoDataPrecedente() throws ServletException, IOException {
    request.addParameter("data", "2019-06-15 15:00:00");
    request.addParameter("codice", "AAAA0000AAZA");
    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }

  @Test
  public void ControlloCodiceTestCodiceErratoDataSuccessiva() throws ServletException, IOException {
    request.addParameter("data", "2021-06-15 15:00:00");
    request.addParameter("codice", "AAAA0000AANA");
    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }

  @Test
  public void ControlloCodiceTestCodiceEsattoDataPrecedente() throws ServletException, IOException {
    request.addParameter("data", "2019-06-15 15:00:00");
    request.addParameter("codice", "AAAA0000AAAA");
    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }

  @Test
  public void ControlloCodiceTestCodiceEsattoDataSuccessiva() throws ServletException, IOException {
    request.addParameter("data", "2025-06-20 15:00:00");
    request.addParameter("codice", "AAAA0000AAAA");
    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }


  @Test
  public void ControlloCodiceTestDataNonValida() throws ServletException, IOException {
    request.addParameter("data", "0");
    request.addParameter("codice", "AAAA0000AAAA");
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
