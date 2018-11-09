/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakhotelview;

import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class PajakHotelPanel extends JPanel{

    public PajakHotelPanel() {
        ComponentCollectorProvider.addComponent("pajak_hotel_panel", this, null, null);
        init();
    }
    
    public void init(){
        this.setLayout(new BorderLayout());
        
        PersiapanPajakHotelPanel persiapanPajakHotelPanel = new PersiapanPajakHotelPanel();
        this.add(persiapanPajakHotelPanel, BorderLayout.CENTER);
    }
}
