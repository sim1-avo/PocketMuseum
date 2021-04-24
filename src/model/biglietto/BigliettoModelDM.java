package model.biglietto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TimeZone;
import model.DriverManagerConnectionPool;

public class BigliettoModelDM implements BigliettoModel<BigliettoBean> {
  private DriverManagerConnectionPool dmcp = null;
  
  public BigliettoModelDM(DriverManagerConnectionPool dmcp) {
    this.dmcp = dmcp;
    System.out.println("DriverManager Product Model creation....");
  }
  
  public BigliettoModelDM() {}
  
  @Override
  public void doSave(BigliettoBean biglietto) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    TimeZone timeZone = TimeZone.getDefault();
    java.util.Calendar cal = java.util.Calendar.getInstance(timeZone);
    String insertSql = "INSERT INTO biglietto(codice, costo, inizio_turno, fine_turno)"
        + " VALUES(?, ?, ?, ?)";
    try {
      connection = dmcp.getConnection();
      if (biglietto instanceof BigliettoBean) {
        ps = connection.prepareStatement(insertSql);
        ps.setString(1, biglietto.getCodice());
        ps.setFloat(2, biglietto.getCosto());
        ps.setTimestamp(3, biglietto.getInizioTurno(), cal);
        ps.setTimestamp(4, biglietto.getFineTurno(), cal);
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
    String selectSql = "SELECT * FROM biglietto WHERE codice=?";
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

  /** 
   * Recupero biglietti acquistati per data. 
   * @param inizio data inizio periodo 
   * @param fine data fine periodo 
   */  
  public Collection<ArrayList> doRetrievePurchasedTicketByDate(String inizio, String fine)
      throws SQLException {
    ArrayList<ArrayList> biglietti = new ArrayList<ArrayList>();
    PreparedStatement preparedStatement = null;
    Connection connection = null;
    connection = dmcp.getConnection();
    if (inizio instanceof String && fine instanceof String) {
      ArrayList<String> codice = new ArrayList<String>();
      ArrayList<String> dataAcquisto = new ArrayList<String>();
      ArrayList<String> emailAcquirente = new ArrayList<String>();
      ArrayList<String> inizioTurno = new ArrayList<String>();
      ArrayList<String> fineTurno = new ArrayList<String>();
      ArrayList<String> costo = new ArrayList<String>();
      biglietti.add(codice);
      biglietti.add(dataAcquisto);
      biglietti.add(emailAcquirente);
      biglietti.add(inizioTurno);
      biglietti.add(fineTurno);
      biglietti.add(costo);
      String selectSql = "SELECT * FROM Acquista INNER JOIN Biglietto ON Acquista.codicebiglietto"
          + " = Biglietto.codice WHERE data_acquisto>='" + inizio + "' AND data_acquisto<='"
          + fine + "'";
      preparedStatement = connection.prepareStatement(selectSql);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        codice.add(rs.getString("Codice"));
        dataAcquisto.add(rs.getString("Data_acquisto"));
        emailAcquirente.add(rs.getString("email"));
        inizioTurno.add(rs.getString("Inizio_turno"));
        fineTurno.add(rs.getString("Fine_turno"));
        costo.add(rs.getString("Costo"));
      }
    }
    try {
      if (preparedStatement != null) { 
        preparedStatement.close();
      }
    } finally {
      dmcp.releaseConnection(connection);
    }
    
    return biglietti;
  }

  @Override
  public BigliettoBean doRetrieveBigliettoByKey(String codice) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    BigliettoBean bean = new BigliettoBean();
    String selectSql = "SELECT * FROM Biglietto where codice=?";
    TimeZone timeZone = TimeZone.getDefault();
    java.util.Calendar cal = java.util.Calendar.getInstance(timeZone);
    try {
      connection = dmcp.getConnection();
      if (codice instanceof String) {
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1, codice);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
          bean.setCodice(rs.getString("Codice"));
          bean.setCosto(rs.getFloat("Costo"));
          bean.setInizioTurno(rs.getTimestamp("Inizio_turno", cal));
          bean.setFineTurno(rs.getTimestamp("Fine_turno", cal));
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
    return bean;
  }
  
  /** 
   * Controllo disponibilit� biglietti in quel turno.  
   * @param dataInizio data turno*
   */
  public int controlloDisponibilita(Timestamp dataInizio) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String selectSql = "SELECT COUNT(*)  AS count FROM Biglietto WHERE Inizio_turno=?";
    int numBiglietti = 0;
    TimeZone timeZone = TimeZone.getDefault();
    java.util.Calendar cal = java.util.Calendar.getInstance(timeZone);
    try {
      connection = dmcp.getConnection();
      if (dataInizio instanceof Timestamp) {
        preparedStatement = connection.prepareStatement(selectSql);
        dataInizio.setHours(dataInizio.getHours());
        preparedStatement.setTimestamp(1, dataInizio, cal);
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println("State: " + preparedStatement.toString());
        while (rs.next()) {
          numBiglietti = rs.getInt("count");
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
    return numBiglietti;
  }
}
