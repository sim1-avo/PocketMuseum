package model.biglietto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import model.DriverManagerConnectionPool;


public class AcquistaModelDM implements AcquistaModel<AcquistaBean> {

  private DriverManagerConnectionPool dmcp = null;
  /**
   * costruttore della classe AcquistaModelDM.
   */
  

  public AcquistaModelDM() {}

  @Override
  public void doSave(AcquistaBean acquista) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    String insertSql = "INSERT INTO acquista(CodiceBiglietto, email, data_acquisto)VALUES(?, ?, ?)";
    try {
      connection = dmcp.getConnection();
      if (acquista instanceof AcquistaBean) {
        ps = connection.prepareStatement(insertSql);
        ps.setString(1, acquista.getCodice());
        ps.setString(2, acquista.getEmail());
        ps.setDate(3, acquista.getDataAcquisto());
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
        dmcp.releaseConnection(connection);
      }
    }
  }

  @Override
  public boolean doRetrieveByKey(String codice) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String selectSql = "SELECT * FROM acquista WHERE CodiceBiglietto=?";
    try {
      connection = dmcp.getConnection();
      if (codice instanceof String) {
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1, codice);
        ResultSet result = preparedStatement.executeQuery();
        if (!result.next()) {
          return false;
        } else {
          return true;
        }
      }
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        dmcp.releaseConnection(connection);
      }
    }
    return false;
  }

  @Override
  public Collection<AcquistaBean> doRetrieveAllByEmail(String email) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<AcquistaBean> acquista = new ArrayList<AcquistaBean>();
    String selectSql = "SELECT * FROM Biglietto join acquista where email=? and"
        + " biglietto.Codice=acquista.CodiceBiglietto";
    try {
      connection = dmcp.getConnection();
      if (email instanceof String) {
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
          AcquistaBean bean = new AcquistaBean();
          bean.setInizioTurno(rs.getTimestamp("Inizio_turno"));
          bean.setFineTurno(rs.getTimestamp("Fine_turno"));
          bean.setCodice(rs.getString("Codice"));
          bean.setEmail(rs.getString("email")); 
          bean.setDataAcquisto(rs.getDate("data_acquisto"));
          bean.setCosto(rs.getFloat("Costo"));
          acquista.add(bean);
        }
      }
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        dmcp.releaseConnection(connection);
      }
    }
    return acquista;
  }
  
  @Override
  public String doRetrieveByLastDate(String colonna) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String selectSql = "SELECT " + colonna + " FROM acquista ORDER BY Data_acquisto DESC LIMIT 1";
    try {
      connection = dmcp.getConnection();
      if (colonna instanceof String) {
        preparedStatement = connection.prepareStatement(selectSql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
          String codice = rs.getString("CodiceBiglietto");
          return codice;
        }
      }
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        dmcp.releaseConnection(connection);
      }
    }
    return null;
  }
  
  @Override
  public void doDelete(String codice) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String selectSql = "DELETE FROM acquista WHERE CodiceBiglietto=?";
    try {
      connection = dmcp.getConnection();
      if (codice instanceof String) {
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1, codice);
        preparedStatement.executeUpdate();
      }
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        dmcp.releaseConnection(connection);
      }
    }
  }
}
