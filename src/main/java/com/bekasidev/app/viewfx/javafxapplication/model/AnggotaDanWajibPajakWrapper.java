/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

/**
 *
 * @author Bayu Arafli
 */
public class AnggotaDanWajibPajakWrapper {
    private String idWajibPajak;
    private String namaWajibPajak;
    private short jenisWp;
    
    private String idTim;
    private String nipPegawai;
    private String namaPegawai;
    private String pangkat;
    private String golongan;
    private String jabatan;

    public AnggotaDanWajibPajakWrapper() {
    }

    public AnggotaDanWajibPajakWrapper(String idWajibPajak, String namaWajibPajak, short jenisWp, String idTim, String nipPegawai, String namaPegawai, String pangkat, String golongan, String jabatan) {
        this.idWajibPajak = idWajibPajak;
        this.namaWajibPajak = namaWajibPajak;
        this.jenisWp = jenisWp;
        this.idTim = idTim;
        this.nipPegawai = nipPegawai;
        this.namaPegawai = namaPegawai;
        this.pangkat = pangkat;
        this.golongan = golongan;
        this.jabatan = jabatan;
    }

    public String getIdWajibPajak() {
        return idWajibPajak;
    }

    public void setIdWajibPajak(String idWajibPajak) {
        this.idWajibPajak = idWajibPajak;
    }

    public String getNamaWajibPajak() {
        return namaWajibPajak;
    }

    public void setNamaWajibPajak(String namaWajibPajak) {
        this.namaWajibPajak = namaWajibPajak;
    }

    public short getJenisWp() {
        return jenisWp;
    }

    public void setJenisWp(short jenisWp) {
        this.jenisWp = jenisWp;
    }

    public String getIdTim() {
        return idTim;
    }

    public void setIdTim(String idTim) {
        this.idTim = idTim;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    
    
}
