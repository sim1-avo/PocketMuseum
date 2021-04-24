package model;


import java.sql.SQLException; 
import java.sql.Timestamp;
import model.biglietto.BigliettoBean;
import model.biglietto.BigliettoModelDM;
import org.junit.jupiter.api.Test;


public class BigliettoModelDMTC {
  private static BigliettoModelDM model = new BigliettoModelDM();
  
  @Test
  public void testOggetti() throws SQLException {
    Object ob = null;
    model.doSave((BigliettoBean) ob);
    model.doRetrieveBigliettoByKey((String) ob);
    model.doRetrievePurchasedTicketByDate((String) ob, (String) ob);
    model.doRetrievePurchasedTicketByDate((String) ob, "2020-10-10");
    model.doRetrievePurchasedTicketByDate("2020-10-10", (String) ob);
    model.controlloDisponibilita((Timestamp) ob);
    model.doRetrieveByKey((String) ob);
  }
}
