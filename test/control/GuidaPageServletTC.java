package control;


import control.opera.GuidaPageServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;



class GuidaPageServletTC {
  private GuidaPageServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  
  @BeforeEach
  void setUp() throws Exception {
    servlet = new GuidaPageServlet();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }

  @Test
  void test() throws IOException, ServletException {
    servlet.doGet(request, response);
  }

  @Test
  public void GuidaPageServletTestNoDbConnection() 
      throws ServletException, IOException {
    request.getSession().setAttribute("NoDbConnection", "NoDbConnection"); 
    servlet.doGet(request, response);
  }
  
  @AfterEach
  void tearDown() throws Exception {
    servlet = null;
    request = null;
    response = null;
  }

}
