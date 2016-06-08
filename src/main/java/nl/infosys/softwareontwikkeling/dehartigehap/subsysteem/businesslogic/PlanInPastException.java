/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

/**
 * Exception thrown by DAO code when an attempt was made to insert an employee
 * planning for a date in the past
 */
public class PlanInPastException extends Exception 
{
    public PlanInPastException() { 
        super(); 
    }
    
    public PlanInPastException(String message) { 
        super(message); 
    }
    
    public PlanInPastException(String message, Throwable cause) {
        super(message, cause); 
    }
    
    public PlanInPastException(Throwable cause) {
        super(cause); 
    }
}
