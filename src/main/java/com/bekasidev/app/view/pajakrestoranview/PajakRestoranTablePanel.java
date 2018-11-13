/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakrestoranview;

import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Bayu Arafli
 */
public class PajakRestoranTablePanel extends JPanel{

    public PajakRestoranTablePanel() {
        ComponentCollectorProvider.addComponent("pajak_restoran_table_panel", this, null, null);
        init();
    }
    
    public void init(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.decode("#f3f3f3"));
        
        JLabel judul = new JLabel("DAFTAR RESTORAN");
        judul.setFont(new Font("Tahoma", Font.BOLD, 20));
        judul.setHorizontalAlignment(JLabel.CENTER);
        judul.setVerticalAlignment(JLabel.CENTER);
        judul.setBorder(new EmptyBorder(15,0,10,0));
        this.add(judul, BorderLayout.PAGE_START);
        
        PajakRestoranTableComponent restoranTableComponent = new PajakRestoranTableComponent();
        this.add(restoranTableComponent, BorderLayout.CENTER);
    }
    
}
