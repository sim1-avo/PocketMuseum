package model;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

public class DriverManagerConnectionPoolTC {

  @Test
  public void Test1() throws SQLException {
    Connection conn = DriverManagerConnectionPool.getConnection();
    DriverManagerConnectionPool.releaseConnection(conn);
    conn = DriverManagerConnectionPool.getConnection();
    conn.close();
  }

  @Test
  public void Test2() throws SQLException {
    Connection conn = null;
    DriverManagerConnectionPool.releaseConnection(conn);
  }

  @Test
  public void Test3() throws SQLException {
    Connection conn = DriverManagerConnectionPool.getConnection();
    DriverManagerConnectionPool.releaseConnection(conn);
    conn.close();
    Connection conn2 = DriverManagerConnectionPool.getConnection();
    conn2.close();
  }
}
