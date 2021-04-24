package control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import control.opera.RecuperaImmagini;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


class RecuperaImmaginiTC {

  private RecuperaImmagini servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  
  @BeforeEach
  void setUp() throws Exception {
    servlet = new RecuperaImmagini();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void RecuperaImmaginiTest() throws ServletException, IOException {
    request.addParameter("id", "1");
    servlet.doPost(request, response);
    assertEquals("text/plain", response.getContentType());
  }

  @Test
  public void RecuperaImmaginiTestOperaNonEsistente() throws ServletException, IOException {
    request.addParameter("id", "0");
    servlet.doPost(request, response);
  }

  @AfterEach
  void tearDown() throws Exception {
    servlet = null;
    request = null;    
    response = null;
  }

}
