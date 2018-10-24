/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
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
        this.setSize(width, height);
        
        Dimension screenSize = this.getToolkit().getScreenSize();
        int x = (int) screenSize.getWidth() / 2 - this.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - this.getHeight() / 2;
        this.setLocation(x, y);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        
        /**
         * add main panel to frame
         */
        addMainPanel();
        
        /**
         * add side menu bar panel
         */
        addSideMenuBar();
        
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
        SubContentMain subContentMain = new SubContentMain();
        subContentMain.initSubContentMain();
        
//        this.add(subContentMain, BorderLayout.CENTER);
        this.getContentPane().add(subContentMain, BorderLayout.CENTER);
    }
    
    private void addLandingPagePanel() {
        LandingPagePanel landingPagePanel = new LandingPagePanel();
        landingPagePanel.init();
        
        this.getContentPane().add(landingPagePanel, BorderLayout.CENTER);
    }
   
}
