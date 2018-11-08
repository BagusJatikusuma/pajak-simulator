/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakrestoranview;

/**
 *
 * @author Bayu Arafli
 */
public class FormPersiapanPanel extends javax.swing.JPanel {

    /**
     * Creates new form FormPersiapanPanel
     */
    public FormPersiapanPanel() {
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
        tfSuratPerintahDikeluarkanOleh = new javax.swing.JTextField();
        labelNomorSuratPerintah = new javax.swing.JLabel();
        tfNomorSuratPerintah = new javax.swing.JTextField();
        labelTanggalSuratPerintah = new javax.swing.JLabel();
        tfTanggalSuratPerintah = new javax.swing.JTextField();
        labelTimPemeriksa = new javax.swing.JLabel();
        tfTimPemeriksa = new javax.swing.JTextField();
        bCekAnggotaTim = new javax.swing.JButton();
        labelMasaPajak = new javax.swing.JLabel();
        tfMasaPajak = new javax.swing.JTextField();
        labelKeteranganMasaPajak = new javax.swing.JLabel();
        labelLamaPemeriksaan = new javax.swing.JLabel();
        tfLamaPemeriksaan = new javax.swing.JTextField();
        labelPenandatangan = new javax.swing.JLabel();
        tfPenandatangan = new javax.swing.JTextField();
        bGenerate = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelJudul.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelJudul.setText("FORM PERSIAPAN");

        labelSuratPerintahDikeluarkanOleh.setText("Surat Perintah Dikeluarkan Oleh");

        tfSuratPerintahDikeluarkanOleh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSuratPerintahDikeluarkanOlehActionPerformed(evt);
            }
        });

        labelNomorSuratPerintah.setText("Nomor Surat Perintah");

        tfNomorSuratPerintah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomorSuratPerintahActionPerformed(evt);
            }
        });

        labelTanggalSuratPerintah.setText("Tanggal Surat Perintah Dikeluarkan");

        labelTimPemeriksa.setText("Tim Pemeriksa");

        tfTimPemeriksa.setText("Tim 1");

        bCekAnggotaTim.setText("Cek Anggota Tim");

        labelMasaPajak.setText("Masa Pajak");

        tfMasaPajak.setText("1 Tahun");

        labelKeteranganMasaPajak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelKeteranganMasaPajak.setText("Januari 2017   s.d   Desember 2017");

        labelLamaPemeriksaan.setText("Lama Pemeriksaan ( Hari )");

        tfLamaPemeriksaan.setText("10");

        labelPenandatangan.setText("Penandatangan");

        tfPenandatangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPenandatanganActionPerformed(evt);
            }
        });

        bGenerate.setText("Generate");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelSuratPerintahDikeluarkanOleh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfSuratPerintahDikeluarkanOleh)
                        .addComponent(labelNomorSuratPerintah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfNomorSuratPerintah)
                        .addComponent(labelTanggalSuratPerintah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfTanggalSuratPerintah)
                        .addComponent(labelTimPemeriksa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(tfTimPemeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bCekAnggotaTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(labelMasaPajak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(tfMasaPajak, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(labelKeteranganMasaPajak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(labelLamaPemeriksaan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfLamaPemeriksaan)
                        .addComponent(labelPenandatangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bGenerate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfPenandatangan, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(labelJudul)
                .addGap(18, 18, 18)
                .addComponent(labelSuratPerintahDikeluarkanOleh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfSuratPerintahDikeluarkanOleh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNomorSuratPerintah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNomorSuratPerintah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTanggalSuratPerintah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTanggalSuratPerintah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(labelTimPemeriksa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTimPemeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCekAnggotaTim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMasaPajak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMasaPajak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelKeteranganMasaPajak))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLamaPemeriksaan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLamaPemeriksaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPenandatangan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPenandatangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bGenerate)
                .addContainerGap(37, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfSuratPerintahDikeluarkanOlehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSuratPerintahDikeluarkanOlehActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSuratPerintahDikeluarkanOlehActionPerformed

    private void tfNomorSuratPerintahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomorSuratPerintahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomorSuratPerintahActionPerformed

    private void tfPenandatanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPenandatanganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPenandatanganActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCekAnggotaTim;
    private javax.swing.JButton bGenerate;
    private javax.swing.JLabel labelJudul;
    private javax.swing.JLabel labelKeteranganMasaPajak;
    private javax.swing.JLabel labelLamaPemeriksaan;
    private javax.swing.JLabel labelMasaPajak;
    private javax.swing.JLabel labelNomorSuratPerintah;
    private javax.swing.JLabel labelPenandatangan;
    private javax.swing.JLabel labelSuratPerintahDikeluarkanOleh;
    private javax.swing.JLabel labelTanggalSuratPerintah;
    private javax.swing.JLabel labelTimPemeriksa;
    private javax.swing.JTextField tfLamaPemeriksaan;
    private javax.swing.JTextField tfMasaPajak;
    private javax.swing.JTextField tfNomorSuratPerintah;
    private javax.swing.JTextField tfPenandatangan;
    private javax.swing.JTextField tfSuratPerintahDikeluarkanOleh;
    private javax.swing.JTextField tfTanggalSuratPerintah;
    private javax.swing.JTextField tfTimPemeriksa;
    // End of variables declaration//GEN-END:variables
}
