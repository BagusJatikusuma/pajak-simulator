/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 *
 * @author sudoroot
 */
public class PilihWajibPajakTableWrapper {
    private SimpleStringProperty idWP;
    private SimpleStringProperty namaWP;
    private SimpleStringProperty jenisWP;

    public PilihWajibPajakTableWrapper() {
    }

    public PilihWajibPajakTableWrapper(String idWP, String namaWP, String jenisWP) {
        this.idWP = new SimpleStringProperty(idWP);
        this.namaWP = new SimpleStringProperty(namaWP);
        this.jenisWP = new SimpleStringProperty(jenisWP);
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

    public String getJenisWP() {
        return jenisWP.get();
    }

    public void setJenisWP(String jenisWP) {
        this.jenisWP.set(jenisWP);
    }
    
}
