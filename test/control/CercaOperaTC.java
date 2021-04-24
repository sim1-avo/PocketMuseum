package control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import control.opera.CercaOpera;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


class CercaOperaTC {
  private CercaOpera servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  
  @BeforeEach
  void setUp() throws Exception {
    servlet = new CercaOpera();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void CercaOperaTest() throws ServletException, IOException {
    request.addParameter("search", "");
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @Test
  public void CercaOperaTestNoDbConnection() throws ServletException, IOException {
    request.getSession().setAttribute("NoDbConnection", "NoDbConnection");
    servlet.doPost(request, response);
  }
  
  @AfterEach
  void tearDown() throws Exception {
    servlet = null;
    request = null;    
    response = null;
  }

}
