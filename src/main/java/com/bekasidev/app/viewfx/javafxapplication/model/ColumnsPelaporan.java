/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.WajibPajak;

/**
 *
 * @author sudoroot
 */
public class ColumnsPelaporan {
    private Pegawai pegawai;
    private String namaPegawai;
    private String nipPegawai;
    
    private WajibPajak wajibPajak;
    private String namaWajibPajak;
    private String npwpdWajibPajak;
    private String temuanHasil;
    private String nomorSKPD;
    private String tanggalSKPD;
    private String keterangan;

    public ColumnsPelaporan() {
    }

    public ColumnsPelaporan(Pegawai pegawai, WajibPajak wajibPajak, String temuanHasil, String nomorSKPD, String tanggalSKPD, String keterangan) {
        this.pegawai = pegawai;
        this.wajibPajak = wajibPajak;
        this.temuanHasil = temuanHasil;
        this.nomorSKPD = nomorSKPD;
        this.tanggalSKPD = tanggalSKPD;
        this.keterangan = keterangan;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public WajibPajak getWajibPajak() {
        return wajibPajak;
    }

    public void setWajibPajak(WajibPajak wajibPajak) {
        this.wajibPajak = wajibPajak;
    }

    public String getTemuanHasil() {
        return temuanHasil;
    }

    public void setTemuanHasil(String temuanHasil) {
        this.temuanHasil = temuanHasil;
    }

    public String getNomorSKPD() {
        return nomorSKPD;
    }

    public void setNomorSKPD(String nomorSKPD) {
        this.nomorSKPD = nomorSKPD;
    }

    public String getTanggalSKPD() {
        return tanggalSKPD;
    }

    public void setTanggalSKPD(String tanggalSKPD) {
        this.tanggalSKPD = tanggalSKPD;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getNamaWajibPajak() {
        return namaWajibPajak;
    }

    public void setNamaWajibPajak(String namaWajibPajak) {
        this.namaWajibPajak = namaWajibPajak;
    }

    public String getNpwpdWajibPajak() {
        return npwpdWajibPajak;
    }

    public void setNpwpdWajibPajak(String npwpdWajibPajak) {
        this.npwpdWajibPajak = npwpdWajibPajak;
    }
    
    
    
}
