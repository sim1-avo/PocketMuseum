package model.opera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import model.DriverManagerConnectionPool;
import model.utente.UtenteBean;


public class RecensioneModelDM implements RecensioneModel<RecensioneBean> {
  @Override
  public void doSave(RecensioneBean recensione) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    String insertSql = "INSERT into Recensioni(Valutazione,email,IDopera, commento) values (?,?,?,?)";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (recensione instanceof RecensioneBean) {
        ps = connection.prepareStatement(insertSql);
        ps.setInt(1, recensione.getValutazione());
        ps.setString(2, recensione.getEmail());
        ps.setInt(3, recensione.getIDopera());
        ps.setString(4, recensione.getCommento());
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
  
  @Override
  public RecensioneBean doRetrieveByKey(Integer idOpera, String email) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    RecensioneBean bean = new RecensioneBean();
    String selectSql = "SELECT * FROM Recensioni WHERE email = ? AND IDopera=?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (idOpera instanceof Integer && email instanceof String) {
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1, email);
        preparedStatement.setInt(2, idOpera);
        System.out.println("doRetrieveByKey: " + preparedStatement.toString());
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        bean.setValutazione(rs.getInt("Valutazione"));
        bean.setEmail(rs.getString("email"));
        bean.setIDopera(rs.getInt("IDopera"));
        bean.setCommento(rs.getString("commento"));
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
    return bean;
  }
  
  @Override
  public void doDelete(Integer idOpera, String email) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String selectSql = "DELETE FROM Recensioni WHERE email = ? AND IDopera=?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (idOpera instanceof Integer && email instanceof String) {
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1, email);
        preparedStatement.setInt(2, idOpera);
        System.out.println("doDelete: " + preparedStatement.toString());
        preparedStatement.executeUpdate();
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
  
  @Override
  public Collection<RecensioneBean> doRetrieveById(Integer idOpera) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<RecensioneBean> recensioni = new ArrayList<RecensioneBean>();
    String selectSql = "SELECT * FROM Recensioni WHERE IDopera=?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (idOpera instanceof Integer) {
        preparedStatement = connection.prepareStatement(selectSql); 
        preparedStatement.setInt(1, idOpera);
        System.out.println("doRetrieveById: " + preparedStatement.toString());
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
          RecensioneBean bean = new RecensioneBean();
          bean.setValutazione(rs.getInt("Valutazione"));
          bean.setEmail(rs.getString("email"));
          bean.setIDopera(rs.getInt("IDopera"));
          bean.setCommento(rs.getString("commento"));
          recensioni.add(bean);
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
    return recensioni;
  }
  @Override
  public Collection<RecensioneBean> doRetrieveAll() throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<RecensioneBean> recensioni = new ArrayList<RecensioneBean>();
    String selectSql = "SELECT * FROM Recensioni";
    try {
      connection = DriverManagerConnectionPool.getConnection();
        preparedStatement = connection.prepareStatement(selectSql);
        System.out.println("doRetrieveAll: " + preparedStatement.toString());
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
          RecensioneBean bean = new RecensioneBean();
          bean.setValutazione(rs.getInt("Valutazione"));
          bean.setEmail(rs.getString("email"));
          bean.setIDopera(rs.getInt("IDopera"));
          bean.setCommento(rs.getString("commento"));
          recensioni.add(bean);
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
    return recensioni;
  }
  
  @Override
  public int mediaRecensioni(Integer idOpera) throws SQLException {
    int numRec = 0;
    int totRec = 0;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String selectSql = "SELECT valutazione FROM Recensioni WHERE IDopera=?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (idOpera instanceof Integer) {
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setInt(1, idOpera);
        System.out.println("MediaRecensioni: " + preparedStatement.toString());
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
          numRec++;
          totRec += rs.getInt("valutazione");
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
    if (numRec == 0) {
      return 0;
    }
    return totRec / numRec;
  }

  public void doUpdate(RecensioneBean recensione) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    String updateSql = "UPDATE Recensioni SET commento = ?, Valutazione = ? WHERE email = ? AND IDopera = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (recensione instanceof RecensioneBean) {
        ps = connection.prepareStatement(updateSql);
        ps.setString(1, recensione.getCommento());
        ps.setInt(2, recensione.getValutazione());
        ps.setString(3, recensione.getEmail());
        ps.setInt(4, recensione.getIDopera());
        ps.executeUpdate();
        System.out.println("doUpdate:" + ps.toString());
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
}
