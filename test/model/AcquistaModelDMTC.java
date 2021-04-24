package model;

import java.sql.SQLException;
import java.util.ArrayList;
import model.biglietto.AcquistaBean;
import model.biglietto.AcquistaModelDM;
import org.junit.jupiter.api.Test;



class AcquistaModelDMTC {
  private static AcquistaModelDM model = new AcquistaModelDM();
  
  @Test
  void testByKey() throws SQLException {
    model.doRetrieveByKey("AAAA0000AAAA");
  }
  
  @Test
  void testOggetti() throws SQLException {
    Object object = null;
    model.doSave((AcquistaBean) object);
    model.doRetrieveAllByEmail((String) object);
    model.doRetrieveByKey((String) object);
    model.doRetrieveByLastDate((String) object);
    model.doDelete((String) object);
  }
  
  @Test
  void testNessunBiglietto() throws SQLException {
    ArrayList<AcquistaBean> biglietti =  (ArrayList<AcquistaBean>) model
        .doRetrieveAllByEmail("mariorossi@gmail.com");
    AcquistaModelDM model = new AcquistaModelDM();
    for (int i = 0; i < biglietti.size(); i++) {
      model.doDelete(biglietti.get(i).getCodice());
    }
    model.doRetrieveByLastDate("CodiceBiglietto");
    for (int i = 0; i < biglietti.size(); i++) {
      model.doSave(biglietti.get(i));
    }
  }
  
  @Test
  void testByKeyFail() throws SQLException {
    model.doRetrieveByKey("A");
  }
}
