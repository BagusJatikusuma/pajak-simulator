package com.bekasidev.app.model;

public class Menu {
    private String idMenu;
    private String idRestoran;
    private String namaMenu;
    private short jenisMenu;
    private double hargaMenu;

    public Menu() {
    }

    public Menu(String idMenu, String idRestoran, String namaMenu, short jenisMenu, double hargaMenu) {
        this.idMenu = idMenu;
        this.idRestoran = idRestoran;
        this.namaMenu = namaMenu;
        this.jenisMenu = jenisMenu;
        this.hargaMenu = hargaMenu;
    }

    public String getIdRestoran() {
        return idRestoran;
    }

    public void setIdRestoran(String idRestoran) {
        this.idRestoran = idRestoran;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public short getJenisMenu() {
        return jenisMenu;
    }

    public void setJenisMenu(short jenisMenu) {
        this.jenisMenu = jenisMenu;
    }

    public double getHargaMenu() {
        return hargaMenu;
    }

    public void setHargaMenu(double hargaMenu) {
        this.hargaMenu = hargaMenu;
    }
}
