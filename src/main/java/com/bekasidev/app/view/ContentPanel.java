/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.model.Restoran;
import com.bekasidev.app.service.backend.RestoranService;
import com.bekasidev.app.service.backend.impl.RestoranServiceImpl;
import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Bayu Arafli
 */
public class ContentPanel extends JPanel{

    MainFrame mainFrame;
    
    // Variables declaration
    JTextField  tfNamaRestoran,
                tfOmzetRamai,
                tfOmzetSepi,
                tfOmzetNormal,
                tfFrekuensiRamai,
                tfFrekuensiSepi,
                tfFrekuensiNormal;
    
    JPanel panelHeaderFormIdentitas, 
           panelIdentitasRestoran, 
           panelFormAddRestoran,
           panelFormOmzetPenjualan;
    
    JLabel labelHeaderFormIdentitas,
           labelNamaRestoran,
           labelNamaAddRestoran,
           labelSituasi,
           labelOmzetHarian,
           labelFrekuensi,
           labelOF,
           labelOFRamai,
           labelOFNormal,
           labelOFSepi,
           labelOFJumlah,
           labelRamai,
           labelNormal,
           labelSepi,
           labelJumlah,
           labelTotalOmzet,
           labelTotalFrekuensi,
           labelTotal;
    
    JButton bFormRestoran, bCalculate;
            
    // Service
    RestoranService restoranService = new RestoranServiceImpl();
    
    public ContentPanel() {
        super();
    }
    
    public ContentPanel(MainFrame mainFrame){
        super();
        this.mainFrame = mainFrame;
    } 
    
    public void initPanel(){
        // setting layout content panel
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        
        // restauran
        restauranContent();
                
    }
    
    public void resetComponentSize() {
        panelHeaderFormIdentitas.setSize(mainFrame.getWidth(), 50);
        panelIdentitasRestoran.setSize(mainFrame.getWidth()-115, 80);
        panelFormAddRestoran.setSize(mainFrame.getWidth()-115, 80);
        panelFormOmzetPenjualan.setSize(mainFrame.getWidth()-115, 80);
    }
    
    public void restauranContent(){
        //===== header form identitas =====//
        panelHeaderFormIdentitas = new JPanel(new GridBagLayout());
        panelHeaderFormIdentitas.setBackground(Color.decode("#4377ca"));
        panelHeaderFormIdentitas.setSize(mainFrame.getWidth(), 50);
        panelHeaderFormIdentitas.setLocation(0, 0);
        panelHeaderFormIdentitas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        labelHeaderFormIdentitas = new JLabel("RESTORAN");
        labelHeaderFormIdentitas.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelHeaderFormIdentitas.setForeground(Color.WHITE);
        labelHeaderFormIdentitas.setSize(200, 50);
        
        // add to panelHeader
        panelHeaderFormIdentitas.add(labelHeaderFormIdentitas);
        
        // add to panel
        this.add(panelHeaderFormIdentitas);
        //===== header form identitas =====//
        
        
        identitasRestoran();
        formAddRestoran();
        formMenghitungRataRataOmzetPenjualan();
    }
   
    public void addRestoran(Restoran restoran){
        restoranService.createDataRestoran(restoran);
        tfNamaRestoran.setText("");
        System.out.println("Berhasil Add Restoran");
    }
    
