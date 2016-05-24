/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author J. Bouman
 */
public class EditPlanningUI extends JPanel {
    private JPanel panelNorth, panelCenter;
    private JButton backButton, logOutButton, getActualRosterButton, confirmEditButton;
    private JComboBox employeeCB, statusDayPart1, statusDayPart2, statusDayPart3;
    private JTextField dayTF, monthTF, yearTF;
    private JTextArea rosterArea; 
    
    public EditPlanningUI() {
    setLayout (new BorderLayout() );
    
    panelNorth = new JPanel();
    panelCenter = new JPanel();
    
    panelNorth.setLayout( new GridLayout(1,5));
    panelCenter.setLayout(  new GridLayout(6,6));
    
    backButton = new JButton("<--");
    logOutButton = new JButton("Uitloggen");
   
    employeeCB = new JComboBox();
    dayTF = new JTextField();
    monthTF = new JTextField();
    yearTF = new JTextField();
    
    rosterArea = new JTextArea();
    statusDayPart1 = new JComboBox();
    statusDayPart2 = new JComboBox();
    statusDayPart3 = new JComboBox();
    
    getActualRosterButton = new JButton("Vraag actueel rooster op");
    confirmEditButton = new JButton("Voer wijzigingen door");
    
    panelNorth.add(backButton);
    panelNorth.add(new JLabel());    
    panelNorth.add(new JLabel());
    panelNorth.add(new JLabel());
    panelNorth.add(logOutButton);
    
    panelCenter.add(new JLabel());
    panelCenter.add(new JLabel("Selecteer medewerkeer"));
    panelCenter.add(new JLabel("Voer dag in"));
    panelCenter.add(new JLabel("Voer maand in"));
    panelCenter.add(new JLabel("Voer jaar in"));
    panelCenter.add(new JLabel());   
    
    panelCenter.add(new JLabel());
    panelCenter.add(employeeCB);
    panelCenter.add(dayTF);
    panelCenter.add(monthTF);
    panelCenter.add(yearTF);
    panelCenter.add(new JLabel()); 
    
    panelCenter.add(new JLabel());
    panelCenter.add(new JLabel("Actuele planning"));
    panelCenter.add(new JLabel("Selecteer gewenste status voor ochtend"));
    panelCenter.add(new JLabel("Selecteer gewenste status voor middag"));
    panelCenter.add(new JLabel("Selecteer gewenste status voor avond"));
    panelCenter.add(new JLabel());   
    
    panelCenter.add(new JLabel());
    panelCenter.add(rosterArea);
    panelCenter.add(statusDayPart1);
    panelCenter.add(statusDayPart2);
    panelCenter.add(statusDayPart3);
    panelCenter.add(new JLabel());
    
    panelCenter.add(new JLabel());
    panelCenter.add(new JLabel());
    panelCenter.add(new JLabel());
    panelCenter.add(new JLabel());
    panelCenter.add(new JLabel());
    panelCenter.add(new JLabel());  
    
    panelCenter.add(new JLabel());
    panelCenter.add(getActualRosterButton);
    panelCenter.add(new JLabel());
    panelCenter.add(new JLabel());
    panelCenter.add(confirmEditButton);
    panelCenter.add(new JLabel());
    
    add(panelNorth, BorderLayout.NORTH);
    add(panelCenter, BorderLayout.CENTER);
}
}


