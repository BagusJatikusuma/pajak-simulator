/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewold;

import com.bekasidev.app.model.Menu;
import com.bekasidev.app.model.RestoranTransaction;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.MenuService;
import com.bekasidev.app.service.backend.WajibPajakService;
import com.bekasidev.app.service.backend.RestoranTransactionService;
import com.bekasidev.app.service.backend.impl.WajibPajakServiceImpl;
import java.awt.BorderLayout;
import com.bekasidev.app.service.backend.impl.RestoranTransactionServiceImpl;
import com.bekasidev.app.view.tablecomponent.ColumnGroup;
import com.bekasidev.app.view.tablecomponent.GroupableTableHeader;
import com.bekasidev.app.view.util.SessionProvider;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Bayu Arafli
 */
public class ContentPanel extends JPanel{

    MainFrame mainFrame;
    
    // Variables declaration
    private String namaRestoran;
    private String idRestoran;
    private int statusPage = 2;
    private int totalOFRamai = 0;
    private int totalOFNormal = 0;
    private int totalOFSepi = 0;
    private int jumlahHari = 0;

    private JTextField  tfOmzetRamai,
                        tfOmzetSepi,
                        tfOmzetNormal,
                        tfFrekuensiRamai,
                        tfFrekuensiSepi,
                        tfFrekuensiNormal;
    
    private JPanel  panelFormOmzetPenjualan,
                    panelSubRamai,
                    panelSubNormal,
                    panelSubSepi,
                    parentPanel;

    ContentPanel contentPanelCover;
    
    private JLabel labelRataRataOmzet,
                    labelOFRamai,
                    labelOFNormal,
                    labelOFSepi,
                    labelJumlah,
            labelPotensiPajakTahun,
            labelPotensiPajakBulan,
                    labelTotalKeseluruhan;

    private JButton bCalculate, bKembali, bSavePotensiPajak, bTambahMenu;

    // model
    RestoranTransaction restoranTransaction;

    // Service
    private MenuService menuService;
    private WajibPajakService wajibPajakService = new WajibPajakServiceImpl();
    private RestoranTransactionService restoranTransactionService = new RestoranTransactionServiceImpl();
    
    public ContentPanel() {
        super();
    }
    
    public ContentPanel(MainFrame mainFrame){
        super();
        this.mainFrame = mainFrame;
    }

    public ContentPanel(MainFrame mainFrame, JPanel parentPanel){
        super();
        this.mainFrame = mainFrame;
        this.parentPanel = parentPanel;
    }
    
    public void initPanel(){
        this.contentPanelCover = this;
        
        // setting layout content panel
        this.setPreferredSize(new Dimension((mainFrame.getWidth()/2), 250));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       
        // restauran
        restauranContent(statusPage);                
    }
    
    public void resetComponentSize() {
        panelFormOmzetPenjualan.setSize((mainFrame.getWidth()/2)-115, 500);
    }
    
    public void restauranContent(int statusPage){
        
        if (this.getComponents().length > 0){
            this.remove(0);
            this.invalidate();
            this.revalidate();
        }

        switch (statusPage){
            case 0 :
                panelFormMenghitungRataRataOmzetPenjualan();
                break;
            case 1 :
                panelFormHasilPotensiPenjualan();
                break;
            case 2 :
                panelFormInformasiMenu();
                break;
            default :
                panelFormMenghitungRataRataOmzetPenjualan();
                break;
        }
    }
    
