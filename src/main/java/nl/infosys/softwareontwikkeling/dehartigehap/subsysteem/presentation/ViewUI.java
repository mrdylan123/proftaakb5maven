/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.datastorage.DBUtils;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Date;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPart;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartEmployee;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;


public class ViewUI extends JPanel{
    
    private JButton backBtn, logOutBtn, requestBtn;
    private JLabel dateLbl;
    private JTable morningTable, afternoonTable, eveningTable;
    private JTextField dayTF, monthTF, yearTF;
    private JPanel panelNorth;
    private JPanel panelCenter;
    private JPanel panelSouth;
    private ViewManager viewManager;
    
    public ViewUI() {   
        viewManager = new ViewManager();
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelSouth = new JPanel();
        setLayout(new BorderLayout());
        
        add(panelNorth, NORTH);
        add(panelCenter, CENTER);
        add(panelSouth, SOUTH);
        
        // De knoppen op het paneel
        backBtn = new JButton ("<--");
        logOutBtn = new JButton ("Uitloggen");
        dateLbl = new JLabel("Datum :");
        
        morningTable = new JTable(5,3);
        afternoonTable = new JTable(5,3);
        eveningTable = new JTable(5,3);
        
        requestBtn = new JButton ("Vraag op");
        
        dayTF = new JTextField("");
        monthTF = new JTextField("");
        yearTF = new JTextField("");
               
        
        panelNorth.add(backBtn);
        panelNorth.add(Box.createHorizontalStrut(100));
        panelNorth.add(logOutBtn);
        
        panelCenter.add(dateLbl);
        panelCenter.add(dayTF);
        panelCenter.add(monthTF);
        panelCenter.add(yearTF);
        
        panelCenter.add(morningTable);
        panelCenter.add(afternoonTable);
        panelCenter.add(eveningTable);
        
        panelSouth.add(requestBtn);
        
        backBtn.addActionListener(a1 -> PresentationUtils.
                                                  returnToMainMenu(this));
        logOutBtn.addActionListener(a1 -> PresentationUtils.logout());
        
        dayTF.addMouseListener(new EmptyOnMouseClickListener(dayTF));        
        monthTF.addMouseListener(new EmptyOnMouseClickListener(monthTF));                 
        yearTF.addMouseListener(new EmptyOnMouseClickListener(yearTF));               
    
        requestBtn.addActionListener(a1 -> doRequestButtonPress());       
  }
    
    private void doRequestButtonPress()
    {   
        String dayStr = dayTF.getText();
        String monthStr = monthTF.getText();
        String yearStr = yearTF.getText();

        try
        {
            Integer day = Integer.parseInt(dayStr);
            Integer month = Integer.parseInt(monthStr);
            Integer year = Integer.parseInt(yearStr);

            if (Utils.isDateValid(day, month, year) == false)
            {
                PresentationUtils.showSwingAlert("Entered date is invalid");
                return;
            }
            
            Date d = new Date(day, month, year);
            
            DayPart[] dpArr = viewManager.getDayPartsForDate(d);

            setTableData(morningTable, dpArr[0]);
            setTableData(afternoonTable, dpArr[1]);
            setTableData(eveningTable, dpArr[2]);
        }
        catch(NumberFormatException nfe)
        {
            PresentationUtils.showSwingAlert("Entered date is invalid");
        }
    }
    
    private void setTableData(JTable table, DayPart dp)
    {
        clearTable(table);
        
        DefaultTableModel tm = (DefaultTableModel)table.getModel();
        
        for (DayPartEmployee dpe : dp.getDpeList())
        {
            Employee e = dpe.getEmployee();
            Object[] data = new Object[]{"" + e.getName(), "" + e.getFunction(),
                                "" + PresentationUtils.presenceStatusToDutchString(
                                        dpe.getPresenceStatus())};
            tm.insertRow(0, data);
        }
    }
    
    private void clearTable(JTable table)
    {
        DefaultTableModel tm = (DefaultTableModel)table.getModel();
        
        int rowCount = tm.getRowCount();
        
        for (int i = 0; i < rowCount; i++)
        {
            tm.removeRow(i);
        }
    }
    
    public String getFormattedOutputForDayPart(DayPart dp)
    {
        String s = DBUtils.toSQLString(dp.getDate());
        s += "\t\t\t";
        s += dp.getDayPartType().toString();
        s += "\n----------------------------------------\n";
        
        boolean found = false;
        
        for (DayPartEmployee dpe : dp.getDpeList())
        {
            found = true;
            
            Employee e = dpe.getEmployee();
            
            s += e.getName();
            s += "\t(";
            s += dpe.getPresenceStatus().toString();
            s += ")\n";
        }
        
        if (found == false)
        {
            s += "<NO RESULTS>\n";
        }
        
        s += "\n\n";
        return s;
    }
}

