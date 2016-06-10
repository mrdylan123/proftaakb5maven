package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage;

import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DatabaseConnectionException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.BCrypt;


public class UserLoginDAO {
    private static final int HASHSIZE = 12;
    
    public UserLoginDAO() {
    }
    
   /**
   * Returns a hashed password for the given username
   * @param username the username to return the hashed password for
   * @return the hashed password
   * @throws DatabaseConnectionException if connection can't be opened
   */
    public String getHashedPasswordForUsername(String username) 
            throws DatabaseConnectionException {       
        String s = null;
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection()) {
            // If a connection was successfully setup, execute the SELECT statement.
            ResultSet resultset = connection.executeSQLSelectStatement(
                "SELECT password FROM userlogin WHERE username = \"" + username + "\";");

            if(resultset == null) {
                connection.closeConnection();
                return s;
            }
            
            try {
                if(resultset.next()) {
                    s = resultset.getString("password");
                }} catch (SQLException ex) {
                Logger.getLogger(UserLoginDAO.class.getName()).log(
                                                    Level.OFF, null, ex);
                s = null;
            }         
            connection.closeConnection();          
        } else {
            throw new DatabaseConnectionException();
        }
        return s;       
    }
  
   /**
   * Saves a user login (username + password combo), the password is hashed
   * before storing in the database
   * @param username username to save
   * @param password password to save
   * @throws DatabaseConnectionException when connection can not be opened
   */
    public void saveUserLogin(String username, String password) 
            throws DatabaseConnectionException {
        
        String hashedpassword = BCrypt.hashPassword(password, BCrypt.generateSalt(HASHSIZE));
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection()) {
            try {
                String execStr = "INSERT INTO userlogin(username,"
                    + "password) VALUES('" + username + "','"  + hashedpassword + "');";

                connection.executeSQLInsertStatement(execStr);
            } catch (SQLException ex) {
                    Logger.getLogger(UserLoginDAO.class.getName()).log(Level.OFF, null, ex);
                }
            
            connection.closeConnection();
        } else {
            throw new DatabaseConnectionException();
        }
    }
}
