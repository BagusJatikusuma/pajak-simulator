/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakparkirview;

import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class PajakParkirPanel extends JPanel{

    public PajakParkirPanel() {
        ComponentCollectorProvider.addComponent("pajak_parkir_panel", this, null, null);
        init();
    }
    
    public void init(){
        this.setLayout(new BorderLayout());
        
        PersiapanPajakParkirPanel persiapanPajakParkirPanel = new PersiapanPajakParkirPanel();
        this.add(persiapanPajakParkirPanel, BorderLayout.CENTER);
    }
}
