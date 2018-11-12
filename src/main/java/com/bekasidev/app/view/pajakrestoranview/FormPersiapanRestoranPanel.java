/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakrestoranview;

import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.view.util.modelview.PersiapanPajakPOJO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Bayu Arafli
 */
public class FormPersiapanRestoranPanel extends javax.swing.JPanel {
    private ReportService reportService;

    /**
     * Creates new form FormPersiapanPanel
     */
    public FormPersiapanRestoranPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelJudul = new javax.swing.JLabel();
        labelSuratPerintahDikeluarkanOleh = new javax.swing.JLabel();
        cbSPDari = new javax.swing.JComboBox<>();
        labelNomorSuratPerintah = new javax.swing.JLabel();
        tfNomorSuratPerintah = new javax.swing.JTextField();
        labelTanggalSuratPerintah = new javax.swing.JLabel();
        dcTanggalSPDikeluarkan = new com.toedter.calendar.JDateChooser();
        labelTimPemeriksa = new javax.swing.JLabel();
        cbTimPemeriksa = new javax.swing.JComboBox<>();
        bCekAnggotaTim = new javax.swing.JButton();
        labelMasaPajak = new javax.swing.JLabel();
        mcAwalMasa = new com.toedter.calendar.JMonthChooser();
        ycAwalMasa = new com.toedter.calendar.JYearChooser();
        labelSampai = new javax.swing.JLabel();
        mcAkhirMasa = new com.toedter.calendar.JMonthChooser();
        ycAkhirMasa = new com.toedter.calendar.JYearChooser();
        labelLamaPemeriksaan = new javax.swing.JLabel();
        spLamaPemeriksaan = new javax.swing.JSpinner();
        labelPenandatangan = new javax.swing.JLabel();
        cbPenandatangan = new javax.swing.JComboBox<>();
        bGenerate = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelJudul.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelJudul.setText("FORM PERSIAPAN RESTORAN");

        labelSuratPerintahDikeluarkanOleh.setText("Surat Perintah Dikeluarkan Oleh");

        cbSPDari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kepala Badan Pendapatan", "Sekretaris" }));

        labelNomorSuratPerintah.setText("Nomor Surat Perintah");

