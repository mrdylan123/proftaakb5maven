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
       /**
   * saves into the database (plans in) up to three employees into a day part
   * @param e1 Employee to save (can be null)
   * @param e1 Employee to save (can be null)
   * @param e1 Employee to save (can be null)
   * @param dpt DayPartType to save for
   * @param d Date to save for
   * @throws PlanInPastException when attempting to plan in employee(s) for a 
   * past date
   * @throws SQLException for other SQL-related exceptions that might have 
   * occurred
   * @return Nothing
   */
    public void planEmployeesIntoDayPart(Employee e1, Employee e2, Employee e3, 
            DayPartType dpt, Date d) throws SQLException, PlanInPastException
    {
        try
        {
            DayPartDAO dpDAO = new DayPartDAO();

            DayPart dp = dpDAO.loadDayPart(d, dpt);

            if (e1 != null) dp.getDpeList().add(new DayPartEmployee(e1, 
                                                    PresenceStatus.PLANNED));
            if (e2 != null) dp.getDpeList().add(new DayPartEmployee(e2, 
                                                    PresenceStatus.PLANNED));
            if (e3 != null) dp.getDpeList().add(new DayPartEmployee(e3, 
                                                    PresenceStatus.PLANNED));

            dpDAO.saveDayPart(dp);
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