/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.Component;
import java.awt.Container;
import javafx.scene.control.Alert;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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
    
    static public void createWindow(JPanel panel, String windowName)
    {
            JFrame frame = new JFrame();
            frame.setSize( 400, 280);
            frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE);
            frame.setTitle (windowName);
            frame.setContentPane(panel);
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
    
    static public void logout()
    {
        int dialogResult = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to exit?","Warning",
                    JOptionPane.WARNING_MESSAGE);
        
        if(dialogResult == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }
    
    static public void removeCloseButton(Component comp) {
    if (comp instanceof JMenu) {
      Component[] children = ((JMenu) comp).getMenuComponents();
      for (int i = 0; i < children.length; ++i)
        removeCloseButton(children[i]);
    }
    else if (comp instanceof AbstractButton) {
      Action action = ((AbstractButton) comp).getAction();
      String cmd = (action == null) ? "" : action.toString();
      if (cmd.contains("CloseAction")) {
        comp.getParent().remove(comp);
      }
    }
    else if (comp instanceof Container) {
      Component[] children = ((Container) comp).getComponents();
      for (int i = 0; i < children.length; ++i)
        removeCloseButton(children[i]);
    }
  }
}
