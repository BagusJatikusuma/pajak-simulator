/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.view.util.ComponentCollector;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
        ComponentCollectorProvider.addComponent("main_frame",this, new ArrayList<Component>(), null);
        
        Dimension screenSize = this.getToolkit().getScreenSize();
        
        this.setSize((int) (screenSize.getWidth()-50), (int) (screenSize.getHeight()-75));
        
        int x = (int) screenSize.getWidth() / 2 - this.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - this.getHeight() / 2;
        this.setLocation(x, y);
        
        this.setTitle("Aplikasi Perpajakan Kabupaten Bekasi");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        
        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        
        addMenuBar();
        
        
    }
    
    private void addMenuBar() {
        MenuContainer menuContainer = new MenuContainer();
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        this.add(menuContainer, BorderLayout.PAGE_START);
        this.add(panel, BorderLayout.CENTER);
        
        ComponentCollectorProvider.addComponentChild(
                Arrays.asList((Component)menuContainer), "main_frame");
    }
}
