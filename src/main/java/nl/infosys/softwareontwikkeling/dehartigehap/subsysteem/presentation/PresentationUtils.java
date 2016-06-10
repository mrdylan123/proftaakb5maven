package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DayPartType;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.PresenceStatus;

public class PresentationUtils {
    
    private PresentationUtils() {
    }
    
    public static void showSwingAlert(String msg) {
        JOptionPane.showMessageDialog(null, msg, "", 
                                            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showDutchUnableToOpenDatabaseConnectionAlert()
    {
        showSwingAlert("Kan geen verbinding met de database openen.");
    }
    
    public static void createWindow(JPanel panel, String windowName) {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE);
            frame.setTitle (windowName);
            frame.setContentPane(panel);
            frame.setExtendedState(frame.MAXIMIZED_BOTH);
            frame.setVisible(true);
    }
    
    public static  void destroyWindow(JPanel panel) {
       ((JFrame)SwingUtilities.getRoot(panel)).dispose();
    }
    
    public static void returnToMainMenu(JPanel panel) {
        PresentationUtils.createWindow(new MainMenuUI(), "Hoofdmenu");
        PresentationUtils.destroyWindow(panel);
    }
    
    public static void logout(JPanel currentPanel) {
        int dialogResult = JOptionPane.showConfirmDialog(null, 
                    "Weet u zeker dat u wilt uitloggen?","Waarschuwing",
                    JOptionPane.WARNING_MESSAGE);
        
        if(dialogResult == JOptionPane.YES_OPTION) {
            destroyWindow(currentPanel);
        }
    }
    
    public static String presenceStatusToDutchString(PresenceStatus ps) {
        switch(ps) {
            case PLANNED: return "Ingepland";
            case PRESENT: return "Aanwezig";              
            case NOTPRESENT: return "Niet aanwezig";             
            default: return "";
        }
    }
    
    public static String dayPartTypeToDutchString(DayPartType dpt) {
        switch(dpt) {
            case MORNING: return "Ochtend";
            case AFTERNOON: return "Middag";              
            case EVENING: return "Avond";             
            default: return "";
        }
    }
}
