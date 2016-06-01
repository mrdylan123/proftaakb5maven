/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.UserLoginManager;

/**
 *
 * @author maikel
 */
public class MainMenuUI extends JPanel {
    private JButton inputBtn, editBtn, viewBtn, statsBtn, presenceBtn, logoutBtn;
    
    public MainMenuUI()
    {
        setLayout(new GridLayout(3,1, 2, 2));
        
        inputBtn = new JButton("Planning Invoeren");
        editBtn = new JButton("Planning wijzigen");
        viewBtn = new JButton("Planning bekijken");
        statsBtn = new JButton("Statistieken bekijken");
        presenceBtn = new JButton("Presentie bekijken");
        logoutBtn = new JButton("Uitloggen");
        
        add(inputBtn);
        add(editBtn);
        add(viewBtn);
        add(statsBtn);
        add(presenceBtn);
        add(logoutBtn);

        
        logoutBtn.addActionListener(a1 -> logoutButtonPress());
    }
    
    public void logoutButtonPress()
    {
        int dialogResult = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to exit?","Warning",
                    JOptionPane.WARNING_MESSAGE);
        
        if(dialogResult == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }
}
