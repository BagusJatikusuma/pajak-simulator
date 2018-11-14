package com.bekasidev.app.model;

/**
 * Created by waddi on 31/10/18.
 */
public class DataPembukuan {

    private String idHotel;
    private String idDataPembukuan;
    private String label;
    private String uraianDataPembukuan;
    private Integer jumlahDataPembukuan;
    private String satuanBarang;
    private String tanggalBuat;

    public DataPembukuan(){}

    public DataPembukuan(String idHotel, String idDataPembukuan, String label,String uraianDataPembukuan, Integer jumlahDataPembukuan, String satuanBarang, String tanggalBuat) {
        this.idHotel = idHotel;
        this.idDataPembukuan = idDataPembukuan;
        this.label = label;
        this.uraianDataPembukuan = uraianDataPembukuan;
        this.jumlahDataPembukuan = jumlahDataPembukuan;
        this.satuanBarang = satuanBarang;
        this.tanggalBuat =tanggalBuat;
    }

    public String getIdHotel() { return idHotel;}

    public void setIdHotel(String idHotel) { this.idHotel = idHotel; }

    public String getIdDataPembukuan() { return idDataPembukuan; }

    public void setIdDataPembukuan(String idDataPembukuan) {this.idDataPembukuan = idDataPembukuan; }

    public String getLabel() {return label; }

    public void setLabel(String label) {this.label = label; }

    public String getUraianDataPembukuan() { return uraianDataPembukuan; }

    public void setUraianDataPembukuan(String uraianDataPembukuan) {this.uraianDataPembukuan = uraianDataPembukuan; }

    public Integer getJumlahDataPembukuan() { return jumlahDataPembukuan; }

    public void setJumlahDataPembukuan(Integer jumlahDataPembukuan) {this.jumlahDataPembukuan = jumlahDataPembukuan; }

    public String getSatuanBarang() { return satuanBarang; }

    public void setSatuanBarang(String satuanBarang) { this.satuanBarang = satuanBarang; }

    public String getTanggalBuat() { return tanggalBuat; }

    public void setTanggalBuat(String tanggalBuat) {this.tanggalBuat = tanggalBuat; }
}
