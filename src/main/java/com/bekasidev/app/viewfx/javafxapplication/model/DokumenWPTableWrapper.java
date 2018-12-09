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
public class DokumenWPTableWrapper {
    private SimpleStringProperty no;
    private SimpleStringProperty dokumen;
    private SimpleStringProperty keterangan;
    private Button action;

    public DokumenWPTableWrapper() {
    }

    public DokumenWPTableWrapper(
            String no, String dokumen, String keterangan, Button action) {
        this.dokumen = new SimpleStringProperty(dokumen);
        this.no = new SimpleStringProperty(no);
        this.keterangan = new SimpleStringProperty(keterangan);
        this.action = action;
    }

    public String getDokumen() {
        return dokumen.get();
    }

    public void setDokumen(String dokumen) {
        this.dokumen.set(dokumen);
    }

    public String getKeterangan() {
        return keterangan.get();
    }

    public void setKeterangan(String keterangan) {
        this.keterangan.set(keterangan);
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }

    public String getNo() {
        return no.get();
    }

    public void setNo(String no) {
        this.no.set(no);
    }
    
    
    
    
}
