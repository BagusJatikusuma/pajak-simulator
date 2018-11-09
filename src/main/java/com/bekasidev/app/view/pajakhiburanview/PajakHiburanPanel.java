/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakhiburanview;

import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class PajakHiburanPanel extends JPanel{

    public PajakHiburanPanel() {
        ComponentCollectorProvider.addComponent("pajak_hiburan_panel", this, null, null);
        init();
    }
    
    public void init(){
        this.setLayout(new BorderLayout());
        
        PersiapanPajakHiburanPanel persiapanPajakHiburanPanel = new PersiapanPajakHiburanPanel();
        this.add(persiapanPajakHiburanPanel, BorderLayout.CENTER);
    }
    
}
