package com.bekasidev.app.model;

/**
 * Created by waddi on 25/10/18.
 */
public class TarifMeetingRoom {

    private String idHotel;
    private String idMeetingRoom;
    private String label;
    private String namaMeetingRoom;
    private Integer jumlahPengunjung;
    private Double hargaSewa;
    private String tanggalBuat;

    public TarifMeetingRoom(){}

    public TarifMeetingRoom(String idHotel, String idMeetingRoom, String label,String namaMeetingRoom, Integer jumlahPengunjung, Double hargaSewa, String tanggalBuat) {
        this.idHotel = idHotel;
        this.idMeetingRoom = idMeetingRoom;
        this.label = label;
        this.namaMeetingRoom = namaMeetingRoom;
        this.jumlahPengunjung = jumlahPengunjung;
        this.hargaSewa = hargaSewa;
        this.tanggalBuat = tanggalBuat;
    }

    public String getIdHotel() {return idHotel;}

    public void setIdHotel(String idHotel) {this.idHotel = idHotel; }

    public String getIdMeetingRoom() {return idMeetingRoom; }

    public void setIdMeetingRoom(String idMeetingRoom) {this.idMeetingRoom = idMeetingRoom; }

    public String getLabel() {return label; }

    public void setLabel(String label) {this.label = label;}

    public String getNamaMeetingRoom() {return namaMeetingRoom; }

    public void setNamaMeetingRoom(String namaMeetingRoom) {this.namaMeetingRoom = namaMeetingRoom;}

    public Integer getJumlahPengunjung() {return jumlahPengunjung; }

    public void setJumlahPengunjung(Integer jumlahPengunjung) {this.jumlahPengunjung = jumlahPengunjung; }

    public Double getHargaSewa() {return hargaSewa;}

    public void setHargaSewa(Double hargaSewa) {this.hargaSewa = hargaSewa; }

    public String getTanggalBuat() { return tanggalBuat; }

    public void setTanggalBuat(String tanggalBuat) { this.tanggalBuat = tanggalBuat; }

}
