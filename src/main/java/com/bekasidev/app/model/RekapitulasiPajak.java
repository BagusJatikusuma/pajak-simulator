package com.bekasidev.app.model;

/**
 * Created by waddi on 23/11/18.
 */
public class RekapitulasiPajak {

    private String idRekapitulasi;
    private String bulanPajak;
    private double omzetHasilPemeriksaan;
    private double pajakRestoran;
    private double omzetDilaporkan;
    private double pajakTelahSetor;
    private double omzet;
    private double pokokPajak;
    private float denda;
    private double jumlahTotal;

    public RekapitulasiPajak() {}

    public RekapitulasiPajak(String idRekapitulasi, String bulanPajak, double omzetHasilPemeriksaan, double pajakRestoran, double omzetDilaporkan, double pajakTelahSetor, double omzet, double pokokPajak, float denda, double jumlahTotal) {
        this.idRekapitulasi = idRekapitulasi;
        this.bulanPajak = bulanPajak;
        this.omzetHasilPemeriksaan = omzetHasilPemeriksaan;
        this.pajakRestoran = pajakRestoran;
        this.omzetDilaporkan = omzetDilaporkan;
        this.pajakTelahSetor = pajakTelahSetor;
        this.omzet = omzet;
        this.pokokPajak = pokokPajak;
        this.denda = denda;
        this.jumlahTotal = jumlahTotal;
    }

    public String getIdRekapitulasi() {
        return idRekapitulasi;
    }

    public void setIdRekapitulasi(String idRekapitulasi) {
        this.idRekapitulasi = idRekapitulasi;
    }

    public String getBulanPajak() {
        return bulanPajak;
    }

    public void setBulanPajak(String bulanPajak) {
        this.bulanPajak = bulanPajak;
    }

    public double getOmzetHasilPemeriksaan() {
        return omzetHasilPemeriksaan;
    }

    public void setOmzetHasilPemeriksaan(double omzetHasilPemeriksaan) {
        this.omzetHasilPemeriksaan = omzetHasilPemeriksaan;
    }

    public double getPajakRestoran() {
        return pajakRestoran;
    }

    public void setPajakRestoran(double pajakRestoran) {
        this.pajakRestoran = pajakRestoran;
    }

    public double getOmzetDilaporkan() {
        return omzetDilaporkan;
    }

    public void setOmzetDilaporkan(double omzetDilaporkan) {
        this.omzetDilaporkan = omzetDilaporkan;
    }

    public double getPajakTelahSetor() {
        return pajakTelahSetor;
    }

    public void setPajakTelahSetor(double pajakTelahSetor) {
        this.pajakTelahSetor = pajakTelahSetor;
    }

    public double getOmzet() {
        return omzet;
    }

    public void setOmzet(double omzet) {
        this.omzet = omzet;
    }

    public double getPokokPajak() {
        return pokokPajak;
    }

    public void setPokokPajak(double pokokPajak) {
        this.pokokPajak = pokokPajak;
    }

    public float getDenda() {
        return denda;
    }

    public void setDenda(float denda) {
        this.denda = denda;
    }

    public double getJumlahTotal() {
        return jumlahTotal;
    }

    public void setJumlahTotal(double jumlahTotal) {
        this.jumlahTotal = jumlahTotal;
    }
}
