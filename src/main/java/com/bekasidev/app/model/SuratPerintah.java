package com.bekasidev.app.model;

import java.util.List;

public class SuratPerintah {
    private String idSP;
    private int nomorSurat;
    private String kodeSkpd;
    private String nomorUrut;
    private String nomorSP;
    private String tanggalSP;
    private int tahunAnggatan;
    private String namaPemberi;
    private String jabatanPemberi;
    private String masaPajak;
    private short tahap;
    private String lamaPelaksanaan;
    private String tempat;
    private String tanggalSurat;
    private List<TimSP> listTim;

    public List<TimSP> getListTim() {
        return listTim;
    }

    public void setListTim(List<TimSP> listTim) {
        this.listTim = listTim;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public int getNomorSurat() {
        return nomorSurat;
    }

    public void setNomorSurat(int nomorSurat) {
        this.nomorSurat = nomorSurat;
    }

    public String getKodeSkpd() {
        return kodeSkpd;
    }

    public void setKodeSkpd(String kodeSkpd) {
        this.kodeSkpd = kodeSkpd;
    }

    public String getNomorUrut() {
        return nomorUrut;
    }

    public void setNomorUrut(String nomorUrut) {
        this.nomorUrut = nomorUrut;
    }

    public String getNomorSP() {
        return nomorSP;
    }

    public void setNomorSP(String nomorSP) {
        this.nomorSP = nomorSP;
    }

    public String getTanggalSP() {
        return tanggalSP;
    }

    public void setTanggalSP(String tanggalSP) {
        this.tanggalSP = tanggalSP;
    }

    public int getTahunAnggatan() {
        return tahunAnggatan;
    }

    public void setTahunAnggatan(int tahunAnggatan) {
        this.tahunAnggatan = tahunAnggatan;
    }

    public String getNamaPemberi() {
        return namaPemberi;
    }

    public void setNamaPemberi(String namaPemberi) {
        this.namaPemberi = namaPemberi;
    }

    public String getJabatanPemberi() {
        return jabatanPemberi;
    }

    public void setJabatanPemberi(String jabatanPemberi) {
        this.jabatanPemberi = jabatanPemberi;
    }

    public String getMasaPajak() {
        return masaPajak;
    }

    public void setMasaPajak(String masaPajak) {
        this.masaPajak = masaPajak;
    }

    public short getTahap() {
        return tahap;
    }

    public void setTahap(short tahap) {
        this.tahap = tahap;
    }

    public String getLamaPelaksanaan() {
        return lamaPelaksanaan;
    }

    public void setLamaPelaksanaan(String lamaPelaksanaan) {
        this.lamaPelaksanaan = lamaPelaksanaan;
    }

    public String getTanggalSurat() {
        return tanggalSurat;
    }

    public void setTanggalSurat(String tanggalSurat) {
        this.tanggalSurat = tanggalSurat;
    }
}
