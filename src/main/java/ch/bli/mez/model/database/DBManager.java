package ch.bli.mez.model.database;

import java.sql.*;

public class DBManager {

  private static final DBManager dbManager = new DBManager();
  private static Connection connection;
  private static final String DB_PATH = "test.db";
  private static final String DB_DRIVER = "jdbc:sqlite";

  private DBManager() {
  }

  public static DBManager getInstance() {
    return dbManager;
  }
  
  private void initDBConnection() throws SQLException{
    if (connection != null)
      return;
    connection = DriverManager.getConnection(DB_DRIVER+":"+DB_PATH);
  }

  public ResultSet query(String sqlStatement) {
    try {
      initDBConnection();
      Statement statement = connection.createStatement();
      ResultSet results = statement.executeQuery( sqlStatement );
      results.close();
      statement.close();
      connection.close();
      return results;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public int update(String sqlStatement) {
    try {
      initDBConnection();
      Statement statement = connection.createStatement();
      Integer result = statement.executeUpdate( sqlStatement );
      statement.close();
      connection.close();
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }
}