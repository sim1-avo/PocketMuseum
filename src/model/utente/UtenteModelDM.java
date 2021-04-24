package model.utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import model.DriverManagerConnectionPool;
import model.TemplateDM;

public class UtenteModelDM extends TemplateDM implements UtenteModel<UtenteBean> {

  private DriverManagerConnectionPool dmcp = null;
  
  /**
   * Questo è il costruttore per UtenteModelDM.
   */

  public UtenteModelDM() {}
  
  @Override
  public DriverManagerConnectionPool dm() {
    return new DriverManagerConnectionPool();
  }
  
  @Override
  public void doSave(DriverManagerConnectionPool dm, Object ob) throws SQLException {
    UtenteBean utente = (UtenteBean) ob;
    Connection connection = null;
    PreparedStatement ps = null;
    String insertSql = "INSERT INTO Utente(email,Password,Nome,Cognome,Tipo,CF)"
        + "VALUES (?,?,?,?,?,?)";

    try {
      connection = dm.getConnection();
      if (ob instanceof UtenteBean) {
        ps = connection.prepareStatement(insertSql);
        ps.setString(1, utente.getEmail());
        ps.setString(2, utente.getPassword());
        ps.setString(3, utente.getNome());
        ps.setString(4, utente.getCognome());
        ps.setString(5, utente.getTipo());
        ps.setString(6, utente.getCf());
        ps.executeUpdate();
        System.out.println("doSave:" + ps.toString());
        connection.commit();
      }
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
  }
  
  /**
   * Questo è il metodo doUpdate.
   */
  @Override
  public void doUpdate(DriverManagerConnectionPool dm, Object ob) throws SQLException {
    UtenteBean utente = (UtenteBean) ob;
    Connection connection = null;
    PreparedStatement ps = null;

    String updateSql = "UPDATE Utente SET Password=?, Nome=?, Cognome=?, Tipo=?, CF=?"
        + "WHERE email=?";

    try {
      connection = dm.getConnection();
      if (ob instanceof UtenteBean) {
        ps = connection.prepareStatement(updateSql);
        ps.setString(1, utente.getPassword());
        ps.setString(2, utente.getNome());
        ps.setString(3, utente.getCognome());
        ps.setString(4, utente.getTipo());
        ps.setString(5, utente.getCf());
        ps.setString(6, utente.getEmail());
        ps.executeUpdate();
        System.out.println("doSave:" + ps.toString());
        connection.commit();
      }
    } finally {
      try {
        if (ps != null)  {
          ps.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }

  }

  /**
   * Questo è il metodo doDelete.
   */
  @Override
  public void doDelete(DriverManagerConnectionPool dm, Object ob) throws SQLException {
    String utente = (String) ob;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSql = "DELETE FROM Utente WHERE email = ?";

    try {
      connection = dm.getConnection();
      if (ob instanceof String) {
        preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setString(1, utente);
        preparedStatement.executeUpdate();
        connection.commit();
      }
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }  
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }

  }

  /**
   * Questo è il metodo UtenteBean.
   */
  public UtenteBean doRetrieveByKey(String email) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String selectSql = "SELECT * FROM Utente WHERE email = ?";
    UtenteBean bean = new UtenteBean();

    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (email instanceof String) {
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        bean.setTipo(rs.getString("Tipo"));
        bean.setCognome(rs.getString("Cognome"));
        bean.setNome(rs.getString("Nome"));
        bean.setEmail(rs.getString("Email"));
        bean.setPassword(rs.getString("Password"));
        bean.setCf(rs.getString("CF"));
      }
    } finally {
      try {
        if (preparedStatement != null)  {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return bean;
  }

  
  /**
   * Questo è il metodo doRetrieveAllByEmail.
   */
  public Collection<UtenteBean> doRetrieveAllByEmail(String email) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ArrayList<UtenteBean> utente = new ArrayList<UtenteBean>();

    String selectSql = "SELECT * FROM Utente WHERE email = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (email instanceof String) {
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery(); 
        while (rs.next()) {  
          UtenteBean bean = new UtenteBean();
          bean.setEmail(rs.getString("email"));
          bean.setPassword(rs.getString("Password"));
          bean.setNome(rs.getString("Nome"));
          bean.setCognome(rs.getString("Cognome"));
          bean.setTipo(rs.getString("Tipo"));
          bean.setCf(rs.getString("CF"));
          utente.add(bean);
        }
      }
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }

    return utente;
  }
}
