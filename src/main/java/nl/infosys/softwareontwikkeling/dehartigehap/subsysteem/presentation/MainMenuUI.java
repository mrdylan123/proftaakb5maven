package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.*;
import javax.swing.*;

public class MainMenuUI extends JPanel {
    private JButton inputBtn, editBtn, viewBtn, statsBtn, presenceBtn, logoutBtn;
    
    public MainMenuUI() {
        
        int rows = 1;
        int columns = 3;
        int spacing = 2;
        
        setLayout(new GridLayout(columns, rows, spacing, spacing));
        
        inputBtn = new JButton("Planning Invoeren");
        editBtn = new JButton("Planning wijzigen");
        viewBtn = new JButton("Planning bekijken");
        statsBtn = new JButton("Statistieken bekijken");
        presenceBtn = new JButton("Presentie bekijken");
        logoutBtn = new JButton("Uitloggen");
        
        add(inputBtn);
        add(editBtn);
        add(viewBtn);
        add(statsBtn);
        add(presenceBtn);
        add(logoutBtn);
       
        inputBtn.addActionListener(al -> newWindowButtonPress(new InputUI(),
                                                "Planning invoeren"));
        editBtn.addActionListener(al -> newWindowButtonPress(new EditUI(),
                                                "Planning wijzigen"));
        viewBtn.addActionListener(al -> newWindowButtonPress(new ViewUI(),
                                                "Planning weergeven"));
        statsBtn.addActionListener(al -> newWindowButtonPress(new StatsUI(),
                                                "Statistieken weergeven"));

        presenceBtn.addActionListener(al -> newWindowButtonPress(new PresentionUI(),
                                                "Presentie bekijken"));
              
        logoutBtn.addActionListener(al -> logoutButtonPress());
    }
    
    public void logoutButtonPress() {
        PresentationUtils.logout(this);
    }
    
    public void newWindowButtonPress(JPanel panel, String windowTitle) {
        PresentationUtils.createWindow(panel, windowTitle);    
        PresentationUtils.destroyWindow(this);
    }
}
