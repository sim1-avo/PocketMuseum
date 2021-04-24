package control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import control.utente.RegistrazioneServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


public class TC_1_1 extends Mockito {
  private RegistrazioneServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  /**
   * Test relativo alla registrazione dell'utente.
   */
  
  @BeforeEach
    public void setUp() {
    servlet = new RegistrazioneServlet();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void TC1_1_01() throws ServletException, IOException {
    request.addParameter("Cognome", "");
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("CF", "RSSMRA80A01F205X");
    String message = "La registrazione non va a buon fine poichè il campo cognome è vuoto";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_02() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("Cognome", "RossiRossiRossiRossi");
    String message = "La registrazione non va a buon fine poichè il campo "
        + "cognome non rispetta la lunghezza";
    IllegalArgumentException exception = 
        assertThrows(IllegalArgumentException.class, () -> {      
          servlet.doGet(request, response);
        });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_03() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("Cognome", "_Rossi.");
    String message = "La registrazione non va a buon fine poichè "
        + "il campo cognome non rispetta il formato";
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> {      
          servlet.doGet(request, response);
        });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_04() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Nome", "");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo nome è vuoto";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_05() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "MarioMarioMarioMario");
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo nome"
        + " non rispetta la lunghezza";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_06() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "_Mario.");
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo nome "
        + "non rispetta il formato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_07() throws ServletException, IOException {
    request.addParameter("email", "");
    request.addParameter("Nome", "Mario");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo e-mail è vuoto";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_08() throws ServletException, IOException {
    request.addParameter("email", "m@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo e-mail "
        + "non rispetta la lunghezza minima";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_09() throws ServletException, IOException {
    request.addParameter("email", "marioroossirosssirossirossirossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo e-mail"
        + " non rispetta la lunghezza massima";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_10() throws ServletException, IOException {
    request.addParameter("email", "m.rossirossirossi");
    request.addParameter("Nome", "Mario");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo e-mail"
        + " non rispetta il formato.";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_11() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè l'e-mail "
        + "utilizzata è gia presente nel sistema";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_12() throws ServletException, IOException {
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("CF", "");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo C.F. è vuoto";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Test
  public void TC1_1_13() throws ServletException, IOException {
    request.addParameter("CF", "RSSMRA80A01F20");
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo C.F. "
        + "non rispetta la lunghezza minima";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Test
  public void TC1_1_14() throws ServletException, IOException {
    request.addParameter("CF", "RSSMRA80A01F205XXX");
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo C.F. "
        + "non rispetta la lunghezza massima";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_15() throws ServletException, IOException {
    request.addParameter("CF", "RSSMRA80A01F20_5");
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè "
        + "il campo C.F. non rispetta il formato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Test
  public void TC1_1_16() throws ServletException, IOException {
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "1234567");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo password "
        + "non rispetta la lunghezza minima";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_17() throws ServletException, IOException {
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345677777777775777777758425768");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo password"
        + " non rispetta la lunghezza massima";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_18() throws ServletException, IOException {
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("Password", "");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo password è vuoto";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_19() throws ServletException, IOException {
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "1234567");
    request.addParameter("Nome", "Mario");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo verifica password"
        + " non rispetta la lunghezza minima";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_20() throws ServletException, IOException {
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345677777777775777777758425768");
    request.addParameter("Nome", "Mario");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè il campo"
        + " verifica password non rispetta la lunghezza massima";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_21() throws ServletException, IOException {
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("Cognome", "Rossi");
    request.addParameter("VerificaPassword", "");
    String message = "La registrazione non va a buon fine poichè il campo "
        + "verifica password è vuoto";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void TC1_1_22() throws ServletException, IOException {
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "123456789");
    request.addParameter("Nome", "Mario");
    request.addParameter("Cognome", "Rossi");
    String message = "La registrazione non va a buon fine poichè "
        + "il campo verifica password e il campo password non corrispondono";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }

  /*
  @Disabled
  @Test
  public void TC1_1_23() throws ServletException, IOException {
    request.addParameter("CF", "RSSMRA80A01F205X");
    request.addParameter("email", "mariorossi@gmail.com");
    request.addParameter("Password", "12345678");
    request.addParameter("VerificaPassword", "12345678");
    request.addParameter("Nome", "Mario");
    request.addParameter("Cognome", "Rossi");
    String message = "";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doGet(request, response);
    });
    assertEquals(message, exception.getMessage());
  }*/
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
