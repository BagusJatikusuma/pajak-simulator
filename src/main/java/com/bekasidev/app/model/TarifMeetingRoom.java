package com.bekasidev.app.model;

/**
 * Created by waddi on 25/10/18.
 */
public class TarifMeetingRoom {

    private String idHotel;
    private String idMeetingRoom;
    private String namaMeetingRoom;
    private Integer jumlahPengunjung;
    private Integer jumlahPengunjungSebulan;
    private Double hargaSewa;
    private Double hargaSewaSebulan;

    public String getIdHotel() {return idHotel;}

    public void setIdHotel(String idHotel) {this.idHotel = idHotel; }

    public String getIdMeetingRoom() {return idMeetingRoom; }

    public void setIdMeetingRoom(String idMeetingRoom) {this.idMeetingRoom = idMeetingRoom; }

    public String getNamaMeetingRoom() {return namaMeetingRoom; }

    public void setNamaMeetingRoom(String namaMeetingRoom) {this.namaMeetingRoom = namaMeetingRoom;}

    public Integer getJumlahPengunjung() {return jumlahPengunjung; }

    public void setJumlahPengunjung(Integer jumlahPengunjung) {this.jumlahPengunjung = jumlahPengunjung; }

    public Integer getJumlahPengunjungSebulan() {return jumlahPengunjungSebulan;}

    public void setJumlahPengunjungSebulan(Integer jumlahPengunjungSebulan) {this.jumlahPengunjungSebulan = jumlahPengunjungSebulan;}

    public Double getHargaSewa() {return hargaSewa;}

    public void setHargaSewa(Double hargaSewa) {this.hargaSewa = hargaSewa; }

    public Double getHargaSewaSebulan() {return hargaSewaSebulan;}

    public void setHargaSewaSebulan(Double hargaSewaSebulan) {this.hargaSewaSebulan = hargaSewaSebulan; }
}
