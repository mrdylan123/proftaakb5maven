package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.*;

/**
 *
 * @author maikel
 */
public class ViewManager {
    
    public ViewManager() {
    }
    
   /**
   * gets the morning, afternoon and evening DayParts for a date
   * database
   * @param d the date to grab the DayParts list for
   * for
   * @return List of DayParts for the given date
   */
    public DayPart[] getDayPartsForDate(Date d) {
        int NUMOFDAYPARTS = 3;
        DayPart[] dpArr = new DayPart[NUMOFDAYPARTS];

        DayPartDAO dpDAO = new DayPartDAO();

        dpArr[0] = dpDAO.loadDayPart(d, DayPartType.MORNING);
        dpArr[1] = dpDAO.loadDayPart(d, DayPartType.AFTERNOON);
        int TWO = 2;
        dpArr[TWO] = dpDAO.loadDayPart(d, DayPartType.EVENING);

        return dpArr;
    }

}
