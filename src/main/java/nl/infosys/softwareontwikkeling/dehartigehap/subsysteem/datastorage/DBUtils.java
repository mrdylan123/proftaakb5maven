/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage;

import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Date;

/**
 *
 * @author maikel
 */
public class DBUtils {
    
    private DBUtils() {
    }
    
   /**
   * Returns a Date object from a SQL date string
   * @param s the SQL string to return a Date object for
   * @return the date object for the SQL string
   */
    public static Date fromSQLString(String s)
    {
        String[] tokens = s.split("[-]");
        
        int year = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int day = Integer.parseInt(tokens[2]);
        
        return new Date(day, month, year);
    }
 
   /**
   * Returns a SQL style date string from a Date object
   * @param d the date object to return a SQL style date string for
   * @return the SQL style string for the Date object
   */
    public static String toSQLString(Date d)
    {
        String s = "";
        s += d.getYear();
        s += "-";
        s += d.getMonth();
        s += "-";
        s += d.getDay();
        
        return s;
    }
}
