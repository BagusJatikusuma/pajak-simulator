package com.bekasidev.app.wrapper;

import com.bekasidev.app.model.Potensi;

public class PotensiJoinWrapper extends Potensi {
    private String namaMenu;
    private short jenisMenu;
    private float frekuensiPotensi;
    private double jumlahPotensi;

    public float getFrekuensiPotensi() {
        return frekuensiPotensi;
    }

    public void setFrekuensiPotensi(float frekuensiPotensi) {
        this.frekuensiPotensi = frekuensiPotensi;
    }

    public double getJumlahPotensi() {
        return jumlahPotensi;
    }

    public void setJumlahPotensi(double jumlahPotensi) {
        this.jumlahPotensi = jumlahPotensi;
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
}
