package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import java.sql.SQLException;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.*;
import java.util.ArrayList;
import java.util.List;

public class InputManager {
    private List<Employee> employees;
    
    public InputManager() {
        employees = (new EmployeeDAO()).loadEmployees();
    }
    
    public List<Employee> getEmployees() {
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
            DayPartType dpt, Date d) throws PlanInPastException {
        try {
            DayPartDAO dpDAO = new DayPartDAO();

            DayPart dp = dpDAO.loadDayPart(d, dpt);

            // We're manually adding DayPartEmployees to the dpeList which is
            // normally only loaded directly from the database (so there are no
            // duplicate DayPartEmployees with the same name in this list,
            // because we're manually adding DayPartEmployees we first need to
            // make sure there is only ONE DayPartEmployee with the same name
            // in the list, so we first try to remove the DayPartEmployee we
            // will then add, to make sure there are no duplicate entries.
            
            if (e1 != null) {
                dp.removeByNameFromDpeList(e1.getName());
                dp.getDpeList().add(new DayPartEmployee(e1, 
                                                    PresenceStatus.PLANNED));
            }
            if (e2 != null) { 
                dp.removeByNameFromDpeList(e2.getName());
                dp.getDpeList().add(new DayPartEmployee(e2, 
                                                    PresenceStatus.PLANNED));
            }
            if (e3 != null) {
                dp.removeByNameFromDpeList(e3.getName());
                dp.getDpeList().add(new DayPartEmployee(e3, 
                                                    PresenceStatus.PLANNED));
            }

            dpDAO.saveDayPart(dp);
            
        } catch(PlanInPastException pipe) {
            throw pipe;
        }
    }
}