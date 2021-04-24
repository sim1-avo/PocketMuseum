package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import model.biglietto.BigliettoBean;
import org.junit.jupiter.api.Test;

class BigliettoBeanTC {

  @Test
  void testCostruttoreVuoto() {
    BigliettoBean b = new BigliettoBean();
    assertNotNull(b);
  }

  @Test
  void testCostruttore() {
    BigliettoBean b = new BigliettoBean("AAAA0000BBBB", 12f,
        new Timestamp(System.currentTimeMillis()),
        new Timestamp(System.currentTimeMillis()));
    assertNotNull(b);
  }

  @Test
  void testSetCodice() {
    BigliettoBean b = new BigliettoBean("AAAA0000BBBB", 12f,
        new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
    b.setCodice("ZZZZ1234AAAA");
    assertEquals("ZZZZ1234AAAA", b.getCodice());
  }

  @Test
  void testSetCosto() {
    BigliettoBean b = new BigliettoBean("AAAA0000BBBB", 12f,
        new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
    b.setCosto(15f);
    assertEquals(15f, b.getCosto());
  }

  @Test
  void testSetDataInizio() {
    BigliettoBean b = new BigliettoBean("AAAA0000BBBB", 12f, 
        new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
    Timestamp ora = new Timestamp(System.currentTimeMillis());
    b.setInizioTurno(ora);
    assertEquals(ora, b.getInizioTurno());
  }
  
  @Test
  void testSetDataFine() {
    BigliettoBean b = new BigliettoBean("AAAA0000BBBB", 12f,
        new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
    Timestamp ora = new Timestamp(System.currentTimeMillis());
    b.setFineTurno(ora);
    assertEquals(ora, b.getFineTurno());
  }

}
