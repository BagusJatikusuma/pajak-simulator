/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.model.RestoranTransaction;
import com.bekasidev.app.service.backend.RestoranService;
import com.bekasidev.app.service.backend.RestoranTransactionService;
import com.bekasidev.app.service.backend.impl.RestoranServiceImpl;
import java.awt.BorderLayout;
import com.bekasidev.app.service.backend.impl.RestoranTransactionServiceImpl;
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
                    panelSubSepi;

    ContentPanel contentPanelCover;
    
    private JLabel labelRataRataOmzet,
                    labelOFRamai,
                    labelOFNormal,
                    labelOFSepi,
                    labelJumlah,
            labelPotensiPajakTahun,
            labelPotensiPajakBulan,
                    labelTotalKeseluruhan;

    private JButton bCalculate, bKembali, bSavePotensiPajak;

    // model
    RestoranTransaction restoranTransaction;

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

        bSavePotensiPajak = new JButton("  Save  ");
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
        panelFormHasilPotensiPenjualanAtas.add(new JLabel("Potensi Pajak Restoran"), constraintsFormHasilAtas);
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
        //===== panel form informasi menu =====//
        JPanel panelFormInformasiMenu = new JPanel(new GridBagLayout());
        panelFormInformasiMenu.setBackground(Color.WHITE);

        GridBagConstraints constraintsFormInformasiMenu = new GridBagConstraints();

        // add components to the panel
        constraintsFormInformasiMenu.insets = new Insets(7, 7, 7, 7);

        // add components to the panel
        constraintsFormInformasiMenu.gridx = 0;
        constraintsFormInformasiMenu.gridy = 0;
        constraintsFormInformasiMenu.anchor = GridBagConstraints.CENTER;
        panelFormInformasiMenu.add(new JLabel("<html><body><h2>DAFTAR HARGA MAKANAN</h2></body></html>"), constraintsFormInformasiMenu);

        // set border for the panel
        panelFormInformasiMenu.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Informasi Daftar Menu " + namaRestoran));

        // add the panel to this panel
        this.add(panelFormInformasiMenu, BorderLayout.CENTER);
        //===== panel form informasi menu =====//
    }
}
