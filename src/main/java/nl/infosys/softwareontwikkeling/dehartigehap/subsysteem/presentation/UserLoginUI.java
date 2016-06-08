package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.GridLayout;
import javax.swing.*;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.UserLoginManager;


public class UserLoginUI extends JPanel {
    private JTextField usernameTF; 
    private JPasswordField passwordTF;
    private JButton loginBtn, exitBtn;
    private UserLoginManager ulm;
    
    public UserLoginUI() {      
        ulm = new UserLoginManager();
        
        int COLUMNS = 1;
        int ROWS = 5;
        int SPACING = 6;
        
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
        
        if (ulm.checkPassword(username, password)) {
            /* create main menu window */
            PresentationUtils.createWindow(new MainMenuUI(), 
                                                "Hoofdmenu");
            
            PresentationUtils.destroyWindow(this);
        } else {
            PresentationUtils.showSwingAlert("Invalid username/password.");
        }   
    }
    
    public void exitButtonPress() {
        PresentationUtils.logout(this);
    }
}
