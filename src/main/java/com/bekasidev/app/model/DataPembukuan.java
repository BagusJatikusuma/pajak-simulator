package com.bekasidev.app.model;

/**
 * Created by waddi on 31/10/18.
 */
public class DataPembukuan {

    private String idHotel;
    private String idDataPembukuan;
    private String uraianDataPembukuan;
    private Integer jumlahDataPembukuan;
    private Integer jumlahTotalDataPembukuan;
    private String satuanBarang;

    public String getIdHotel() { return idHotel;}

    public void setIdHotel(String idHotel) { this.idHotel = idHotel; }

    public String getIdDataPembukuan() { return idDataPembukuan; }

    public void setIdDataPembukuan(String idDataPembukuan) {this.idDataPembukuan = idDataPembukuan; }

    public String getUraianDataPembukuan() { return uraianDataPembukuan; }

    public void setUraianDataPembukuan(String uraianDataPembukuan) {this.uraianDataPembukuan = uraianDataPembukuan; }

    public Integer getJumlahDataPembukuan() { return jumlahDataPembukuan; }

    public void setJumlahDataPembukuan(Integer jumlahDataPembukuan) {this.jumlahDataPembukuan = jumlahDataPembukuan; }

    public Integer getJumlahTotalDataPembukuan() {return jumlahTotalDataPembukuan;}

    public void setJumlahTotalDataPembukuan(Integer jumlahTotalDataPembukuan) {this.jumlahTotalDataPembukuan = jumlahTotalDataPembukuan; }

    public String getSatuanBarang() { return satuanBarang; }

    public void setSatuanBarang(String satuanBarang) { this.satuanBarang = satuanBarang; }
}
