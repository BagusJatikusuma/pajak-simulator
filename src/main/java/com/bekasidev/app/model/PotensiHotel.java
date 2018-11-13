package com.bekasidev.app.model;

/**
 * Created by waddi on 09/11/18.
 */
public class PotensiHotel {

    private String idHotel;
    private String idPotensiHotel;
    private String idKamarHotel;
    private String idMeetingRoom;
    private String idFitnessCenter;
    private String idBenchmarkHotel;
    private String label;
    private Integer sptpd;
    private Integer jumlahTerpakai;
    private Double tarifAwal;
    private Double sptpdTotal;
    private Double potensiTotal;
    private String tanggalBuat;

    public PotensiHotel() {};

    public PotensiHotel(String idHotel, String idPotensiHotel,String idKamarHotel, String idMeetingRoom, String tanggalBuat,String idFitnessCenter, String idBenchmarkHotel, String label, Integer sptpd, Integer jumlahTerpakai, Double tarifAwal, Double sptpdTotal, Double potensiTotal){
        this.idHotel = idHotel;
        this.idPotensiHotel = idPotensiHotel;
        this.idKamarHotel = idKamarHotel;
        this.idMeetingRoom = idMeetingRoom;
        this.idFitnessCenter = idFitnessCenter;
        this.idBenchmarkHotel = idBenchmarkHotel;
        this.label = label;
        this.sptpd = sptpd;
        this.jumlahTerpakai = jumlahTerpakai;
        this.tarifAwal = tarifAwal;
        this.sptpdTotal = sptpdTotal;
        this.potensiTotal = potensiTotal;
        this.tanggalBuat = tanggalBuat;
    }

    public String getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }

    public String getIdPotensiHotel() {
        return idPotensiHotel;
    }

    public void setIdPotensiHotel(String idPotensiHotel) {
        this.idPotensiHotel = idPotensiHotel;
    }

    public String getIdKamarHotel() {
        return idKamarHotel;
    }

    public void setIdKamarHotel(String idKamarHotel) {
        this.idKamarHotel = idKamarHotel;
    }

    public String getIdMeetingRoom() {
        return idMeetingRoom;
    }

    public void setIdMeetingRoom(String idMeetingRoom) {
        this.idMeetingRoom = idMeetingRoom;
    }

    public String getIdFitnessCenter() {
        return idFitnessCenter;
    }

    public void setIdFitnessCenter(String idFitnessCenter) {
        this.idFitnessCenter = idFitnessCenter;
    }

    public String getIdBenchmarkHotel() {
        return idBenchmarkHotel;
    }

    public void setIdBenchmarkHotel(String idBenchmarkHotel) {
        this.idBenchmarkHotel = idBenchmarkHotel;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getSptpd() {
        return sptpd;
    }

    public void setSptpd(Integer sptpd) {
        this.sptpd = sptpd;
    }

    public Integer getJumlahTerpakai() {
        return jumlahTerpakai;
    }

    public void setJumlahTerpakai(Integer jumlahTerpakai) {
        this.jumlahTerpakai = jumlahTerpakai;
    }

    public Double getTarifAwal() {
        return tarifAwal;
    }

    public void setTarifAwal(Double tarifAwal) {
        this.tarifAwal = tarifAwal;
    }

    public Double getSptpdTotal() {
        return sptpdTotal;
    }

    public void setSptpdTotal(Double sptpdTotal) {
        this.sptpdTotal = sptpdTotal;
    }

    public Double getPotensiTotal() {
        return potensiTotal;
    }

    public void setPotensiTotal(Double potensiTotal) {
        this.potensiTotal = potensiTotal;
    }

    public String getTanggalBuat() {
        return tanggalBuat;
    }

    public void setTanggalBuat(String tanggalBuat) {
        this.tanggalBuat = tanggalBuat;
    }
}
