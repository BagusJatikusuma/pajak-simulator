/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.model.Restoran;
import com.bekasidev.app.model.RestoranTransaction;
import com.bekasidev.app.service.backend.RestoranService;
import com.bekasidev.app.service.backend.RestoranTransactionService;
import com.bekasidev.app.service.backend.impl.RestoranServiceImpl;
import java.awt.BorderLayout;
import com.bekasidev.app.service.backend.impl.RestoranTransactionServiceImpl;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Bayu Arafli
 */
public class ContentPanel extends JPanel{

    MainFrame mainFrame;
    
    // Variables declaration
    private String namaRestoran;
    private String idRestoran;
    
    private JTextField  tfOmzetRamai,
                        tfOmzetSepi,
                        tfOmzetNormal,
                        tfFrekuensiRamai,
                        tfFrekuensiSepi,
                        tfFrekuensiNormal;
    
    private JPanel  panelFormOmzetPenjualan,
                    panelSubRamai,
                    panelSubNormal,
                    panelSubSepi;
    
    JPanel panelTampilRestaurant;
    ContentPanel contentPanelCover;
    
    private JLabel  labelOF,
                    labelOFRamai,
                    labelOFNormal,
                    labelOFSepi,
                    labelJumlah,
                    labelTotalOmzet,
                    labelTotalFrekuensi,
                    labelTotal;
    
    private JButton bCalculate;
            
    // Service
    private RestoranService restoranService = new RestoranServiceImpl();
    private RestoranTransactionService restoranTransactionService = new RestoranTransactionServiceImpl();
    
    public ContentPanel() {
        super();
    }
    
    public ContentPanel(MainFrame mainFrame){
        super();
        this.mainFrame = mainFrame;
    } 
    
    public void initPanel(){
        this.contentPanelCover = this;
        // setting layout content panel
        this.setPreferredSize(new Dimension((mainFrame.getWidth()/2), 250));
        this.setLayout(null);
        this.setBackground(Color.WHITE);
       
        // restauran
        restauranContent();                
    }
    
    public void resetComponentSize() {
        panelFormOmzetPenjualan.setSize((mainFrame.getWidth()/2)-115, 500);
    }
    
    public void restauranContent(){
        formMenghitungRataRataOmzetPenjualan();
    }
    
