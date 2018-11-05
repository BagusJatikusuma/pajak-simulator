/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.view.util.ComponentCollector;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.viewold.FrameDragListener;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JFrame;

/**
 *
 * @author bagus
 */
public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        super();
        init();
    }
    
    /**
     * init main frame with its attribute
     */
    public void init() {
        //add into component collector
        Map<String, ComponentCollector> comMap 
                = ComponentCollectorProvider.getComponentMapper();
        ComponentCollector compCollector 
                = new ComponentCollector("main_frame",this, new ArrayList<Component>(), null);
        comMap.put("main_frame", compCollector);
        
        Dimension screenSize = this.getToolkit().getScreenSize();
        
        this.setSize((int) (screenSize.getWidth()-50), (int) (screenSize.getHeight()-75));
        
        int x = (int) screenSize.getWidth() / 2 - this.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - this.getHeight() / 2;
        this.setLocation(x, y);
        
        this.setTitle("Aplikasi Perpajakan Kabupaten Bekasi");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        addMenuBar();
        
        
    }
    
    private void addMenuBar() {
//        MenuContainer menuContainer = new MenuContainer();
//        menuContainer.init();
        
//        this.add(menuContainer, BorderLayout.PAGE_START);

        MenuBarPanelRestoran menuBarPanelRestoran = new MenuBarPanelRestoran();
        menuBarPanelRestoran.init();

        this.add(menuBarPanelRestoran, BorderLayout.PAGE_START);
    }
}
