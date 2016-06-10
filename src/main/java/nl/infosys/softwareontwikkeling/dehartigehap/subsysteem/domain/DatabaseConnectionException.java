/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain;

/**
 *
 * @author maikel
 */
public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException() { 
        super(); 
    }
    
    public DatabaseConnectionException(String message) { 
        super(message); 
    }
    
    public DatabaseConnectionException(String message, Throwable cause) { 
        super(message, cause); 
    }
    public DatabaseConnectionException(Throwable cause) { 
        super(cause); 
    }
}