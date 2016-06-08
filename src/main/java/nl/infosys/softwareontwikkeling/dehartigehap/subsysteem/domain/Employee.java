/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain;

/**
 *
 * @author Maikel
 */
public class Employee {
    private String name, function;
    private int employeeId;

    public Employee(int employeeId, String name, String function) {
        this.name = name;
        this.function = function;
        this.employeeId = employeeId;
    }

    
    
    public int getEmployeeId() {
        return employeeId;
    }
    
    public String getName()
    {
        return name;
    }
}

