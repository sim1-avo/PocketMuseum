package model;

import java.sql.SQLException;


public abstract class TemplateDM {
  public abstract void doSave(DriverManagerConnectionPool dm, Object ob) throws SQLException; 
  
  public abstract void doUpdate(DriverManagerConnectionPool dm, Object ob) throws SQLException; 
  
  public abstract void doDelete(DriverManagerConnectionPool dm, Object ob) throws SQLException; 
  
  public abstract DriverManagerConnectionPool dm(); 
  
  public final void salva(Object ob) throws SQLException {
    DriverManagerConnectionPool dm = dm();
    doSave(dm, ob);
  }
  
  public final void modifica(Object ob) throws SQLException {
    DriverManagerConnectionPool dm = dm();
    doUpdate(dm, ob);
  }
  
  public final void elimina(Object ob) throws SQLException {
    DriverManagerConnectionPool dm = dm();
    doDelete(dm, ob);
  }
}
