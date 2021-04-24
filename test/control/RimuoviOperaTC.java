package control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import control.opera.RimuoviOpera;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


class RimuoviOperaTC {

  private RimuoviOpera servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @BeforeEach
  void setUp() throws Exception {
    servlet = new RimuoviOpera();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void rimuoviOperaTest() throws ServletException, IOException {
    request.addParameter("id", "0");
    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @AfterEach
  void tearDown() throws Exception {
    servlet = null;
    request = null;    
    response = null;
  }

}
