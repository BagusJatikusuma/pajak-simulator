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
    private SimpleStringProperty tanggalDiBuat;
    private SimpleStringProperty status;
    private Button action;

    public ArsipTablePelaksanaanWrapper() {
    }

    public ArsipTablePelaksanaanWrapper(String id, String tanggalDiBuat, String status, Button action) {
        this.id = new SimpleStringProperty(id);
        this.tanggalDiBuat = new SimpleStringProperty(tanggalDiBuat);
        this.status = new SimpleStringProperty(status);
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

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }    
    
    
    
}
