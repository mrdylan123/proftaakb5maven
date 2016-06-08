/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

/**
 * Exception which is thrown when an invalid date has been encountered
 */
public class DateInvalidException extends Exception 
{
    public DateInvalidException() { super(); }
    public DateInvalidException(String message) { super(message); }
    public DateInvalidException(String message, Throwable cause) { super(message, cause); }
    public DateInvalidException(Throwable cause) { super(cause); }
}
