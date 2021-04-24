package model.opera;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface OperaModel<T> {

  public void doSave(T opera) throws SQLException, IOException;
  
  public void doUpdate(T opera) throws SQLException, IOException;
  
  public void doDelete(T opera) throws SQLException;
  
  public T doRetrieveByKey(Integer code) throws SQLException;
  
  public Collection<T> doRetrieveAll(String order) throws SQLException;
  
  public Collection<T> doRetrieveName(String nome) throws SQLException;
}
