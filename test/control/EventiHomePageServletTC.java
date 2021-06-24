package control;

import control.evento.EventiHomePageServlet;
import java.io.IOException; 
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;



class EventiHomePageServletTC {
  
  private EventiHomePageServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  
  @BeforeEach
  public void setUp() {
    servlet = new control.evento.EventiHomePageServlet();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void HomePageEventi() throws ServletException, IOException {
    servlet.doGet(request, response);
  }

  @Test
  public void HomePageEventiTestNoDbConnection() throws ServletException, IOException {
    request.getSession().setAttribute("NoDbConnection", "NoDbConnection");
    servlet.doGet(request, response);
  }
  
  @AfterEach
  public void tearDown() {
    servlet = null;
    request = null;    
    response = null;
  }

}
