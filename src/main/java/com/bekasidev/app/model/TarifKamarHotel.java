package com.bekasidev.app.model;

/**
 * Created by waddi on 23/10/18.
 */
public class TarifKamarHotel {

    private String idHotel;
    private String idKamarHotel;
    private String tipeKamar;
    private Integer jumlahKamar;
    private Integer jumlahPemakaianKamarSebulan;
    private Double hargaPerKamar;
    private Integer rataKamarHotel;
    private Double rataHargaKamarHotel;
    private Double totalHargaPerKamar;
    private Double totalHargaSeluruhKamar;
    private String tanggalBuat;
    private String label;

    public TarifKamarHotel(){}

    public TarifKamarHotel(String idHotel, String idKamarHotel, String label,String tipeKamar, Double rataHargaKamarHotel, Double totalHargaPerKamar, Double totalHargaSeluruhKamar,Integer rataKamarHotel,Integer jumlahKamar, Integer jumlahPemakaianKamarSebulan, Double hargaPerKamar, String tanggalBuat) {
        this.idHotel = idHotel;
        this.idKamarHotel = idKamarHotel;
        this.label = label;
        this.tipeKamar = tipeKamar;
        this.jumlahKamar = jumlahKamar;
        this.jumlahPemakaianKamarSebulan =jumlahPemakaianKamarSebulan;
        this.hargaPerKamar = hargaPerKamar;
        this.rataKamarHotel = rataKamarHotel;
        this.tanggalBuat = tanggalBuat;
        this.rataHargaKamarHotel = rataHargaKamarHotel;
        this.totalHargaPerKamar = totalHargaPerKamar;
        this.totalHargaSeluruhKamar = totalHargaSeluruhKamar;
    }

    public String getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }

    public String getIdKamarHotel() {
        return idKamarHotel;
    }

    public void setIdKamarHotel(String idKamarHotel) {
        this.idKamarHotel = idKamarHotel;
    }

    public String getTipeKamar() {
        return tipeKamar;
    }

    public void setTipeKamar(String tipeKamar) {
        this.tipeKamar = tipeKamar;
    }

    public Integer getJumlahKamar() {
        return jumlahKamar;
    }

    public void setJumlahKamar(Integer jumlahKamar) {
        this.jumlahKamar = jumlahKamar;
    }

    public Integer getJumlahPemakaianKamarSebulan() {
        return jumlahPemakaianKamarSebulan;
    }

    public void setJumlahPemakaianKamarSebulan(Integer jumlahPemakaianKamarSebulan) {
        this.jumlahPemakaianKamarSebulan = jumlahPemakaianKamarSebulan;
    }

    public Double getHargaPerKamar() {
        return hargaPerKamar;
    }

    public void setHargaPerKamar(Double hargaPerKamar) {
        this.hargaPerKamar = hargaPerKamar;
    }

    public Integer getRataKamarHotel() {
        return rataKamarHotel;
    }

    public void setRataKamarHotel(Integer rataKamarHotel) {
        this.rataKamarHotel = rataKamarHotel;
    }

    public Double getRataHargaKamarHotel() {
        return rataHargaKamarHotel;
    }

    public void setRataHargaKamarHotel(Double rataHargaKamarHotel) {
        this.rataHargaKamarHotel = rataHargaKamarHotel;
    }

    public Double getTotalHargaPerKamar() {
        return totalHargaPerKamar;
    }

    public void setTotalHargaPerKamar(Double totalHargaPerKamar) {
        this.totalHargaPerKamar = totalHargaPerKamar;
    }

    public Double getTotalHargaSeluruhKamar() {
        return totalHargaSeluruhKamar;
    }

    public void setTotalHargaSeluruhKamar(Double totalHargaSeluruhKamar) {
        this.totalHargaSeluruhKamar = totalHargaSeluruhKamar;
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
