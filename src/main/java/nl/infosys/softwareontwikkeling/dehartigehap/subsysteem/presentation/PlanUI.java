/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author J. Bouman
 */
public class PlanUI extends JPanel{
    JPanel paneelNoord, paneelCentrum;
    private JButton terugKnop, loguitKnop, aanwezigheidKnop, inplanKnop, planningKnop, prestatieoverzichtKnop;
    
    public PlanUI() {
        setLayout (new BorderLayout() );
        
        // Het paneel dat bovenaan komt, met de terugknop en de uitlogknop
        paneelNoord = new JPanel();
        
        // Het paneel dat in het midden komt, met de knoppen om te navigeren naar de verschillende usecases
        paneelCentrum = new JPanel();
        
        paneelNoord.setLayout ( new GridLayout( 1 , 3 ) );
        
        paneelCentrum.setLayout ( new GridLayout( 4, 3 ) );
        
        // De knoppen op het paneel
        terugKnop = new JButton ("<--");
        loguitKnop = new JButton ("Uitloggen");
        
        aanwezigheidKnop = new JButton ("Aanwezigheid opvragen");
        inplanKnop = new JButton ("Inplannen");
        planningKnop = new JButton ("Planning bekijken");
        prestatieoverzichtKnop = new JButton ("Prestatieoverzicht opvragen");
        
        // Voeg de 2 bovenste knoppen toe aan het bovenste paneel
        paneelNoord.add(terugKnop);
        paneelNoord.add(new JLabel(""));
        paneelNoord.add(loguitKnop);
        
        // Voeg de knoppen toe om te navigeren naar de verschillende usecases aan het middelste paneel
        paneelCentrum.add(new JLabel(""));
        paneelCentrum.add(aanwezigheidKnop);
        paneelCentrum.add(new JLabel(""));
        paneelCentrum.add(new JLabel(""));
        paneelCentrum.add(inplanKnop);
        paneelCentrum.add(new JLabel(""));
        paneelCentrum.add(new JLabel(""));
        paneelCentrum.add(planningKnop);
        paneelCentrum.add(new JLabel(""));
        paneelCentrum.add(new JLabel(""));
        paneelCentrum.add(prestatieoverzichtKnop);
        paneelCentrum.add(new JLabel(""));
        
        // Voeg de panelen toe aan het hoofdpaneel
        add (paneelNoord, BorderLayout.NORTH);
        add (paneelCentrum, BorderLayout.CENTER);
        
    }
}
