package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.UserLoginDAO;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.BCrypt;

public class UserLoginManager {

    public UserLoginManager() {
    }
    
   /**
   * saves into the database (plans in) up to three employees into a day part
   * @param username the username to save
   * @param password he password to save
   * @return Nothing
   */
    public void registerUser(String username, String password) {
        try {
            (new UserLoginDAO()).saveUserLogin(username, password);
        } catch (SQLException ex) {
            Logger.getLogger(UserLoginManager.class.getName()).log(Level.SEVERE, 
                                    null, ex);
        }
    }
   
   /**
   * checks if a password for a user matches the hashed password from the
   * database
   * @param username the username to check for
   * @param password the password to check for
   * @return true if the password matches the hashed password from database,
   * else false
   */
    public boolean checkPassword(String username, String password) {
        String hashedPasswordFromDB = (new UserLoginDAO()).
                                        getHashedPasswordForUsername(username);
                
        if (hashedPasswordFromDB == null ) {
            return false;
        }
        
        return BCrypt.checkPassword(password, hashedPasswordFromDB);
    }
}
