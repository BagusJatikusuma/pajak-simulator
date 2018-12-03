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
public class DaftarSuratPelaksanaanTableWrapper {
    private SimpleStringProperty no;
    private SimpleStringProperty namaSurat;
    private SimpleStringProperty keterangan;

    public DaftarSuratPelaksanaanTableWrapper() {
    }

    public DaftarSuratPelaksanaanTableWrapper(String no, String namaSurat, String keterangan) {
        this.no = new SimpleStringProperty(no);
        this.namaSurat = new SimpleStringProperty(namaSurat);
        this.keterangan = new SimpleStringProperty(keterangan);
    }

    public String getNo() {
        return no.get();
    }

    public void setNo(String no) {
        this.no.set(no);
    }

    public String getNamaSurat() {
        return namaSurat.get();
    }

    public void setNamaSurat(String namaSurat) {
        this.namaSurat.set(namaSurat);
    }

    public String getKeterangan() {
        return keterangan.get();
    }

    public void setKeterangan(String keterangan) {
        this.keterangan.set(keterangan);
    }
    
}
