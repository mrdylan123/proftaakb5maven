/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.StatsManager;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

/**
 *
 * @author dyl
 */
public class StatsUI extends JPanel {
    //Add 2 panels, in north we will place the back and logout button. In center we will position our specific usecase.
    private JPanel panelNorth, panelCenter;
    private JButton backButton, logOutButton, getStatsButton;
    private JLabel employeesLabel;
    private JComboBox employeeCB;
    private JTextArea statresults;
    private StatsManager statsManager;
    
    public StatsUI() {
        statsManager = new StatsManager();
        
        setLayout (new BorderLayout() );
        
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        
        panelNorth.setLayout(new GridLayout(1, 3) );
        panelCenter.setLayout(new GridLayout(2, 3) );
        
        backButton = new JButton ("<--");
        //backbutton.addActionListener(new backButtonHandler);
        
        logOutButton = new JButton ("Uitloggen");
        //logOutButton.addActionListener(new logOutButtonHandler);
        
        getStatsButton = new JButton ("Vraag op");
        //getStatsButton.addActionListener(new getStatsButtonHandler);
        
        employeesLabel = new JLabel ("Medewerker");
        
        employeeCB = new JComboBox();
        
        for (Employee e : statsManager.getEmployees() )
        {
            employeeCB.addItem(e.getName());
            
        }
        
        statresults = new JTextArea("Het aantal uitgeserveerde gerechten voor medewerker x.");
        
        panelNorth.add(backButton);
        panelNorth.add(new JLabel("") );
        panelNorth.add(logOutButton);
        
        panelCenter.add(employeesLabel);
        panelCenter.add(employeeCB);
        panelCenter.add(statresults);
        
        panelCenter.add(new JLabel("") );
        panelCenter.add(getStatsButton);
        panelCenter.add(new JLabel("") );
        
        add( panelNorth, BorderLayout.NORTH );
        add( panelCenter, BorderLayout.CENTER);
    }
    
}
