/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic;

import java.util.ArrayList;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Date;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPart;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartEmployee;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartType;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

/**
 *
 * @author maikel
 */
public class EditPlanningManager {
    private ArrayList<Employee> employees;
    private Date selectedDate = null;
    private Employee selectedEmployee = null;
    
    public EditPlanningManager()
    {
        employees = (new EmployeeDAO()).loadEmployees();
    }
    
    public ArrayList<Employee> getEmployees() {
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
    
    public ArrayList<DayPart> getDayPartsForEmployee(Employee e, Date d)
    {
         return (new DayPartDAO()).loadDayPartsForEmployee(e, d);
    }
    
    public void saveDayPartEmployee(DayPartEmployee dpe, Date d, DayPartType dpt) {
        (new DayPartDAO()).saveDayPartEmployee(dpe, d, dpt);
    }
    
    public void deleteDayPartEmployee(Employee e, Date d, DayPartType dpt) {
        (new DayPartDAO()).deleteDayPartEmployee(e, d, dpt);
    }
}
