package com.bekasidev.app.wrapper;

import com.bekasidev.app.model.Rekapitulasi;

public class RekapitulasiExport extends Rekapitulasi {
    private String idWP;
    private String idSP;

    public String getIdWP() {
        return idWP;
    }

    public void setIdWP(String idWP) {
        this.idWP = idWP;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }
}
