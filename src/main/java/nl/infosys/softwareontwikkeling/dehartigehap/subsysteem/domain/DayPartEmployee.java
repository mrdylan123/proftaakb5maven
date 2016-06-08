package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain;

public class DayPartEmployee {
    private Employee employee;
    private PresenceStatus presenceStatus;
    
    public DayPartEmployee(Employee employee, PresenceStatus presenceStatus) {
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
