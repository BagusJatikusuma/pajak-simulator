/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author bagus
 */
public class MenuBarPanel extends JPanel {
    private MainFrame mainFrame;

    public MenuBarPanel() {
        super();
    }

    public MenuBarPanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        
        JMenuBar menuBar;
        JMenu menuFile, menuHelp, submenu;
        
        JMenuItem menuItem;
        
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);

        //Build the first menu.
        menuFile = new JMenu("File");
        menuFile.setMnemonic(KeyEvent.VK_A);
        menuFile.getAccessibleContext().setAccessibleDescription("Open File menu");
        menuBar.add(menuFile);
        
        //Build the first menu.
        menuHelp = new JMenu("Help");
        menuHelp.setMnemonic(KeyEvent.VK_A);
        menuHelp.getAccessibleContext().setAccessibleDescription("Open Help menu");
        menuBar.add(menuHelp);
        
        //menuFile item
        menuItem = new JMenuItem("Atur Restoran",
                         KeyEvent.VK_T);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Atur Restoran");
        menuItem.setBackground(Color.WHITE);
        menuItem.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                restaurantItemClicked(evt);
            }
        });
        menuFile.add(menuItem);
        
        menuItem = new JMenuItem("Atur Hotel",
                         KeyEvent.VK_T);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Atur Hotel");
        menuItem.setBackground(Color.WHITE);
        menuItem.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hotelItemClicked(evt);
            }
        });
        menuFile.add(menuItem);
        
        menuItem = new JMenuItem("Atur Parkiran",
                         KeyEvent.VK_T);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "close application");
        menuItem.setBackground(Color.WHITE);
        menuItem.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                parkiranItemClicked(evt);
            }
        });
        menuFile.add(menuItem);
        
        menuItem = new JMenuItem("Exit",
                         KeyEvent.VK_T);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "close application");
        menuItem.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitItemClicked(evt);
            }
        });
        menuFile.add(menuItem);
        
        //menuHelp About
        menuItem = new JMenuItem("About",
                         KeyEvent.VK_T);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "See about us");
        menuItem.setBackground(Color.WHITE);
        menuItem.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                aboutItemClicked(evt);
            }
        });
        menuHelp.add(menuItem);
        
        this.add(menuBar, BorderLayout.CENTER);
    }
    
    private void restaurantItemClicked(MouseEvent evt) {
        System.out.println("restoran clicked");
        
        SubContentMain contentMain = new SubContentMain(mainFrame);
        contentMain.initSubContentMain();
        
        mainFrame.getContentPane().remove(1);
        mainFrame.getContentPane().add(contentMain, BorderLayout.CENTER);
        
        mainFrame.invalidate();
        mainFrame.validate();
    }
    
    private void hotelItemClicked(MouseEvent evt) {
        System.out.println("hotel clicked");
    }
    
    private void parkiranItemClicked(MouseEvent evt) {
        System.out.println("parkiran clicked");
    }
    
    private void exitItemClicked(MouseEvent evt) {
        System.out.println("exit clicked");
        mainFrame.dispose();
    }
    
    private void aboutItemClicked(MouseEvent evt) {
        System.out.println("about clicked");
    }
    
}
