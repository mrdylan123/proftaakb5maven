/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.BCrypt;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Date;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartEmployee;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartType;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

/**
 *
 * @author maikel
 */
public class UserLoginDAO {
    public UserLoginDAO() {}
    
    public String getHashedPasswordForUsername(String username)
    {
        
        String s = null;
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            // If a connection was successfully setup, execute the SELECT statement.
            ResultSet resultset = connection.executeSQLSelectStatement(
                "SELECT password FROM userlogin WHERE username = \"" + username + "\";");

            if(resultset != null)
            {
                try
                {
                    if(resultset.next())
                    {
                         s = resultset.getString("password");
                    }
                }
                catch(SQLException ex)
                {
                    System.out.println(ex);
                    s = null;
                }
            }
            // else an error occurred leave 'member' to null.
            
            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.closeConnection(); 
        }
        return s;
        
    }
    
    public void saveUserLogin(String username, String password)
    {
        String hashedpassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            String execStr = "INSERT INTO userlogin(username,"
                    + "password) VALUES('" + username + "','"  + hashedpassword + "');";
                
               // System.out.println(execStr);
                connection.executeSQLInsertStatement(execStr);
        }
    }
}