    public void panelFormMenghitungRataRataOmzetPenjualan(){
        restoranTransaction = SessionProvider.getRestoranTransaction();

        //===== text field =====//
        tfOmzetRamai = new JTextField(10);
        if (!(restoranTransaction.getOmzetRamai() == 0.0)){
            tfOmzetRamai.setText(String.valueOf(Integer.valueOf((int) restoranTransaction.getOmzetRamai())));
        }
        tfOmzetRamai.setFont(new Font("Tahoma", 0, 16));
        
        tfOmzetNormal = new JTextField(10);
        if (!(restoranTransaction.getOmzetNormal() == 0.0)){
            tfOmzetNormal.setText(String.valueOf(Integer.valueOf((int) restoranTransaction.getOmzetNormal())));
        }
        tfOmzetNormal.setFont(new Font("Tahoma", 0, 16));
        
        tfOmzetSepi = new JTextField(10);
        if (!(restoranTransaction.getOmzetSepi() == 0.0)){
            tfOmzetSepi.setText(String.valueOf(Integer.valueOf((int) restoranTransaction.getOmzetSepi())));
        }
        tfOmzetSepi.setFont(new Font("Tahoma", 0, 16));
        
        tfFrekuensiRamai = new JTextField(10);
        if (!(restoranTransaction.getFrekuensiRamai() == 0.0)){
            tfFrekuensiRamai.setText(String.valueOf(Integer.valueOf((int) restoranTransaction.getFrekuensiRamai())));
        }
        tfFrekuensiRamai.setFont(new Font("Tahoma", 0, 16));
        
        tfFrekuensiNormal = new JTextField(10);
        if (!(restoranTransaction.getFrekuesniNormal() == 0.0)){
            tfFrekuensiNormal.setText(String.valueOf(Integer.valueOf((int) restoranTransaction.getFrekuesniNormal())));
        }
        tfFrekuensiNormal.setFont(new Font("Tahoma", 0, 16));
        
        tfFrekuensiSepi = new JTextField(10);
        if (!(restoranTransaction.getFrekuensiSepi() == 0.0)){
            tfFrekuensiSepi.setText(String.valueOf(Integer.valueOf((int) restoranTransaction.getFrekuensiSepi())));
        }
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
        panelSubRamai.add(new JLabel("Omzet Harian (Rp.)"), constraintsRamai);
        constraintsRamai.gridy ++;
        panelSubRamai.add(tfOmzetRamai, constraintsRamai);
        
        constraintsRamai.gridx = 1;
        constraintsRamai.gridy = 0;
        constraintsRamai.anchor = GridBagConstraints.LINE_START;
        panelSubRamai.add(new JLabel("Frekuensi (Hari)"), constraintsRamai);
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
        panelSubNormal.add(new JLabel("Omzet Harian (Rp.)"), constraintsNormal);
        constraintsNormal.gridy ++;
        panelSubNormal.add(tfOmzetNormal, constraintsNormal);
        
        constraintsNormal.gridx = 1;
        constraintsNormal.gridy = 0;
        constraintsNormal.anchor = GridBagConstraints.LINE_START;
        panelSubNormal.add(new JLabel("Frekuensi (Hari)"), constraintsNormal);
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
        panelSubSepi.add(new JLabel("Omzet Harian (Rp.)"), constraintsSepi);
        constraintsSepi.gridy ++;
        panelSubSepi.add(tfOmzetSepi, constraintsSepi);
        
        constraintsSepi.gridx = 1;
        constraintsSepi.gridy = 0;
        constraintsSepi.anchor = GridBagConstraints.LINE_START;
        panelSubSepi.add(new JLabel("Frekuensi (Hari)"), constraintsSepi);
        constraintsSepi.gridy ++;
        panelSubSepi.add(tfFrekuensiSepi, constraintsSepi);
        
        // set border for the panel
        panelSubSepi.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Sepi"));
        //===== sub panel sepi =====//
        
        //===== panel form omzet penjualan =====//
        panelFormOmzetPenjualan = new JPanel(new GridBagLayout());
        panelFormOmzetPenjualan.setBackground(Color.WHITE);
        
        GridBagConstraints constraintsFormOmzet = new GridBagConstraints();
        
        // add components to the panel
        constraintsFormOmzet.insets = new Insets(7, 7, 7, 7);
        
        // add components to the panel
        constraintsFormOmzet.gridx = 0;
        constraintsFormOmzet.gridy = 0;
        constraintsFormOmzet.anchor = GridBagConstraints.PAGE_START;
        panelFormOmzetPenjualan.add(panelSubRamai, constraintsFormOmzet);
        constraintsFormOmzet.gridy ++;
        panelFormOmzetPenjualan.add(panelSubNormal, constraintsFormOmzet);
        constraintsFormOmzet.gridy ++;
        panelFormOmzetPenjualan.add(panelSubSepi, constraintsFormOmzet);
        constraintsFormOmzet.gridy ++;
        constraintsFormOmzet.anchor = GridBagConstraints.LINE_END;
        constraintsFormOmzet.gridwidth = 3;
        constraintsFormOmzet.fill = GridBagConstraints.HORIZONTAL;
        panelFormOmzetPenjualan.add(bCalculate, constraintsFormOmzet);
        
        // set border for the panel
        panelFormOmzetPenjualan.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Form Menghitung Rata-Rata Omzet Penjualan " + namaRestoran));
         
        // add the panel to this panel
        this.add(panelFormOmzetPenjualan, BorderLayout.CENTER);
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

                    restoranTransaction.setIdRestoran(idRestoran);
                    restoranTransaction.setIdTransaction(null);
                    restoranTransaction.setOmzetRamai((double) Integer.parseInt(tfOmzetRamai.getText()));
                    restoranTransaction.setOmzetNormal((double) Integer.parseInt(tfOmzetNormal.getText()));
                    restoranTransaction.setOmzetSepi((double) Integer.parseInt(tfOmzetSepi.getText()));
                    restoranTransaction.setFrekuensiRamai(Float.parseFloat(tfFrekuensiRamai.getText()));
                    restoranTransaction.setFrekuesniNormal(Float.parseFloat(tfFrekuensiNormal.getText()));
                    restoranTransaction.setFrekuensiSepi(Float.parseFloat(tfFrekuensiSepi.getText()));

                    calculateOF();
                    calculatePotensiPajakRestoran(restoranTransaction);
                    JOptionPane.showMessageDialog(mainFrame ,"Berhasil Calculate");
                } else {
                    JOptionPane.showMessageDialog(mainFrame ,"Masukkan Dahulu Data Omzet Penjualan");
//                    statusPage = 1;
//                    restauranContent(statusPage);
                }
            }
        });
        //===== Action Button =====//
    }
    
    public void panelFormHasilPotensiPenjualan(){
        restoranTransaction = SessionProvider.getRestoranTransaction();

        //===== label =====//
        labelOFRamai = new JLabel(printMoney(totalOFRamai));
        labelOFRamai.setFont(new Font("Tahoma", Font.BOLD, 16));

        labelOFNormal = new JLabel(printMoney(totalOFNormal) );
        labelOFNormal.setFont(new Font("Tahoma", Font.BOLD, 16));

        labelOFSepi = new JLabel(printMoney(totalOFSepi));
        labelOFSepi.setFont(new Font("Tahoma", Font.BOLD, 16));

        labelTotalKeseluruhan = new JLabel(printMoney((int) restoranTransaction.getOmzetTotal()));
        labelTotalKeseluruhan.setFont(new Font("Tahoma", Font.BOLD, 16));

        labelRataRataOmzet = new JLabel(printMoney((int) restoranTransaction.getRatarataOmzet()));
        labelRataRataOmzet.setFont(new Font("Tahoma", Font.BOLD, 16));

        labelPotensiPajakTahun = new JLabel(printMoney((int) restoranTransaction.getPajakSetahun()));
        labelPotensiPajakTahun.setFont(new Font("Tahoma", Font.BOLD, 16));

        labelPotensiPajakBulan = new JLabel(printMoney((int) restoranTransaction.getPajakPerBulan()));
        labelPotensiPajakBulan.setFont(new Font("Tahoma", Font.BOLD, 16));
        //===== label =====//

        //===== button =====//
        bKembali = new JButton("Kembali");
        bKembali.setFont(new Font("Tahoma", 0, 16));

        bSavePotensiPajak = new JButton("Simpan & Selanjutnya");
        bSavePotensiPajak.setFont(new Font("Tahoma", 0, 16));

        //===== button =====//
        
        //===== panel form hasil potensi penjualan Atas =====//
        JPanel panelFormHasilPotensiPenjualanAtas = new JPanel(new GridBagLayout());
        panelFormHasilPotensiPenjualanAtas.setBackground(Color.WHITE);
        
        GridBagConstraints constraintsFormHasilAtas = new GridBagConstraints();

        // add components to the panel
        constraintsFormHasilAtas.insets = new Insets(7, 7, 7, 7);
        
        // add components to the panel
        constraintsFormHasilAtas.gridx = 0;
        constraintsFormHasilAtas.gridy = 1;
        constraintsFormHasilAtas.anchor = GridBagConstraints.LINE_START;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel("Total Omzet (Ramai)"), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel(":"), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(labelOFRamai, constraintsFormHasilAtas);
        
        constraintsFormHasilAtas.gridx = 0;
        constraintsFormHasilAtas.gridy = 2;
        constraintsFormHasilAtas.anchor = GridBagConstraints.LINE_START;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel("Total Omzet (Normal)"), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel(":"), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(labelOFNormal, constraintsFormHasilAtas);
        
        constraintsFormHasilAtas.gridx = 0;
        constraintsFormHasilAtas.gridy = 3;
        constraintsFormHasilAtas.anchor = GridBagConstraints.LINE_START;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel("Total Omzet (Sepi)"), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel(":"), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(labelOFSepi, constraintsFormHasilAtas);
        
        constraintsFormHasilAtas.gridx = 0;
        constraintsFormHasilAtas.gridy = 4;
        constraintsFormHasilAtas.anchor = GridBagConstraints.LINE_START;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel("Total Keseluruhan"), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel(":"), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(labelTotalKeseluruhan, constraintsFormHasilAtas);
        
        constraintsFormHasilAtas.gridx = 0;
        constraintsFormHasilAtas.gridy = 5;
        constraintsFormHasilAtas.anchor = GridBagConstraints.LINE_START;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel("Rata-Rata Omzet Penjualan"), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel(":"), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(labelRataRataOmzet, constraintsFormHasilAtas);
        
        constraintsFormHasilAtas.gridx = 0;
        constraintsFormHasilAtas.gridy = 7;
        constraintsFormHasilAtas.anchor = GridBagConstraints.NORTHWEST;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel("Potensi Pajak WajibPajak"), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel("="), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel("<html><body>Rata-Rara Omzet Penjualan x Jumlah Hari Beroprasi ("+ String.valueOf(jumlahHari) +" Hari)<br>x Pajak restoran (10%)</body></html>"), constraintsFormHasilAtas);
        
        constraintsFormHasilAtas.gridx = 0;
        constraintsFormHasilAtas.gridy = 8;
        constraintsFormHasilAtas.anchor = GridBagConstraints.NORTHWEST;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel(""), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel("="), constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel("<html><body>" + labelPotensiPajakTahun.getText() + " per tahun atau<br>"+ labelPotensiPajakBulan.getText() +" per bulan</body></html>"), constraintsFormHasilAtas);

        constraintsFormHasilAtas.gridx = 0;
        constraintsFormHasilAtas.gridy = 0;
        constraintsFormHasilAtas.anchor = GridBagConstraints.CENTER;
        constraintsFormHasilAtas.fill = GridBagConstraints.CENTER;
        constraintsFormHasilAtas.gridwidth = 3;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel("<html><body><h2>HASIL CALCULATE</h2></body></html>"), constraintsFormHasilAtas);
        
        constraintsFormHasilAtas.gridx = 0;
        constraintsFormHasilAtas.gridy = 6;
        constraintsFormHasilAtas.anchor = GridBagConstraints.CENTER;
        constraintsFormHasilAtas.fill = GridBagConstraints.CENTER;
        constraintsFormHasilAtas.gridwidth = 3;
        constraintsFormHasilAtas.insets = new Insets(20, 0, 7, 0);
        panelFormHasilPotensiPenjualanAtas.add(new JLabel("<html><body><h2>POTENSI PAJAK RESTORAN</h2></body></html>"), constraintsFormHasilAtas);

        constraintsFormHasilAtas.gridx = 0;
        constraintsFormHasilAtas.gridy = 9;
        constraintsFormHasilAtas.anchor = GridBagConstraints.LINE_START;
        constraintsFormHasilAtas.insets = new Insets(20, 0, 20, 0);
        panelFormHasilPotensiPenjualanAtas.add(bKembali, constraintsFormHasilAtas);
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(new JLabel(""), constraintsFormHasilAtas);
        constraintsFormHasilAtas.anchor = GridBagConstraints.LINE_END;
        constraintsFormHasilAtas.gridx ++;
        panelFormHasilPotensiPenjualanAtas.add(bSavePotensiPajak, constraintsFormHasilAtas);

        // set border for the panel
        panelFormHasilPotensiPenjualanAtas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Potensi Pajak " + namaRestoran));
         
        // add the panel to this panel
        this.add(panelFormHasilPotensiPenjualanAtas, BorderLayout.CENTER);
        //===== panel form hasil potensi penjualan =====//
        
        //===== Action Button =====//
        bKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusPage = 0;
                restauranContent(statusPage);
            }
        });

        bSavePotensiPajak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPotensiPajakRestoran(restoranTransaction);
                statusPage = 2;
                restauranContent(statusPage);
            }
        });

        //===== Action Button =====//
    }
    
    public void calculatePotensiPajakRestoran(RestoranTransaction restoranTransaction){
        restoranTransactionService.calculatePotensiPajakRestoran(restoranTransaction);
        statusPage = 1;
        restauranContent(statusPage);
    }

    public void addPotensiPajakRestoran(RestoranTransaction restoranTransaction){
        restoranTransactionService.createRestoranTransaction(restoranTransaction);
        JOptionPane.showMessageDialog(mainFrame ,"Data Berhasil Disimpan");
        SessionProvider.resetRestoranTranstion();
        System.out.println(SessionProvider.getRestoranTransaction().getOmzetRamai());
        statusPage = 0;
        restauranContent(statusPage);
    }

    public void calculateOF(){
        totalOFRamai = Integer.parseInt(tfOmzetRamai.getText()) * Integer.parseInt(tfFrekuensiRamai.getText());
        totalOFNormal = Integer.parseInt(tfOmzetNormal.getText()) * Integer.parseInt(tfFrekuensiNormal.getText());
        totalOFSepi = Integer.parseInt(tfOmzetSepi.getText()) * Integer.parseInt(tfFrekuensiSepi.getText());
        jumlahHari = Integer.parseInt(tfFrekuensiRamai.getText())+Integer.parseInt(tfFrekuensiNormal.getText())+Integer.parseInt(tfFrekuensiSepi.getText());
    }
    
    public void setNamaRestoran(String namaRestoran) {
        this.namaRestoran = namaRestoran;
    }
    
    public void setIdRestoran(String idRestoran) {
        this.idRestoran = idRestoran;
    }

    String printMoney(double money){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        return kursIndonesia.format(money);
    }

    public void panelFormInformasiMenu(){
        menuService = ServiceFactory.getMenuService();

        bTambahMenu = new JButton("Tambah Menu");

        //===== table menu makanan =====//
        JScrollPane jScrollPane1 = new JScrollPane();
        JTable menuTableMakanan = new JTable() {
            @Override
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        DefaultTableModel dtmMakanan = new DefaultTableModel(0,0);
        String headerMakanan[] = new String[] {"ID_MENU", "ID_RESTAURANT", "NAMA_MENU","JENIS_MENU", "HARGA",""};
        dtmMakanan.setColumnIdentifiers(headerMakanan);
        menuTableMakanan.setModel(dtmMakanan);

        List<Menu> menusMakanan = menuService.getMenu(idRestoran);

        for (Menu obj : menusMakanan) {
            if (obj.getJenisMenu() == 0){
                dtmMakanan.addRow(new Object[] {obj.getIdMenu(), obj.getIdRestoran(), obj.getNamaMenu(), "Makanan", obj.getHargaMenu(), "delete"});
            }
        }

        TableColumnModel cmMakanan = menuTableMakanan.getColumnModel();
        ColumnGroup g_nameMakanan = new ColumnGroup("Menu Makanan " + namaRestoran);
        g_nameMakanan.add(cmMakanan.getColumn(0));
        g_nameMakanan.add(cmMakanan.getColumn(1));
        g_nameMakanan.add(cmMakanan.getColumn(2));
        g_nameMakanan.add(cmMakanan.getColumn(3));
        g_nameMakanan.add(cmMakanan.getColumn(4));

        GroupableTableHeader headerTableMakanan = (GroupableTableHeader)menuTableMakanan.getTableHeader();
        headerTableMakanan.addColumnGroup(g_nameMakanan);

        jScrollPane1.setViewportView(menuTableMakanan);

        jScrollPane1.setSize(500, 200);
        jScrollPane1.setPreferredSize(new Dimension(500, 200));
        //===== table menu makanan =====//

        //===== table menu minuman =====//
        JScrollPane jScrollPane2 = new JScrollPane();
        JTable menuTableMinuman = new JTable() {
            @Override
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        DefaultTableModel dtmMinuman = new DefaultTableModel(0,0);
        String headerMinuman[] = new String[] {"ID_MENU", "ID_RESTAURANT", "NAMA_MENU","JENIS_MENU", "HARGA",""};
        dtmMinuman.setColumnIdentifiers(headerMinuman);
        menuTableMinuman.setModel(dtmMinuman);

        List<Menu> menusMinuman = menuService.getMenu(idRestoran);

        for (Menu obj : menusMinuman) {
            if (obj.getJenisMenu() == 1){
                dtmMinuman.addRow(new Object[] {obj.getIdMenu(), obj.getIdRestoran(), obj.getNamaMenu(), "Minuman", obj.getHargaMenu(), "delete"});
            }
        }

        TableColumnModel cmMinuman= menuTableMinuman.getColumnModel();
        ColumnGroup g_nameMinuman= new ColumnGroup("Menu Minuman " + namaRestoran);
        g_nameMinuman.add(cmMinuman.getColumn(0));
        g_nameMinuman.add(cmMinuman.getColumn(1));
        g_nameMinuman.add(cmMinuman.getColumn(2));
        g_nameMinuman.add(cmMinuman.getColumn(3));
        g_nameMinuman.add(cmMinuman.getColumn(4));

        GroupableTableHeader headerTableMinuman = (GroupableTableHeader)menuTableMinuman.getTableHeader();
        headerTableMinuman.addColumnGroup(g_nameMinuman);

        jScrollPane2.setViewportView(menuTableMinuman);

        jScrollPane2.setSize(500, 200);
        jScrollPane2.setPreferredSize(new Dimension(500, 200));
        //===== table menu minuman =====//

        //===== panel form informasi menu =====//
        JPanel panelFormInformasiMenu = new JPanel(new GridBagLayout());
        panelFormInformasiMenu.setBackground(Color.WHITE);

        GridBagConstraints constraintsFormInformasiMenu = new GridBagConstraints();

        // add components to the panel
        constraintsFormInformasiMenu.gridx = 0;
        constraintsFormInformasiMenu.gridy = 0;
        constraintsFormInformasiMenu.anchor = GridBagConstraints.CENTER;
        panelFormInformasiMenu.add(new JLabel("<html><body><h3>DAFTAR HARGA MAKANAN</h2></body></html>"), constraintsFormInformasiMenu);
        constraintsFormInformasiMenu.gridy ++;
        panelFormInformasiMenu.add(jScrollPane1, constraintsFormInformasiMenu);
        constraintsFormInformasiMenu.gridy ++;
        panelFormInformasiMenu.add(new JLabel("<html><body><h3>DAFTAR HARGA MINUMAN</h2></body></html>"), constraintsFormInformasiMenu);
        constraintsFormInformasiMenu.gridy ++;
        panelFormInformasiMenu.add(jScrollPane2, constraintsFormInformasiMenu);
        constraintsFormInformasiMenu.gridy ++;
        constraintsFormInformasiMenu.anchor = GridBagConstraints.LINE_END;
        constraintsFormInformasiMenu.insets = new Insets(7,0,10,0);
        panelFormInformasiMenu.add(bTambahMenu, constraintsFormInformasiMenu);

        // set border for the panel
        panelFormInformasiMenu.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Informasi Daftar Menu " + namaRestoran));

        // add the panel to this panel
        this.add(panelFormInformasiMenu, BorderLayout.PAGE_START);
        //===== panel form informasi menu =====//

        // action
        bTambahMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuInputFrame menuInputFrame = new MenuInputFrame();
                menuInputFrame.setIdRestoran(idRestoran);
                menuInputFrame.init();
                menuInputFrame.setVisible(true);
            }
        });
    }
}
