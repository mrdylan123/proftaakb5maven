/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.main;

import javax.swing.JFrame;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation.StatsUI;

/**
 *
 * @author dyl
 */
public class main2 extends JFrame{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new main2();
        frame.setSize( 400, 280);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        frame.setTitle ("Testje");
        frame.setContentPane(new StatsUI());
        frame.setVisible(true);
    }
    
}
