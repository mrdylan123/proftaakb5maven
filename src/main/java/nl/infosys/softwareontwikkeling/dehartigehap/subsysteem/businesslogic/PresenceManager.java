/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import java.util.ArrayList;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.EmployeeDAO;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

public class PresenceManager {
    private ArrayList<Employee> employees;
   
    public PresenceManager()
    {
        employees = (new EmployeeDAO()).loadEmployees();
    }
   
    public ArrayList<Employee> getEmployees()
    {
        return employees;
    }
}
