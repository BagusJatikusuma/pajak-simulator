/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author bagus
 */
public class MenuContainer extends JPanel {

    public MenuContainer() {
        init();
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        
        MenuBarPanel menuBarPanel = new MenuBarPanel();
        
        MenuBarPanelRestoran menuBarPanelRestoran = new MenuBarPanelRestoran();
        menuBarPanelRestoran.init();
        
        this.add(menuBarPanel, BorderLayout.PAGE_START);
        this.add(menuBarPanelRestoran, BorderLayout.CENTER);
    }
    
    
   
}
