package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    
    private Connection connection;
    
    // The Statement object has been defined as a field because some methods
    // may return a ResultSet object. If so, the statement object may not
    // be closed as you would do when it was a local variable in the query
    // execution method.
    private Statement statement;
    
    public DatabaseConnection() {
        connection = null;
        statement = null;
    }
    
   /**
   * opens connection with database
   * @return true if the connection was successfully opened,
   *  false otherwise
   */
    public boolean openConnection() {
        boolean result = false;

        if(connection == null) {
            try {   
                // Try to create a connection with the library database
                String url = "" + "jdbc:mysql://164.1" + "32.101.65:3306/ivp4b";
                String username = "" + "planner";
                String password = "" + "planner";
                
                connection = DriverManager.getConnection(
                    url , username, password);

                if(connection != null) {
                    statement = connection.createStatement();
                }
                
                result = true;
            } catch(SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(
                                                    Level.OFF, null, ex);
                result = false;
            }
        } else {
            // A connection was already initalized.
            result = true;
        }
        
        return result;
    }
    
   /**
   * Checks if a connection with the database is open
   * @return true if a connection with the database is open, false otherwise
   */
    public boolean connectionIsOpen() {
        boolean open = false;
        
        if(connection != null && statement != null) {
            try {
                open = !connection.isClosed() && !statement.isClosed();
            } catch(SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(
                                                    Level.OFF, null, ex);
                open = false;
            }
        }
        // Else, at least one the connection or statement fields is null, so
        // no valid connection.
        
        return open;
    }
    
   /**
   * closes the connection with the database
   */
    public void closeConnection() {
        try {
            statement.close();
            
            // Close the connection
            connection.close();
        } catch(Exception ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(
                                                    Level.OFF, null, ex);
        }
    }
    
   /**
   * Executes a given SQL Select statement
   * @param query the SQL Select statement to executes
   * @return an ResultSet object with the result of the executed select query,
   * or null if the SQL Select statement couldn't be executed
   */
    public ResultSet executeSQLSelectStatement(String query) {
        ResultSet resultset = null;
        
        // First, check whether a some query was passed and the connection with
        // the database.
        if(query != null && connectionIsOpen()) {
            // Then, if succeeded, execute the query.
            try {
                resultset = statement.executeQuery(query);
            } catch(SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(
                                                    Level.OFF, null, ex);
                resultset = null;
            }
        }
        
        return resultset;
    }
    
   /**
   * Executes a given SQL Delete statement
   * @param query the SQL Select statement to executes
   * @return true if iit was possible to successfully execute the SQL delete
   * statement, otherwise false
   */
    public boolean executeSQLDeleteStatement(String query) {
        boolean result = false;
        
        // First, check whether a some query was passed and the connection with
        // the database.
        if(query != null && connectionIsOpen()) {
            // Then, if succeeded, execute the query.
            try {
                statement.executeUpdate(query);
                result = true;
            } catch(SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(
                                                    Level.OFF, null, ex);
                result = false;
            }
        }
        
        return result;
    }
    
   /**
   * Executes a given SQL Insert statement
   * @param query the SQL Insert statement to executes
   * @return an ResultSet object with the result of the executed Insert query,
   * or null if the SQL Select statement couldn't be executed
   * @throws SQLException if a SQL-related exception occurs
   */
    public ResultSet executeSQLInsertStatement(String query) 
            throws SQLException {
        ResultSet rs = null;
        
        // First, check whether a some query was passed and the connection with
        // the database.
        if(query != null && connectionIsOpen()) {
            // Then, if succeeded, execute the query.
            try {
                statement.execute(query);
            } catch(SQLException e) {
                throw e;
            }
        }
        
        return rs;
    }
    
    public Connection getConnection() {
        return connection;
    }
}