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
public class PersiapanTimWPTableWrapper {
    private SimpleStringProperty idTim;
    private SimpleStringProperty namaTim;
    private Button hapusButton;
    private Button aturButton;

    public PersiapanTimWPTableWrapper() {
    }

    public PersiapanTimWPTableWrapper(String idTim, String namaTim, Button hapusButton, Button aturButton) {
        this.idTim = new SimpleStringProperty(idTim);
        this.namaTim = new SimpleStringProperty(namaTim);
        this.hapusButton = hapusButton;
        this.aturButton = aturButton;
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

    public void setTim(String namaTim) {
        this.namaTim.set(namaTim);
    }

    public Button getHapusButton() {
        return hapusButton;
    }

    public void setHapusButton(Button hapusButton) {
        this.hapusButton = hapusButton;
    }

    public Button getAturButton() {
        return aturButton;
    }

    public void setAturButton(Button aturButton) {
        this.aturButton = aturButton;
    }
    
}
