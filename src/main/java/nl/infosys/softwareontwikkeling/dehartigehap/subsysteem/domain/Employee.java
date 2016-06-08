package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain;

public class Employee {
    private String name, function;
    private int employeeId;

    public Employee(int employeeId, String name, String function) {
        this.name = name;
        this.function = function;
        this.employeeId = employeeId;
    }

    public String getFunction() {
        return function;
    }
    
    public int getEmployeeId() {
        return employeeId;
    }
    
    public String getName() {
        return name;
    }
}

