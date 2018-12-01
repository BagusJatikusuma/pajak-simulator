/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author sudoroot
 */
public class PilihSPTableWrapper {
    private SimpleStringProperty idSP;
    private SimpleStringProperty nomorSP;
    private SimpleStringProperty tanggalSP;

    public PilihSPTableWrapper() {
    }

    public PilihSPTableWrapper(String idSP, String nomorSP, String tanggalSP) {
        this.idSP = new SimpleStringProperty(idSP);
        this.nomorSP = new SimpleStringProperty(nomorSP);
        this.tanggalSP = new SimpleStringProperty(tanggalSP);
    }

    public String getIdSP() {
        return idSP.get();
    }

    public void setIdSP(String idSP) {
        this.idSP.set(idSP);
    }

    public String getNomorSP() {
        return nomorSP.get();
    }

    public void setNomorSP(String nomorSP) {
        this.nomorSP.set(nomorSP);
    }

    public String getTanggalSP() {
        return tanggalSP.get();
    }

    public void setTanggalSP(String tanggalSP) {
        this.tanggalSP.set(tanggalSP);
    }
    
    
    
    
}
