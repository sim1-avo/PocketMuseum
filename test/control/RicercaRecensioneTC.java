package control;

import control.opera.RicercaRecensione;
import java.io.IOException; 
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


class RicercaRecensioneTC {
  private RicercaRecensione servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
 
  @BeforeEach
  public void setUp() {
    servlet = new RicercaRecensione();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }
  
  @Test
  public void RicercaRecensione() throws ServletException, IOException {
    request.addParameter("stelle", "4");
    servlet.doPost(request, response); 
  }
  
  @Test
  public void RicercaRecensionefai() throws ServletException, IOException {
    request.addParameter("stelle", "");
    servlet.doPost(request, response); 
  }
  
  @AfterEach
  public void tearDown() {
    servlet = null;
    request = null;    
    response = null;
  }
}
