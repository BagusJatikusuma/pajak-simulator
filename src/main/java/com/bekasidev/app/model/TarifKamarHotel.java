package com.bekasidev.app.model;

/**
 * Created by waddi on 23/10/18.
 */
public class TarifKamarHotel {

    private String idHotel;
    private String idKamarHotel;
    private String tipeKamar;
    private Integer jumlahKamar;
    private Integer jumlahTotalKamar;
    private Integer jumlahPemakaianKamarSebulan;
    private Integer jumlahTotalPemakaianKamarSebulan;
    private Double hargaPerKamar;
    private Double jumlahHargaSewaKamar;
    private Double jumlahTotalKeseluruhanHargaSewa;

    public String getIdHotel() {return idHotel;}

    public void setIdHotel(String idHotel) {this.idHotel = idHotel;}

    public String getIdKamarHotel() {return idKamarHotel; }

    public void setIdKamarHotel(String idKamarHotel) {this.idKamarHotel = idKamarHotel; }

    public String getTipeKamar() {return tipeKamar; }

    public void setTipeKamar(String tipeKamar) {this.tipeKamar = tipeKamar; }

    public Integer getJumlahKamar() { return jumlahKamar; }

    public void setJumlahKamar(Integer jumlahKamar) { this.jumlahKamar = jumlahKamar; }

    public Integer getJumlahTotalKamar() {return jumlahTotalKamar; }

    public void setJumlahTotalKamar(Integer jumlahTotalKamar) {this.jumlahTotalKamar = jumlahTotalKamar;}

    public Integer getJumlahPemakaianKamarSebulan() {return jumlahPemakaianKamarSebulan; }

    public void setJumlahPemakaianKamarSebulan(Integer jumlahPemakaianKamarSebulan) {this.jumlahPemakaianKamarSebulan = jumlahPemakaianKamarSebulan; }

    public Double getHargaPerKamar() {return hargaPerKamar; }

    public void setHargaPerKamar(Double hargaPerKamar) {this.hargaPerKamar = hargaPerKamar; }

    public Integer getJumlahTotalPemakaianKamarSebulan() {return jumlahTotalPemakaianKamarSebulan; }

    public void setJumlahTotalPemakaianKamarSebulan(Integer jumlahTotalPemakaianKamarSebulan) {this.jumlahTotalPemakaianKamarSebulan = jumlahTotalPemakaianKamarSebulan; }

    public Double getJumlahHargaSewaKamar() {return jumlahHargaSewaKamar; }

    public void setJumlahHargaSewaKamar(Double jumlahHargaSewaKamar) {this.jumlahHargaSewaKamar = jumlahHargaSewaKamar; }

    public Double getJumlahTotalKeseluruhanHargaSewa() {return jumlahTotalKeseluruhanHargaSewa; }

    public void setJumlahTotalKeseluruhanHargaSewa(Double jumlahTotalKeseluruhanHargaSewa) {this.jumlahTotalKeseluruhanHargaSewa = jumlahTotalKeseluruhanHargaSewa; }
}
