package com.bekasidev.app.model;

public class Potensi {

    private String idRestoran;
    private String idTransaksi;
    private String namaMenu;
    private short tipeMenu;
    private double hargaSatuan;
    private int frekuensiPenjualan;
    private double jumlahPenjualan;
    private double totalPenjualan;
    private int frekuensiPotensiJual;
    private double jumlahPotensiJual;
    private double totalPotensiJual;
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

    public double getTotalPenjualan() {
        return totalPenjualan;
    }

    public void setTotalPenjualan(double totalPenjualan) {
        this.totalPenjualan = totalPenjualan;
    }

    public int getFrekuensiPotensiJual() {
        return frekuensiPotensiJual;
    }

    public void setFrekuensiPotensiJual(int frekuensiPotensiJual) {
        this.frekuensiPotensiJual = frekuensiPotensiJual;
    }

    public double getJumlahPotensiJual() {
        return jumlahPotensiJual;
    }

    public void setJumlahPotensiJual(double jumlahPotensiJual) {
        this.jumlahPotensiJual = jumlahPotensiJual;
    }

    public double getTotalPotensiJual() {
        return totalPotensiJual;
    }

    public void setTotalPotensiJual(double totalPotensiJual) {
        this.totalPotensiJual = totalPotensiJual;
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

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
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
