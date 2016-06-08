/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.EditPlanningManager;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Date;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPart;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartEmployee;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartType;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.PresenceStatus;
/**
 *
 * @author J. Bouman
 */
public class EditUI extends JPanel {
    private JPanel panelNorth, panelCenter;
    private JButton backButton, logOutButton, getActualRosterButton, confirmEditButton;
    private JComboBox employeeCB, statusDayPart1, statusDayPart2, statusDayPart3;
    private JTextField dayTF, monthTF, yearTF;
    private JTextArea rosterArea; 
    private EditPlanningManager epManager;
    private EditAction actionCB1, actionCB2, actionCB3;
    
    public enum EditAction
    {
        ACTION_NONE,
        ACTION_ADD,
        ACTION_DELETE
    };
    
    public EditUI() {
        
        epManager = new EditPlanningManager();

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
        panelCenter.add(new JLabel("Ochtend"));
        panelCenter.add(new JLabel("Middag"));
        panelCenter.add(new JLabel("Avond"));
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

        for (Employee e : epManager.getEmployees())
        {
            String str = String.format("%s (%s)", e.getName(), e.getFunction());
            employeeCB.addItem(str);
        }

        getActualRosterButton.addActionListener(al -> getRoster());
        confirmEditButton.addActionListener(a1 -> editRoster());
        
        backButton.addActionListener(a1 -> PresentationUtils.
                                                  returnToMainMenu(this));
        
        logOutButton.addActionListener(a1 -> PresentationUtils.logout(this));
    }
    
    private void getRoster()
    {
        // clear comboboxes
        statusDayPart1.removeAllItems();
        statusDayPart2.removeAllItems();
        statusDayPart3.removeAllItems();
        
        int cBIndex = employeeCB.getSelectedIndex();
        
        int day = 0, month = 0, year = 0;
        
        try {
            day = Integer.parseInt(dayTF.getText());
            month = Integer.parseInt(monthTF.getText());
            year = Integer.parseInt(yearTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            return; // TODO: show error message
        }
        
        Date d = new Date(day,month,year);
        Employee e = epManager.getEmployees().get(cBIndex);
        
        epManager.setSelectedDate(d);
        epManager.setSelectedEmployee(e);
        
        
        ArrayList<DayPart> dayParts = epManager.getDayPartsForEmployee(
                                   e, d);
        
        setRosterAreaText(dayParts, e);
        
        actionCB1 = setComboBox(statusDayPart1, DayPartType.MORNING, dayParts);
        actionCB2 = setComboBox(statusDayPart2, DayPartType.AFTERNOON, dayParts);
        actionCB3 = setComboBox(statusDayPart3, DayPartType.EVENING, dayParts);
    }
    
    private EditAction setComboBox(JComboBox cmBox, DayPartType dpt,
                                ArrayList<DayPart> dayParts)
    {
        EditAction ea = EditAction.ACTION_NONE;
        
        cmBox.addItem("Geen actie");
        cmBox.setSelectedIndex(0);
        
        boolean set = false;
        
        // we already know that an employee is planned for every
        // daypart in the dayparts list, so just check if one of the 
        // dayparts matches the DayPartType dpt that was passed as arg
        for (DayPart dp : dayParts)
        {
            if (dp.getDayPartType() == dpt)
            {
                // Matched, so the employee is planned in for the daypart
                // set selected combobox item to 'verwijder planning'
                set = true;
            }
        }
        if (set)
        {
            cmBox.addItem("Verwijder planning");
            ea = EditAction.ACTION_DELETE;
        }
        else
        {
            cmBox.addItem("Plan in");
            ea = EditAction.ACTION_ADD;
        }
        return ea;
    }
    
    private void setRosterAreaText(ArrayList<DayPart> dayParts, Employee e)
    {
        String s = "";
       for (DayPart dp : dayParts)
        {
            DayPartEmployee dpe = dp.getDaypartEmployeeForEmployee(e);
            if (dpe != null)
            {           
                s += String.format("%s (%s)\n", 
                        PresentationUtils.dayPartTypeToDutchString(dp.getDayPartType()),
                            PresentationUtils.presenceStatusToDutchString(dpe.getPresenceStatus()));
            }
        }
       
       rosterArea.setText(s);
    }
    
    private void editRoster()
    {
        int CbIndex1 = statusDayPart1.getSelectedIndex();
        int CbIndex2 = statusDayPart2.getSelectedIndex();
        int CbIndex3 = statusDayPart3.getSelectedIndex();
        
        // user didn't first press 'get roster' button, so display warning
        if (CbIndex1 == -1 && CbIndex2 == -1 && CbIndex3 == -1)
        {
            PresentationUtils.showSwingAlert("Haal eerst de planning op "
                    + "voordat u probeert de planning te wijzigen.");
        }
                
        EditAction eaMorning = actionCB1;
        EditAction eaAfternoon = actionCB2; 
        EditAction eaEvening = actionCB3;
        
        if (CbIndex1 == 0) eaMorning = EditAction.ACTION_NONE;
        if (CbIndex2 == 0) eaAfternoon = EditAction.ACTION_NONE;
        if (CbIndex3 == 0) eaEvening = EditAction.ACTION_NONE;
        
        doAction(eaMorning, DayPartType.MORNING);
        doAction(eaAfternoon, DayPartType.AFTERNOON);
        doAction(eaEvening, DayPartType.EVENING);
        
        getRoster();
        
        PresentationUtils.showSwingAlert("De planning is gewijzigd.");
    }
    
    private void doAction(EditAction ea, DayPartType dpt)
    {
        Employee e = epManager.getSelectedEmployee();
        if (ea == EditAction.ACTION_ADD )
        {
            DayPartEmployee dpe = new DayPartEmployee(e, PresenceStatus.PLANNED);
            epManager.saveDayPartEmployee(dpe, epManager.getSelectedDate(), dpt);
        }
        
        if (ea == EditAction.ACTION_DELETE)
        {
            epManager.deleteDayPartEmployee(e, epManager.getSelectedDate(), dpt);
        }
    }
}


