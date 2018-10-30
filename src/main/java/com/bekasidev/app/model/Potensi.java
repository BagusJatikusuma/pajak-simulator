package com.bekasidev.app.model;

public class Potensi {

    private String idRestoran;
    private String idTransaksi;
    private String idMenu;
    private short tipeMenu;
    private double hargaSatuan;
    private int frekuensiPenjualan;
    private double jumlahPenjualan;
    private String tanggalBuat;

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

    public short getTipeMenu() {
        return tipeMenu;
    }

    public void setTipeMenu(short tipeMenu) {
        this.tipeMenu = tipeMenu;
    }

    public double getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(double hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }
}
