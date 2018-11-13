/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakhotelview;

import com.bekasidev.app.view.MainFrame;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class PersiapanPajakHotelPanel extends JPanel{

    public PersiapanPajakHotelPanel() {
        ComponentCollectorProvider.addComponent("persiapan_pajak_hotel_panel", this, null, null);
        init();
    }
    
    public void init(){
        this.setLayout(new BorderLayout());
        
        MainFrame mainFrame
                = (MainFrame) ComponentCollectorProvider
                .getComponentMapper()
                .get("main_frame")
                .getComponent();
        // add component panel
        PajakHotelTablePanel tablePanel = new PajakHotelTablePanel();
        tablePanel.setPreferredSize(new Dimension(mainFrame.getWidth()/2, 0));
        this.add(tablePanel, BorderLayout.LINE_START);
        
        PajakHotelFormPanel formPanel = new PajakHotelFormPanel();
        formPanel.setPreferredSize(new Dimension(mainFrame.getWidth()/2, 0));
        this.add(formPanel, BorderLayout.LINE_END);
    }
}
