/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author bagus
 */
public class SideMenuBar extends JMenuBar {
    private static final LayoutManager grid = new GridLayout(0,1);
    
    public SideMenuBar() {
        setLayout(grid);
    }
    
    public void addMenu(String menuName) {
        JMenu menu = new JMenu(menuName);
        menu.setMnemonic(KeyEvent.VK_F);
       
        this.add(menu);
    }
    
    public void deleteMenu(String menuName) 
            throws NullPointerException {
        this.remove(new JMenu(menuName));
    }
    
}
