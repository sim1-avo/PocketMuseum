package control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import control.opera.GestioneOperaServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;



class TC_3_2 extends Mockito {
  private GestioneOperaServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @BeforeEach
  public void setUp() {
    servlet = new GestioneOperaServlet();
    request = new MockHttpServletRequest();    
    response = new MockHttpServletResponse();
  }

  @Test
  public void TC3_2_01() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.addParameter("nome", "La");
    request.addParameter("autore", "Leonardo Da Vinci");
    request.addParameter("descrizione", "Dipinto olio su tavola di legno. 1503");
    request.addParameter("stato", "visibile");
    String message = "Lunghezza nome opera non rispettata";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Test
  public void TC3_2_02() throws ServletException, IOException {
    String nome = "a";
    for (int i = 0; i < 101; i++) {
      nome += "a";
    }
    request.addParameter("id", "1");
    request.addParameter("nome", nome);
    request.addParameter("autore", "Leonardo Da Vinci");
    request.addParameter("descrizione", "Dipinto olio su tavola di legno 1503");
    request.addParameter("stato", "visibile");
    String message = "Lunghezza nome opera non rispettata";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Test
  public void TC3_2_03() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.addParameter("nome", "La _gioco.nda");
    request.addParameter("autore", "Leonardo Da Vinci");
    request.addParameter("descrizione", "Dipinto olio su tavola di legno 1503");
    request.addParameter("stato", "visibile");
    String message = "Formato nome opera non rispettato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Test
  public void TC3_2_04() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.addParameter("nome", "La gioconda");
    request.addParameter("autore", "Le");
    request.addParameter("descrizione", "Dipinto olio su tavola di legno 1503");
    request.addParameter("stato", "visibile");
    String message = "Lunghezza autore opera non rispettata";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Test
  public void TC3_2_05() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.addParameter("nome", "La gioconda");
    request.addParameter("autore", "Leonardo Da Vinciiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
    request.addParameter("descrizione", "Dipinto olio su tavola di legno 1503");
    request.addParameter("stato", "visibile");
    String message = "Lunghezza autore opera non rispettata";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Test
  public void TC3_2_06() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.addParameter("nome", "La gioconda");
    request.addParameter("autore", "Leon.ardo_da_vinci");
    request.addParameter("descrizione", "Dipinto olio su tavola di legno 1503");
    request.addParameter("stato", "visibile");
    String message = "Formato autore opera non rispettato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Test
  public void TC3_2_07() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.addParameter("nome", "La gioconda");
    request.addParameter("autore", "Leonardo da Vinci");
    request.addParameter("descrizione", "Quad");
    request.addParameter("stato", "visibile");
    String message = "Lunghezza descrizione opera non rispettata";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  /*
  @Disabled
  @Test
  public void TC3_2_08() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.addParameter("nome", "La gioconda");
    request.addParameter("autore", "Leonardo da Vinci");
    request.addParameter("descrizione", "descrizione");
    request.addParameter("stato", "visibile");
    String message = "Lunghezza descrizione opera non rispettata";
    //Non possibile generare una string con pi?? di 5000 caratteri
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }*/
  
  @Test
  public void TC3_2_09() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.addParameter("nome", "La gioconda");
    request.addParameter("autore", "Leonardo da Vinci");
    request.addParameter("descrizione", "Qua_dro amm.irato in fr_ancia");
    request.addParameter("stato", "visibile");
    String message = "Formato descrizione opera non rispettato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  /* 
  @Disabled
  @Test
  public void TC3_2_10() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.addParameter("nome", "La gioconda");
    request.addParameter("autore", "Leonardo da Vinci");
    request.addParameter("descrizione", "Dipinto olio su tavola di legno 1503");
    request.addParameter("stato", "visibile");
    //Non possibile testare poich?? non si pu?? generare dinamicamente un Part
    String message = "dimensioni copertina errato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Disabled
  @Test
  public void TC3_2_11() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.addParameter("nome", "La gioconda");
    request.addParameter("autore", "Leonardo da Vinci");
    request.addParameter("descrizione", "Dipinto olio su tavola di legno 1503");
    request.addParameter("stato", "visibile");
    //Non possibile testare poich?? non si pu?? generare dinamicamente un Part
    String message = "formato copertina errato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Disabled
  @Test
  public void TC3_2_12() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.addParameter("nome", "La gioconda");
    request.addParameter("autore", "Leonardo da Vinci");
    request.addParameter("descrizione", "Dipinto olio su tavola di legno 1503");
    request.addParameter("stato", "visibile");
    //Non possibile testare poich?? non si pu?? generare dinamicamente un Part
    String message = "dimensioni immagine troppo grande";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Disabled
  @Test
  public void TC3_2_13() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.addParameter("nome", "La gioconda");
    request.addParameter("autore", "Leonardo da Vinci");
    request.addParameter("descrizione", "Dipinto olio su tavola di legno 1503");
    request.addParameter("stato", "visibile");
    //Non possibile testare poich?? non si pu?? generare dinamicamente un Part
    String message = "formato immagine errato";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }
  
  @Test
  @Disabled
  public void TC3_2_14() throws ServletException, IOException {
    request.addParameter("id", "1");
    request.addParameter("nome", "La gioconda");
    request.addParameter("autore", "Leonardo da Vinci");
    request.addParameter("descrizione", "Dipinto olio su tavola di legno 1503");
    request.addParameter("stato", "visibile");
    String message = "";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {      
      servlet.doPost(request, response);
    });
    assertEquals(message, exception.getMessage());
  }*/
  
  @AfterEach
  public void tearDown() {
    servlet = null;
    request = null;    
    response = null;
  }

}
