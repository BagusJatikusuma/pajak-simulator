/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
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
     * init main frame with its attribute in whole this app
     * 
     */
    public void init(short width, short height, short xPos, short yPos) {
        this.setBounds(xPos, yPos, width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
        addContentPanel();
        
        /**
         * add copyright panel
         */

    }
    
    private void addSideMenuBar() {
        SideMenuBar sideMenuBar = new SideMenuBar();
        sideMenuBar.addMenu("file");
        sideMenuBar.addMenu("option");
    }
    
    private void addMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        this.add(mainPanel);
    }
    
    private void addContentPanel() {
        ContentPanel contentPanel = new ContentPanel();
        contentPanel.initPanel();
        
        this.add(contentPanel, BorderLayout.CENTER);
    }
   
}