    public void identitasRestoran(){
        //===== form restoran =====//
        labelNamaRestoran = new JLabel("Nama Restoran");
        labelNamaRestoran.setFont(new Font("Tahoma", 0, 16));
        labelNamaRestoran.setForeground(Color.BLACK);
        
        //===== combo box =====//        
        List<Restoran> listRestoran = new ArrayList<>();
        listRestoran = restoranService.getAllRestoran();
        Restoran restorans[];
        restorans = new Restoran[listRestoran.size()];
        for (int i = 0; i < listRestoran.size(); i++) {
            restorans[i] = listRestoran.get(i);
        }
        
        JComboBox<Restoran> cbListRestoran = new JComboBox<Restoran>(restorans);
        cbListRestoran.setFont(new Font("Tahoma", 0, 16));
        cbListRestoran.setMaximumRowCount(5);
        cbListRestoran.setSize(300,20);
        
        cbListRestoran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<Restoran> combo = (JComboBox<Restoran>) event.getSource();
                Restoran selected = (Restoran) combo.getSelectedItem();
                System.out.println("Nama : " + selected.getNamaRestoran() + "\nId : " + selected.getIdRestoran());
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
                BorderFactory.createEtchedBorder(), "Identitas Restoran"));
         
        // add the panel to this panel
        this.add(panelIdentitasRestoran);
        //===== panel identitas restoran =====//
        //===== form restoran =====//
    }
    
    public void formAddRestoran(){
        //===== form add restoran =====//
        labelNamaAddRestoran = new JLabel("Nama Restoran");
        labelNamaAddRestoran.setFont(new Font("Tahoma", 0, 16));
        labelNamaAddRestoran.setForeground(Color.BLACK);
        
        //===== TextField =====//
        tfNamaRestoran = new JTextField(20);
        //===== TextField =====//
        
        //===== Button =====//
        bFormRestoran = new JButton("Submit");
        bFormRestoran.setSize(100, 20);
        //===== Button =====//
        
        //===== Action Button =====//
        bFormRestoran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfNamaRestoran.getText().equals("")) {
                    Restoran restoran = new Restoran();
                    restoran.setNamaRestoran(tfNamaRestoran.getText());
                    
                    addRestoran(restoran);
                    JOptionPane.showMessageDialog(mainFrame ,"Berhasil Menambahkan Data Restoran");
                } else {
                    JOptionPane.showMessageDialog(mainFrame ,"Masukkan Dahulu Data Restoran");
                }
            }
        });
        //===== Action Button =====//

        //===== panel form restoran =====//
        panelFormAddRestoran = new JPanel(new GridBagLayout());
        panelFormAddRestoran.setBackground(Color.WHITE);
        panelFormAddRestoran.setSize(mainFrame.getWidth()-115, 100);
        panelFormAddRestoran.setLocation(50, 170);
        
        GridBagConstraints constraintsFormRestoran = new GridBagConstraints();
        
        // add components to the panel
        constraintsFormRestoran.insets = new Insets(5, 5, 5, 5);
        
        constraintsFormRestoran.gridx = 0;
        constraintsFormRestoran.gridy = 0;
        constraintsFormRestoran.anchor = GridBagConstraints.LINE_END;
        panelFormAddRestoran.add(labelNamaAddRestoran, constraintsFormRestoran);
 
        constraintsFormRestoran.gridx = 1;
        constraintsFormRestoran.gridy = 0;
        constraintsFormRestoran.anchor = GridBagConstraints.LINE_START;
        panelFormAddRestoran.add(tfNamaRestoran, constraintsFormRestoran);
        constraintsFormRestoran.gridy ++;
        panelFormAddRestoran.add(bFormRestoran, constraintsFormRestoran);
        
        // set border for the panel
        panelFormAddRestoran.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Form Add Restoran"));
         
        // add the panel to this panel
        this.add(panelFormAddRestoran);
        //===== panel form restoran =====//
        //===== form add restoran =====//
    }
    
    public void formMenghitungRataRataOmzetPenjualan(){
        //===== label =====//
        labelSituasi = new JLabel("Situasi", SwingConstants.RIGHT );
        labelSituasi.setOpaque(true);
        labelSituasi.setBackground(Color.red);
        labelSituasi.setFont(new Font("Tahoma", 0, 14));
        labelSituasi.setBounds(30, 30, 100, 20);
        
        labelRamai = new JLabel("Ramai", SwingConstants.RIGHT);
        labelRamai.setOpaque(true);
        labelRamai.setBackground(Color.red);
        labelRamai.setFont(new Font("Tahoma", 0, 14));
        labelRamai.setBounds(30, 60, 100, 20);
        
        labelNormal = new JLabel("Normal", SwingConstants.RIGHT);
        labelNormal.setOpaque(true);
        labelNormal.setBackground(Color.red);
        labelNormal.setFont(new Font("Tahoma", 0, 14));
        labelNormal.setBounds(30, 90, 100, 20);
        
        labelSepi = new JLabel("Sepi", SwingConstants.RIGHT);
        labelSepi.setOpaque(true);
        labelSepi.setBackground(Color.red);
        labelSepi.setFont(new Font("Tahoma", 0, 14));
        labelSepi.setBounds(30, 120, 100, 20);
        
        labelJumlah = new JLabel("Jumlah", SwingConstants.RIGHT);
        labelJumlah.setOpaque(true);
        labelJumlah.setBackground(Color.red);
        labelJumlah.setFont(new Font("Tahoma", 0, 14));
        labelJumlah.setBounds(30, 150, 100, 20);
        
        labelOmzetHarian = new JLabel("Omzet Harian", SwingConstants.CENTER);
        labelOmzetHarian.setOpaque(true);
        labelOmzetHarian.setBackground(Color.red);
        labelOmzetHarian.setFont(new Font("Tahoma", 0, 14));
        labelOmzetHarian.setBounds(140, 30, 100, 20);
        
        labelFrekuensi = new JLabel("Frekuensi", SwingConstants.CENTER);
        labelFrekuensi.setOpaque(true);
        labelFrekuensi.setBackground(Color.red);
        labelFrekuensi.setFont(new Font("Tahoma", 0, 14));
        labelFrekuensi.setBounds(250, 30, 100, 20);
        
        labelOF = new JLabel("Total Omzet", SwingConstants.CENTER);
        labelOF.setOpaque(true);
        labelOF.setBackground(Color.red);
        labelOF.setFont(new Font("Tahoma", 0, 14));
        labelOF.setBounds(360, 30, 100, 20);
        
        labelTotalOmzet = new JLabel("Total Omzet", SwingConstants.CENTER);
        labelTotalOmzet.setOpaque(true);
        labelTotalOmzet.setBackground(Color.red);
        labelTotalOmzet.setFont(new Font("Tahoma", 0, 14));
        labelTotalOmzet.setBounds(140, 150, 100, 20);
        
        labelTotalFrekuensi = new JLabel("Total Frekuensi", SwingConstants.CENTER);
        labelTotalFrekuensi.setOpaque(true);
        labelTotalFrekuensi.setBackground(Color.red);
        labelTotalFrekuensi.setFont(new Font("Tahoma", 0, 14));
        labelTotalFrekuensi.setBounds(250, 150, 100, 20);
        
        labelTotal = new JLabel("Total", SwingConstants.CENTER);
        labelTotal.setOpaque(true);
        labelTotal.setBackground(Color.red);
        labelTotal.setFont(new Font("Tahoma", 0, 14));
        labelTotal.setBounds(360, 150, 100, 20);
        
        labelOFRamai = new JLabel("OF Ramai", SwingConstants.CENTER);
        labelOFRamai.setOpaque(true);
        labelOFRamai.setBackground(Color.red);
        labelOFRamai.setFont(new Font("Tahoma", 0, 14));
        labelOFRamai.setBounds(360, 60, 100, 20);
        
        labelOFNormal = new JLabel("OF Normal", SwingConstants.CENTER);
        labelOFNormal.setOpaque(true);
        labelOFNormal.setBackground(Color.red);
        labelOFNormal.setFont(new Font("Tahoma", 0, 14));
        labelOFNormal.setBounds(360, 90, 100, 20);
        
        labelOFSepi = new JLabel("OF Sepi", SwingConstants.CENTER);
        labelOFSepi.setOpaque(true);
        labelOFSepi.setBackground(Color.red);
        labelOFSepi.setFont(new Font("Tahoma", 0, 14));
        labelOFSepi.setBounds(360, 120, 100, 20);
        //===== label =====//
        
        //===== text field =====//
        tfOmzetRamai = new JTextField(10);
        tfOmzetRamai.setFont(new Font("Tahoma", 0, 14));
        tfOmzetRamai.setBounds(140, 60, 100, 20);
        
        tfOmzetNormal = new JTextField(10);
        tfOmzetNormal.setFont(new Font("Tahoma", 0, 14));
        tfOmzetNormal.setBounds(140, 90, 100, 20);
        
        tfOmzetSepi = new JTextField(10);
        tfOmzetSepi.setFont(new Font("Tahoma", 0, 14));
        tfOmzetSepi.setBounds(140, 120, 100, 20);
        
        tfFrekuensiRamai = new JTextField(10);
        tfFrekuensiRamai.setFont(new Font("Tahoma", 0, 14));
        tfFrekuensiRamai.setBounds(250, 60, 100, 20);
        
        tfFrekuensiNormal = new JTextField(10);
        tfFrekuensiNormal.setFont(new Font("Tahoma", 0, 14));
        tfFrekuensiNormal.setBounds(250, 90, 100, 20);
        
        tfFrekuensiSepi = new JTextField(10);
        tfFrekuensiSepi.setFont(new Font("Tahoma", 0, 14));
        tfFrekuensiSepi.setBounds(250, 120, 100, 20);
        //===== text field =====//
        
        //===== button =====//
        bCalculate = new JButton("Calculate");
        bCalculate.setBounds(360, 180, 100, 20);
        //===== button =====//
        
        
        //===== panel form restoran =====//
        panelFormOmzetPenjualan = new JPanel();
        panelFormOmzetPenjualan.setLayout(null);
        panelFormOmzetPenjualan.setBackground(Color.WHITE);
        panelFormOmzetPenjualan.setSize(mainFrame.getWidth()-115, 250);
        panelFormOmzetPenjualan.setLocation(50, 290);
       
        
        // add components to the panel
        
        panelFormOmzetPenjualan.add(labelSituasi);
        panelFormOmzetPenjualan.add(labelRamai);
        panelFormOmzetPenjualan.add(labelNormal);
        panelFormOmzetPenjualan.add(labelSepi);
        panelFormOmzetPenjualan.add(labelJumlah);
        
        panelFormOmzetPenjualan.add(labelOmzetHarian);
        panelFormOmzetPenjualan.add(tfOmzetRamai);
        panelFormOmzetPenjualan.add(tfOmzetNormal);
        panelFormOmzetPenjualan.add(tfOmzetSepi);
        
        panelFormOmzetPenjualan.add(labelFrekuensi);
        panelFormOmzetPenjualan.add(tfFrekuensiRamai);
        panelFormOmzetPenjualan.add(tfFrekuensiNormal);
        panelFormOmzetPenjualan.add(tfFrekuensiSepi);
        
        panelFormOmzetPenjualan.add(labelOF);
        panelFormOmzetPenjualan.add(labelTotalOmzet);
        panelFormOmzetPenjualan.add(labelTotalFrekuensi);
        panelFormOmzetPenjualan.add(labelTotal);
        
        panelFormOmzetPenjualan.add(labelOFRamai);
        panelFormOmzetPenjualan.add(labelOFNormal);
        panelFormOmzetPenjualan.add(labelOFSepi);
        panelFormOmzetPenjualan.add(bCalculate);
        
        // set border for the panel
        panelFormOmzetPenjualan.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Form Menhitung Rata-Rata Omzet Penjualan"));
         
        // add the panel to this panel
        this.add(panelFormOmzetPenjualan);
        //===== panel form restoran =====//
    }
}
