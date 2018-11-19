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
public class MasterTimTableWrapper {
    private SimpleStringProperty id;
    private SimpleStringProperty nama;
    private Button button;
    private Button hapusButton;

    public MasterTimTableWrapper() {
    }

    public MasterTimTableWrapper(String id, String nama, Button button, Button hapusButton) {
        this.id = new SimpleStringProperty(id);
        this.nama = new SimpleStringProperty(nama);
        this.button = button;
        this.hapusButton = hapusButton;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getHapusButton() {
        return hapusButton;
    }

    public void setHapusButton(Button hapusButton) {
        this.hapusButton = hapusButton;
    }
    
    
    
}
