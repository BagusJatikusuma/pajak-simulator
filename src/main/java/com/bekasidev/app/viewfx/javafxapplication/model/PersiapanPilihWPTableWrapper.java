/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Bayu Arafli
 */
public class PersiapanPilihWPTableWrapper {
    private CheckBox pilih;
    private SimpleStringProperty idWP;
    private SimpleStringProperty namaWP;
    private SimpleStringProperty jenisWP;

    public PersiapanPilihWPTableWrapper() {
    }

    public PersiapanPilihWPTableWrapper(CheckBox pilih, String idWP, String namaWP, String jenisWP) {
        this.pilih = pilih;
        this.idWP = new SimpleStringProperty(idWP);
        this.namaWP = new SimpleStringProperty(namaWP);
        this.jenisWP = new SimpleStringProperty(jenisWP);
    }

    public CheckBox getPilih() {
        return pilih;
    }

    public void setPilih(CheckBox pilih) {
        this.pilih = pilih;
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
