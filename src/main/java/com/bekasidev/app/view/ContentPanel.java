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
    private String idRestoran;
    
    private JTextField  tfNamaRestoran,
                        tfOmzetRamai,
                        tfOmzetSepi,
                        tfOmzetNormal,
                        tfFrekuensiRamai,
                        tfFrekuensiSepi,
                        tfFrekuensiNormal;
    
    private JPanel  panelHeaderFormIdentitas, 
                    panelIdentitasRestoran, 
                    panelFormAddRestoran,
                    panelFormOmzetPenjualan,
                    panelSubRamai,
                    panelSubNormal,
                    panelSubSepi;
    
    JPanel panelTampilRestaurant;
    ContentPanel contentPanelCover;
    
    private JLabel  labelHeaderFormIdentitas,
                    labelNamaRestoran,
                    labelNamaAddRestoran,
                    labelOF,
                    labelOFRamai,
                    labelOFNormal,
                    labelOFSepi,
                    labelJumlah,
                    labelTotalOmzet,
                    labelTotalFrekuensi,
                    labelTotal;
    
    private JButton bFormRestoran, bCalculate;
            
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
        this.setLayout(null);
        this.setBackground(Color.WHITE);
       
        // restauran
        restauranContent();                
    }
    
    public void resetComponentSize() {
        panelHeaderFormIdentitas.setSize(mainFrame.getWidth(), 50);
        panelIdentitasRestoran.setSize(mainFrame.getWidth()-115, 80);
        panelFormAddRestoran.setSize(mainFrame.getWidth()-115, 100);
        panelFormOmzetPenjualan.setSize(mainFrame.getWidth()-115, 350);
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
//        addInputRestaurantButton();
//        showRestaurantTable();
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
                idRestoran = selected.getIdRestoran();
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
        panelFormOmzetPenjualan.setSize(mainFrame.getWidth()-115, 350);
        panelFormOmzetPenjualan.setLocation(50, 290);
       
        GridBagConstraints constraintsFormOmzet = new GridBagConstraints();
        
        // add components to the panel
        constraintsFormOmzet.insets = new Insets(7, 7, 7, 7);
        
        // add components to the panel
        constraintsFormOmzet.gridx = 0;
        constraintsFormOmzet.gridy = 0;
        constraintsFormOmzet.anchor = GridBagConstraints.CENTER;
        panelFormOmzetPenjualan.add(panelSubRamai, constraintsFormOmzet);
        constraintsFormOmzet.gridy ++;
        panelFormOmzetPenjualan.add(panelSubNormal, constraintsFormOmzet);
        constraintsFormOmzet.gridy ++;
        panelFormOmzetPenjualan.add(panelSubSepi, constraintsFormOmzet);
        
        constraintsFormOmzet.gridx = 1;
        constraintsFormOmzet.gridy = 0;
        constraintsFormOmzet.anchor = GridBagConstraints.CENTER;
        panelFormOmzetPenjualan.add(labelOFRamai, constraintsFormOmzet);
        constraintsFormOmzet.gridy ++;
        panelFormOmzetPenjualan.add(labelOFNormal, constraintsFormOmzet);
        constraintsFormOmzet.gridy ++;
        panelFormOmzetPenjualan.add(labelOFSepi, constraintsFormOmzet);
        
        // set border for the panel
        panelFormOmzetPenjualan.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Form Menghitung Rata-Rata Omzet Penjualan"));
         
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
    
    private void showRestaurantTable() {
        //===== panel identitas restoran =====//
        panelTampilRestaurant = new JPanel(new BorderLayout());
        panelTampilRestaurant.setBackground(Color.WHITE);
        panelTampilRestaurant.setLocation(50, 350);
        panelTampilRestaurant.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "daftar restaurant"));
        
        RestaurantTableComponent restaurantTable = new RestaurantTableComponent();
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
        addRestoranBtn.setLocation(50, 320);
        //===== Button =====//
        
        //===== Action Button =====//
        addRestoranBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestaurantInputFrame restaurantInputFrame 
                        = new RestaurantInputFrame(contentPanelCover);
                restaurantInputFrame.init();
                restaurantInputFrame.setVisible(true);
            }
        });
        
        this.add(addRestoranBtn);
    } 
}
