/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakpeneranganjalanview;

import com.bekasidev.app.view.pajakparkirview.*;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class PajakPeneranganJalanFormPanel extends JPanel{

    public PajakPeneranganJalanFormPanel() {
        ComponentCollectorProvider.addComponent("pajak_penerangan_jalan_form_panel", this, null, null);
        init();
    }
    
    public void init(){
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
        this.setBackground(Color.decode("#e6e6e6"));
        
        FormPersiapanPeneranganJalanPanel formPersiapanPanel = new FormPersiapanPeneranganJalanPanel();
        formPersiapanPanel.setLocation(this.getWidth()/2, this.getHeight()/2);
        formPersiapanPanel.setSize(500, 500);
        this.add(formPersiapanPanel);
    }
    
}
