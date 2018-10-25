/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author bagus
 */
public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        super();
    }
    
    /**
     * init main frame with its attribute
     */
    public void init() {
    }
    /**
     * init main frame with its attribute in this whole app
     * 
     */
    public void init(short width, short height, short xPos, short yPos) {
//        this.setBounds(xPos, yPos, width, height);  
        Dimension screenSize = this.getToolkit().getScreenSize();
        
        this.setSize((int) (screenSize.getWidth()-50), (int) (screenSize.getHeight()-75));
        
        int x = (int) screenSize.getWidth() / 2 - this.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - this.getHeight() / 2;
        this.setLocation(x, y);
        
        this.setTitle("Aplikasi Perpajakan Kabupaten Bekasi");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        
        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("Size Changed");
                
            }
        });
        
        /**
         * add menu bar
         */
        addMenuBar();
        
        /**
         * add content menu panel
         */
        addLandingPagePanel();
        
        /**
         * add copyright panel
         */

    }
    
    private void addSideMenuBar() {
        SideMenuPanel sideMenuPanel = new SideMenuPanel(this);
        sideMenuPanel.initPanel();
        
//        this.add(sideMenuPanel, BorderLayout.LINE_START);
        this.getContentPane().add(sideMenuPanel, BorderLayout.LINE_START);
    }
    
    private void addMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        
//        this.add(mainPanel);
        this.getContentPane().add(mainPanel, 0);
    }
    
    private void addSubContentPanel() {
        SubContentMain subContentMain = new SubContentMain(this);
        subContentMain.initSubContentMain();
        
//        this.add(subContentMain, BorderLayout.CENTER);
        this.getContentPane().add(subContentMain, BorderLayout.CENTER);
    }
    
    private void addLandingPagePanel() {
        LandingMainPanel landingPagePanel = new LandingMainPanel(this);
        landingPagePanel.init();
        
        this.getContentPane().add(landingPagePanel, BorderLayout.CENTER);
    }
    
    private void addMenuBar() {
        //add menubar panel
        MenuBarPanel menuBarPanel = new MenuBarPanel(this);
        menuBarPanel.init();
        
        this.add(menuBarPanel, BorderLayout.PAGE_START);
    }
   
}
