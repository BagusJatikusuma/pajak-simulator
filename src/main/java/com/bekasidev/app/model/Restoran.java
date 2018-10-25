package com.bekasidev.app.model;

public class Restoran {

    private String idRestoran;
    private String namaRestoran;

    public String getIdRestoran() {
        return idRestoran;
    }

    public void setIdRestoran(String idRestoran) {
        this.idRestoran = idRestoran;
    }

    public String getNamaRestoran() {
        return namaRestoran;
    }

    public void setNamaRestoran(String namaRestoran) {
        this.namaRestoran = namaRestoran;
    }
    
    @Override
    public String toString() {
        return namaRestoran;
    }
}
