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
import javax.swing.JTextField;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.*;



/**
 *
 * @author J. Bouman
 */
public class InvoerUI extends JPanel{
    private JButton backButton, logOutButton, confirmButton, checkButton;
    private JComboBox worker1CB, worker2CB, worker3CB, dayPartCB;
    private JLabel workerLabel, dateLabel, dayPartLabel;
    private JTextField dayTF, monthTF, yearTF;
    private JPanel panelNorth;
    private JPanel panelSouth;
    private JPanel panelCenter;
    private InputManager inputManager;
    
    public InvoerUI() {
        inputManager = new InputManager();
        
        setLayout(new BorderLayout());

        panelNorth = new JPanel();
        panelSouth = new JPanel();
        panelCenter = new JPanel();
   
        add(panelNorth, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);
        
        panelNorth.setLayout(new GridLayout(1,4, 2, 2));
        panelSouth.setLayout(new GridLayout(1,4, 2, 2 ));
        panelCenter.setLayout(new GridLayout(4,4,2,2));
        

        
        backButton = new JButton("<--");
        logOutButton = new JButton("Uitloggen");
        confirmButton = new JButton("Invoer bevestigen");
        checkButton = new JButton("Check mogelijkheid");

        worker1CB = new JComboBox();
        worker2CB = new JComboBox();
        worker3CB = new JComboBox();
        
        
        worker1CB.addItem("");
        worker2CB.addItem("");
        worker3CB.addItem("");
        
        for(Employee e : inputManager.getEmployees() )
        {
            worker1CB.addItem(e.getName());
            worker2CB.addItem(e.getName());
            worker3CB.addItem(e.getName());
        }
        
        dayPartCB = new JComboBox();
        
        dayPartCB.addItem("Ochtend"); 
        dayPartCB.addItem("Middag");
        dayPartCB.addItem("Avond");
        dayPartCB.setSelectedIndex(0);
        
        workerLabel = new JLabel("Medewerker");
        dateLabel = new JLabel("Datum");
        dayPartLabel = new JLabel("Dagdeel");

        dayTF = new JTextField("Dag");
        // Add a listener to the textfield, so that when the textfield is clicked, it removes the placeholder text.
        dayTF.addActionListener(al -> dayTF.setText("") );
                
        monthTF = new JTextField("Maand");
        // Add a listener to the textfield, so that when the textfield is clicked, it removes the placeholder text.
        monthTF.addActionListener(al -> monthTF.setText("") );
                  
        yearTF = new JTextField("Jaar");
        // Add a listener to the textfield, so that when the textfield is clicked, it removes the placeholder text.
        yearTF.addActionListener(al -> yearTF.setText("") );        

        // north is 1 x 4
        panelNorth.add(backButton);
        panelNorth.add(new JLabel(""));
        panelNorth.add(new JLabel(""));
        panelNorth.add(logOutButton);

        // center is 4 x 4
        // rij 1
        panelCenter.add(workerLabel);
        panelCenter.add(worker1CB);
        panelCenter.add(worker2CB);
        panelCenter.add(worker3CB);
        
        // rij 2
        panelCenter.add(dateLabel);
        panelCenter.add(dayTF);
        panelCenter.add(monthTF);
        panelCenter.add(yearTF);
        
        // rij 3
        panelCenter.add(dayPartLabel);
        panelCenter.add(new JLabel(""));
        panelCenter.add(dayPartCB);    
        panelCenter.add(new JLabel(""));
        panelCenter.add(new JLabel(""));       
        panelCenter.add(new JLabel(""));

        // south is 1 x 4
        panelSouth.add(checkButton);
        panelSouth.add(new JLabel(""));
        panelSouth.add(new JLabel(""));
        panelSouth.add(confirmButton);
        

        
        confirmButton.addActionListener(a1 -> {
            int CB1SelectedIndex = worker1CB.getSelectedIndex();
            int CB2SelectedIndex = worker2CB.getSelectedIndex();
            int CB3SelectedIndex = worker3CB.getSelectedIndex();
            int dayPartCBSelectedIndex = dayPartCB.getSelectedIndex();

            if (CB1SelectedIndex < 1 && CB2SelectedIndex < 1 
                       && CB3SelectedIndex < 1)
            {
                return;
            }

            String dayStr = dayTF.getText();
            String monthStr = monthTF.getText();
            String yearStr = yearTF.getText();

            try
            {
                inputManager.OnConfirmButtonPress(CB1SelectedIndex, CB2SelectedIndex, 
                        CB3SelectedIndex, dayPartCBSelectedIndex, dayStr, monthStr, yearStr);

                PresentationUtils.showSwingAlert("Successfully entered input.");
            }
            catch(DateInvalidException die)
            {
                PresentationUtils.showSwingAlert("Entered date is invalid.");
            }
           });
    }
}


