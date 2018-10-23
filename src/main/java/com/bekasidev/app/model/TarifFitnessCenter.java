package com.bekasidev.app.model;

/**
 * Created by waddi on 23/10/18.
 */
public class TarifFitnessCenter {

    private String idTarifFitness;
    private Double tarifFitness;
    private Integer jumlahPengunjung;
    private Double jumlahTotal;

    public String getIdTarifFitness() { return idTarifFitness; }

    public void setIdTarifFitness(String idTarifFitness) {this.idTarifFitness = idTarifFitness; }

    public Double getTarifFitness() { return tarifFitness; }

    public void setTarifFitness(Double tarifFitness) {this.tarifFitness = tarifFitness; }

    public Integer getJumlahPengunjung() { return jumlahPengunjung; }

    public void setJumlahPengunjung(Integer jumlahPengunjung) {this.jumlahPengunjung = jumlahPengunjung; }

    public Double getJumlahTotal() {return jumlahTotal; }

    public void setJumlahTotal(Double jumlahTotal) {this.jumlahTotal = jumlahTotal; }
}
