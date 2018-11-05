/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.view.util.ComponentCollectorProvider;
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
        this.setLayout(new GridLayout(0,1));
        
        MenuBarPanel menuBarPanel = new MenuBarPanel();
//        MainFrame mainFrame = ComponentColle
        menuBarPanel.setPreferredSize(
                new Dimension(0, 0));
        
        this.add(menuBarPanel);
    }
    
    
   
}
