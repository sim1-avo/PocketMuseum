package model;

import java.sql.SQLException;
import model.opera.RecensioneBean;
import model.opera.RecensioneModelDM;
import org.junit.jupiter.api.Test;

public class RecensioneModelDMTC {

  @Test
  public void testRecensioniAssenti() throws SQLException {
    RecensioneModelDM model = new RecensioneModelDM();
    model.doDelete(1, "mariorossi@gmail.com");
    model.mediaRecensioni(1);
    model.doSave(new RecensioneBean(4, "mariorossi@gmail.com", 1, "recensione"));
  }

  @Test
  public void objectTest() throws SQLException {
    RecensioneModelDM model = new RecensioneModelDM();
    Object ob = null;
    model.doSave((RecensioneBean) ob);
    model.doRetrieveByKey((Integer) ob, (String) ob);
    model.doRetrieveByKey(1, (String) ob);
    model.doRetrieveById((Integer) ob);
    model.mediaRecensioni((Integer) ob);
    model.doDelete((Integer) ob, (String) ob);
    model.doDelete(1, (String) ob);
  }
}
