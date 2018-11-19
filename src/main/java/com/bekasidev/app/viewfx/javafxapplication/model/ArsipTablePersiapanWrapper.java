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
public class ArsipTablePersiapanWrapper {
    private SimpleStringProperty id;
    private SimpleStringProperty tanggalDiBuat;
    private SimpleStringProperty statusSelesai;
    private Button action;

    public ArsipTablePersiapanWrapper() {
    }

    public ArsipTablePersiapanWrapper(String id, String tanggalDiBuat, String statusSelesai, Button action) {
        this.id = new SimpleStringProperty(id);
        this.tanggalDiBuat = new SimpleStringProperty(tanggalDiBuat);
        this.statusSelesai = new SimpleStringProperty(statusSelesai);
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

    public String getStatusSelesai() {
        return statusSelesai.get();
    }

    public void setStatusSelesai(String statusSelesai) {
        this.statusSelesai.set(statusSelesai);
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }
    
}
