package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

/**
 * Exception which is thrown when an invalid date has been encountered
 */
public class DateInvalidException extends Exception {
    public DateInvalidException() { 
        super(); 
    }
    
    public DateInvalidException(String message) { 
        super(message); 
    }
    
    public DateInvalidException(String message, Throwable cause) { 
        super(message, cause); 
    }
    public DateInvalidException(Throwable cause) { 
        super(cause); 
    }
}
