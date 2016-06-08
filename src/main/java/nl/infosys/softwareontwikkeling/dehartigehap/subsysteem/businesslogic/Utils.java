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
    private static final int MIN_DAY = 1;
    private static final int MIN_MONTH = 1;
    private static final int MIN_YEAR = 2015;
    private static final int MAX_DAY = 31;
    private static final int MAX_MONTH = 12;
    private static final int MAX_YEAR = 2020;
    
    private Utils() {
    }
    
    public static boolean isDateValid(Integer day, Integer month, Integer year) {
        if (checkNull(year, month, day)) {
            return false;
        }    
        
        if (checkDayMonthYear(day, month, year) ) {
            return false;
        }        
         
        return true;
    }

    private static boolean checkDayMonthYear(Integer day, Integer month, Integer year) {
        if (day < MIN_DAY || day > MAX_DAY || month < MIN_MONTH) {
            return true;
        }
        if (month > MAX_MONTH || year < MIN_YEAR || year > MAX_YEAR) {
            return true;
        }
        return false;
    }

    private static boolean checkNull(Integer year, Integer month, Integer day) {
        if (year == null || month == null || day == null) {
            return true;
        }
        return false;
    }
}
