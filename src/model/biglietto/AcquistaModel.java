package model.biglietto;

import java.sql.SQLException;
import java.util.Collection;

public interface AcquistaModel<T> {
  public void doSave(T acquista) throws SQLException;
  
  public boolean doRetrieveByKey(String codice) throws SQLException;
    
  public Collection<AcquistaBean> doRetrieveAllByEmail(String email) throws SQLException;
  
  public String doRetrieveByLastDate(String colonna) throws SQLException;
  
  public void doDelete(String codice) throws SQLException;
}
