package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import java.util.ArrayList;
import java.util.List;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.EmployeeDAO;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

public class StatsManager {
    private List<Employee> employees;
    
    public StatsManager() {
        employees = new ArrayList<>();
        
        for (Employee e : (new EmployeeDAO()).loadEmployees())
        {
            if (e.getFunction().equals("barmedewerker"))
            {
                employees.add(e);
            }
        }
    }
    
    public List<Employee> getEmployees() {
        return employees;
    }
        
    public int getAmountDrinksServed(Employee e) {
        return (new EmployeeDAO()).getAmountDrinksServed(e);
    }
    
    public int getAmountMealsServed(Employee e) {
        return (new EmployeeDAO()).getAmountMealsServed(e);
    }
}