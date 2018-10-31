package com.bekasidev.app.model;

public class Benchmark {
    private String idRestoran;
    private String idTransaksi;
    private String idBenchmark;
    private String deskripsi;
    private int porsi;
    private float jumlah;
    private String satuanJumlah;
    private String tanggalBuat;
    private String label;

    public Benchmark(String idRestoran, String idTransaksi, String idBenchmark, String deskripsi, int porsi, float jumlah, String satuanJumlah, String tanggalBuat, String label) {
        this.idRestoran = idRestoran;
        this.idTransaksi = idTransaksi;
        this.idBenchmark = idBenchmark;
        this.deskripsi = deskripsi;
        this.porsi = porsi;
        this.jumlah = jumlah;
        this.satuanJumlah = satuanJumlah;
        this.tanggalBuat = tanggalBuat;
        this.label = label;
    }

    public Benchmark(){}

    public String getIdBenchmark() {
        return idBenchmark;
    }

    public void setIdBenchmark(String idBenchmark) {
        this.idBenchmark = idBenchmark;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIdRestoran() {
        return idRestoran;
    }

    public void setIdRestoran(String idRestoran) {
        this.idRestoran = idRestoran;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getPorsi() {
        return porsi;
    }

    public void setPorsi(int porsi) {
        this.porsi = porsi;
    }

    public float getJumlah() {
        return jumlah;
    }

    public void setJumlah(float jumlah) {
        this.jumlah = jumlah;
    }

    public String getSatuanJumlah() {
        return satuanJumlah;
    }

    public void setSatuanJumlah(String satuanJumlah) {
        this.satuanJumlah = satuanJumlah;
    }

    public String getTanggalBuat() {
        return tanggalBuat;
    }

    public void setTanggalBuat(String tanggalBuat) {
        this.tanggalBuat = tanggalBuat;
    }
}
