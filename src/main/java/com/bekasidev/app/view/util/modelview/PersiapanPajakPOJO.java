/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.util.modelview;

/**
 *
 * @author Bayu Arafli
 */
public class PersiapanPajakPOJO {
    private String tanggalSuratDibuat;
    private WajibPajak wajibPajak;
    private String spDari;
    private String tanggalTurunSP;
    private String nomorSP;
    private String nipPenandatangan;
    private String namaPenandatangan;
    private String jabatanPenandatangan;
    private TimPemeriksa timPemeriksa;
    private String jenisPajak;
    private String masaPajak;
    private String lamaPemeriksaan;

    public PersiapanPajakPOJO() {
    }

    public PersiapanPajakPOJO(String tanggalSuratDibuat, WajibPajak npwpd, String spDari, String tanggalTurunSP, String nomorSP, String nipPenandatangan, String namaPenandatangan, String jabatanPenandatangan, TimPemeriksa timPemeriksa, String jenisPajak, String masaPajak, String lamaPemeriksaan) {
        this.tanggalSuratDibuat = tanggalSuratDibuat;
        this.wajibPajak = npwpd;
        this.spDari = spDari;
        this.tanggalTurunSP = tanggalTurunSP;
        this.nomorSP = nomorSP;
        this.nipPenandatangan = nipPenandatangan;
        this.namaPenandatangan = namaPenandatangan;
        this.jabatanPenandatangan = jabatanPenandatangan;
        this.timPemeriksa = timPemeriksa;
        this.jenisPajak = jenisPajak;
        this.masaPajak = masaPajak;
        this.lamaPemeriksaan = lamaPemeriksaan;
    }

    public String getTanggalSuratDibuat() {
        return tanggalSuratDibuat;
    }

    public void setTanggalSuratDibuat(String tanggalSuratDibuat) {
        this.tanggalSuratDibuat = tanggalSuratDibuat;
    }

    public WajibPajak getNpwpd() {
        return wajibPajak;
    }

    public void setNpwpd(WajibPajak npwpd) {
        this.wajibPajak = npwpd;
    }

    public String getSpDari() {
        return spDari;
    }

    public void setSpDari(String spDari) {
        this.spDari = spDari;
    }

    public String getTanggalTurunSP() {
        return tanggalTurunSP;
    }

    public void setTanggalTurunSP(String tanggalTurunSP) {
        this.tanggalTurunSP = tanggalTurunSP;
    }

    public String getNomorSP() {
        return nomorSP;
    }

    public void setNomorSP(String nomorSP) {
        this.nomorSP = nomorSP;
    }

    public String getNipPenandatangan() {
        return nipPenandatangan;
    }

    public void setNipPenandatangan(String nipPenandatangan) {
        this.nipPenandatangan = nipPenandatangan;
    }

    public String getNamaPenandatangan() {
        return namaPenandatangan;
    }

    public void setNamaPenandatangan(String namaPenandatangan) {
        this.namaPenandatangan = namaPenandatangan;
    }

    public String getJabatanPenandatangan() {
        return jabatanPenandatangan;
    }

    public void setJabatanPenandatangan(String jabatanPenandatangan) {
        this.jabatanPenandatangan = jabatanPenandatangan;
    }

    public TimPemeriksa getTimPemeriksa() {
        return timPemeriksa;
    }

    public void setTimPemeriksa(TimPemeriksa timPemeriksa) {
        this.timPemeriksa = timPemeriksa;
    }

    public String getJenisPajak() {
        return jenisPajak;
    }

    public void setJenisPajak(String jenisPajak) {
        this.jenisPajak = jenisPajak;
    }

    public String getMasaPajak() {
        return masaPajak;
    }

    public void setMasaPajak(String masaPajak) {
        this.masaPajak = masaPajak;
    }

    public String getLamaPemeriksaan() {
        return lamaPemeriksaan;
    }

    public void setLamaPemeriksaan(String lamaPemeriksaan) {
        this.lamaPemeriksaan = lamaPemeriksaan;
    }
    
    
}
