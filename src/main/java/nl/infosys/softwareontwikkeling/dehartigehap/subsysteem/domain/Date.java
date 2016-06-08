package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain;

public class Date {
    private int day, month, year;
    
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public int getDay() {
        return day;
    }
    
    public int getMonth() {
        return month;
    }
    
    public int getYear() {
        return year;
    }
}
