/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.softwareontwikkeling.dehartigehap.subsysteem.presentation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;

/**
 *
 * @author maikel
 */
public class EmptyOnMouseClickListener implements MouseListener {
    private JTextField textField;
    
    public EmptyOnMouseClickListener(JTextField textField)
    {
        this.textField = textField;
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        textField.setText("");
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
}
