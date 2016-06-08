/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import javafx.scene.control.Alert;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartType;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.PresenceStatus;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.main.Main2;

/**
 *
 * @author maikel
 */
public class PresentationUtils {
    
    private PresentationUtils() {}
    
    static public void showSwingAlert(String msg) 
    {
        JOptionPane.showMessageDialog(null, msg, "", 
                                            JOptionPane.INFORMATION_MESSAGE);
    }
    
    static public void createWindow(JPanel panel, String windowName)
    {
            JFrame frame = new JFrame();
            frame.setSize( 400, 280);
            frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE);
            frame.setTitle (windowName);
            frame.setContentPane(panel);
            frame.setExtendedState(frame.MAXIMIZED_BOTH);
            frame.setVisible(true);
    }
    
    static public void destroyWindow(JPanel panel)
    {
       ((JFrame)SwingUtilities.getRoot(panel)).dispose();
    }
    
    static public void returnToMainMenu(JPanel panel)
    {
        PresentationUtils.createWindow(new MainMenuUI(), "Hoofdmenu");
        PresentationUtils.destroyWindow(panel);
    }
    
    static public void logout(JPanel currentPanel)
    {
        int dialogResult = JOptionPane.showConfirmDialog(null, 
                    "Weet u zeker dat u wilt uitloggen?","Waarschuwing",
                    JOptionPane.WARNING_MESSAGE);
        
        if(dialogResult == JOptionPane.YES_OPTION)
        {
            destroyWindow(currentPanel);
        }
    }
    
    static String presenceStatusToDutchString(PresenceStatus ps)
    {
        switch(ps)
        {
            case PLANNED: return "Ingepland";
            case PRESENT: return "Aanwezig";              
            case NOTPRESENT: return "Niet aanwezig";             
            default: return "";
        }
    }
    
    static String dayPartTypeToDutchString(DayPartType dpt)
    {
        switch(dpt)
        {
            case MORNING: return "Ochtend";
            case AFTERNOON: return "Middag";              
            case EVENING: return "Avond";             
            default: return "";
        }
    }
}