    public void formMenghitungRataRataOmzetPenjualan(){
        //===== label =====//        
        labelJumlah = new JLabel("Jumlah", SwingConstants.RIGHT);
        labelJumlah.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        labelOF = new JLabel("Total Omzet", SwingConstants.CENTER);
        labelOF.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        labelTotalOmzet = new JLabel("Total Omzet", SwingConstants.CENTER);
        labelTotalOmzet.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        labelTotalFrekuensi = new JLabel("Total Frekuensi", SwingConstants.CENTER);
        labelTotalFrekuensi.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        labelTotal = new JLabel("Total", SwingConstants.CENTER);
        labelTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        labelOFRamai = new JLabel("OF Ramai", SwingConstants.CENTER);
        labelOFRamai.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        labelOFNormal = new JLabel("OF Normal", SwingConstants.CENTER);
        labelOFNormal.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        labelOFSepi = new JLabel("OF Sepi", SwingConstants.CENTER);
        labelOFSepi.setFont(new Font("Tahoma", Font.BOLD, 16));
        //===== label =====//
        
        //===== text field =====//
        tfOmzetRamai = new JTextField(10);
        tfOmzetRamai.setFont(new Font("Tahoma", 0, 16));
        
        tfOmzetNormal = new JTextField(10);
        tfOmzetNormal.setFont(new Font("Tahoma", 0, 16));
        
        tfOmzetSepi = new JTextField(10);
        tfOmzetSepi.setFont(new Font("Tahoma", 0, 16));
        
        tfFrekuensiRamai = new JTextField(10);
        tfFrekuensiRamai.setFont(new Font("Tahoma", 0, 16));
        
        tfFrekuensiNormal = new JTextField(10);
        tfFrekuensiNormal.setFont(new Font("Tahoma", 0, 16));
        
        tfFrekuensiSepi = new JTextField(10);
        tfFrekuensiSepi.setFont(new Font("Tahoma", 0, 16));
        //===== text field =====//
        
        //===== button =====//
        bCalculate = new JButton("Calculate");
        bCalculate.setFont(new Font("Tahoma", 0, 16));

        //===== button =====//
        
        //===== sub panel ramai =====//
        panelSubRamai = new JPanel(new GridBagLayout());
        panelSubRamai.setSize(500, 500);
        
        GridBagConstraints constraintsRamai = new GridBagConstraints();
        
        // add components to the panel
        constraintsRamai.insets = new Insets(5, 5, 5, 5);
        constraintsRamai.gridx = 0;
        constraintsRamai.gridy = 0;
        constraintsRamai.anchor = GridBagConstraints.LINE_START;
        panelSubRamai.add(new JLabel("Omzet Harian"), constraintsRamai);
        constraintsRamai.gridy ++;
        panelSubRamai.add(tfOmzetRamai, constraintsRamai);
        
        constraintsRamai.gridx = 1;
        constraintsRamai.gridy = 0;
        constraintsRamai.anchor = GridBagConstraints.LINE_START;
        panelSubRamai.add(new JLabel("Frekuensi"), constraintsRamai);
        constraintsRamai.gridy ++;
        panelSubRamai.add(tfFrekuensiRamai, constraintsRamai);
        
        // set border for the panel
        panelSubRamai.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Ramai"));
        //===== sub panel ramai =====//
        
        //===== sub panel normal =====//
        panelSubNormal = new JPanel(new GridBagLayout());
        panelSubNormal.setSize(500, 500);
        
        GridBagConstraints constraintsNormal = new GridBagConstraints();
        
        // add components to the panel
        constraintsNormal.insets = new Insets(5, 5, 5, 5);
        constraintsNormal.gridx = 0;
        constraintsNormal.gridy = 0;
        constraintsNormal.anchor = GridBagConstraints.LINE_START;
        panelSubNormal.add(new JLabel("Omzet Harian"), constraintsNormal);
        constraintsNormal.gridy ++;
        panelSubNormal.add(tfOmzetNormal, constraintsNormal);
        
        constraintsNormal.gridx = 1;
        constraintsNormal.gridy = 0;
        constraintsNormal.anchor = GridBagConstraints.LINE_START;
        panelSubNormal.add(new JLabel("Frekuensi"), constraintsNormal);
        constraintsNormal.gridy ++;
        panelSubNormal.add(tfFrekuensiNormal, constraintsNormal);
        
        // set border for the panel
        panelSubNormal.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Normal"));
        //===== sub panel normal =====//
        
        //===== sub panel sepi =====//
        panelSubSepi = new JPanel(new GridBagLayout());
        panelSubSepi.setSize(500, 500);
        
        GridBagConstraints constraintsSepi = new GridBagConstraints();
        
        // add components to the panel
        constraintsSepi.insets = new Insets(5, 5, 5, 5);
        constraintsSepi.gridx = 0;
        constraintsSepi.gridy = 0;
        constraintsSepi.anchor = GridBagConstraints.LINE_START;
        panelSubSepi.add(new JLabel("Omzet Harian"), constraintsSepi);
        constraintsSepi.gridy ++;
        panelSubSepi.add(tfOmzetSepi, constraintsSepi);
        
        constraintsSepi.gridx = 1;
        constraintsSepi.gridy = 0;
        constraintsSepi.anchor = GridBagConstraints.LINE_START;
        panelSubSepi.add(new JLabel("Frekuensi"), constraintsSepi);
        constraintsSepi.gridy ++;
        panelSubSepi.add(tfFrekuensiSepi, constraintsSepi);
        
        // set border for the panel
        panelSubSepi.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Sepi"));
        //===== sub panel sepi =====//
        
        //===== panel form omzet penjualan =====//
        panelFormOmzetPenjualan = new JPanel(new GridBagLayout());
        panelFormOmzetPenjualan.setBackground(Color.WHITE);
        panelFormOmzetPenjualan.setSize((mainFrame.getWidth()/2)-115, 500);
        panelFormOmzetPenjualan.setLocation(50, 20);
       
        GridBagConstraints constraintsFormOmzet = new GridBagConstraints();
        
        // add components to the panel
        constraintsFormOmzet.insets = new Insets(7, 7, 7, 7);
        
        // add components to the panel
        constraintsFormOmzet.gridx = 0;
        constraintsFormOmzet.gridy = 0;
        constraintsFormOmzet.anchor = GridBagConstraints.LINE_START;
        panelFormOmzetPenjualan.add(panelSubRamai, constraintsFormOmzet);
        constraintsFormOmzet.gridy ++;
        panelFormOmzetPenjualan.add(panelSubNormal, constraintsFormOmzet);
        constraintsFormOmzet.gridy ++;
        panelFormOmzetPenjualan.add(panelSubSepi, constraintsFormOmzet);
        constraintsFormOmzet.gridy ++;
        constraintsFormOmzet.anchor = GridBagConstraints.LINE_END;
        panelFormOmzetPenjualan.add(bCalculate, constraintsFormOmzet);
        
        // set border for the panel
        panelFormOmzetPenjualan.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Form Menghitung Rata-Rata Omzet Penjualan " + namaRestoran));
         
        // add the panel to this panel
        this.add(panelFormOmzetPenjualan);
        //===== panel form omzet penjualan =====//
        
        //===== Action Button =====//
        bCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(  tfOmzetRamai.getText().equals("") || 
                        tfOmzetNormal.getText().equals("") ||
                        tfOmzetSepi.getText().equals("") ||
                        tfFrekuensiRamai.getText().equals("") ||
                        tfFrekuensiNormal.getText().equals("") ||
                        tfFrekuensiSepi.getText().equals(""))) {
                    RestoranTransaction restoranTransaction = new RestoranTransaction();
                    
                    restoranTransaction.setIdRestoran(idRestoran);
                    restoranTransaction.setIdTransaction(null);
                    restoranTransaction.setOmzetRamai((double) Integer.parseInt(tfOmzetRamai.getText()));
                    restoranTransaction.setOmzetNormal((double) Integer.parseInt(tfOmzetNormal.getText()));
                    restoranTransaction.setOmzetSepi((double) Integer.parseInt(tfOmzetSepi.getText()));
                    restoranTransaction.setFrekuensiRamai(Float.parseFloat(tfFrekuensiRamai.getText()));
                    restoranTransaction.setFrekuesniNormal(Float.parseFloat(tfFrekuensiNormal.getText()));
                    restoranTransaction.setFrekuensiSepi(Float.parseFloat(tfFrekuensiSepi.getText()));
                    
                    addRestoranTransaction(restoranTransaction);
                    JOptionPane.showMessageDialog(mainFrame ,"Berhasil Calculate");
                } else {
                    JOptionPane.showMessageDialog(mainFrame ,"Masukkan Dahulu Data Omzet Penjualan");
                }
            }
        });
        //===== Action Button =====//
    }
    
    public void addRestoranTransaction(RestoranTransaction restoranTransaction){
        restoranTransactionService.calculatePotensiPajakRestoran(restoranTransaction);
    }
}
