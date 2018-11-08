package com.bekasidev.app.model;

public class Pembukuan {
    private String idRestoran;
    private String idTransaksi;
    private String idBenchmark;
    private String deskripsi;
    private int jumlah;
    private String satuanJumlah;
    private float potensiPorsi;
    private String tanggalBuat;
    private short statusBahan;

    public Pembukuan() {
    }

    public Pembukuan(String idRestoran, String idTransaksi, String idBenchmark, String deskripsi,
                     int jumlah, String satuanJumlah, float potensiPorsi, String tanggalBuat, short statusBahan) {
        this.idRestoran = idRestoran;
        this.idTransaksi = idTransaksi;
        this.idBenchmark = idBenchmark;
        this.deskripsi = deskripsi;
        this.jumlah = jumlah;
        this.satuanJumlah = satuanJumlah;
        this.potensiPorsi = potensiPorsi;
        this.tanggalBuat = tanggalBuat;
        this.statusBahan = statusBahan;
    }

    public short getStatusBahan() {
        return statusBahan;
    }

    public void setStatusBahan(short statusBahan) {
        this.statusBahan = statusBahan;
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

    public String getIdBenchmark() {
        return idBenchmark;
    }

    public void setIdBenchmark(String idBenchmark) {
        this.idBenchmark = idBenchmark;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getSatuanJumlah() {
        return satuanJumlah;
    }

    public void setSatuanJumlah(String satuanJumlah) {
        this.satuanJumlah = satuanJumlah;
    }

    public float getPotensiPorsi() {
        return potensiPorsi;
    }

    public void setPotensiPorsi(float potensiPorsi) {
        this.potensiPorsi = potensiPorsi;
    }

    public String getTanggalBuat() {
        return tanggalBuat;
    }

    public void setTanggalBuat(String tanggalBuat) {
        this.tanggalBuat = tanggalBuat;
    }
}
