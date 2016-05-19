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

import java.util.*;

public class DayPart {
    ArrayList<DayPartEmployee> dpeList = new ArrayList<>();
    Date date;
    DayPartType dayPartType;
    
    public DayPart(Date date, DayPartType dayPartType, 
            ArrayList<DayPartEmployee> dpeList)
    {
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
    
    public ArrayList<DayPartEmployee> getDpeList() {
        return dpeList;
    }
}
