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
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
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
    private static final int NUMROWSPERTABLE = 25;
    private static final int NUMCOLUMNSPERTABLE = 3;
    
    public ViewUI() {   
        viewManager = new ViewManager();
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelSouth = new JPanel();
        setLayout(new BorderLayout());
        
        
        int rowsNorth = 1;
        int columnsNorth = 3;
        int rowsCenter = 4;
        int columnsCenter = 6;
        
        panelNorth.setLayout(new GridLayout(rowsNorth, columnsNorth) );
        panelCenter.setLayout(new GridLayout(rowsCenter, columnsNorth) );
        
        add(panelNorth, NORTH);
        add(panelCenter, CENTER);
        add(panelSouth, SOUTH);
        
        // The buttons on the panel
        backBtn = new JButton ("<--");
        logOutBtn = new JButton ("Uitloggen");
        dateLbl = new JLabel("Datum :");
        
        morningTable = new JTable(NUMROWSPERTABLE, NUMCOLUMNSPERTABLE);
        afternoonTable = new JTable(NUMROWSPERTABLE, NUMCOLUMNSPERTABLE);
        eveningTable = new JTable(NUMROWSPERTABLE, NUMCOLUMNSPERTABLE);
        
        requestBtn = new JButton ("Vraag op");
        
        dayTF = new JTextField("");
        monthTF = new JTextField("");
        yearTF = new JTextField("");
               
        
        panelNorth.add(backBtn);
        
        int horizontalSpacing = 100;
        panelNorth.add(Box.createHorizontalStrut(horizontalSpacing));
        panelNorth.add(logOutBtn);
        
        panelCenter.add(new JLabel(""));
        panelCenter.add(new JLabel("Dag"));
        panelCenter.add(new JLabel(""));
        panelCenter.add(new JLabel("Maand"));
        panelCenter.add(new JLabel(""));
        panelCenter.add(new JLabel("Jaar"));
        
        panelCenter.add(dateLbl);
        panelCenter.add(dayTF);
        panelCenter.add(new JLabel(""));
        panelCenter.add(monthTF);
        panelCenter.add(new JLabel(""));
        panelCenter.add(yearTF);
        
        panelCenter.add(new JLabel(""));
        panelCenter.add(new JLabel("Ochtend"));
        panelCenter.add(new JLabel(""));
        panelCenter.add(new JLabel("Middag"));
        panelCenter.add(new JLabel(""));
        panelCenter.add(new JLabel("Avond"));
        
        panelCenter.add(new JLabel(""));
        panelCenter.add(morningTable);
        panelCenter.add(new JLabel(""));
        panelCenter.add(afternoonTable);
        panelCenter.add(new JLabel(""));
        panelCenter.add(eveningTable);
        
        panelSouth.add(requestBtn);
        
        backBtn.addActionListener(a1 -> PresentationUtils.
                                                  returnToMainMenu(this));
        logOutBtn.addActionListener(a1 -> PresentationUtils.logout(this));             
    
        requestBtn.addActionListener(a1 -> doRequestButtonPress());       
  }
    
    private void doRequestButtonPress() {   
        String dayStr = dayTF.getText();
        String monthStr = monthTF.getText();
        String yearStr = yearTF.getText();

        try {
            Integer day = Integer.parseInt(dayStr);
            Integer month = Integer.parseInt(monthStr);
            Integer year = Integer.parseInt(yearStr);

            if ( ! Utils.isDateValid(day, month, year) ) {
                PresentationUtils.showSwingAlert("Ingevoerde datum is incorrect.");
                return;
            }
            
            Date d = new Date(day, month, year);
            
            DayPart[] dpArr = viewManager.getDayPartsForDate(d);

            setTableData(morningTable, dpArr[0]);
            setTableData(afternoonTable, dpArr[1]);
            int two = 2;
            setTableData(eveningTable, dpArr[two]);
        } catch(NumberFormatException nfe) {
            PresentationUtils.showSwingAlert("Ingevoerde datum is incorrect.");
        }
    }
    
    private void setTableData(JTable table, DayPart dp) {
        clearTable(table);
        
        DefaultTableModel tm = (DefaultTableModel)table.getModel();
        
        for (DayPartEmployee dpe : dp.getDpeList()) {
            Employee e = dpe.getEmployee();
            Object[] data = new Object[]{"" + e.getName(), "" + e.getFunction(),
                                "" + PresentationUtils.presenceStatusToDutchString(
                                        dpe.getPresenceStatus())};
            tm.addRow(data);
        }
        
        addBlankRows(table, NUMROWSPERTABLE);
    }
    
    private void clearTable(JTable table) {
        DefaultTableModel tm = (DefaultTableModel)table.getModel();
        
        int rowCount = tm.getRowCount();
        
        for (int i = 0; i < rowCount; i++) {
            tm.removeRow(0);
        }
    }
    
    private void addBlankRows(JTable table, int amount) {
        DefaultTableModel tm = (DefaultTableModel)table.getModel();
        
        for (int i = tm.getRowCount(); i < amount; i++) {
            tm.addRow(new Object[]{"","",""});
        }
    }
}

