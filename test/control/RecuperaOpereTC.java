package control;


import static org.junit.jupiter.api.Assertions.assertEquals;

import control.opera.RecuperaOpere;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


class RecuperaOpereTC {
  private RecuperaOpere servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @BeforeEach
  void setUp() throws Exception {
    servlet = new RecuperaOpere();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void RecuperaOpereTest() throws ServletException, IOException {
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @Test
  public void RecuperaOpereTestNoDbConnection() throws ServletException, IOException {
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
