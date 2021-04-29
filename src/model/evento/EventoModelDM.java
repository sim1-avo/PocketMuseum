package model.evento;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DriverManagerConnectionPool;
import model.Utility;

public class EventoModelDM implements EventoModel<EventoBean> {

    @Override
    public void doSave(EventoBean evento) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement ps = null;
        String insertSql = "";
        InputStream in = null;
        try {
            connection = DriverManagerConnectionPool.getConnection();
            if (evento instanceof EventoBean) {
                if (evento.getImmaginePart() == null) {
                    insertSql = "insert into Evento(Nome,data_inizio,data_fine,Descrizione"
                            + ") values (?,?,?,?)";
                    ps = connection.prepareStatement(insertSql);
                    ps.setString(1, evento.getNome());
                    ps.setTimestamp(2, evento.getDataInizio());
                    ps.setTimestamp(3, evento.getDataFine());
                    ps.setString(4, evento.getDescrizione());
                    ps.executeUpdate();
                    System.out.println("doSave:" + ps.toString());
                } else {
                    insertSql = "insert into Evento(Nome,data_inizio,data_fine,Descrizione,"
                            + "immagine) values (?,?,?,?,?)";
                    ps = connection.prepareStatement(insertSql);
                    ps.setString(1, evento.getNome());
                    ps.setTimestamp(2, evento.getDataInizio());
                    ps.setTimestamp(3, evento.getDataFine());
                    ps.setString(4, evento.getDescrizione());
                    in = evento.getImmaginePart().getInputStream();
                    ps.setBlob(5, in);
                    ps.executeUpdate();
                    System.out.println("doSave:" + ps.toString());
                }
                connection.commit();
            }
            if (in != null) {
                in.close();
            }
            if (ps != null) {
                ps.close();
            }
        } finally {
            DriverManagerConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public ArrayList<EventoBean> doRetrieveAll(String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<EventoBean> evento = new ArrayList<EventoBean>();
        String selectSql = "SELECT * FROM Evento e ORDER BY e.Data_inizio " + order;
        try {
            connection = DriverManagerConnectionPool.getConnection();
            if (order instanceof String) {
                preparedStatement = connection.prepareStatement(selectSql);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    EventoBean bean = new EventoBean();
                    bean.setId(rs.getInt("ID"));
                    bean.setNome(rs.getString("Nome"));
                    bean.setDataInizio(rs.getTimestamp("Data_inizio"));
                    bean.setDataFine(rs.getTimestamp("Data_fine"));
                    bean.setDescrizione(rs.getString("Descrizione"));

                    if (rs.getBlob("Immagine") != null) {
                        bean.setImmagine(Utility.base64ImageString(Utility.blobToBytes(rs
                                .getBlob("Immagine"))));
                    }
                    evento.add(bean);
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
        return evento;
    }

    @Override
    public boolean doDelete(EventoBean evento) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String deleteSql = "delete from Evento where id = ?";
        boolean result = false;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            if (evento instanceof EventoBean) {
                preparedStatement = connection.prepareStatement(deleteSql);
                preparedStatement.setInt(1, evento.getId());
                if (preparedStatement.executeUpdate() == 1)
                    result = true;
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

        return result;
    }

    @Override
    public EventoBean doRetrieveByName(String nomeEvento) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        EventoBean bean = new EventoBean();
        String selectSql = "SELECT * FROM Evento WHERE Nome like ?";
        try {
            connection = DriverManagerConnectionPool.getConnection();
            if (nomeEvento instanceof String) {
                preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setString(1, "%" + nomeEvento + "%");
                System.out.println("doRetrieveByName: " + preparedStatement.toString());
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    bean.setId(rs.getInt("ID"));
                    bean.setNome(rs.getString("Nome"));
                    bean.setDataInizio(rs.getTimestamp("Data_inizio"));
                    bean.setDataFine(rs.getTimestamp("Data_fine"));
                    bean.setDescrizione(rs.getString("Descrizione"));
                    if (rs.getBlob("Immagine") != null) {
                        bean.setImmagine(Utility.base64ImageString(Utility.blobToBytes(rs
                                .getBlob("Immagine"))));
                    }
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


    public boolean doUpdate(EventoBean evento) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement ps = null;
        String updateSql = "";
        InputStream in = null;
        InputStream in1 = null;
        boolean result = false;
        try {
            connection = DriverManagerConnectionPool.getConnection();
            if (evento instanceof EventoBean) {
                if(evento.getImmaginePart() == null)
                    updateSql = "update Evento set Nome=?, Descrizione=?, Data_inizio=?, Data_fine=?"
                            + " where id=?";
                else
                    updateSql = "update Evento set Nome=?, Descrizione=?, Data_inizio=?, Data_fine=?, Immagine=?"
                            + " where id=?";
                ps = connection.prepareStatement(updateSql);
                ps.setString(1, evento.getNome());
                ps.setString(2, evento.getDescrizione());
                ps.setTimestamp(3, evento.getDataInizio());
                ps.setTimestamp(4, evento.getDataFine());
                if(evento.getImmaginePart() != null) {
                    in = evento.getImmaginePart().getInputStream();
                    ps.setBlob(5, in);
                    ps.setInt(6, evento.getId());
                } else
                    ps.setInt(5, evento.getId());

                System.out.println("doUpdate:" + ps.toString());
                if ( ps.executeUpdate() == 1)
                    result=true;
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

        return result;
    }
}
