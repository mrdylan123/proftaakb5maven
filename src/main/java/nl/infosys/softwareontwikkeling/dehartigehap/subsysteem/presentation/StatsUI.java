package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.StatsManager;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

public class StatsUI extends JPanel {
    //Add 2 panels, in north we will place the back and logout button. In center
    // we will position our specific usecase.
    private JPanel panelNorth, panelCenter;
    private JButton backButton, logOutButton, getStatsButton;
    private JLabel employeesLabel;
    private JComboBox employeeCB;
    private JTextArea statsResults;
    private StatsManager statsManager;
    
    public StatsUI() {
        statsManager = new StatsManager();
        
        setLayout(new BorderLayout());
        
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        
        int columns = 1;
        int rows = 3;
        int columnsCenter = 2;
        panelNorth.setLayout(new GridLayout(columns, rows) );
        panelCenter.setLayout(new GridLayout(columnsCenter, rows) );
        
        backButton = new JButton ("<--");
        logOutButton = new JButton ("Uitloggen");       
        getStatsButton = new JButton ("Vraag op");
        
        employeesLabel = new JLabel ("Medewerker");
        
        employeeCB = new JComboBox();
        
        for (Employee e : statsManager.getEmployees()) {
            employeeCB.addItem(e.getName());         
        }
        
        statsResults = new JTextArea("");
        
        panelNorth.add(backButton);
        panelNorth.add(new JLabel("") );
        panelNorth.add(logOutButton);
        
        panelCenter.add(employeesLabel);
        panelCenter.add(employeeCB);
        panelCenter.add(statsResults);
        
        panelCenter.add(new JLabel("") );
        panelCenter.add(getStatsButton);
        panelCenter.add(new JLabel("") );
        
        add( panelNorth, BorderLayout.NORTH );
        add( panelCenter, BorderLayout.CENTER);
        
        getStatsButton.addActionListener(a1 -> getStats());
        
        backButton.addActionListener(a1 -> PresentationUtils.
                                                  returnToMainMenu(this));
        logOutButton.addActionListener(a1 -> PresentationUtils.logout(this));
    }
    
    private void getStats() {
        Employee e = statsManager.getEmployees().get(
                employeeCB.getSelectedIndex());
        
        if (e == null) {
            return;
        }
        
        setStatsResultTextArea(e);
    }
    
    private void setStatsResultTextArea(Employee e) {        
        int mealsServed = statsManager.getAmountMealsServed(e);
        int drinksServed = statsManager.getAmountDrinksServed(e);
        
        String s = String.format("Hoeveelheid maaltijden geserveerd: %d\n",
                                                mealsServed);
        s += String.format("Hoeveelheid drankjes geserveerd: %d\n",
                                                drinksServed);
             
        statsResults.setText(s);     
    }
}
