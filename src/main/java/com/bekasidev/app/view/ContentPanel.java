/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Bayu Arafli
 */
public class ContentPanel extends JPanel{

    public ContentPanel() {
        super();
        
    }
    
    public void initPanel(){
        // Setting for the panel
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxlayout);
        this.setBorder(new EmptyBorder(new Insets(70, 70, 70, 70)));
        
        // Define new buttons
        JButton jb1 = new JButton("Button 1");
        JButton jb2 = new JButton("Button 2");		
        JButton jb3 = new JButton("Button 3");

        // Add buttons to the frame (and spaces between buttons)
        this.add(jb1);	
        this.add(jb2);
        this.add(jb3);
    }
}
