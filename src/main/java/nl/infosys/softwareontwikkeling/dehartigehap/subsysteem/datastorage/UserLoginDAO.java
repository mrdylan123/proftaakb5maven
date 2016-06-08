package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.BCrypt;


public class UserLoginDAO {
    private static final int HASHSIZE = 12;
    
    public UserLoginDAO() {
    }
    
   /**
   * Returns a hashed password for the given username
   * @param username the username to return the hashed password for
   * @return the hashed password
   */
    public String getHashedPasswordForUsername(String username) {
        
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
                }
            } catch(SQLException ex) {
                s = null;
            }
        }
        // else an error occurred leave 'member' to null.

        // We had a database connection opened. Since we're finished,
        // we need to close it.
        connection.closeConnection(); 
        
        return s;
        
    }
  
   /**
   * Saves a user login (username + password combo), the password is hashed
   * before storing in the database
   * @param username username to save
   * @param password password to save
   * @return Nothing
   * @throws SQLException when an SQL exception occurs
   */
    public void saveUserLogin(String username, String password) 
            throws SQLException {
        
        String hashedpassword = BCrypt.hashpw(password, BCrypt.gensalt(HASHSIZE));
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection()) {
            try {
                String execStr = "INSERT INTO userlogin(username,"
                    + "password) VALUES('" + username + "','"  + hashedpassword + "');";
                
                connection.executeSQLInsertStatement(execStr);
            } catch(SQLException sqle) {
                throw sqle;
            }
            
            connection.closeConnection();
        }
    }
}
