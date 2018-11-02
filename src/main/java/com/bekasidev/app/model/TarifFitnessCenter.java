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
    private String tanggalBuat;

    public TarifFitnessCenter(){}

    public TarifFitnessCenter(String idHotel, String idTarifFitness, String namaFitnessCenter, Double tarifFitness, Integer jumlahPengunjung, String tanggalBuat) {
        this.idHotel = idHotel;
        this.idTarifFitness = idTarifFitness;
        this.namaFitnessCenter = namaFitnessCenter;
        this.tarifFitness = tarifFitness;
        this.jumlahPengunjung = jumlahPengunjung;
        this.tanggalBuat = tanggalBuat;
    }

    public String getIdHotel() {return idHotel; }

    public void setIdHotel(String idHotel) {this.idHotel = idHotel; }

    public String getIdTarifFitness() { return idTarifFitness; }

    public void setIdTarifFitness(String idTarifFitness) {this.idTarifFitness = idTarifFitness; }

    public String getNamaFitnessCenter() {return namaFitnessCenter; }

    public void setNamaFitnessCenter(String namaFitnessCenter) {this.namaFitnessCenter = namaFitnessCenter; }

    public Double getTarifFitness() { return tarifFitness; }

    public void setTarifFitness(Double tarifFitness) {this.tarifFitness = tarifFitness; }

    public Integer getJumlahPengunjung() { return jumlahPengunjung; }

    public void setJumlahPengunjung(Integer jumlahPengunjung) {this.jumlahPengunjung = jumlahPengunjung; }

    public String getTanggalBuat() { return tanggalBuat; }

    public void setTanggalBuat(String tanggalBuat) {this.tanggalBuat = tanggalBuat; }
}
