/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.main;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation.*;
import javax.swing.JFrame;

/**
 *
 * @author J. Bouman
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final JFXPanel fxPanelWeergave = new JFXPanel();       
                Platform.runLater(new Runnable() {
            @Override
            public void run(){
                WeergaveUI ui = new WeergaveUI();
                ui.initFX(fxPanelWeergave);
            }
       });
        
        final JFXPanel fxPanelInplannen = new JFXPanel();
        
                Platform.runLater(new Runnable() {
            @Override
            public void run(){
                InvoerUI ui = new InvoerUI();
                ui.initFX(fxPanelInplannen);
            }
       });
    }

    
    
}
