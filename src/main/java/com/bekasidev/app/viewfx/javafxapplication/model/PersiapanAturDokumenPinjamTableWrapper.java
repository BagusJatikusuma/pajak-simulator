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
public class PersiapanAturDokumenPinjamTableWrapper {
    private SimpleStringProperty idWP;
    private SimpleStringProperty namaWP;
    private Button action;

    public PersiapanAturDokumenPinjamTableWrapper() {
    }
    
    public PersiapanAturDokumenPinjamTableWrapper(
            String idWP, 
            String namaWP, 
            Button action) {
        this.idWP = new SimpleStringProperty(idWP);
        this.namaWP = new SimpleStringProperty(namaWP);
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

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }
    
}
