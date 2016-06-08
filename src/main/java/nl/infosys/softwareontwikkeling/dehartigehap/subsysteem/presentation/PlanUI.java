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
    JPanel panelNorth, panelCenter;
    private JButton terugKnop, loguitKnop, aanwezigheidKnop, inplanKnop, planningKnop, prestatieoverzichtKnop;
    
    public PlanUI() {
        setLayout (new BorderLayout() );
        
        // Het paneel dat bovenaan komt, met de terugknop en de uitlogknop
        panelNorth = new JPanel();
        
        // Het paneel dat in het midden komt, met de knoppen om te navigeren naar de verschillende usecases
        panelCenter = new JPanel();
        
        int COLUMNS = 1;
        int ROWS = 3;
        int COLUMNSCENTER = 4;
        
        panelNorth.setLayout ( new GridLayout( COLUMNS, ROWS ) );      
        panelCenter.setLayout ( new GridLayout( COLUMNSCENTER, ROWS ) );
        
        // De knoppen op het paneel
        terugKnop = new JButton ("<--");
        loguitKnop = new JButton ("Uitloggen");
        
        aanwezigheidKnop = new JButton ("Aanwezigheid opvragen");
        inplanKnop = new JButton ("Inplannen");
        planningKnop = new JButton ("Planning bekijken");
        prestatieoverzichtKnop = new JButton ("Prestatieoverzicht opvragen");
        
        // Voeg de 2 bovenste knoppen toe aan het bovenste paneel
        panelNorth.add(terugKnop);
        panelNorth.add(new JLabel(""));
        panelNorth.add(loguitKnop);
        
        // Voeg de knoppen toe om te navigeren naar de verschillende usecases aan het middelste paneel
        panelCenter.add(new JLabel(""));
        panelCenter.add(aanwezigheidKnop);
        panelCenter.add(new JLabel(""));
        panelCenter.add(new JLabel(""));
        panelCenter.add(inplanKnop);
        panelCenter.add(new JLabel(""));
        panelCenter.add(new JLabel(""));
        panelCenter.add(planningKnop);
        panelCenter.add(new JLabel(""));
        panelCenter.add(new JLabel(""));
        panelCenter.add(prestatieoverzichtKnop);
        panelCenter.add(new JLabel(""));
        
        // Voeg de panelen toe aan het hoofdpaneel
        add (panelNorth, BorderLayout.NORTH);
        add (panelCenter, BorderLayout.CENTER);
        
    }
}
