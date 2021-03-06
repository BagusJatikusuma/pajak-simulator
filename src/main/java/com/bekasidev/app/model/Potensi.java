package com.bekasidev.app.model;

public class Potensi {

    private String idRestoran;
    private String idTransaksi;
    private String idMenu;
    private double hargaSatuan;
    private int frekuensiPenjualan;
    private double jumlahPenjualan;
    private String tanggalBuat;

    public Potensi() {
    }

    public Potensi(String idRestoran, String idTransaksi, String idMenu, double hargaSatuan, int frekuensiPenjualan, double jumlahPenjualan, String tanggalBuat) {
        this.idRestoran = idRestoran;
        this.idTransaksi = idTransaksi;
        this.idMenu = idMenu;
        this.hargaSatuan = hargaSatuan;
        this.frekuensiPenjualan = frekuensiPenjualan;
        this.jumlahPenjualan = jumlahPenjualan;
        this.tanggalBuat = tanggalBuat;
    }

    public String getTanggalBuat() {
        return tanggalBuat;
    }

    public void setTanggalBuat(String tanggalBuat) {
        this.tanggalBuat = tanggalBuat;
    }

    public int getFrekuensiPenjualan() {
        return frekuensiPenjualan;
    }

    public void setFrekuensiPenjualan(int frekuensiPenjualan) {
        this.frekuensiPenjualan = frekuensiPenjualan;
    }

    public double getJumlahPenjualan() {
        return jumlahPenjualan;
    }

    public void setJumlahPenjualan(double jumlahPenjualan) {
        this.jumlahPenjualan = jumlahPenjualan;
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

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String namaMenu) {
        this.idMenu = namaMenu;
    }

    public double getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(double hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }
}
