package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain;

import java.util.*;

public class DayPart {
    List<DayPartEmployee> dpeList;
    Date date;
    DayPartType dayPartType;
    
    public DayPart(Date date, DayPartType dayPartType, 
            List<DayPartEmployee> dpeList) {
        this.date = date;
        this.dayPartType = dayPartType;
        this.dpeList = dpeList;
    }
    
    public Date getDate() {
        return date;
    }

    public DayPartType getDayPartType() {
        return dayPartType;
    }
    
    public List<DayPartEmployee> getDpeList() {
        return dpeList;
    }
    
    public DayPartEmployee getDaypartEmployeeForEmployee(Employee e) {
        for (DayPartEmployee dpe2 : getDpeList()) {
            if (dpe2.getEmployee().getEmployeeId() == e.getEmployeeId()) {
                return dpe2;
            }
        }
        
        return null;
    }
    
    public void removeByNameFromDpeList(String employeeName) {
        List<DayPartEmployee> dpeList2 = new ArrayList<>();
        
        for( DayPartEmployee dpe : dpeList) {
            if (dpe.getEmployee().getName().equals(employeeName)) {
               dpeList2.add(dpe);
            }
        }
        
        for ( DayPartEmployee dpe2 : dpeList2) {
            dpeList.remove(dpe2);
        }
    }
}
