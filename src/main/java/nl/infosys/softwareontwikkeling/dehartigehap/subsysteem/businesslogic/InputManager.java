/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import java.sql.SQLException;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.*;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author J. Bouman
 */
public class InputManager {
    private ArrayList<Employee> employees;
    
    public InputManager() 
    {
        employees = (new EmployeeDAO()).loadEmployees();
    }
    
    public ArrayList<Employee> getEmployees()
    {
        return employees;
    }
    
    public void planEmployeesIntoDayPart(Employee e1, Employee e2, Employee e3, 
            int dayPartSelectedIndex, String dayStr, String monthStr, 
            String yearStr) throws DateInvalidException, SQLException, PlanInPastException
    {

        try {
            Integer day = Integer.parseInt(dayStr);
            Integer month = Integer.parseInt(monthStr);
            Integer year = Integer.parseInt(yearStr);

            if (Utils.isDateValid(day, month, year) == false)
            {
                throw new DateInvalidException();
            }

            DayPartType dpt = DayPartType.values()[dayPartSelectedIndex];

            DayPartDAO dpDAO = new DayPartDAO();

            DayPart dp = dpDAO.loadDayPart(new Date((int)day, (int)month, 
                                                (int)year), dpt);

            if (e1 != null) dp.getDpeList().add(new DayPartEmployee(e1, 
                                                    PresenceStatus.PLANNED));
            if (e2 != null) dp.getDpeList().add(new DayPartEmployee(e2, 
                                                    PresenceStatus.PLANNED));
            if (e3 != null) dp.getDpeList().add(new DayPartEmployee(e3, 
                                                    PresenceStatus.PLANNED));

            dpDAO.saveDayPart(dp);
        }
        catch(NumberFormatException nfe)
        {
            throw new DateInvalidException();
        }
        catch(SQLException sqle)
        {
            throw sqle;
        }
        catch(PlanInPastException pipe)
        {
            throw pipe;
        }
    }
}