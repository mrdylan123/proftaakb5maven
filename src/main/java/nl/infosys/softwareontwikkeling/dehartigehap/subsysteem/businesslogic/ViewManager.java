package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.*;

public class ViewManager {
    private static final int TWO = 2;
    private static final int NUMOFDAYPARTS = 3;
    
    public ViewManager() {
    }
    
   /**
   * gets the morning, afternoon and evening DayParts for a date
   * database
   * @param d the date to grab the DayParts list for
   * @throws DatabaseConnectionException when connection can't be opened
   * @return List of DayParts for the given date
   */
    public DayPart[] getDayPartsForDate(Date d) 
            throws DatabaseConnectionException {
        DayPart[] dpArr = new DayPart[NUMOFDAYPARTS];

        DayPartDAO dpDAO = new DayPartDAO();

        dpArr[0] = dpDAO.loadDayPart(d, DayPartType.MORNING);
        dpArr[1] = dpDAO.loadDayPart(d, DayPartType.AFTERNOON);
        dpArr[TWO] = dpDAO.loadDayPart(d, DayPartType.EVENING);

        return dpArr;
    }

}
