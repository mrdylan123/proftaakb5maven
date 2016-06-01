/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.main;

import javax.swing.JFrame;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.UserLoginManager;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.BCrypt;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation.*;

/**
 *
 * @author dyl
 */
public class main2 extends JFrame{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /* UserLoginManager ulm = new UserLoginManager();
        
        ulm.registerUser("Maikel", "test");
        
        boolean check = ulm.checkPassword("Maikel", "test");
        
        System.out.println(check); */
        
        {
            JFrame frame = new main2();
            frame.setSize( 400, 280);
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
            frame.setTitle ("Login");
            frame.setContentPane(new UserLoginUI());
            frame.setVisible(true);
        }
        
        /*{
            JFrame frame = new main2();
            frame.setSize( 400, 280);
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
            frame.setTitle ("Statistieken");
            frame.setContentPane(new StatsUI());
            frame.setVisible(true);
        }
        
        {
            JFrame frame = new main2();
            frame.setSize( 400, 280);
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
            frame.setTitle ("Edit Planning UI");
            frame.setContentPane(new EditUI());
            frame.setVisible(true);
        }
        
        {
            JFrame frame = new Main();
            frame.setTitle("Planning invoeren");     
            frame.setSize(500,500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new InputUI());
            frame.setVisible(true);
        }
        
        {
            JFrame frame = new Main();
            frame.setTitle("Planning weergave");     
            frame.setSize(500,500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new ViewUI());
            frame.setVisible(true);
        } */
    }
    
}
