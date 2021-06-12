package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import model.utente.UtenteBean;
import org.junit.jupiter.api.Test;

class UtenteBeanTC {

  @Test
  void testCostruttoreVuoto() {
    UtenteBean b = new UtenteBean();
    assertNotNull(b);
  }
  
  @Test
  void testCostruttore() {
    UtenteBean b = new UtenteBean("prova@prova.it", "pass", "mario", "verdi", "reg", "12345");
    assertNotNull(b);
  }
 
  @Test
  void testSetEmail() {
    UtenteBean b = new UtenteBean("prova@prova.it", "pass", "mario", "verdi", "reg", "12345");
    b.setEmail("nuovamail");
    assertEquals("nuovamail", b.getEmail());
  }

  @Test
  void testSetPassword() {
    UtenteBean b = new UtenteBean("prova@prova.it", "pass", "mario", "verdi", "reg", "12345");
    b.setPassword("nuovapass");
    assertEquals("nuovapass", b.getPassword());
  }

  @Test
  void testSetNome() {
    UtenteBean b = new UtenteBean("prova@prova.it", "pass", "mario", "verdi", "reg", "12345");
    b.setNome("nuovonome");
    assertEquals("nuovonome", b.getNome());
  }

  @Test
  void testSetCognome() {
    UtenteBean b = new UtenteBean("prova@prova.it", "pass", "mario", "verdi", "reg", "12345");
    b.setCognome("nuovocognome");
    assertEquals("nuovocognome", b.getCognome());
  }

  @Test
  void testSetTipo() {
    UtenteBean b = new UtenteBean("prova@prova.it", "pass", "mario", "verdi", "reg", "12345");
    b.setTipo("admin");
    assertEquals("admin", b.getTipo());
  }

  @Test
  void testSetCF() {
    UtenteBean b = new UtenteBean("prova@prova.it", "pass", "mario", "verdi", "reg", "12345");
    b.setCf("nuovocf");
    assertEquals("nuovocf", b.getCf());
  }

  @Test
  void testToString() {
    UtenteBean b = new UtenteBean("prova@prova.it", "pass", "mario", "verdi", "reg", "12345");
    String s = b.toString();
    assertEquals(s, b.toString());
  }

}
