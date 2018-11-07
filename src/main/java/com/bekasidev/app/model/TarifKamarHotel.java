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
    private String tanggalBuat;

    public TarifKamarHotel(){}

    public TarifKamarHotel(String idHotel, String idKamarHotel, String tipeKamar, Integer jumlahKamar, Integer jumlahPemakaianKamarSebulan, Double hargaPerKamar, String tanggalBuat) {
        this.idHotel = idHotel;
        this.idKamarHotel = idKamarHotel;
        this.tipeKamar = tipeKamar;
        this.jumlahKamar = jumlahKamar;
        this.jumlahPemakaianKamarSebulan =jumlahPemakaianKamarSebulan;
        this.hargaPerKamar = hargaPerKamar;
        this.tanggalBuat = tanggalBuat;
    }

    public String getIdHotel() {return idHotel;}

    public void setIdHotel(String idHotel) {this.idHotel = idHotel;}

    public String getIdKamarHotel() {return idKamarHotel; }

    public void setIdKamarHotel(String idKamarHotel) {this.idKamarHotel = idKamarHotel; }

    public String getTipeKamar() {return tipeKamar; }

    public void setTipeKamar(String tipeKamar) {this.tipeKamar = tipeKamar; }

    public Integer getJumlahKamar() { return jumlahKamar; }

    public void setJumlahKamar(Integer jumlahKamar) { this.jumlahKamar = jumlahKamar; }

    public Integer getJumlahPemakaianKamarSebulan() {return jumlahPemakaianKamarSebulan; }

    public void setJumlahPemakaianKamarSebulan(Integer jumlahPemakaianKamarSebulan) {this.jumlahPemakaianKamarSebulan = jumlahPemakaianKamarSebulan; }

    public Double getHargaPerKamar() {return hargaPerKamar; }

    public void setHargaPerKamar(Double hargaPerKamar) {this.hargaPerKamar = hargaPerKamar; }

    public String getTanggalBuat() { return tanggalBuat; }

    public void setTanggalBuat(String tanggalBuat) { this.tanggalBuat = tanggalBuat; }
}
