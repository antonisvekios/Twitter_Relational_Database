package database;

import java.sql.*;

public class DBUtils {

  private final String url;
  private final String user;
  private final String password;
  private Connection con = null;

  public DBUtils(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
    this.con = getConnection();
  }

  public Connection getConnection() {
    if (con == null) {
      try {
        con = DriverManager.getConnection(url, user, password);
        return con;
      } catch (SQLException e) {
        System.err.println(e.getMessage());
        System.exit(1);
      }
    }

    return con;
  }

  public void closeConnection() {
    try {
      con.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }

  public void insertOneRecord(String insertSQL) {
    try {
      // get connection and initialize statement
      Connection con = getConnection();
      Statement stmt = con.createStatement();

      stmt.executeUpdate(insertSQL, Statement.RETURN_GENERATED_KEYS);
      stmt.close();
    } catch (SQLException e) {
      System.err.println("ERROR: Could not insert record: " + insertSQL);
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}


