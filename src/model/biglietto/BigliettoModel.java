package model.biglietto;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

public interface BigliettoModel<T> {
  public void doSave(T biglietto) throws SQLException;
  
  public boolean doRetrieveByKey(String codice) throws SQLException;
  
  public Collection<ArrayList> doRetrievePurchasedTicketByDate(String inizio, String fine) 
      throws SQLException;
  
  public BigliettoBean doRetrieveBigliettoByKey(String codice) throws SQLException;
  
  public int controlloDisponibilita(Timestamp dataInizio) throws SQLException;
}
