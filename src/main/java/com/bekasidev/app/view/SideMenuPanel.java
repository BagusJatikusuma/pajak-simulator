/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * referensi pembuatan sidebar https://github.com/k33ptoo/SwingInspire
 * @author bagus
 */
public class SideMenuPanel extends JPanel {
    JFrame mainFrame;
    
    public SideMenuPanel() {
        super();
    }
    
    public SideMenuPanel(JFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }
    
    public void initPanel() {
        this.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        this.setBackground(new java.awt.Color(23, 35, 51));
        this.setPreferredSize(new Dimension(200, 480));
        
        addRestaurantPanelButton();
        addHotelPanelButton();
        addParkiranPanelButton();
       
    }
    /**
     * 
     */
    private void addRestaurantPanelButton() {
        btnRestaurantPanel = new JPanel();
        
        btnRestaurantPanel.setBackground(new java.awt.Color(23, 35, 51));
        btnRestaurantPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                restaurantPanelButtonPressed(evt);
            }
        });
        
        btnRestaurantPanelLabel = new JLabel("Restaurant");
        btnRestaurantPanelLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnRestaurantPanelLabel.setForeground(new java.awt.Color(255, 255, 255));
        
        ind_1 = new JPanel();
        ind_1.setOpaque(false);
        ind_1.setPreferredSize(new java.awt.Dimension(3, 43));
        
        javax.swing.GroupLayout ind_1Layout = new javax.swing.GroupLayout(ind_1);
        ind_1.setLayout(ind_1Layout);
        ind_1Layout.setHorizontalGroup(
            ind_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_1Layout.setVerticalGroup(
            ind_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        
        javax.swing.GroupLayout btnRestaurantPanelLayout 
                = new javax.swing.GroupLayout(btnRestaurantPanel);
        
        btnRestaurantPanel.setLayout(btnRestaurantPanelLayout);
        
        btnRestaurantPanelLayout.setHorizontalGroup(
            btnRestaurantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRestaurantPanelLayout.createSequentialGroup()
                .addComponent(ind_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnRestaurantPanelLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btnRestaurantPanelLayout.setVerticalGroup(
            btnRestaurantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRestaurantPanelLayout.createSequentialGroup()
                .addComponent(ind_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btnRestaurantPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRestaurantPanelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
       
        this.add(btnRestaurantPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 200, -1));
    }
    
    private void addHotelPanelButton() {
        btnHotelPanel = new JPanel();
        
        btnHotelPanel.setBackground(new java.awt.Color(23, 35, 51));
        btnHotelPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hotelPanelButtonPressed(evt);
            }
        });
        
        ind_2 = new JPanel();
        ind_2.setOpaque(false);
        ind_2.setPreferredSize(new java.awt.Dimension(3, 43));
        
        javax.swing.GroupLayout ind_2Layout = new javax.swing.GroupLayout(ind_2);
        ind_2.setLayout(ind_2Layout);
        ind_2Layout.setHorizontalGroup(
            ind_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_2Layout.setVerticalGroup(
            ind_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        
        btnHotelPanelLabel = new JLabel();
        btnHotelPanelLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnHotelPanelLabel.setForeground(new java.awt.Color(255, 255, 255));
        btnHotelPanelLabel.setText("hotel");
        
        javax.swing.GroupLayout btn_2Layout = new javax.swing.GroupLayout(btnHotelPanel);
        btnHotelPanel.setLayout(btn_2Layout);
        btn_2Layout.setHorizontalGroup(
            btn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_2Layout.createSequentialGroup()
                .addComponent(ind_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnHotelPanelLabel)
                .addGap(0, 40, Short.MAX_VALUE))
        );
        btn_2Layout.setVerticalGroup(
            btn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_2Layout.createSequentialGroup()
                .addComponent(ind_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btn_2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHotelPanelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        this.add(btnHotelPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 200, -1));
    }
    
    private void addParkiranPanelButton() {
        btnParkiranPanel = new JPanel();
        
        btnParkiranPanel.setBackground(new java.awt.Color(23, 35, 51));
        btnParkiranPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                parkiranPanelButtonPressed(evt);
            }
        });
        
        ind_3 = new JPanel();
        ind_3.setOpaque(false);
        ind_3.setPreferredSize(new java.awt.Dimension(3, 43));
        
        javax.swing.GroupLayout ind_3Layout = new javax.swing.GroupLayout(ind_3);
        ind_3.setLayout(ind_3Layout);
        ind_3Layout.setHorizontalGroup(
            ind_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind_3Layout.setVerticalGroup(
            ind_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        
        btnParkiranPanelLabel = new JLabel();
        btnParkiranPanelLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnParkiranPanelLabel.setForeground(new java.awt.Color(255, 255, 255));
        btnParkiranPanelLabel.setText("parkiran");
        
        javax.swing.GroupLayout btn_3Layout = new javax.swing.GroupLayout(btnParkiranPanel);
        btnParkiranPanel.setLayout(btn_3Layout);
        btn_3Layout.setHorizontalGroup(
            btn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_3Layout.createSequentialGroup()
                .addComponent(ind_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnParkiranPanelLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btn_3Layout.setVerticalGroup(
            btn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_3Layout.createSequentialGroup()
                .addComponent(ind_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(btn_3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnParkiranPanelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        this.add(btnParkiranPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 200, -1));
    }
    
    private void restaurantPanelButtonPressed(MouseEvent evt) {
        System.out.println("ditekan tombol 1");
        setColor(btnRestaurantPanel);   
        ind_1.setOpaque(true);
        
//        JPanel subContentMain = (JPanel) mainFrame.getContentPane().getComponent(2);
//        
//        ContentPanel contentPanel = (ContentPanel) subContentMain.getComponent(1);
//        contentPanel.addTable();
        
//        subContentMain.revalidate();
//        subContentMain.repaint();  
    }
    private void hotelPanelButtonPressed(MouseEvent evt) {
        System.out.println("ditekan tombol 2");
        setColor(btnHotelPanel);
        ind_2.setOpaque(true);
    }
    private void parkiranPanelButtonPressed(MouseEvent evt) {
        System.out.println("ditekan tombol 3");
        setColor(btnParkiranPanel);
        ind_3.setOpaque(true);
    }
    
    private void setColor(JPanel pane) {
        pane.setBackground(new Color(41,57,80));
    }
    
    private JPanel btnRestaurantPanel;
    private JLabel btnRestaurantPanelLabel;
    private JPanel ind_1;
    private JPanel btnHotelPanel;
    private JLabel btnHotelPanelLabel;
    private JPanel ind_2;
    private JPanel btnParkiranPanel;
    private JLabel btnParkiranPanelLabel;
    private JPanel ind_3;
}
