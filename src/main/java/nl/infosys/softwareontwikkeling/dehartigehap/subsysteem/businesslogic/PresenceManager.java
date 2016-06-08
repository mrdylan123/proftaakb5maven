package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import java.util.List;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.DayPartDAO;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.EmployeeDAO;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPart;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

public class PresenceManager {
    private List<Employee> employees;
   
    public PresenceManager() {
        employees = (new EmployeeDAO()).loadEmployees();
    }
   
    public List<Employee> getEmployees() {
        return employees;
    }
    
    /**
    * returns list of DayParts for a given Employee, with a limit of max DayParts
    * returned
    * @param e Employee to return DayParts for
    * @param limit max limit of DayParts to return
    * @return ArrayList of Dayparts to return for Employee e with limit
    */
    public List<DayPart> getDayPartsForEmployee(Employee e, int limit) {
        return (new DayPartDAO()).loadDayPartsForEmployee(e, limit);
    }
}
