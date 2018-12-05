package com.bekasidev.app.model;

public class Rekapitulasi {

    private String idWP;
    private String idSP;
    private String bulan;
    private Double omzetHasilPeriksa;
    private Double pajakHasilPeriksa;
    private Double omzetLaporan;
    private Double pajakDisetor;
    private Double omzet;
    private Double pokokPajak;
    private Double denda;
    private int persentaseDenda;
    private Double jumlah;

    public String getIdWP() {
        return idWP;
    }

    public void setIdWP(String idWP) {
        this.idWP = idWP;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public Rekapitulasi(String bulan, int persentaseDenda) {
        this.bulan = bulan;
        this.persentaseDenda = persentaseDenda;
    }

    public Rekapitulasi() {
    }

    public Rekapitulasi(String bulan, Double omzetHasilPeriksa, Double pajakHasilPeriksa, Double omzetLaporan, Double pajakDisetor, Double omzet, Double pokokPajak, Double denda, int persentaseDenda, Double jumlah) {
        this.bulan = bulan;
        this.omzetHasilPeriksa = omzetHasilPeriksa;
        this.pajakHasilPeriksa = pajakHasilPeriksa;
        this.omzetLaporan = omzetLaporan;
        this.pajakDisetor = pajakDisetor;
        this.omzet = omzet;
        this.pokokPajak = pokokPajak;
        this.denda = denda;
        this.persentaseDenda = persentaseDenda;
        this.jumlah = jumlah;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public Double getOmzetHasilPeriksa() {
        return omzetHasilPeriksa;
    }

    public void setOmzetHasilPeriksa(Double omzetHasilPeriksa) {
        this.omzetHasilPeriksa = omzetHasilPeriksa;
    }

    public Double getPajakHasilPeriksa() {
        return pajakHasilPeriksa;
    }

    public void setPajakHasilPeriksa(Double pajakHasilPeriksa) {
        this.pajakHasilPeriksa = pajakHasilPeriksa;
    }

    public Double getOmzetLaporan() {
        return omzetLaporan;
    }

    public void setOmzetLaporan(Double omzetLaporan) {
        this.omzetLaporan = omzetLaporan;
    }

    public Double getPajakDisetor() {
        return pajakDisetor;
    }

    public void setPajakDisetor(Double pajakDisetor) {
        this.pajakDisetor = pajakDisetor;
    }

    public Double getOmzet() {
        return omzet;
    }

    public void setOmzet(Double omzet) {
        this.omzet = omzet;
    }

    public Double getPokokPajak() {
        return pokokPajak;
    }

    public void setPokokPajak(Double pokokPajak) {
        this.pokokPajak = pokokPajak;
    }

    public Double getDenda() {
        return denda;
    }

    public void setDenda(Double denda) {
        this.denda = denda;
    }

    public int getPersentaseDenda() {
        return persentaseDenda;
    }

    public void setPersentaseDenda(int persentaseDenda) {
        this.persentaseDenda = persentaseDenda;
    }

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }
}
