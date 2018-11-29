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
public class ArsipPelaksanaanTableWrapper {
    private SimpleStringProperty no;
    private SimpleStringProperty bulan;
    private SimpleStringProperty omzethasilPemeriksaan;
    private SimpleStringProperty omzetDiLaporkan;
    private Button action;

    public ArsipPelaksanaanTableWrapper() {
    }

    public ArsipPelaksanaanTableWrapper(String no, String bulan, String omzethasilPemeriksaan, String omzetDiLaporkan, Button action) {
        this.no = new SimpleStringProperty(no);
        this.bulan = new SimpleStringProperty(bulan);
        this.omzethasilPemeriksaan = new SimpleStringProperty(omzethasilPemeriksaan);
        this.omzetDiLaporkan = new SimpleStringProperty(omzetDiLaporkan);
        this.action = action;
    }

    public String getNo() {
        return no.get();
    }

    public void setNo(String no) {
        this.no.set(no);
    }

    public String getBulan() {
        return bulan.get();
    }

    public void setBulan(String bulan) {
        this.bulan.set(bulan);
    }

    public String getOmzethasilPemeriksaan() {
        return omzethasilPemeriksaan.get();
    }

    public void setOmzethasilPemeriksaan(String omzethasilPemeriksaan) {
        this.omzethasilPemeriksaan.set(omzethasilPemeriksaan);
    }

    public String getOmzetDiLaporkan() {
        return omzetDiLaporkan.get();
    }

    public void setOmzetDiLaporkan(String omzetDiLaporkan) {
        this.omzetDiLaporkan.set(omzetDiLaporkan);
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }
    
    
    
    
}
