/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain;

/**
 *
 * @author maikel
 */
public class DayPartEmployee {
    private Employee employee;
    private PresenceStatus presenceStatus;
    
    public DayPartEmployee(Employee employee, PresenceStatus presenceStatus)
    {
        this.employee = employee;
        this.presenceStatus = presenceStatus;
    }
    
    public Employee getEmployee() {
        return employee;
    }

    public PresenceStatus getPresenceStatus() {
        return presenceStatus;
    }
}
