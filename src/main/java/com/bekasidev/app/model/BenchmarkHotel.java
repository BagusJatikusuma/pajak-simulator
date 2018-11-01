package com.bekasidev.app.model;

/**
 * Created by waddi on 01/11/18.
 */
public class BenchmarkHotel {

    private String idHotel;
    private String idBenchmarkHotel;
    private String labelFasilitas;
    private String labelBarang;
    private Integer jumlahDataPembukuan;
    private Integer jumlahPengunjung;
    private String satuan;
    private String tanggalBuat;

    public BenchmarkHotel(){}

    public BenchmarkHotel(String idHotel, String idBenchmarkHotel, String labelFasilitas, String labelBarang, Integer jumlahDataPembukuan, Integer jumlahPengunjung, String satuan, String tanggalBuat) {
        this.idHotel = idHotel;
        this.idBenchmarkHotel = idBenchmarkHotel;
        this.labelFasilitas = labelFasilitas;
        this.labelBarang = labelBarang;
        this.jumlahDataPembukuan = jumlahDataPembukuan;
        this.jumlahPengunjung = jumlahPengunjung;
        this.satuan = satuan;
        this.tanggalBuat = tanggalBuat;
    }

    public String getIdHotel() {return idHotel;}

    public void setIdHotel(String idHotel) {this.idHotel = idHotel;}

    public String getIdBenchmarkHotel() {return  idBenchmarkHotel;}

    public void setIdBenchmarkHotel(String idBenchmarkHotel) {this.idBenchmarkHotel = idBenchmarkHotel;}

    public String getLabelFasilitas() {return labelFasilitas;}

    public void setLabelFasilitas(String labelFasilitas) {this.labelFasilitas = labelFasilitas;}

    public String getLabelBarang() {return labelBarang; }

    public void setLabelBarang(String labelBarang) {this.labelBarang = labelBarang; }

    public Integer getJumlahDataPembukuan() {return jumlahDataPembukuan;}

    public void setJumlahDataPembukuan(Integer jumlahDataPembukuan) {this.jumlahDataPembukuan = jumlahDataPembukuan;}

    public Integer getJumlahPengunjung() {return jumlahPengunjung;}

    public void setJumlahPengunjung(Integer jumlahPengunjung) {this.jumlahPengunjung = jumlahPengunjung;}

    public String getSatuan() {return satuan;}

    public void setSatuan(String satuan) {this.satuan = satuan;}

    public String getTanggalBuat() {return tanggalBuat;}

    public void setTanggalBuat(String tanggalBuat) {this.tanggalBuat = tanggalBuat;}
}
