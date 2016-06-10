package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DatabaseConnectionException;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.UserLoginDAO;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.BCrypt;

public class UserLoginManager {

    public UserLoginManager() {
    }
    
   /**
   * saves into the database (plans in) up to three employees into a day part
   * @param username the username to save
   * @param password the passsword to save
   * @throws DatabaseConnectionException if connection could not be opened
   * @return Nothing
   */
    public void registerUser(String username, String password) 
            throws DatabaseConnectionException {
        
        (new UserLoginDAO()).saveUserLogin(username, password);
    }
   
   /**
   * checks if a password for a user matches the hashed password from the
   * database
   * @param username the username to check for
   * @param password the password to check for
   * @throws DatabaseConnectionException if connection could not be opened
   * @return true if the password matches the hashed password from database,
   * else false
   */
    public boolean checkPassword(String username, String password) 
            throws DatabaseConnectionException {
        String hashedPasswordFromDB = (new UserLoginDAO()).
                                        getHashedPasswordForUsername(username);
                
        if (hashedPasswordFromDB == null ) {
            return false;
        }
        
        return BCrypt.checkPassword(password, hashedPasswordFromDB);
    }
}
