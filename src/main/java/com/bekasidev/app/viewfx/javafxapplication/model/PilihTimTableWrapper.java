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
public class PilihTimTableWrapper {
    private SimpleStringProperty idTim;
    private SimpleStringProperty namaTim;

    public PilihTimTableWrapper() {
    }

    public PilihTimTableWrapper(String idTim, String namaTim) {
        this.idTim = new SimpleStringProperty(idTim);
        this.namaTim = new SimpleStringProperty(namaTim);
    }

    public String getIdTim() {
        return idTim.get();
    }

    public void setIdTim(String idTim) {
        this.idTim.set(idTim);
    }

    public String getNamaTim() {
        return namaTim.get();
    }

    public void setNamaTim(String namaTim) {
        this.namaTim.set(namaTim);
    }
    
    
    
}
