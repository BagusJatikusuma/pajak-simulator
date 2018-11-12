/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.view.pajakhiburanview.PajakHiburanPanel;
import com.bekasidev.app.view.pajakhotelview.PajakHotelPanel;
import com.bekasidev.app.view.pajakparkirview.PajakParkirPanel;
import com.bekasidev.app.view.pajakpeneranganjalanview.PajakPeneranganJalanPanel;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.JPanel;

/**
 *
 * @author bagus
 */
public class MenuContainer extends JPanel {

    public MenuContainer() {
        ComponentCollectorProvider.addComponent("menu_container", this, null, null);
        init();
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        
        CustomHeaderPanel customHeaderPanel = new CustomHeaderPanel();
        MenuBarPanel menuBarPanel = new MenuBarPanel();
        
        MenuBarPanelRestoran menuBarPanelRestoran = new MenuBarPanelRestoran();
        menuBarPanelRestoran.init();
        FileMenuBarPanel fileMenuBarPanel = new FileMenuBarPanel();
        PajakHotelPanel pajakHotelPanel = new PajakHotelPanel();
        PajakParkirPanel pajakParkirPanel = new PajakParkirPanel();
        PajakHiburanPanel pajakHiburanPanel = new PajakHiburanPanel();
        PajakPeneranganJalanPanel pajakPeneranganJalanPanel = new PajakPeneranganJalanPanel();
        
//        this.add(customHeaderPanel, BorderLayout.PAGE_START);
        this.add(menuBarPanel, BorderLayout.CENTER);
        this.add(menuBarPanelRestoran, BorderLayout.PAGE_END);
        this.add(fileMenuBarPanel, BorderLayout.PAGE_END);
        
        ComponentCollectorProvider.addComponent("custom_header_panel", customHeaderPanel, null, this);
        ComponentCollectorProvider.addComponent("menu_bar_panel", menuBarPanel, null, this);
        ComponentCollectorProvider.addComponent("menu_bar_panel_restoran", menuBarPanelRestoran, null, this);
        ComponentCollectorProvider.addComponent("file_menu_bar_panel", fileMenuBarPanel, null, this);
        
        ComponentCollectorProvider.addComponentChild(
                Arrays.asList((Component)customHeaderPanel, menuBarPanel, menuBarPanelRestoran, fileMenuBarPanel), 
                "menu_container");
    }
    
    
   
}
