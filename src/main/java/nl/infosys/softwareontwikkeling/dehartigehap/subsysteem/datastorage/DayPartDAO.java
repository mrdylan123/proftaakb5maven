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
public class DayPartDAO {

    public DayPartDAO() {}
    
    public DayPart loadDayPart(Date d, DayPartType dayPartType)
    {
        DayPart dp = null;
        
        String dateStr = DBUtils.toSQLString(d);
        
        ArrayList<DayPartEmployee> dpeList = new ArrayList<>();
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            // If a connection was successfully setup, execute the SELECT statement.
            String execStr = "SELECT * FROM daypart_employee WHERE date ='" + dateStr + 
                        "' AND dayparttype ='" + 
                    dayPartType.toString().toLowerCase() + "';";
            
            ResultSet resultset = connection.executeSQLSelectStatement(execStr);

            if(resultset != null)
            {
                try
                {
                    while(resultset.next())
                    {
                        Employee e = (new EmployeeDAO()).
                                loadEmployee(resultset.getString("employeeid"));
                        
                        PresenceStatus ps = PresenceStatus.valueOf(
                                resultset.getString("presencestatus").toUpperCase());
                        
                        DayPartEmployee dpe = new DayPartEmployee(e, ps);
                        dpeList.add(dpe);
                   }
                    dp = new DayPart(d, dayPartType, dpeList);
                }
                catch(SQLException e)
                {
                    System.out.println(e);
                    dp = null;
                }
            }
            // else an error occurred leave array list empty.

            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.closeConnection();
        }
        
        return dp;
    }
    
    public void saveDayPart(DayPart dp)
    {
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            String execStr = "INSERT INTO daypart VALUES(\'" + 
                    DBUtils.toSQLString(dp.getDate()) + "\',\'" + 
                    dp.getDayPartType().toString().toLowerCase() + "\');";
            
            connection.executeSQLInsertStatement(execStr);
            
            // Delete existing records for this DayPart in employee_daypart
            deleteDayPartEmployees(dp.getDate(), dp.getDayPartType());
            
            for( DayPartEmployee dpe : dp.getDpeList())
            {
                saveDayPartEmployee(dpe, dp.getDate(), dp.getDayPartType());
            }
        }
    }
    
    public void saveDayPartEmployee(DayPartEmployee dpe, Date d, DayPartType dpt)
    {
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            String execStr = "INSERT INTO daypart_employee(employeeid,"
                    + "date,dayparttype,presencestatus)"
                    + " VALUES('" +
                    dpe.getEmployee().getEmployeeId() + "','" + 
                    DBUtils.toSQLString(d) + "','" + 
                    dpt.toString().toLowerCase() + "','" +
                        dpe.getPresenceStatus().toString().toLowerCase() 
                        + "');";
                
               // System.out.println(execStr);
                connection.executeSQLInsertStatement(execStr);
        }
    }
    
    public void deleteDayPartEmployees(Date d, DayPartType dpt)
    {
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            String execStr = "DELETE FROM daypart_employee WHERE date='" + 
                    DBUtils.toSQLString(d) + "' AND dayparttype='" + 
                    dpt.toString().toLowerCase() + "';";
                
                connection.executeSQLInsertStatement(execStr);
                
                connection.closeConnection();
        }
    }
    
        public void deleteDayPartEmployee(Employee e, Date d, DayPartType dpt)
    {
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            String execStr = "DELETE FROM daypart_employee WHERE date='" + 
                    DBUtils.toSQLString(d) + "' AND dayparttype='" + 
                    dpt.toString().toLowerCase() + "' AND employeeid='" +  
                    e.getEmployeeId() + "';";
                
                connection.executeSQLInsertStatement(execStr);
                
                connection.closeConnection();
        }
    }
    
    public boolean checkExistsDayPartEmployee(Employee e, Date d, DayPartType dpt)
    {
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            // If a connection was successfully setup, execute the SELECT statement.
            String execStr = "SELECT * FROM daypart_employee WHERE date =\'" 
                    + DBUtils.toSQLString(d) + "\' AND dayparttype =\'" + 
                    dpt.toString().toLowerCase() + "\' AND employeeid=\'" +
                    e.getEmployeeId() + "\';";
            
            ResultSet resultset = connection.executeSQLSelectStatement(execStr);

            if(resultset != null)
            {
                try
                {
                    while(resultset.next())
                    {
                        return true;
                   }
                }
                catch(SQLException ex)
                {
                    System.out.println(ex);
                }
            }
            // else an error occurred leave array list empty.

            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.closeConnection();
        }
        
        return false;
    }
    
    public ArrayList<DayPart> loadDayPartsForEmployee(Employee e, Date date)
    {
        ArrayList<DayPart> dayParts = new ArrayList<>();
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            // If a connection was successfully setup, execute the SELECT statement.
            String execStr = String.format("SELECT * FROM daypart_employee WHERE"
                    + " employeeid='%d' AND date='%s';",
                    e.getEmployeeId(), DBUtils.toSQLString(date));
            
            ResultSet resultset = connection.executeSQLSelectStatement(execStr);

            if(resultset != null)
            {
                try
                {
                    while(resultset.next())
                    {
                       String dptStr = resultset.getString("dayparttype");
                       String dateStr = resultset.getString("date");
                       
                       DayPartType dpt = DayPartType.valueOf(dptStr.toUpperCase());
                       Date d = DBUtils.fromSQLString(dateStr);
                       
                       DayPart dp = (new DayPartDAO()).loadDayPart(d, dpt);
                       dayParts.add(dp);
                    }
                }
                catch(SQLException excpt)
                {
                    System.out.println(e);
                }
            }
            // else an error occurred leave array list empty.

            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.closeConnection();
        }
        
        return dayParts;
    
    }
    
    public ArrayList<DayPart> loadDayPartsForEmployee(Employee e, int limit)
    {
        ArrayList<DayPart> dayParts = new ArrayList<>();
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection())
        {
            // If a connection was successfully setup, execute the SELECT statement.
            String execStr = String.format("SELECT * FROM daypart_employee WHERE"
                    + " employeeid='%d' ORDER BY date ASC LIMIT %d;",
                    e.getEmployeeId(), limit);
            
            ResultSet resultset = connection.executeSQLSelectStatement(execStr);

            if(resultset != null)
            {
                try
                {
                    while(resultset.next())
                    {
                       String dptStr = resultset.getString("dayparttype");
                       String dateStr = resultset.getString("date");
                       
                       DayPartType dpt = DayPartType.valueOf(dptStr.toUpperCase());
                       Date d = DBUtils.fromSQLString(dateStr);
                       
                       DayPart dp = (new DayPartDAO()).loadDayPart(d, dpt);
                       dayParts.add(dp);
                    }
                }
                catch(SQLException excpt)
                {
                    System.out.println(e);
                }
            }
            // else an error occurred leave array list empty.

            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.closeConnection();
        }
        
        return dayParts;
    
    }
}
