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
    private SimpleStringProperty nomor;
    private SimpleStringProperty bulan;
    private SimpleStringProperty omzetHasilPemeriksaan;
    private SimpleStringProperty omzetYangDilaporkan;
    private Button action;

    public ArsipTablePelaksanaanWrapper() {
    }

    public ArsipTablePelaksanaanWrapper(String nomor, String bulan, String omzetHasilPemeriksaan, String omzetYangDilaporkan, Button action) {
        this.nomor = new SimpleStringProperty(nomor);
        this.bulan = new SimpleStringProperty(bulan);
        this.omzetHasilPemeriksaan = new SimpleStringProperty(omzetHasilPemeriksaan);
        this.omzetYangDilaporkan = new SimpleStringProperty(omzetYangDilaporkan);
        this.action = action;
    }

    public String getNomor() {
        return nomor.get();
    }

    public void setNomor(String nomor) {
        this.nomor.set(nomor);
    }

    public String getBulan() {
        return bulan.get();
    }

    public void setBulan(String bulan) {
        this.bulan.set(bulan);
    }

    public String getOmzetHasilPemeriksaan() {
        return omzetHasilPemeriksaan.get();
    }

    public void setOmzetHasilPemeriksaan(String omzetHasilPemeriksaan) {
        this.omzetHasilPemeriksaan.set(omzetHasilPemeriksaan);
    }

    public String getOmzetYangDilaporkan() {
        return omzetYangDilaporkan.get();
    }

    public void setOmzetYangDilaporkan(String omzetYangDilaporkan) {
        this.omzetYangDilaporkan.set(omzetYangDilaporkan);
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }
    
    
    
    
}
