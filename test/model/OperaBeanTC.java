package model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import model.opera.OperaBean;
import org.junit.jupiter.api.Test;

class OperaBeanTC {
  @Test
  void test() {
    OperaBean bean = new OperaBean(1, "nome", "descrizione", "autore", "copertina",
        "stato", "immagine");
    assertNotNull(bean);
  }
}
