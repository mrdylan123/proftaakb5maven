/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.main;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation.*;
import javax.swing.JFrame;

/**
 *
 * @author J. Bouman
 */
public class Main extends JFrame{

    /**
     * @param args the command line arguments
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new Main();
        frame.setSize( 400, 280);
        frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE);
        frame.setTitle ("Login");
        frame.setContentPane(new UserLoginUI());
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }  
    
}
