/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.*;
import java.util.ArrayList;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author maikel
 */
public class PresentationManager {
    
    public PresentationManager() {}
    
    public DayPart[] OnRequestButtonPress(String dayStr, String monthStr, 
            String yearStr) throws DateInvalidException
    {
        try {
            DayPart[] dpArr = new DayPart[3];
            
            
            Integer day = Integer.parseInt(dayStr);
            Integer month = Integer.parseInt(monthStr);
            Integer year = Integer.parseInt(yearStr);

            if (Utils.isDateValid(day, month, year) == false)
            {
                throw new DateInvalidException();
            }

            Date d = new Date((int)day, (int)month, (int)year);
            DayPartDAO dpDAO = new DayPartDAO();

            dpArr[0] = dpDAO.loadDayPart(d, DayPartType.MORNING);
            dpArr[1] = dpDAO.loadDayPart(d, DayPartType.AFTERNOON);
            dpArr[2] = dpDAO.loadDayPart(d, DayPartType.EVENING);

            return dpArr;
        }
        catch(NumberFormatException nfe)
        {
            throw new DateInvalidException();
        }
    }

}
