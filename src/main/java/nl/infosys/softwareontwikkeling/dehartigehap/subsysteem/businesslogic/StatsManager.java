package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import java.util.ArrayList;
import java.util.List;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.EmployeeDAO;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DatabaseConnectionException;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

public class StatsManager {
    private List<Employee> employees;
    
    public StatsManager() throws DatabaseConnectionException {
        employees = new ArrayList<>();
        
        for (Employee e : (new EmployeeDAO()).loadEmployees())
        {
            if ("barmedewerker".equals(e.getFunction()))
            {
                employees.add(e);
            }
        }
    }
    
    public List<Employee> getEmployees() {
        return employees;
    }
        
    public int getAmountDrinksServed(Employee e) throws DatabaseConnectionException {
        return (new EmployeeDAO()).getAmountDrinksServed(e);
    }
    
    public int getAmountMealsServed(Employee e) throws DatabaseConnectionException {
        return (new EmployeeDAO()).getAmountMealsServed(e);
    }
}