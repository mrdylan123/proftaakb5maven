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
    
    public static Date fromSQLString(String s)
    {
        String[] tokens = s.split("[-]");
        
        int year = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int day = Integer.parseInt(tokens[2]);
        
        return new Date(day, month, year);
    }
    
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
