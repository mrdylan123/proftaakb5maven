/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
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
public class InputUI extends JPanel{
    private JButton backButton, logOutButton, confirmButton;
    private JComboBox worker1CB, worker2CB, worker3CB, dayPartCB;
    private JLabel workerLabel, dateLabel, dayPartLabel;
    private JTextField dayTF, monthTF, yearTF;
    private JPanel panelNorth;
    private JPanel panelSouth;
    private JPanel panelCenter;
    private InputManager inputManager;
    
    public InputUI() {
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

        initComboBoxes();
        
        dayPartCB = new JComboBox();
        
        dayPartCB.addItem("Ochtend"); 
        dayPartCB.addItem("Middag");
        dayPartCB.addItem("Avond");
        dayPartCB.setSelectedIndex(0);
        
        workerLabel = new JLabel("Medewerker");
        dateLabel = new JLabel("Datum");
        dayPartLabel = new JLabel("Dagdeel");

        dayTF = new JTextField("");
        // Add a listener to the textfield, so that when the textfield is clicked, it removes the placeholder text.
        dayTF.addActionListener(al -> dayTF.setText("") );
                
        monthTF = new JTextField("");
        // Add a listener to the textfield, so that when the textfield is clicked, it removes the placeholder text.
        monthTF.addActionListener(al -> monthTF.setText("") );
                  
        yearTF = new JTextField("");
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
        panelSouth.add(new JLabel(""));
        panelSouth.add(new JLabel(""));
        panelSouth.add(confirmButton);
        
        backButton.addActionListener(a1 -> PresentationUtils.
                                                  returnToMainMenu(this));
        logOutButton.addActionListener(a1 -> PresentationUtils.logout(this));

        
        confirmButton.addActionListener(a1 -> doConfirmButtonPress() );
    
    }

    private void initComboBoxes() {
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
    }
    
    private void doConfirmButtonPress()
    {
        int cB1SelectedIndex = worker1CB.getSelectedIndex();
        int cB2SelectedIndex = worker2CB.getSelectedIndex();
        int cB3SelectedIndex = worker3CB.getSelectedIndex();
        int dayPartCBSelectedIndex = dayPartCB.getSelectedIndex();

        if (cB1SelectedIndex < 1 && cB2SelectedIndex < 1 
                    && cB3SelectedIndex < 1)
        {
            PresentationUtils.showSwingAlert("Geen medewerker(s) geselecteerd.");
            return;
        }

        String dayStr = dayTF.getText();
        String monthStr = monthTF.getText();
        String yearStr = yearTF.getText();

        Integer day = null, month = null, year = null;
        try
        {
            day = Integer.parseInt(dayStr);
            month = Integer.parseInt(monthStr);
            year = Integer.parseInt(yearStr);
        }
        catch(NumberFormatException nfe)
        {
            PresentationUtils.showSwingAlert("De ingevulde datum is incorrect.");
        }

        if (Utils.isDateValid(day, month, year) == false)
        {
            PresentationUtils.showSwingAlert("De ingevulde datum is incorrect.");
            return;
        }

        planEmployees(cB1SelectedIndex, cB2SelectedIndex, cB3SelectedIndex, day, 
                month, year, dayPartCBSelectedIndex);
    }

    private void planEmployees(int CB1SelectedIndex, int CB2SelectedIndex, 
            int CB3SelectedIndex, Integer day, Integer month, Integer year, 
            int dayPartCBSelectedIndex) {
        Employee e1 = null, e2 = null, e3 = null;
        
        if (CB1SelectedIndex > 0) {
            e1 = inputManager.getEmployees().get(CB1SelectedIndex - 1);
        }
        if (CB2SelectedIndex > 0) {
            e2 = inputManager.getEmployees().get(CB2SelectedIndex - 1);
        }
        if (CB3SelectedIndex > 0) {
            e3 = inputManager.getEmployees().get(CB3SelectedIndex - 1);
        }
        
        try
        {
            Date d = new Date(day, month, year);
            DayPartType dpt = DayPartType.values()[dayPartCBSelectedIndex];
            inputManager.planEmployeesIntoDayPart(e1, e2, e3, dpt,
                    d);
            
            PresentationUtils.showSwingAlert("Medewerker(s) succesvol ingepland.");
        }
        catch(PlanInPastException pipe)
        {
            PresentationUtils.showSwingAlert("Database fout: poging om in het verleden"
                    + " medewerker(s) in te voeren");
        }
    }
}


