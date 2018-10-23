/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.HeadlessException;
import javax.swing.JFrame;

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
        this.getContentPane().setLayout(null);
        
        /**
         * add side menu bar
         */
        addSideMenuBar();
    }
    
    public void addSideMenuBar() {
        SideMenuBar sideMenuBar = new SideMenuBar();
        sideMenuBar.addMenu("file");
        sideMenuBar.addMenu("option");
        
        this.setJMenuBar(sideMenuBar);
    }
   
}
