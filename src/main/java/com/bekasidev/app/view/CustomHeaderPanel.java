/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author bagus
 */
public class CustomHeaderPanel extends JPanel {

    public CustomHeaderPanel() {
        init();
    }
    
    public void init() {
        this.setBackground(Color.decode("#144429"));
        this.setPreferredSize(new Dimension(0,35));
    }
    
}
