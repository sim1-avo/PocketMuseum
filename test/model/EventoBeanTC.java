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
  void testSetImmagine() {
    EventoBean b = new EventoBean(1, "nome", "descrizione", null, "immagine", 
        new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
    b.setImmagine("ok");
    assertEquals("ok", b.getImmagine());
  }
  @Test
  void testSetNome() {
    EventoBean b = new EventoBean(1, "nome", "descrizione", null, "immagine",
            new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
    b.setNome("ok");
    assertEquals("ok", b.getNome());
  }
  @Test
  void testSetDescizione() {
    EventoBean b = new EventoBean(1, "nome", "descrizione", null, "immagine",
            new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
    b.setDescrizione("ok");
    assertEquals("ok", b.getDescrizione());
  }
  @Test
  void testSetDataInizio() {
    EventoBean b = new EventoBean(1, "nome", "descrizione", null, "immagine",
            new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
    b.setDataInizio(new Timestamp(System.currentTimeMillis()));
    assertEquals(new Timestamp(System.currentTimeMillis()), b.getDataInizio());
  }
  @Test
  void testSetDataFine() {
    EventoBean b = new EventoBean(1, "nome", "descrizione", null, "immagine",
            new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
    b.setDataFine(new Timestamp(System.currentTimeMillis()));
    assertEquals(new Timestamp(System.currentTimeMillis()), b.getDataFine());
  }
}
