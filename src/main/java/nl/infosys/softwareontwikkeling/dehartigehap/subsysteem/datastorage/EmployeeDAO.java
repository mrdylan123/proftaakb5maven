/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.*;

/**
 *
 * @author maikel
 */
public class EmployeeDAO {
    
    public EmployeeDAO() {
    }
    
   /**
   * Returns the list of employees from the database (all records
   * in employee table)
   * @return ArrayList of Employees loaded from database
   */
    public List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection()) {
            // If a connection was successfully setup, execute the SELECT statement.
            ResultSet resultset = connection.executeSQLSelectStatement(
                "SELECT * FROM view_planning_employee;");

            if(resultset != null) {
                try {
                    while(resultset.next()) {
                        Employee e = loadEmployee(resultset.getString("employeeid"));
                        
                        employees.add(e);
                   }
                } catch(SQLException e) {
                    employees.clear();
                }
            }
            // else an error occurred leave array list empty.

            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.closeConnection();
        }
        
        return employees;
    }

   /**
   * Returns an employee by employeeId
   * @param employeeId employeeId for the employee to load
   * @return an Employee, or null if the Employee wasn't found
   */
    public Employee loadEmployee(String employeeId) {
        Employee e = null;
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection()) {
            // If a connection was successfully setup, execute the SELECT statement.
            ResultSet resultset = connection.executeSQLSelectStatement(
                "SELECT * FROM view_planning_employee WHERE employeeid = \"" 
                        + employeeId + "\";");

            if(resultset != null) {
                try {
                    // The membershipnumber for a member is unique, so in case the
                    // resultset does contain data, we need its first entry.
                    if(resultset.next()) {
                        int employee_Id = resultset.getInt("employeeid");                      
                        String function = resultset.getString("function");
                        String name = resultset.getString("name");
                        
                        
                        e = new Employee(employee_Id, name, function);
                        
                    }
                }
                catch(SQLException ex) {
                    e = null;
                }
            }
            // else an error occurred leave 'member' to null.
            
            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.closeConnection(); 
        }
        return e;
    }
  
   /**
   * Returns an amount served for an employee by database table
   * (helper function to be used with the 'mealorder' and 'drinkorder' tables)
   * @param e Employee to return amount served for
   * @param table database table to retrieve amount served from
   * @return amount served for database table and Employee
   */
    private int getAmountServed(Employee e, String table) {
        int count = 0;
        
        DatabaseConnection connection = new DatabaseConnection();
        
        if(connection.openConnection()) {
            // If a connection was successfully setup, execute the SELECT statement.
            ResultSet resultset = connection.executeSQLSelectStatement(
                "SELECT * FROM " + table + " WHERE employeeid='" 
                        + e.getEmployeeId() + "' AND status='ready';");

            try {
                count = resultset.last() ? resultset.getRow() : 0;
            } catch(SQLException sqlexcept) {
                connection.closeConnection();
            }
        }
        
        return count;
    }
    
   /**
   * Returns amount of meals served by an Employee
   * @param e Employee to return amount served meals for
   * @return amount of meals served by Employee
   */
    public int getAmountMealsServed(Employee e) {
        return getAmountServed(e, "view_planning_mealorder");
    }
    
   /**
   * Returns amount of drinks served by an Employee
   * @param e Employee to return amount served drinks for
   * @return amount of drinks served by Employee
   */
    public int getAmountDrinksServed(Employee e) {
        return getAmountServed(e, "view_planning_drinkorder");
    }
    
}