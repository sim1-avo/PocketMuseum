package control;


import control.utente.LogoutServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


class LogoutServletTC {

  private LogoutServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  
  @BeforeEach
  void setUp() throws Exception {
    servlet = new LogoutServlet();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void LogoutTest() throws ServletException, IOException {
    servlet.doGet(request, response);
  }
  
  @AfterEach
  void tearDown() throws Exception {
    servlet = null;
    request = null;    
    response = null;
  }

}
