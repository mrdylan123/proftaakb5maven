/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.*;

/**
 *
 * @author maikel
 */
public class EmployeeDAO {
    
    public EmployeeDAO() {}
    
    public ArrayList<Employee> loadEmployees()
    {
        ArrayList<Employee> employees = new ArrayList<>();
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            // If a connection was successfully setup, execute the SELECT statement.
            ResultSet resultset = connection.executeSQLSelectStatement(
                "SELECT * FROM employee;");

            if(resultset != null)
            {
                try
                {
                    while(resultset.next())
                    {
                        Employee e = loadEmployee(resultset.getString("employeeid"));
                        
                        employees.add(e);
                   }
                }
                catch(SQLException e)
                {
                    System.out.println(e);
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

    public Employee loadEmployee(String employeeId)
    {
        Employee e = null;
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            // If a connection was successfully setup, execute the SELECT statement.
            ResultSet resultset = connection.executeSQLSelectStatement(
                "SELECT * FROM employee WHERE employeeid = \"" + employeeId + "\";");

            if(resultset != null)
            {
                try
                {
                    // The membershipnumber for a member is unique, so in case the
                    // resultset does contain data, we need its first entry.
                    if(resultset.next())
                    {
                        int wage = resultset.getInt("wage");
                        int employeeid = resultset.getInt("employeeid");
                        
                        String zipcode = resultset.getString("zipcode");
                        String name = resultset.getString("name");
                        String nationality = resultset.getString("nationality");
                        String street = resultset.getString("street");
                        String address = resultset.getString("address");
                        String city = resultset.getString("city");
                        String phonenumber = resultset.getString("phonenumber");
                        String email = resultset.getString("email");
                        
                        // date strings
                        String employmentdatestr = resultset.getString("employmentdate");
                        String dateofbirthstr = resultset.getString("dateofbirth");
                        
                        // To our Date class
                        
                        Date employmentdate = Date.fromSQLString(employmentdatestr);
                        Date dateofbirth = Date.fromSQLString(dateofbirthstr);
                        
                        e = new Employee(name, email, null /*loginName*/,
                        null /*loginPassword*/, street, address, city, phonenumber,
                        nationality, employeeid, wage, zipcode, dateofbirth, employmentdate);
                        
                    }
                }
                catch(SQLException ex)
                {
                    System.out.println(ex);
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
}
