package model.opera;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import model.DriverManagerConnectionPool;
import model.Utility;

public class OperaModelDM implements OperaModel<OperaBean> {

  @Override
  public void doSave(OperaBean opera) throws SQLException, IOException {
    Connection connection = null;
    PreparedStatement ps = null;
    String insertSql = "";
    InputStream in = null;
    InputStream in1 = null;
    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (opera instanceof OperaBean) {
        if (opera.getImmaginePart() == null && opera.getCopertinaPart() == null) {
          insertSql = "insert into Opera(Nome,Descrizione,Autore,Stato) values (?,?,?,?)";
          ps = connection.prepareStatement(insertSql);
          ps.setString(1, opera.getNome());
          ps.setString(2, opera.getDescrizione());
          ps.setString(3, opera.getAutore());
          ps.setString(4, opera.getStato());
        } else if (opera.getImmaginePart() == null) {
          insertSql = "insert into Opera(Nome,Descrizione,Autore,Stato,Copertina) "
              + "values (?,?,?,?,?)";
          ps = connection.prepareStatement(insertSql);
          ps.setString(1, opera.getNome());
          ps.setString(2, opera.getDescrizione());
          ps.setString(3, opera.getAutore());
          ps.setString(4, opera.getStato());
          in = opera.getCopertinaPart().getInputStream();
          ps.setBlob(5, in);
        } else if (opera.getCopertinaPart() == null) {
          insertSql = "insert into Opera(Nome,Descrizione,Autore,Stato,Immagine) "
              + "values (?,?,?,?,?)";
          ps = connection.prepareStatement(insertSql);
          ps.setString(1, opera.getNome());
          ps.setString(2, opera.getDescrizione());
          ps.setString(3, opera.getAutore());
          ps.setString(4, opera.getStato());
          in = opera.getImmaginePart().getInputStream();
          ps.setBlob(5, in);
        } else {
          insertSql = "insert into Opera(Nome,Descrizione,Autore,Stato,Immagine,Copertina) "
              + "values (?,?,?,?,?,?)";
          ps = connection.prepareStatement(insertSql);
          ps.setString(1, opera.getNome());
          ps.setString(2, opera.getDescrizione());
          ps.setString(3, opera.getAutore());
          ps.setString(4, opera.getStato());
          in = opera.getImmaginePart().getInputStream();
          ps.setBlob(5, in);
          in1 = opera.getCopertinaPart().getInputStream();
          ps.setBlob(6, in1);
        }
        System.out.println("doSave:" + ps.toString());
        ps.executeUpdate();
        connection.commit(); 
      }
      if (in != null) {
        in.close();
      } 
      if (in1 != null) {
        in1.close();
      }
      if (ps != null) {
        ps.close();
      }
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
    
  }

