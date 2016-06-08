/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import java.util.ArrayList;
import java.util.List;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.EmployeeDAO;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

/**
 *
 * @author dyl
 */
public class StatsManager {
    private List<Employee> employees;
    
    public StatsManager() {
        employees = (new EmployeeDAO()).loadEmployees();
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