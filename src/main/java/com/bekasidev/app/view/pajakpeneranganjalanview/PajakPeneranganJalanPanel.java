/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakpeneranganjalanview;

import com.bekasidev.app.view.pajakparkirview.*;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class PajakPeneranganJalanPanel extends JPanel{

    public PajakPeneranganJalanPanel() {
        ComponentCollectorProvider.addComponent("pajak_penerangan_jalan_panel", this, null, null);
        init();
    }
    
    public void init(){
        this.setLayout(new BorderLayout());
        
        PersiapanPajakPeneranganJalanPanel persiapanPajakPeneranganJalanPanel = new PersiapanPajakPeneranganJalanPanel();
        this.add(persiapanPajakPeneranganJalanPanel, BorderLayout.CENTER);
    }
}
