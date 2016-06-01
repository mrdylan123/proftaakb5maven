/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.UserLoginDAO;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.BCrypt;

/**
 *
 * @author maikel
 */
public class UserLoginManager {

    public UserLoginManager() {
    }
    
    public void registerUser(String username, String Password)
    {
        (new UserLoginDAO()).saveUserLogin(username, Password);
    }
    
    public boolean checkPassword(String username, String password)
    {
        String hashedPasswordFromDB = (new UserLoginDAO()).
                                        getHashedPasswordForUsername(username);
                
        if (hashedPasswordFromDB == null ) return false;
        
        return BCrypt.checkpw(password, hashedPasswordFromDB);
    }
}
