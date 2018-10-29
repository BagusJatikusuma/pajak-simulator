package com.bekasidev.app.model;

public class TarifMenu {

    private String idRestoran;
    private String idTransaksi;
    private String namaMenu;
    private short tipeMenu;
    private double harga;

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

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
