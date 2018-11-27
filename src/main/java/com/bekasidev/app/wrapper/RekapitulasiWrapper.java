package com.bekasidev.app.wrapper;

import com.bekasidev.app.model.Rekapitulasi;

import java.util.List;

public class RekapitulasiWrapper {
    private String idSP;
    private String idWP;
    List<Rekapitulasi> listRekapitulasi;
    private Double totalOmzetPeriksa;
    private Double totalPajakPeriksa;
    private Double totalOmzetLaporan;
    private Double totalPajakDisetor;
    private Double totalOmzet;
    private Double totalPokokPajak;
    private Double totalDenda;
    private Double totalJumlah;

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getIdWP() {
        return idWP;
    }

    public void setIdWP(String idWP) {
        this.idWP = idWP;
    }

    public List<Rekapitulasi> getListRekapitulasi() {
        return listRekapitulasi;
    }

    public void setListRekapitulasi(List<Rekapitulasi> listRekapitulasi) {
        this.listRekapitulasi = listRekapitulasi;
    }

    public Double getTotalOmzetPeriksa() {
        return totalOmzetPeriksa;
    }

    public void setTotalOmzetPeriksa(Double totalOmzetPeriksa) {
        this.totalOmzetPeriksa = totalOmzetPeriksa;
    }

    public Double getTotalPajakPeriksa() {
        return totalPajakPeriksa;
    }

    public void setTotalPajakPeriksa(Double totalPajakPeriksa) {
        this.totalPajakPeriksa = totalPajakPeriksa;
    }

    public Double getTotalOmzetLaporan() {
        return totalOmzetLaporan;
    }

    public void setTotalOmzetLaporan(Double totalOmzetLaporan) {
        this.totalOmzetLaporan = totalOmzetLaporan;
    }

    public Double getTotalPajakDisetor() {
        return totalPajakDisetor;
    }

    public void setTotalPajakDisetor(Double totalPajakDisetor) {
        this.totalPajakDisetor = totalPajakDisetor;
    }

    public Double getTotalOmzet() {
        return totalOmzet;
    }

    public void setTotalOmzet(Double totalOmzet) {
        this.totalOmzet = totalOmzet;
    }

    public Double getTotalPokokPajak() {
        return totalPokokPajak;
    }

    public void setTotalPokokPajak(Double totalPokokPajak) {
        this.totalPokokPajak = totalPokokPajak;
    }

    public Double getTotalDenda() {
        return totalDenda;
    }

    public void setTotalDenda(Double totalDenda) {
        this.totalDenda = totalDenda;
    }

    public Double getTotalJumlah() {
        return totalJumlah;
    }

    public void setTotalJumlah(Double totalJumlah) {
        this.totalJumlah = totalJumlah;
    }
}
