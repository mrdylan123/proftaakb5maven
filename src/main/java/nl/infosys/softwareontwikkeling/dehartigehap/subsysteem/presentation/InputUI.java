package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.*;


public class InputUI extends JPanel{
    private JButton backButton, logOutButton, confirmButton;
    private JComboBox worker1CB, worker2CB, worker3CB, dayPartCB;
    private JLabel workerLabel, dateLabel, dayPartLabel;
    private JTextField dayTF, monthTF, yearTF;
    private JPanel panelNorth;
    private JPanel panelSouth;
    private JPanel panelCenter;
    private InputManager inputManager;
           
    private static final int SPACING = 2;     
    private static final int ROWSNORTH = 4;
    private static final int COLUMNSNORTH = 1;
    private static final int ROWSSOUTH = 4;
    private static final int COLUMNSSOUTH = 1;
    private static final int ROWSCENTER = 4;
    private static final int COLUMNSCENTER = 4;
        
    public InputUI() {
        try {
            inputManager = new InputManager();
        } catch(DatabaseConnectionException dce) {
            Logger.getLogger(InputUI.class.getName()).log(Level.OFF, null, dce);
            PresentationUtils.showDutchUnableToOpenDatabaseConnectionAlert();
            PresentationUtils.destroyWindow(this);
            return;
        }  
        
        setLayout(new BorderLayout());

        panelNorth = new JPanel();
        panelSouth = new JPanel();
        panelCenter = new JPanel();
   
        add(panelNorth, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);
                
        panelNorth.setLayout(new GridLayout(COLUMNSNORTH, ROWSNORTH, SPACING, SPACING));
        panelSouth.setLayout(new GridLayout(COLUMNSSOUTH, ROWSSOUTH, SPACING, SPACING ));
        panelCenter.setLayout(new GridLayout(COLUMNSCENTER, ROWSCENTER, SPACING, SPACING));
           
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
        monthTF = new JTextField("");
        yearTF = new JTextField("");    

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
        
        for(Employee e : inputManager.getEmployees() ) {
            worker1CB.addItem(e.getName());
            worker2CB.addItem(e.getName());
            worker3CB.addItem(e.getName());
        }
    }
    
    private void doConfirmButtonPress() {
        int cB1SelectedIndex = worker1CB.getSelectedIndex();
        int cB2SelectedIndex = worker2CB.getSelectedIndex();
        int cB3SelectedIndex = worker3CB.getSelectedIndex();
        int dayPartCBSelectedIndex = dayPartCB.getSelectedIndex();

        if (cB1SelectedIndex < 1 && cB2SelectedIndex < 1 
                    && cB3SelectedIndex < 1) {
            PresentationUtils.showSwingAlert("Geen medewerker(s) geselecteerd.");
            return;
        }

        String dayStr = dayTF.getText();
        String monthStr = monthTF.getText();
        String yearStr = yearTF.getText();

        Integer day = null, month = null, year = null;
        
        try {
            day = Integer.parseInt(dayStr);
            month = Integer.parseInt(monthStr);
            year = Integer.parseInt(yearStr);
        } catch(NumberFormatException nfe) {
            PresentationUtils.showSwingAlert("De ingevulde datum is incorrect.");
        }

        if (! Utils.isDateValid(day, month, year) ) {
            PresentationUtils.showSwingAlert("De ingevulde datum is incorrect.");
            return;
        }

        planEmployees(cB1SelectedIndex, cB2SelectedIndex, cB3SelectedIndex, day, 
                month, year, dayPartCBSelectedIndex);
    }

    private void planEmployees(int cB1SelectedIndex, int cB2SelectedIndex, 
            int cB3SelectedIndex, Integer day, Integer month, Integer year, 
            int dayPartCBSelectedIndex) {
        Employee e1 = null, e2 = null, e3 = null;
        
        if (cB1SelectedIndex > 0) {
            e1 = inputManager.getEmployees().get(cB1SelectedIndex - 1);
        }
        if (cB2SelectedIndex > 0) {
            e2 = inputManager.getEmployees().get(cB2SelectedIndex - 1);
        }
        if (cB3SelectedIndex > 0) {
            e3 = inputManager.getEmployees().get(cB3SelectedIndex - 1);
        }
        
        Date d = new Date(day, month, year);   
        
        if (checkForPastDate(d)) {
            showPastDateAlert();
            return;
        }
                 
        try {
            DayPartType dpt = DayPartType.values()[dayPartCBSelectedIndex];
            
            inputManager.planEmployeesIntoDayPart(e1, e2, e3, dpt, d);
            
            PresentationUtils.showSwingAlert("Medewerker(s) succesvol ingepland.");
        } catch(DatabaseConnectionException dce) {
            Logger.getLogger(InputUI.class.getName()).log(Level.OFF, null, dce);
            PresentationUtils.showDutchUnableToOpenDatabaseConnectionAlert();
            return;
        }  
    }

    private boolean checkForPastDate(Date d) {
                Calendar cal = Calendar.getInstance();
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        // Calendar.MONTH is 0-11 so do + 1
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        int currentYear = cal.get(Calendar.YEAR);
        
        if (d.getYear() < currentYear) {
            return true;
        }
        if (d.getMonth() < currentMonth) {
            return true;
        }
        if (d.getDay() < currentDay && d.getMonth() == currentMonth) {
            return true;
        }
        return false;
    }
    
    private void showPastDateAlert() {
        PresentationUtils.showSwingAlert("Poging om medewerker(s) in het verleden"
                + " in te plannen.");
    }
}


