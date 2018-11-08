package com.bekasidev.app.model;

/**
 * Created by waddi on 01/11/18.
 */
public class BenchmarkHotel {

    private String idHotel;
    private String idBenchmarkHotel;
    private String idDataPembukuan;
    private String labelFasilitas;
    private String labelBarang;
    private Integer jumlahDataPembukuan;
    private String uraianDataPembukuan;
    private Integer jumlahTerpakai;
    private String satuan;
    private String tanggalBuat;

    public BenchmarkHotel(){}

    public BenchmarkHotel(String idHotel, String idDataPembukuan, String idBenchmarkHotel, Integer jumlahTerpakai, String uraianDataPembukuan, String labelFasilitas, String labelBarang, Integer jumlahDataPembukuan, String satuan, String tanggalBuat) {
        this.idHotel = idHotel;
        this.idDataPembukuan = idDataPembukuan;
        this.uraianDataPembukuan = uraianDataPembukuan;
        this.idBenchmarkHotel = idBenchmarkHotel;
        this.labelFasilitas = labelFasilitas;
        this.labelBarang = labelBarang;
        this.jumlahTerpakai = jumlahTerpakai;
        this.jumlahDataPembukuan = jumlahDataPembukuan;
        this.satuan = satuan;
        this.tanggalBuat = tanggalBuat;
    }

    public String getIdHotel() {return idHotel;}

    public void setIdHotel(String idHotel) {this.idHotel = idHotel;}

    public String getIdDataPembukuan() {return idDataPembukuan;}

    public void setIdDataPembukuan(String idDataPembukuan) { this.idDataPembukuan = idDataPembukuan;}

    public String getIdBenchmarkHotel() {return  idBenchmarkHotel;}

    public void setIdBenchmarkHotel(String idBenchmarkHotel) {this.idBenchmarkHotel = idBenchmarkHotel;}

    public String getLabelFasilitas() {return labelFasilitas;}

    public void setLabelFasilitas(String labelFasilitas) {this.labelFasilitas = labelFasilitas;}

    public String getLabelBarang() {return labelBarang; }

    public void setLabelBarang(String labelBarang) {this.labelBarang = labelBarang; }

    public String getUraianDataPembukuan() {return uraianDataPembukuan; }

    public void setUraianDataPembukuan(String uraianDataPembukuan) {this.uraianDataPembukuan = uraianDataPembukuan;}

    public Integer getJumlahDataPembukuan() {return jumlahDataPembukuan;}

    public void setJumlahDataPembukuan(Integer jumlahDataPembukuan) {this.jumlahDataPembukuan = jumlahDataPembukuan;}

    public Integer getJumlahTerpakai() {return jumlahTerpakai;}

    public void setJumlahTerpakai(Integer jumlahTerpakai) {this.jumlahTerpakai = jumlahTerpakai;}

    public String getSatuan() {return satuan;}

    public void setSatuan(String satuan) {this.satuan = satuan;}

    public String getTanggalBuat() {return tanggalBuat;}

    public void setTanggalBuat(String tanggalBuat) {this.tanggalBuat = tanggalBuat;}
}
