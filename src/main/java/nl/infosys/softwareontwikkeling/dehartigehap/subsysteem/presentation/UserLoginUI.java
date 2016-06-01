/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.UserLoginManager;


public class UserLoginUI extends JPanel {
    private JTextField usernameTF; 
    private JPasswordField passwordTF;
    private JButton loginBtn;
    private UserLoginManager ulm;
    
    public UserLoginUI()
    {
        
        ulm = new UserLoginManager();
        setLayout(new GridLayout(3,1, 2, 2));
        
        usernameTF = new JTextField("");
        passwordTF = new JPasswordField();
        loginBtn = new JButton("Login");
        
        add(usernameTF);
        add(passwordTF);
        add(loginBtn);
        
        loginBtn.addActionListener(a1 -> loginButtonPress());
    }
    
    public void loginButtonPress()
    {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        
        if ( ulm.checkPassword(username, password) )
        {
            /* create main menu window */
            JFrame frame = new JFrame();
            PresentationUtils.createWindow(new MainMenuUI(), 
                                                "Hoofdmenu");
            
            PresentationUtils.destroyWindow(this);
        }
        else {
            PresentationUtils.showSwingAlert("Invalid username/password.");
        }
        
    }
}
