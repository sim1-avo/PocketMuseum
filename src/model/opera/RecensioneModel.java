package model.opera;

import java.sql.SQLException;
import java.util.Collection;

public interface RecensioneModel<T> {
  public void doSave(T recensione) throws SQLException;
  
  public T doRetrieveByKey(Integer idOpera, String email) throws SQLException;
  
  public Collection<T> doRetrieveById(Integer idOpera) throws SQLException;
  public Collection<T> doRetrieveAll() throws SQLException;

  public int mediaRecensioni(Integer idOpera)throws SQLException;

  void doDelete(Integer idOpera, String email) throws SQLException;
}
