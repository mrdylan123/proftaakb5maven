package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage;

import java.util.List;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.*;


public class DayPartDAO {

    public DayPartDAO() {
    }
    
   /**
   * returns a DayPart from database by Date and DayPartType
   * @param d Date for the DayPart to load from database
   * @param dayPartType DayPartType for DayPart to load from database
   * @throws DatabaseConnectionException if connection could not be opened
   * @return DayPart loaded from database for given Date and DayPartType
   */
    public DayPart loadDayPart(Date d, DayPartType dayPartType) throws DatabaseConnectionException {
        DayPart dp = null;
        
        String dateStr = DBUtils.toSQLString(d);
        
        List<DayPartEmployee> dpeList = new ArrayList<>();
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection()) {
            // If a connection was successfully setup, execute the SELECT statement.
            String execStr = "SELECT * FROM daypart_employee WHERE date ='" + dateStr + 
                        "' AND dayparttype ='" + 
                    dayPartType.toString().toLowerCase() + "';";
            
            ResultSet resultset = connection.executeSQLSelectStatement(execStr);

            if(resultset == null) {
                connection.closeConnection();
                return dp;
            }
            try {
                while(resultset.next()) {
                    Employee e = (new EmployeeDAO()).
                            loadEmployee(resultset.getString("employeeid"));

                    PresenceStatus ps = PresenceStatus.valueOf(
                            resultset.getString("presencestatus").toUpperCase());

                    DayPartEmployee dpe = new DayPartEmployee(e, ps);
                    dpeList.add(dpe);
               }
                dp = new DayPart(d, dayPartType, dpeList);
            } catch(SQLException ex) {
                Logger.getLogger(DayPartDAO.class.getName()).log(
                                                    Level.OFF, null, ex);
                dp = null;
            }
        } else {
            throw new DatabaseConnectionException();
        }           
        return dp;
    }
    
   /**
   * saves a DayPart into the database
   * @param dp DayPart to save into the database
   * @throws PlanInPastException when trying to save a DayPart which has 
   * DayPartEmployees in its DpeList who are planned in for a past date
   * @throws SQLException SQL exceptions other than PlanInPastException
   * @throws DatabaseConnectionException if connection could not be opened
   * @return Nothing
   */
    public void saveDayPart(DayPart dp) throws DatabaseConnectionException {
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection()) {
            try {
                String execStr = "INSERT INTO daypart VALUES(\'" + 
                        DBUtils.toSQLString(dp.getDate()) + "\',\'" + 
                        dp.getDayPartType().toString().toLowerCase() + "\');";

                connection.executeSQLInsertStatement(execStr);

            } catch(SQLException ex){
                Logger.getLogger(DayPartDAO.class.getName()).log(
                                                    Level.OFF, null, ex);
            }
            
            try {
                // Delete existing records for this DayPart in employee_daypart
                deleteDayPartEmployees(dp.getDate(), dp.getDayPartType());

                for( DayPartEmployee dpe : dp.getDpeList()) {
                    saveDayPartEmployee(dpe, dp.getDate(), dp.getDayPartType());
                }
            } catch(SQLException ex){
                Logger.getLogger(DayPartDAO.class.getName()).log(
                                                    Level.OFF, null, ex);
            }
            
            connection.closeConnection();
        } else {
            throw new DatabaseConnectionException();
        }      
    }
    
    /**
   * saves a DayPartEmployee into the database by Date and DayPartType
   * @param dpe DayPartEmployee to save into the database
   * @param d Date to save for
   * @param dpt DayPartType to save for
   * @throws PlanInPastException when trying to save a DayPart which has 
   * DayPartEmployees in its DpeList who are planned in for a past date
   * @throws SQLException SQL exceptions other than PlanInPastException
   * @throws DatabaseConnectionException if connection could not be opened
   * @return Nothing
   */
    public void saveDayPartEmployee(DayPartEmployee dpe, Date d, DayPartType dpt)
            throws DatabaseConnectionException {
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection()) {
            try {
                String execStr = "INSERT INTO daypart_employee(employeeid,"
                        + "date,dayparttype,presencestatus)"
                        + " VALUES('" +
                        dpe.getEmployee().getEmployeeId() + "','" + 
                        DBUtils.toSQLString(d) + "','" + 
                        dpt.toString().toLowerCase() + "','" +
                            dpe.getPresenceStatus().toString().toLowerCase() 
                            + "');";

                    connection.executeSQLInsertStatement(execStr);
            } catch(SQLException ex) {
                Logger.getLogger(DayPartDAO.class.getName()).log(
                                                    Level.OFF, null, ex);
            }
            
            connection.closeConnection();
        } else {
            throw new DatabaseConnectionException();
        }      
    }
    
   /**
   * delete all the planned employees on a daypart 
   * @param d Date to delete for
   * @param dpt DayPartType to delete for
   * @throws SQLException when SQL errors occur
   * @throws DatabaseConnectionException if connection could not be opened
   * @return Nothing
   */
    public void deleteDayPartEmployees(Date d, DayPartType dpt) 
            throws DatabaseConnectionException {
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection()) {
            try {
                String execStr = "DELETE FROM daypart_employee WHERE date='" + 
                        DBUtils.toSQLString(d) + "' AND dayparttype='" + 
                        dpt.toString().toLowerCase() + "';";
                
                connection.executeSQLInsertStatement(execStr);
            } catch(SQLException sqle) {
                Logger.getLogger(DayPartDAO.class.getName()).log(
                                                    Level.OFF, null, ex);
            }
                
            connection.closeConnection();
        } else {
            throw new DatabaseConnectionException();
        }      
    }
    
   /**
   * deletes a specific employees on a daypart
   * @param e Employee to delete
   * @param d Date to delete for
   * @param dpt DayPartType to delete for
   * @throws SQLException when SQL errors occur
   * @throws DatabaseConnectionException if connection could not be opened
   * @return Nothing
   */
    public void deleteDayPartEmployee(Employee e, Date d, DayPartType dpt) 
            throws DatabaseConnectionException {
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection()) {
            try {
                String execStr = "DELETE FROM daypart_employee WHERE date='" + 
                        DBUtils.toSQLString(d) + "' AND dayparttype='" + 
                        dpt.toString().toLowerCase() + "' AND employeeid='" +  
                        e.getEmployeeId() + "';";

                    connection.executeSQLInsertStatement(execStr);
            } catch(SQLException ex) {
                Logger.getLogger(DayPartDAO.class.getName()).log(
                                                    Level.OFF, null, ex);
            }
                
            connection.closeConnection();
        } else {
            throw new DatabaseConnectionException();
        }      
    }
    
   /**
   * checks if an employee is already planned in for a specific daypart
   * @param e Employee to check for
   * @param d Date to check for
   * @param dpt DayPartType to check for
   * @throws DatabaseConnectionException if connection could not be opened
   * @return true if the employee is already planned in on the given daypart,
   * false otherwise
   */
    public boolean checkExistsDayPartEmployee(Employee e, Date d, DayPartType dpt) {
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection()) {
            // If a connection was successfully setup, execute the SELECT statement.
            String execStr = "SELECT * FROM daypart_employee WHERE date =\'" 
                    + DBUtils.toSQLString(d) + "\' AND dayparttype =\'" + 
                    dpt.toString().toLowerCase() + "\' AND employeeid=\'" +
                    e.getEmployeeId() + "\';";
            
            ResultSet resultset = connection.executeSQLSelectStatement(execStr);

            if(resultset == null) {
                connection.closeConnection();
                return false;
            }
            try {
                while(resultset.next()) {
                    return true;
               }
            } catch(SQLException ex){
                Logger.getLogger(DayPartDAO.class.getName()).log(
                                                    Level.OFF, null, ex);
            }

            // else an error occurred leave array list empty.

            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.closeConnection();
        }
        
        return false;
    }
    
   /**
   * loads a List of dayparts for given employee and date from database
   * @param e Employee to search for
   * @param d Date to search for
   * @return List of dayparts for the given employee and date, which were
   * @throws DatabaseConnectionException if connection could not be opened
   * found in the database
   */
    public List<DayPart> loadDayPartsForEmployee(Employee e, Date date) throws DatabaseConnectionException {
        List<DayPart> dayParts = new ArrayList<>();
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection()) {
            // If a connection was successfully setup, execute the SELECT statement.
            String execStr = String.format("SELECT * FROM daypart_employee WHERE"
                    + " employeeid='%d' AND date='%s';",
                    e.getEmployeeId(), DBUtils.toSQLString(date));
            
            if(loadDayParts(connection, execStr, dayParts)) {
                return dayParts;
            }
            connection.closeConnection();           
        } else {
            throw new DatabaseConnectionException();
        }    
        return dayParts;  
    }
   /**
   * returns a list of DayPart in the database for a given employee, with a limit on
   * the amount of records DayParts returned
   * @param e Employee to search for
   * @param limit max limit of amount of DayParts to return
   * @throws DatabaseConnectionException if connection could not be opened
   * @return List of DayParts for the given employee with the given limit of 
   * DayParts returned
   */
    public List<DayPart> loadDayPartsForEmployee(Employee e, int limit) 
            throws DatabaseConnectionException {
        List<DayPart> dayParts = new ArrayList<>();
        
        // First open a database connnection
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.openConnection()) {
            // If a connection was successfully setup, execute the SELECT statement.
            String execStr = String.format("SELECT * FROM daypart_employee WHERE"
                    + " employeeid='%d' ORDER BY date ASC LIMIT %d;",
                    e.getEmployeeId(), limit);
            
            if(loadDayParts(connection, execStr, dayParts)) {
                return dayParts;
            }

            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.closeConnection();
        } else {
            throw new DatabaseConnectionException();
        }    
        return dayParts;    
    }
    
    // private helper function for loadDayPartsForEmployee
    private boolean loadDayParts(DatabaseConnection connection, String execStr, List<DayPart> dayParts) 
            throws DatabaseConnectionException {
        ResultSet resultset = connection.executeSQLSelectStatement(execStr);
        if (resultset == null) {
            connection.closeConnection();
            return true;
        }
        try {
            while(resultset.next()) {
                String dptStr = resultset.getString("dayparttype");
                String dateStr = resultset.getString("date");
                
                DayPartType dpt = DayPartType.valueOf(dptStr.toUpperCase());
                Date d = DBUtils.fromSQLString(dateStr);
                
                DayPart dp = (new DayPartDAO()).loadDayPart(d, dpt);
                dayParts.add(dp);
            }
        } catch(SQLException ex){
            Logger.getLogger(DayPartDAO.class.getName()).log(
                    Level.OFF, null, ex);
        }
        return false;
    }
}
