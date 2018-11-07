package com.bekasidev.app.model;

public class DokumenPinjaman {
    private String namaDokumen;
    private String keterangan;

    public DokumenPinjaman(String namaDokumen, String keterangan) {
        this.namaDokumen = namaDokumen;
        this.keterangan = keterangan;
    }

    public String getNamaDokumen() {
        return namaDokumen;
    }

    public void setNamaDokumen(String namaDokumen) {
        this.namaDokumen = namaDokumen;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
