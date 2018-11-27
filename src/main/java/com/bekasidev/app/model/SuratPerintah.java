package com.bekasidev.app.model;

import java.util.List;

public class SuratPerintah {
    private String idSP;
    private String nomorSurat;
    private String nomorUrut;
    
    private String nomorSK;
    private String tanggalSK;
    private int tahunAnggaranSK;
    private String pemberiSK;
    
    private String nomorSuratBiaya;
    private String tanggalBiaya;
    private int tahunAnggaranBiaya;
    
    private Pegawai pemberiSP;
    
    private String masaPajakAwal;
    private String masaPajakAkhir;
    
    private short tahap;
    
    private short lamaPelaksanaan;
    private String tempat;
    private String tanggalSurat;
    
    private List<TimSP> listTim;

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getNomorSurat() {
        return nomorSurat;
    }

    public void setNomorSurat(String nomorSurat) {
        this.nomorSurat = nomorSurat;
    }

    public String getNomorUrut() {
        return nomorUrut;
    }

    public void setNomorUrut(String nomorUrut) {
        this.nomorUrut = nomorUrut;
    }

    public String getNomorSK() {
        return nomorSK;
    }

    public void setNomorSK(String nomorSK) {
        this.nomorSK = nomorSK;
    }

    public String getTanggalSK() {
        return tanggalSK;
    }

    public void setTanggalSK(String tanggalSK) {
        this.tanggalSK = tanggalSK;
    }

    public int getTahunAnggaranSK() {
        return tahunAnggaranSK;
    }

    public void setTahunAnggaranSK(int tahunAnggaranSK) {
        this.tahunAnggaranSK = tahunAnggaranSK;
    }

    public String getPemberiSK() {
        return pemberiSK;
    }

    public void setPemberiSK(String pemberiSK) {
        this.pemberiSK = pemberiSK;
    }

    public String getNomorSuratBiaya() {
        return nomorSuratBiaya;
    }

    public void setNomorSuratBiaya(String nomorSuratBiaya) {
        this.nomorSuratBiaya = nomorSuratBiaya;
    }

    public String getTanggalBiaya() {
        return tanggalBiaya;
    }

    public void setTanggalBiaya(String tanggalBiaya) {
        this.tanggalBiaya = tanggalBiaya;
    }

    public int getTahunAnggaranBiaya() {
        return tahunAnggaranBiaya;
    }

    public void setTahunAnggaranBiaya(int tahunAnggaranBiaya) {
        this.tahunAnggaranBiaya = tahunAnggaranBiaya;
    }

    public Pegawai getPemberiSP() {
        return pemberiSP;
    }

    public void setPemberiSP(Pegawai pemberiSP) {
        this.pemberiSP = pemberiSP;
    }

    public String getMasaPajakAwal() {
        return masaPajakAwal;
    }

    public void setMasaPajakAwal(String masaPajakAwal) {
        this.masaPajakAwal = masaPajakAwal;
    }

    public String getMasaPajakAkhir() {
        return masaPajakAkhir;
    }

    public void setMasaPajakAkhir(String masaPajakAkhir) {
        this.masaPajakAkhir = masaPajakAkhir;
    }

    public short getTahap() {
        return tahap;
    }

    public void setTahap(short tahap) {
        this.tahap = tahap;
    }

    public short getLamaPelaksanaan() {
        return lamaPelaksanaan;
    }

    public void setLamaPelaksanaan(short lamaPelaksanaan) {
        this.lamaPelaksanaan = lamaPelaksanaan;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTanggalSurat() {
        return tanggalSurat;
    }

    public void setTanggalSurat(String tanggalSurat) {
        this.tanggalSurat = tanggalSurat;
    }

    public List<TimSP> getListTim() {
        return listTim;
    }

    public void setListTim(List<TimSP> listTim) {
        this.listTim = listTim;
    }
}
