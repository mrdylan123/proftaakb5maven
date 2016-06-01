/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import javafx.scene.control.Alert;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.main.main2;

/**
 *
 * @author maikel
 */
public class PresentationUtils {
    
    static public void showSwingAlert(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", 
                                            JOptionPane.INFORMATION_MESSAGE);
    }
    
    static void createWindow(JPanel panel, String windowName)
    {
            JFrame frame = new JFrame();
            frame.setSize( 400, 280);
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
            frame.setTitle (windowName);
            frame.setContentPane(panel);
            frame.setVisible(true);
    }
}
