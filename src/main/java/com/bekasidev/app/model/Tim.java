package com.bekasidev.app.model;

public class Tim {

    private String idTim;
    private String namaTim;

    public Tim(String idTim, String namaTim) {
        this.idTim = idTim;
        this.namaTim = namaTim;
    }

    public String getIdTim() {
        return idTim;
    }

    public void setIdTim(String idTim) {
        this.idTim = idTim;
    }

    public String getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(String namaTim) {
        this.namaTim = namaTim;
    }
}
