/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author sudoroot
 */
public class PersiapanNomorTanggalSuratPemberitahuanTableWrapper {
    private SimpleStringProperty idWP;
    private SimpleStringProperty namaWP;
    private SimpleStringProperty nomorSurat;
    private SimpleStringProperty tanggalSurat;
    private Button action;

    public PersiapanNomorTanggalSuratPemberitahuanTableWrapper() {
    }

    public PersiapanNomorTanggalSuratPemberitahuanTableWrapper(
            String idWP, 
            String namaWP, 
            String nomorSurat, 
            String tanggalSurat, 
            Button action) {
        this.idWP = new SimpleStringProperty(idWP);
        this.namaWP = new SimpleStringProperty(namaWP);
        this.nomorSurat = new SimpleStringProperty(nomorSurat);
        this.tanggalSurat = new SimpleStringProperty(tanggalSurat);
        this.action = action;
    }

    public String getIdWP() {
        return idWP.get();
    }

    public void setIdWP(String idWP) {
        this.idWP.set(idWP);
    }

    public String getNamaWP() {
        return namaWP.get();
    }

    public void setNamaWP(String namaWP) {
        this.namaWP.set(namaWP);
    }

    public String getNomorSurat() {
        return nomorSurat.get();
    }

    public void setNomorSurat(String nomorSurat) {
        this.nomorSurat.set(nomorSurat);
    }

    public String getTanggalSurat() {
        return tanggalSurat.get();
    }

    public void setTanggalSurat(String tanggalSurat) {
        this.tanggalSurat.set(tanggalSurat);
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }
    
    
    
    
}
