/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

   /**
   * Checks if an input date is reasonably valid, and that the input year has 
   * strange value (in the past or future)
   * @param day Integer day to check
   * @param month Integer Month to check
   * @param year Integer Year to check
   * @return true if the input date is considered valid, else false
   */
public class Utils {
    
    private Utils() {
    }
    
    public static boolean isDateValid(Integer day, Integer month, Integer year)
    {
        if (year == null || month == null || day == null)
        {
            return false;
        }
        
        if (day < 1 || day > 32 || month < 1 )
        {
            return false;
        }

        if (month > 12 || year < 2016 || year > 2100)
        {
            return false;
        }        
         
        return true;
    }
}
