/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
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

        
        inputBtn.addActionListener(al -> newWindowButtonPress(new InputUI(),
                                                "Planning invoeren"));
        editBtn.addActionListener(al -> newWindowButtonPress(new EditUI(),
                                                "Planning wijzigen"));
        viewBtn.addActionListener(al -> newWindowButtonPress(new ViewUI(),
                                                "Planning weergeven"));
        statsBtn.addActionListener(al -> newWindowButtonPress(new StatsUI(),
                                                "Statistieken weergeven"));

        presenceBtn.addActionListener(al -> newWindowButtonPress(new PresenceUI(),
                                                "Presentie bekijken"));
        
        
        logoutBtn.addActionListener(al -> logoutButtonPress());
    }
    
    public void logoutButtonPress()
    {
        PresentationUtils.logout();
    }
    
    public void newWindowButtonPress(JPanel panel, String windowTitle)
    {
        PresentationUtils.createWindow(panel, 
                windowTitle);
        
        PresentationUtils.destroyWindow(this);
    }
}
