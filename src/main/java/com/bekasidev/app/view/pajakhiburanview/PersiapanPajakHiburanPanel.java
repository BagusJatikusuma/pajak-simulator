/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakhiburanview;

import com.bekasidev.app.view.pajakparkirview.*;
import com.bekasidev.app.view.MainFrame;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class PersiapanPajakHiburanPanel extends JPanel{

    public PersiapanPajakHiburanPanel() {
        ComponentCollectorProvider.addComponent("persiapan_pajak_hiburan_panel", this, null, null);
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
        PajakHiburanTablePanel tablePanel = new PajakHiburanTablePanel();
        tablePanel.setPreferredSize(new Dimension(mainFrame.getWidth()/2, 0));
        this.add(tablePanel, BorderLayout.LINE_START);
        
        PajakHiburanFormPanel formPanel = new PajakHiburanFormPanel();
        formPanel.setPreferredSize(new Dimension(mainFrame.getWidth()/2, 0));
        this.add(formPanel, BorderLayout.LINE_END);
    }
    
}
