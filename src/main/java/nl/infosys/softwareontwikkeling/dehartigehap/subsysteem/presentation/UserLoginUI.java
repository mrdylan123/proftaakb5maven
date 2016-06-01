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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.businesslogic.UserLoginManager;


public class UserLoginUI extends JPanel {
    private JTextField usernameTF, passwordTF;
    private JButton loginBtn;
    UserLoginManager ulm;
    
    public UserLoginUI()
    {
        ulm = new UserLoginManager();
        setLayout(new GridLayout(3,1, 2, 2));
        
        usernameTF = new JTextField("");
        passwordTF = new JTextField("");
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
            PresentationUtils.createWindow(new MainMenuUI(), "Hoofdmenu");
        }
        else {
            PresentationUtils.showSwingAlert("Invalid username/password.");
        }
        
    }
}
