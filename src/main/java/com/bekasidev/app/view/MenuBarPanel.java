/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author bagus
 */
public class MenuBarPanel extends JPanel {

    public MenuBarPanel() {
        init();
    }
    
    public void init() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.BLUE);
        
        JLabel file = new JLabel("File");
        JLabel restoran = new JLabel("Restoran");
        JLabel hotel = new JLabel("Hotel");
        JLabel parkiran = new JLabel("Parkiran");
        
        this.add(file);
        this.add(restoran);
        this.add(hotel);
        this.add(parkiran);
    }
    
}
