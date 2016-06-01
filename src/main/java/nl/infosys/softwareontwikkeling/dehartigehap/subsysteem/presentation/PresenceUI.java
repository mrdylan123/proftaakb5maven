/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.PresenceManager;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

/**
 *
 * @author maikel
 */
public class PresenceUI extends JPanel {
private JPanel panelNorth, panelCenter;
    private JButton backButton, logOutButton, getPresentionButton;
    private JLabel employeesLabel;
    private JComboBox employeeCB;
    private JTextArea presentionResults;
    private PresenceManager presenceManager;
   
    public PresenceUI() {
        presenceManager = new PresenceManager();
   
        setLayout(new BorderLayout() );

        panelNorth = new JPanel();
        panelCenter = new JPanel();

        panelNorth.setLayout(new GridLayout(3, 1) );
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
    }
   
   
}