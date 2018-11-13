/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.masterview;

import com.bekasidev.app.view.MainFrame;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author USER
 */
public class MasterWPPanel extends JPanel {

    public MasterWPPanel() {
        ComponentCollectorProvider.addComponent("master_wp_panel", this, null, null);
        init();
    }
    
    public void init() {
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.decode("#f3f3f3"));
        MainFrame mainFrame
                = (MainFrame) ComponentCollectorProvider
                .getComponentMapper()
                .get("main_frame")
                .getComponent();
        
        addToolsMasterWP();
        
        MasterWpTableComponent masterWPPanel = new MasterWpTableComponent();
        this.add(masterWPPanel, BorderLayout.CENTER);
        
    }
    
    private void addToolsMasterWP() {
        ToolsButtonPanel toolsButtonPanel = new ToolsButtonPanel();
        this.add(toolsButtonPanel, BorderLayout.PAGE_START);
    }
}
