/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakrestoranview;

import com.bekasidev.app.view.MainFrame;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class PajakRestoranPanel extends JPanel{

    public PajakRestoranPanel() {
        ComponentCollectorProvider.addComponent("pajak_restoran_panel", this, null, null);
        init();
    }
    
    public void init(){
        this.setLayout(new BorderLayout());
        
        PersiapanPajakRestoranPanel persiapanPajakRestoranPanel = new PersiapanPajakRestoranPanel();
        this.add(persiapanPajakRestoranPanel, BorderLayout.CENTER);
    }
    
}
