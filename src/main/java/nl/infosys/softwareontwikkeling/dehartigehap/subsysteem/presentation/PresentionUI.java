/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.PresenceManager;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Date;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPart;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartEmployee;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

/**
 *
 * @author maikel
 */
public class PresentionUI extends JPanel {
private JPanel panelNorth, panelCenter;
    private JButton backButton, logOutButton, getPresentionButton;
    private JLabel employeesLabel;
    private JComboBox employeeCB;
    private JTextArea presentionResults;
    private PresenceManager presenceManager;
   
    public PresentionUI() {
        presenceManager = new PresenceManager();
   
        setLayout(new BorderLayout() );

        panelNorth = new JPanel();
        panelCenter = new JPanel();

        panelNorth.setLayout(new GridLayout(1, 3) );
        panelCenter.setLayout(new GridLayout(2, 3) );

        backButton = new JButton ("<--");

        logOutButton = new JButton ("Uitloggen");

        getPresentionButton = new JButton ("Vraag op");

        employeesLabel = new JLabel ("Medewerker");
        employeeCB = new JComboBox();
       
        for (Employee e : presenceManager.getEmployees() )
        {
            employeeCB.addItem(e.getName());
        }    

        presentionResults = new JTextArea ("");

        panelNorth.add(backButton);
        panelNorth.add(new JLabel(""));
        panelNorth.add(logOutButton);

        panelCenter.add(employeesLabel);
        panelCenter.add(employeeCB);
        panelCenter.add(presentionResults);
        panelCenter.add(new JLabel(""));
        panelCenter.add(getPresentionButton);
        panelCenter.add(new JLabel(""));

        add( panelNorth, BorderLayout.NORTH );
        add( panelCenter, BorderLayout.CENTER);
        
        backButton.addActionListener(a1 -> PresentationUtils.
                                                  returnToMainMenu(this));
        logOutButton.addActionListener(a1 -> PresentationUtils.logout());
        
        getPresentionButton.addActionListener(a1 -> getPresentionButtonPress());
    }
    
    public void getPresentionButtonPress()
    {
        int index = employeeCB.getSelectedIndex();
        Employee e = presenceManager.getEmployees().get(index);
        
        int limit = 10; // amount of results to return
        
        ArrayList<DayPart> dayparts = presenceManager.getDayPartsForEmployee(
                                                            e, limit);
        
        String s = "";
        
        for (DayPart dp : dayparts)
        {
            s += getFormattedOutputForDayPart(dp, e);
        }
        presentionResults.setText(s);
    }
   
   public String getFormattedOutputForDayPart(DayPart dp, Employee e)
   {
       String s = "";
       
       DayPartEmployee dpe = dp.getDaypartEmployeeForEmployee(e);
       Date d = dp.getDate();
       
       if (d != null && dpe != null)
       {
            s += String.format("%d-%d-%d %s (%s)\n", d.getDay(), d.getMonth(),
                    d.getYear(), dp.getDayPartType().toString(), 
                    dpe.getPresenceStatus().toString());
       }
       
       return s;
   }
}