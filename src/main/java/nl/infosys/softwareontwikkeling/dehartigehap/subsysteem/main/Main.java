package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.main;

import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation.*;
import javax.swing.JFrame;

public class Main extends JFrame{

    public static void main(String[] args) {
        JFrame frame = new Main();
        frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE);
        frame.setTitle ("Login");
        frame.setContentPane(new UserLoginUI());
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }  
    
}
