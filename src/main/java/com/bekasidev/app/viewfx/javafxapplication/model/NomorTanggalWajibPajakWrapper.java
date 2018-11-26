/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import com.bekasidev.app.model.WajibPajak;
import java.util.Date;

/**
 *
 * @author sudoroot
 */
public class NomorTanggalWajibPajakWrapper {
    private WajibPajak wajibPajak;
    private String nomorPemberitahuanPemeriksaan;
    private Date tanggalPemberitahuanPemeriksaan;
    private String nomorPeminjamanDokumen;
    private Date tanggalPeminjamanDokumen;

    public NomorTanggalWajibPajakWrapper() {
    }

    public NomorTanggalWajibPajakWrapper(WajibPajak wajibPajak, String nomorPemberitahuanPemeriksaan, Date tanggalPemberitahuanPemeriksaan, String nomorPeminjamanDokumen, Date tanggalPeminjamanDokumen) {
        this.wajibPajak = wajibPajak;
        this.nomorPemberitahuanPemeriksaan = nomorPemberitahuanPemeriksaan;
        this.tanggalPemberitahuanPemeriksaan = tanggalPemberitahuanPemeriksaan;
        this.nomorPeminjamanDokumen = nomorPeminjamanDokumen;
        this.tanggalPeminjamanDokumen = tanggalPeminjamanDokumen;
    }

    public WajibPajak getWajibPajak() {
        return wajibPajak;
    }

    public void setWajibPajak(WajibPajak wajibPajak) {
        this.wajibPajak = wajibPajak;
    }

    public String getNomorPemberitahuanPemeriksaan() {
        return nomorPemberitahuanPemeriksaan;
    }

    public void setNomorPemberitahuanPemeriksaan(String nomorPemberitahuanPemeriksaan) {
        this.nomorPemberitahuanPemeriksaan = nomorPemberitahuanPemeriksaan;
    }

    public Date getTanggalPemberitahuanPemeriksaan() {
        return tanggalPemberitahuanPemeriksaan;
    }

    public void setTanggalPemberitahuanPemeriksaan(Date tanggalPemberitahuanPemeriksaan) {
        this.tanggalPemberitahuanPemeriksaan = tanggalPemberitahuanPemeriksaan;
    }

    public String getNomorPeminjamanDokumen() {
        return nomorPeminjamanDokumen;
    }

    public void setNomorPeminjamanDokumen(String nomorPeminjamanDokumen) {
        this.nomorPeminjamanDokumen = nomorPeminjamanDokumen;
    }

    public Date getTanggalPeminjamanDokumen() {
        return tanggalPeminjamanDokumen;
    }

    public void setTanggalPeminjamanDokumen(Date tanggalPeminjamanDokumen) {
        this.tanggalPeminjamanDokumen = tanggalPeminjamanDokumen;
    }
    
    
}
