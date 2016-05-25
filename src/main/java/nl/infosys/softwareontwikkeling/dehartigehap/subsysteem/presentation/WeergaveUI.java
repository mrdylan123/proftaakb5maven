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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPart;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartEmployee;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.Employee;

/**
 *
 * @author J. Bouman
 */
public class WeergaveUI extends JPanel{
    
    private JButton backBtn, logOutBtn, requestBtn;
    private JLabel dateLbl;
    private JTextArea barTA, keukenTA;
    private JTextField dayTF, monthTF, yearTF;
    private JPanel panelNorth;
    private JPanel panelCenter;
    private JPanel panelSouth;
    private PresentationManager presentationManager;
    
    public WeergaveUI() {   
        presentationManager = new PresentationManager();
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
        barTA= new JTextArea ("De weergave van bar");
        keukenTA = new JTextArea ("De weergave van keuken.");
        requestBtn = new JButton ("Vraag op");
        
        dayTF = new JTextField("dag");
        monthTF = new JTextField("maand");
        yearTF = new JTextField("jaar");
               
        
        panelNorth.add(backBtn);
        panelNorth.add(Box.createHorizontalStrut(100));
        panelNorth.add(logOutBtn);
        
        panelCenter.add(dateLbl);
        panelCenter.add(dayTF);
        panelCenter.add(monthTF);
        panelCenter.add(yearTF);
        
        panelCenter.add(barTA);
        panelCenter.add(keukenTA);
        
        panelSouth.add(requestBtn);
        
        //Reset TextField when mouse click
        dayTF.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        dayTF.setText("");
                    }
                    });
                
        // Add a listener to the textfield, so that when the textfield is clicked, it removes the placeholder text.
        monthTF.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        monthTF.setText("");
                    }
                    });
                  
        // Add a listener to the textfield, so that when the textfield is clicked, it removes the placeholder text.
        yearTF.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        yearTF.setText("");
                    }
                    });
            
    logOutBtn.addActionListener(A1-> {
     barTA.setText("Gelukt!");
});      
    
    requestBtn.addActionListener(A1 -> {        
     
     String dayStr = dayTF.getText();
     String monthStr = monthTF.getText();
     String yearStr = yearTF.getText();
     
     try
     {
         DayPart[] dpArr = presentationManager.OnRequestButtonPress(
                 dayStr, monthStr, yearStr);

         String s = "";

         for (DayPart dp : dpArr)
         {
             s += getFormattedOutputForDayPart(dp);
         }

         barTA.setText(s);
         keukenTA.setText(s);
     }
     catch(DateInvalidException die)
     {
         PresentationUtils.showSwingAlert("Entered date is invalid");
     }
    });}
    
     public String getFormattedOutputForDayPart(DayPart dp)
    {
        String s = dp.getDate().toSQLString();
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

