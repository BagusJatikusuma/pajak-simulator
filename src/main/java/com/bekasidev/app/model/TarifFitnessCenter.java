package com.bekasidev.app.model;

/**
 * Created by waddi on 23/10/18.
 */
public class TarifFitnessCenter {

    private String idHotel;
    private String idTarifFitness;
    private String namaFitnessCenter;
    private Double tarifFitness;
    private Integer jumlahPengunjung;
    private Double totalBulananFitnessCenter;
    private String tanggalBuat;
    private String label;

    public TarifFitnessCenter(){}

    public TarifFitnessCenter(String idHotel, String idTarifFitness, String label, Double totalBulananFitnessCenter,String namaFitnessCenter, Double tarifFitness, Integer jumlahPengunjung, String tanggalBuat) {
        this.idHotel = idHotel;
        this.idTarifFitness = idTarifFitness;
        this.label = label;
        this.namaFitnessCenter = namaFitnessCenter;
        this.tarifFitness = tarifFitness;
        this.jumlahPengunjung = jumlahPengunjung;
        this.totalBulananFitnessCenter = totalBulananFitnessCenter;
        this.tanggalBuat = tanggalBuat;
    }

    public String getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }

    public String getIdTarifFitness() {
        return idTarifFitness;
    }

    public void setIdTarifFitness(String idTarifFitness) {
        this.idTarifFitness = idTarifFitness;
    }

    public String getNamaFitnessCenter() {
        return namaFitnessCenter;
    }

    public void setNamaFitnessCenter(String namaFitnessCenter) {
        this.namaFitnessCenter = namaFitnessCenter;
    }

    public Double getTarifFitness() {
        return tarifFitness;
    }

    public void setTarifFitness(Double tarifFitness) {
        this.tarifFitness = tarifFitness;
    }

    public Integer getJumlahPengunjung() {
        return jumlahPengunjung;
    }

    public void setJumlahPengunjung(Integer jumlahPengunjung) {
        this.jumlahPengunjung = jumlahPengunjung;
    }

    public Double getTotalBulananFitnessCenter() {
        return totalBulananFitnessCenter;
    }

    public void setTotalBulananFitnessCenter(Double totalBulananFitnessCenter) {
        this.totalBulananFitnessCenter = totalBulananFitnessCenter;
    }

    public String getTanggalBuat() {
        return tanggalBuat;
    }

    public void setTanggalBuat(String tanggalBuat) {
        this.tanggalBuat = tanggalBuat;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
