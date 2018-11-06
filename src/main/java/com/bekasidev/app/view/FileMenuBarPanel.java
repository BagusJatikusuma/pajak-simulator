/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author bagus
 */
public class FileMenuBarPanel extends JPanel{

    public FileMenuBarPanel() {
        init();
    }
    
    public void init() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.decode("#f3f3f3"));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#c7c7c7")));
        
    }
    
}
