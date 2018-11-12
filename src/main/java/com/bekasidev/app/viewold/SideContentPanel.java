/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewold;

import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.backend.WajibPajakService;
import com.bekasidev.app.service.backend.RestoranTransactionService;
import com.bekasidev.app.service.backend.impl.WajibPajakServiceImpl;
import com.bekasidev.app.service.backend.impl.RestoranTransactionServiceImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class SideContentPanel extends JPanel{
    
    // Variables declaration
    private String idRestoran;
    private MainFrame mainFrame;
    private SideContentPanel sideContentPanel;
    
    // Service
    private WajibPajakService wajibPajakService = new WajibPajakServiceImpl();
    private RestoranTransactionService restoranTransactionService = new RestoranTransactionServiceImpl();
    
    private JPanel  panelTampilRestaurant,
                    panelIdentitasRestoran;
    private JLabel  labelNamaRestoran;
    
    private JPanel parentPanel;
    
    public SideContentPanel() {
        super();
    }
    
    public SideContentPanel(MainFrame mainFrame){
        super();
        this.mainFrame = mainFrame;
    }
    public SideContentPanel(MainFrame mainFrame, JPanel parentPanel){
        super();
        this.mainFrame = mainFrame;
        this.parentPanel = parentPanel;
    }
    
    public void initSideContentPanel(){
        this.setPreferredSize(new Dimension((mainFrame.getWidth()/2), 250));
        this.setBackground(Color.WHITE);
        
        addInputRestaurantButton();
        showRestaurantTable();
    }
    
    private void showRestaurantTable() {
        //===== panel identitas restoran =====//
        panelTampilRestaurant = new JPanel(new BorderLayout());
        panelTampilRestaurant.setBackground(Color.WHITE);
        panelTampilRestaurant.setLocation(50, 700);
        panelTampilRestaurant.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "daftar restaurant"));
        
        RestaurantTableComponent restaurantTable = new RestaurantTableComponent(mainFrame, parentPanel);
        restaurantTable.init();
        
        panelTampilRestaurant.setSize(mainFrame.getWidth()-115,450);
        
        panelTampilRestaurant.add(restaurantTable, BorderLayout.CENTER);
        
        this.add(panelTampilRestaurant);
    }
    
    public void resetRestaurantTable() {
        for (int i = 0; i < this.getComponents().length; i++) {
            if (this.getComponent(i) instanceof JPanel) {
                JPanel panel = (JPanel)this.getComponent(i);
                if (panel.getComponents().length > 0) {
                    if (panel.getComponent(0) instanceof RestaurantTableComponent) {
                        this.remove(this.getComponent(i));
                        this.invalidate();
                        this.revalidate();
                        this.showRestaurantTable();
                    }
                }
            }
        }
    }
    
    private void addInputRestaurantButton() {
        JButton addRestoranBtn = new JButton();
        
        addRestoranBtn = new JButton("tambah restoran");
        addRestoranBtn.setSize(100, 20);
        addRestoranBtn.setLocation(50, 650);
        //===== Button =====//
        
        //===== Action Button =====//
        addRestoranBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestaurantInputFrame restaurantInputFrame 
                        = new RestaurantInputFrame(sideContentPanel);
                restaurantInputFrame.init();
                restaurantInputFrame.setVisible(true);
            }
        });
        
        this.add(addRestoranBtn);
    }
    
    public void identitasRestoran(){
        //===== form restoran =====//
        labelNamaRestoran = new JLabel("Nama WajibPajak");
        labelNamaRestoran.setFont(new Font("Tahoma", 0, 16));
        labelNamaRestoran.setForeground(Color.BLACK);
        
        //===== combo box =====//        
        List<WajibPajak> listWajibPajak = new ArrayList<>();
        listWajibPajak = wajibPajakService.getAllWP();
        WajibPajak wajibPajaks[];
        wajibPajaks = new WajibPajak[listWajibPajak.size()];
        for (int i = 0; i < listWajibPajak.size(); i++) {
            wajibPajaks[i] = listWajibPajak.get(i);
        }
        
        JComboBox<WajibPajak> cbListRestoran = new JComboBox<WajibPajak>(wajibPajaks);
        cbListRestoran.setFont(new Font("Tahoma", 0, 16));
        cbListRestoran.setMaximumRowCount(5);
        cbListRestoran.setSize(300,20);
        
        cbListRestoran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<WajibPajak> combo = (JComboBox<WajibPajak>) event.getSource();
                WajibPajak selected = (WajibPajak) combo.getSelectedItem();
                idRestoran = selected.getIdWajibPajak();
                System.out.println("Nama : " + selected.getNamaWajibPajak() + "\nId : " + selected.getIdWajibPajak());
            }
        });
       
        //===== combo box =====//
        
        //===== panel identitas restoran =====//
        panelIdentitasRestoran = new JPanel(new GridBagLayout());
        panelIdentitasRestoran.setBackground(Color.WHITE);
        panelIdentitasRestoran.setSize(mainFrame.getWidth()-115,80);
        panelIdentitasRestoran.setLocation(50, 70);
        
        GridBagConstraints constraintsIdentitasRestoran = new GridBagConstraints();
        
        // add components to the panel
        constraintsIdentitasRestoran.insets = new Insets(5, 5, 5, 5);
        
        constraintsIdentitasRestoran.gridx = 0;
        constraintsIdentitasRestoran.gridy = 0;
        constraintsIdentitasRestoran.anchor = GridBagConstraints.LINE_END;
        panelIdentitasRestoran.add(labelNamaRestoran, constraintsIdentitasRestoran);
 
        constraintsIdentitasRestoran.gridx = 1;
        constraintsIdentitasRestoran.gridy = 0;
        constraintsIdentitasRestoran.anchor = GridBagConstraints.LINE_START;
        panelIdentitasRestoran.add(cbListRestoran, constraintsIdentitasRestoran);
        
        // set border for the panel
        panelIdentitasRestoran.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Identitas WajibPajak"));
         
        // add the panel to this panel
        this.add(panelIdentitasRestoran);
        //===== panel identitas restoran =====//
        //===== form restoran =====//
    }
    
    
}
