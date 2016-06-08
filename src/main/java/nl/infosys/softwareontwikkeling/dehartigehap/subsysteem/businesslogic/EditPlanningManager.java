/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Date;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPart;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartEmployee;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartType;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

/**
 * Manager for editing of planning
 */
public class EditPlanningManager {
    private List<Employee> employees;
    private Date selectedDate = null;
    private Employee selectedEmployee = null;
    
    public EditPlanningManager()
    {
        employees = (new EmployeeDAO()).loadEmployees();
    }
    
    public List<Employee> getEmployees() {
        return employees;
    }
    
    
    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }
    
    /**
   * returns a list of DayParts for an employee on a date, returns 
   * morning, afternoon and evening DayParts if the employee is planned in
   * on those dayparts
   * @param e Employee to search for 
   * @param d the Date to get the DayParts for
   * @return list of DayParts
   */
    public List<DayPart> getDayPartsForEmployee(Employee e, Date d) {
         return (new DayPartDAO()).loadDayPartsForEmployee(e, d);
    }
    
   /**
   * saves a DayPartEmployee (Employee & PresenceStatus) on Date and DayPartType
   * in the database
   * @param dpe DayPartEmployee to save
   * @param d Date for which to save
   * @param dpt DayPartType for which to save
   * @return Nothing
   */
    public void saveDayPartEmployee(DayPartEmployee dpe, Date d, DayPartType dpt) {
        try
        {   
            (new DayPartDAO()).saveDayPartEmployee(dpe, d, dpt);
        } catch(PlanInPastException pipe){}
    }
    
   /**
   * deletes an Employee on Date and DayPartType
   * in the database
   * @param e Employee to delete
   * @param d Date for which to delete 
   * @param dpt DayPartType for which to delete
   * @return Nothing
   */
    public void deleteDayPartEmployee(Employee e, Date d, DayPartType dpt) {
        try
        {
            (new DayPartDAO()).deleteDayPartEmployee(e, d, dpt);
        }
        catch(SQLException sqle){}
    }
}