        tfNomorSuratPerintah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomorSuratPerintahActionPerformed(evt);
            }
        });

        labelTanggalSuratPerintah.setText("Tanggal Surat Perintah Dikeluarkan");

        labelTimPemeriksa.setText("Tim Pemeriksa");

        cbTimPemeriksa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tim 1", "Tim 2", "Tim 3", "Tim 4" }));

        bCekAnggotaTim.setText("Cek Anggota Tim");

        labelMasaPajak.setText("Masa Pajak");

        labelSampai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSampai.setText("s.d");

        labelLamaPemeriksaan.setText("Lama Pemeriksaan ( Hari )");

        labelPenandatangan.setText("Penandatangan");

        cbPenandatangan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kepala Badan Pendapatan", "Sekretaris" }));

        bGenerate.setText("Generate");
        bGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGenerateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mcAwalMasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ycAwalMasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSampai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mcAkhirMasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ycAkhirMasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSuratPerintahDikeluarkanOleh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNomorSuratPerintah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfNomorSuratPerintah)
                    .addComponent(labelTanggalSuratPerintah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTimPemeriksa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbTimPemeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCekAnggotaTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelMasaPajak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLamaPemeriksaan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelPenandatangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bGenerate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSPDari, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dcTanggalSPDikeluarkan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spLamaPemeriksaan)
                    .addComponent(cbPenandatangan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(labelJudul)
                .addGap(18, 18, 18)
                .addComponent(labelSuratPerintahDikeluarkanOleh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSPDari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNomorSuratPerintah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNomorSuratPerintah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTanggalSuratPerintah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcTanggalSPDikeluarkan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(labelTimPemeriksa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCekAnggotaTim)
                    .addComponent(cbTimPemeriksa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMasaPajak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(mcAwalMasa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(labelSampai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ycAwalMasa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mcAkhirMasa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ycAkhirMasa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLamaPemeriksaan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spLamaPemeriksaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPenandatangan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPenandatangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bGenerate)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        dcTanggalSPDikeluarkan.setDateFormatString("dd MMMM yyyy");
    }// </editor-fold>//GEN-END:initComponents

    private void tfNomorSuratPerintahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomorSuratPerintahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomorSuratPerintahActionPerformed

    private void bGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGenerateActionPerformed
        // TODO add your handling code here:
        reportService
                = ServiceFactory.getReportService();
        PersiapanPajakPOJO persiapanPajakPOJO
                = (PersiapanPajakPOJO)SessionProvider
                        .getPajakMapSession()
                        .get("persiapan_pajak_restoran");
        
        persiapanPajakPOJO.setSpDari(cbSPDari.getSelectedItem().toString());
        persiapanPajakPOJO.setNomorSP(tfNomorSuratPerintah.getText());
        persiapanPajakPOJO.setJenisPajak("Restoran");
        
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        persiapanPajakPOJO.setTanggalTurunSP(dateFormat.format(dcTanggalSPDikeluarkan.getDate()));        
        
        persiapanPajakPOJO.setLamaPemeriksaan(spLamaPemeriksaan.getValue().toString());        
        persiapanPajakPOJO.setNipPenandatangan(cbPenandatangan.getSelectedItem().toString());        
        
        PersiapanPajakPOJO persiapanPajakPOJO2
                = (PersiapanPajakPOJO)SessionProvider
                        .getPajakMapSession()
                        .get("persiapan_pajak_restoran");
        System.out.println("tfNamaWP "+persiapanPajakPOJO2.getNpwpd().getNamaWP());
        System.out.println("tfLamaPemeriksaan "+persiapanPajakPOJO2.getLamaPemeriksaan());
        System.out.println("tfNomorSuratPerintah "+persiapanPajakPOJO2.getNomorSP());
        System.out.println("tfPenandatangan "+persiapanPajakPOJO2.getNipPenandatangan());
        System.out.println("tfSuratPerintahDikeluarkanOleh "+persiapanPajakPOJO2.getSpDari());
        System.out.println("tfTanggalSuratPerintah "+persiapanPajakPOJO2.getTanggalTurunSP());
        
        reportService.createPersiapanPajakRestoranReport();
    }//GEN-LAST:event_bGenerateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCekAnggotaTim;
    private javax.swing.JButton bGenerate;
    private javax.swing.JComboBox<String> cbPenandatangan;
    private javax.swing.JComboBox<String> cbSPDari;
    private javax.swing.JComboBox<String> cbTimPemeriksa;
    private com.toedter.calendar.JDateChooser dcTanggalSPDikeluarkan;
    private javax.swing.JLabel labelJudul;
    private javax.swing.JLabel labelLamaPemeriksaan;
    private javax.swing.JLabel labelMasaPajak;
    private javax.swing.JLabel labelNomorSuratPerintah;
    private javax.swing.JLabel labelPenandatangan;
    private javax.swing.JLabel labelSampai;
    private javax.swing.JLabel labelSuratPerintahDikeluarkanOleh;
    private javax.swing.JLabel labelTanggalSuratPerintah;
    private javax.swing.JLabel labelTimPemeriksa;
    private com.toedter.calendar.JMonthChooser mcAkhirMasa;
    private com.toedter.calendar.JMonthChooser mcAwalMasa;
    private javax.swing.JSpinner spLamaPemeriksaan;
    private javax.swing.JTextField tfNomorSuratPerintah;
    private com.toedter.calendar.JYearChooser ycAkhirMasa;
    private com.toedter.calendar.JYearChooser ycAwalMasa;
    // End of variables declaration//GEN-END:variables
}
