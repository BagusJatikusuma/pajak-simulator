/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakrestoranview;

import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

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
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
        this.setBackground(Color.decode("#f3f3f3"));
        
        PajakRestoranTableComponent restoranTableComponent = new PajakRestoranTableComponent();
        this.add(restoranTableComponent);
    }
    
}
