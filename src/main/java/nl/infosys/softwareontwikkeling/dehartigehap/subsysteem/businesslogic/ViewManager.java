/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.*;
import java.util.ArrayList;

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
    public DayPart[] getDayPartsForDate(Date d)
    {
        DayPart[] dpArr = new DayPart[3];

        DayPartDAO dpDAO = new DayPartDAO();

        dpArr[0] = dpDAO.loadDayPart(d, DayPartType.MORNING);
        dpArr[1] = dpDAO.loadDayPart(d, DayPartType.AFTERNOON);
        dpArr[2] = dpDAO.loadDayPart(d, DayPartType.EVENING);

        return dpArr;
    }

}
