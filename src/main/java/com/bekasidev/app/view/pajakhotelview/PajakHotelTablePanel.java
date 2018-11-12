/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakhotelview;

import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class PajakHotelTablePanel extends JPanel{

    public PajakHotelTablePanel() {
        ComponentCollectorProvider.addComponent("pajak_hotel_table_panel", this, null, null);
        init();
    }
     
    public void init(){
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
        this.setBackground(Color.GREEN);
        
        PajakHotelTableComponent pajakHotelTableComponent = new PajakHotelTableComponent();
        this.add(pajakHotelTableComponent);
    }
}
