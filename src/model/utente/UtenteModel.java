package model.utente;

import java.sql.SQLException;
import model.DriverManagerConnectionPool;

public interface UtenteModel<T> {

  public void doSave(DriverManagerConnectionPool dm, Object ob) throws SQLException;

  public void doUpdate(DriverManagerConnectionPool dm, Object ob) throws SQLException;

  public void doDelete(DriverManagerConnectionPool dm, Object ob) throws SQLException;

  public T doRetrieveByKey(String email) throws SQLException;

}
