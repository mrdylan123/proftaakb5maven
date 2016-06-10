package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.UserLoginManager;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.domain.DatabaseConnectionException;

public class UserLoginUI extends JPanel {
    private JTextField usernameTF; 
    private JPasswordField passwordTF;
    private JButton loginBtn, exitBtn;
    private UserLoginManager ulm;
        
    private static final int COLUMNS = 1;
    private static final int ROWS = 5;
    private static final int SPACING = 6;
    
    public UserLoginUI() {      
        ulm = new UserLoginManager();
        
        setLayout(new GridLayout(ROWS, COLUMNS, SPACING, SPACING));
        
        usernameTF = new JTextField("");
        passwordTF = new JPasswordField();
        loginBtn = new JButton("Login");
        exitBtn = new JButton("Afsluiten");
        
        add(usernameTF);
        add(passwordTF);
        add(loginBtn);
        add(exitBtn);
        
        loginBtn.addActionListener(a1 -> loginButtonPress());
        exitBtn.addActionListener(a1 -> exitButtonPress());
    }
    
    public void loginButtonPress() {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        
        try {
            if (ulm.checkPassword(username, password)) {
                /* create main menu window */
                PresentationUtils.createWindow(new MainMenuUI(), 
                                                    "Hoofdmenu");

                PresentationUtils.destroyWindow(this);
            } else {
                PresentationUtils.showSwingAlert("Incorrecte gebruikersnaam/wachtwoord");
            }
        } catch(DatabaseConnectionException dce) {
            Logger.getLogger(UserLoginUI.class.getName()).log(Level.OFF, null, dce);
            PresentationUtils.showDutchUnableToOpenDatabaseConnectionAlert();
        }
            
    }
    
    public void exitButtonPress() {
        PresentationUtils.logout(this);
    }
}
