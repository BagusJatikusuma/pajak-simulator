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
public class MasterAnggotaTimTableWrapper {
    private SimpleStringProperty nip;
    private SimpleStringProperty nama;
    private SimpleStringProperty jabatan;
    private Button action;

    public MasterAnggotaTimTableWrapper() {
    }

    public MasterAnggotaTimTableWrapper(
            String nip, 
            String nama, 
            String jabatan, 
            Button action) {
        this.nip = new SimpleStringProperty(nip);
        this.nama = new SimpleStringProperty(nama);
        this.jabatan = new SimpleStringProperty(jabatan);
        this.action = action;
    }

    public String getNip() {
        return nip.get();
    }

    public void setNip(String nip) {
        this.nip.set(nip);
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public String getJabatan() {
        return jabatan.get();
    }

    public void setJabatan(String jabatan) {
        this.jabatan.set(jabatan);
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }
}
