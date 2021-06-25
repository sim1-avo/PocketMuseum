package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import model.evento.EventoBean;
import org.junit.jupiter.api.Test;

class EventoBeanTC {

  @Test
  void testCostruttore() {
    EventoBean b = new EventoBean(1, "nome", "descrizione", null, "immagine", 
        new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
    assertNotNull(b);
  }

  @Test
  void testGetImmagine() {
    EventoBean b = new EventoBean(1, "nome", "descrizione", null, "immagine", 
        new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
    b.setImmagine("ok");
    assertEquals("ok", b.getImmagine());
  }
}
