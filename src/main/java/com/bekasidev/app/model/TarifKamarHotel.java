package com.bekasidev.app.model;

/**
 * Created by waddi on 23/10/18.
 */
public class TarifKamarHotel {

    private String idKamarHotel;
    private String tipeKamar;
    private Integer jumlahKamar;
    private Integer jumlahTotalKamar;
    private Double hargaPerKamar;
    private Double jumlahHargaSewaKamar;
    private Double jumlahTotalKeseluruhanHargaSewa;

    public String getIdKamarHotel() {return idKamarHotel; }

    public void setIdKamarHotel(String idKamarHotel) {this.idKamarHotel = idKamarHotel; }

    public String getTipeKamar() {return tipeKamar; }

    public void setTipeKamar(String tipeKamar) {this.tipeKamar = tipeKamar; }

    public Integer getJumlahKamar() {return jumlahKamar; }

    public void setJumlahKamar(Integer jumlahKamar) {this.jumlahKamar = jumlahKamar; }

    public Double getHargaPerKamar() {return hargaPerKamar; }

    public void setHargaPerKamar(Double hargaPerKamar) {this.hargaPerKamar = hargaPerKamar; }

    public Integer getJumlahTotalKamar() {return jumlahTotalKamar; }

    public void setJumlahTotalKamar(Integer jumlahTotalKamar) {this.jumlahTotalKamar = jumlahTotalKamar; }

    public Double getJumlahHargaSewaKamar() {return jumlahHargaSewaKamar; }

    public void setJumlahHargaSewaKamar(Double jumlahHargaSewaKamar) {this.jumlahHargaSewaKamar = jumlahHargaSewaKamar; }

    public Double getJumlahTotalKeseluruhanHargaSewa() {return jumlahTotalKeseluruhanHargaSewa; }

    public void setJumlahTotalKeseluruhanHargaSewa(Double jumlahTotalKeseluruhanHargaSewa) {this.jumlahTotalKeseluruhanHargaSewa = jumlahTotalKeseluruhanHargaSewa; }
}