package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.*;
import javax.swing.*;

public class MainMenuUI extends JPanel {
    private JButton inputBtn, editBtn, viewBtn, statsBtn, presenceBtn, logoutBtn;
    
    private static final int ROWS = 1;
    private static final int COLUMNS = 3;
    private static final int SPACING = 2;            
                
    public MainMenuUI() {    
        setLayout(new GridLayout(COLUMNS, ROWS, SPACING, SPACING));
        
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
