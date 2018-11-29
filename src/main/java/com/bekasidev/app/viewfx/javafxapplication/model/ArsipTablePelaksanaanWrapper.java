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
 * @author Bayu Arafli
 */
public class ArsipTablePelaksanaanWrapper {
    private SimpleStringProperty id;
    private SimpleStringProperty idSP;
    private SimpleStringProperty namaTim;
    private SimpleStringProperty namaWP;
    private SimpleStringProperty tanggalDiBuat;
    private Button action;

    public ArsipTablePelaksanaanWrapper() {
    }

    public ArsipTablePelaksanaanWrapper(String id, String idSP, String namaTim, String namaWP, String tanggalDiBuat, Button action) {
        this.id = new SimpleStringProperty(id);
        this.idSP = new SimpleStringProperty(idSP);
        this.namaTim = new SimpleStringProperty(namaTim);
        this.namaWP = new SimpleStringProperty(namaWP);
        this.tanggalDiBuat = new SimpleStringProperty(tanggalDiBuat);
        this.action = action;
    }
    
    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getTanggalDiBuat() {
        return tanggalDiBuat.get();
    }

    public void setTanggalDiBuat(String tanggalDiBuat) {
        this.tanggalDiBuat.set(tanggalDiBuat);
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }    

    public String getIdSP() {
        return idSP.get();
    }

    public void setIdSP(String idSP) {
        this.idSP.set(idSP);
    }

    public String getNamaTim() {
        return namaTim.get();
    }

    public void setNamaTim(String namaTim) {
        this.namaTim.set(namaTim);
    }

    public String getNamaWP() {
        return namaWP.get();
    }

    public void setNamaWP(String namaWP) {
        this.namaWP.set(namaWP);
    }
    
    
}
