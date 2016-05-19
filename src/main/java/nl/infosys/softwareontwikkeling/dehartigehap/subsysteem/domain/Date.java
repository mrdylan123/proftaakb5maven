/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain;

/**
 *
 * @author J. Bouman
 */
public class Date {
    private int day, month, year;
    
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public int getDay() {
        return day;
    }
    
    public int getMonth() {
        return month;
    }
    
    public int getYear() {
        return year;
    }
    
    public static Date fromSQLString(String s)
    {
        String[] tokens = s.split("[-]");
        
        int year = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int day = Integer.parseInt(tokens[2]);
        
        return new Date(day, month, year);
    }
    
    public String toSQLString()
    {
        String s = "";
        s += year;
        s += "-";
        s += month;
        s += "-";
        s += day;
        
        return s;
    }
}