  @Override
  public void doUpdate(OperaBean opera) throws SQLException, IOException {
    Connection connection = null;
    PreparedStatement ps = null;
    String updateSql = "";
    InputStream in = null;
    InputStream in1 = null;
    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (opera instanceof OperaBean) {
        if (opera.getImmaginePart() == null && opera.getCopertinaPart() == null) {
          updateSql = "update Opera set Nome=?, Descrizione=?, Autore=?, Stato=? where id=?";
          ps = connection.prepareStatement(updateSql);
          ps.setString(1, opera.getNome());
          ps.setString(2, opera.getDescrizione());
          ps.setString(3, opera.getAutore());
          ps.setString(4, opera.getStato());
          ps.setInt(5, opera.getId());
        } else if (opera.getImmaginePart() == null) {
          updateSql = "update Opera set Nome=?, Descrizione=?, Autore=?, Stato=?, Copertina=?"
              + "where id=?";
          ps = connection.prepareStatement(updateSql);
          ps.setString(1, opera.getNome());
          ps.setString(2, opera.getDescrizione());
          ps.setString(3, opera.getAutore());
          ps.setString(4, opera.getStato());
          in = opera.getCopertinaPart().getInputStream();
          ps.setBlob(5, in);
          ps.setInt(6, opera.getId());
        } else if (opera.getCopertinaPart() == null) {
          updateSql = "update Opera set Nome=?, Descrizione=?, Autore=?, Stato=?, Immagine=?"
              + " where id=?";
          ps = connection.prepareStatement(updateSql);
          ps.setString(1, opera.getNome());
          ps.setString(2, opera.getDescrizione());
          ps.setString(3, opera.getAutore());
          ps.setString(4, opera.getStato());
          in = opera.getImmaginePart().getInputStream();
          ps.setBlob(5, in);
          ps.setInt(6, opera.getId());
        } else {
          updateSql = "update Opera set Nome=?, Descrizione=?, Autore=?, Stato=?, Immagine=?,"
              + " Copertina=? where id=?";
          ps = connection.prepareStatement(updateSql);
          ps.setString(1, opera.getNome());
          ps.setString(2, opera.getDescrizione());
          ps.setString(3, opera.getAutore());
          ps.setString(4, opera.getStato());
          in = opera.getImmaginePart().getInputStream();
          ps.setBlob(5, in);
          in1 = opera.getCopertinaPart().getInputStream();
          ps.setBlob(6, in1);
          ps.setInt(7, opera.getId());
        }
        System.out.println("doUpdate:" + ps.toString());
        ps.executeUpdate();
        connection.commit();
      }
      if (in != null) {
        in.close();
      }
      if (in1 != null) {   
        in1.close();
      }
      if (ps != null) {
        ps.close();
      }
    } finally {
      DriverManagerConnectionPool.releaseConnection(connection);
    }
  }

  @Override
  public void doDelete(OperaBean opera) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String deleteOperaSql = "delete from Opera where id = ?";
    String deleteRecensioniSql = "delete from Recensioni where idOpera = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (opera instanceof OperaBean) {
        preparedStatement = connection.prepareStatement(deleteRecensioniSql);
        preparedStatement.setInt(1, opera.getId());
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        preparedStatement = connection.prepareStatement(deleteOperaSql);
        preparedStatement.setInt(1, opera.getId());
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

  @Override
  public OperaBean doRetrieveByKey(Integer code) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    OperaBean bean = new OperaBean();
    String selectSql = "SELECT * FROM Opera WHERE id = ?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (code instanceof Integer) { 
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setInt(1, code);
        System.out.println("doRetrieveByKey: " + preparedStatement.toString());
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        bean.setId(rs.getInt("ID"));
        bean.setNome(rs.getString("Nome"));
        bean.setDescrizione(rs.getString("Descrizione"));
        bean.setAutore(rs.getString("Autore"));
        bean.setStato(rs.getString("Stato"));
        if (rs.getBlob("Immagine") != null) {
          bean.setImmagine(Utility.base64ImageString(Utility.blobToBytes(rs.getBlob("Immagine"))));
        }
        if (rs.getBlob("Copertina") != null) {
          bean.setCopertina(Utility.base64ImageString(Utility.blobToBytes(rs
              .getBlob("Copertina"))));
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
    return bean;
  }

  @Override
  public Collection<OperaBean> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<OperaBean> opere = new ArrayList<OperaBean>();
    String selectSql = "SELECT * FROM Opera o ORDER BY o.Nome " + order;
    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (order instanceof String) {
        preparedStatement = connection.prepareStatement(selectSql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
          OperaBean bean = new OperaBean();
          bean.setId(rs.getInt("ID"));
          bean.setNome(rs.getString("Nome"));
          bean.setDescrizione(rs.getString("Descrizione"));
          bean.setAutore(rs.getString("Autore"));
          bean.setStato(rs.getString("Stato"));
          if (rs.getBlob("Immagine") != null) {
            bean.setImmagine(Utility.base64ImageString(Utility.blobToBytes(rs
                .getBlob("Immagine"))));
          }
          if (rs.getBlob("Copertina") != null) {
            bean.setCopertina(Utility.base64ImageString(Utility.blobToBytes(rs
                .getBlob("Copertina"))));
          }
          opere.add(bean);
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
    return opere;
  }

  @Override
  public Collection<OperaBean> doRetrieveName(String nome) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<OperaBean> opere = new ArrayList<OperaBean>();
    String selectSql = "SELECT * FROM Opera WHERE Nome like ? or Autore like ?";
    try {
      connection = DriverManagerConnectionPool.getConnection();
      if (nome instanceof String) {
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1, "%" + nome + "%");
        preparedStatement.setString(2, "%" + nome + "%");
        System.out.println("doRetrieveByName: " + preparedStatement.toString());
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
          OperaBean bean = new OperaBean();
          bean.setId(rs.getInt("ID"));
          bean.setNome(rs.getString("Nome"));
          bean.setDescrizione(rs.getString("Descrizione"));
          bean.setAutore(rs.getString("Autore"));
          bean.setStato(rs.getString("Stato"));
          if (rs.getBlob("Immagine") != null) {
            bean.setImmagine(Utility.base64ImageString(Utility.blobToBytes(rs
                .getBlob("Immagine"))));
          }
          if (rs.getBlob("Copertina") != null) {
            bean.setCopertina(Utility.base64ImageString(Utility.blobToBytes(rs
                .getBlob("Copertina"))));
          }
          opere.add(bean);
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
    return opere;
  }
}
