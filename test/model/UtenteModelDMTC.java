package model;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import model.utente.UtenteModelDM;
import org.junit.jupiter.api.Test;


class UtenteModelDMTC {

  private static UtenteModelDM model = new UtenteModelDM();

  @Test
  void test() throws IOException, ServletException, SQLException {
    model.doRetrieveAllByEmail("mariorossi@gmail.com");
  }

  @Test
  void objectTest() throws IOException, ServletException, SQLException {
    Object ob = null;
    TemplateDM model1 = new UtenteModelDM();
    model1.salva(ob);
    model1.modifica(ob);
    model1.elimina(ob);
    model.doRetrieveAllByEmail((String) ob);
    model.doRetrieveByKey((String) ob);
  }
}
