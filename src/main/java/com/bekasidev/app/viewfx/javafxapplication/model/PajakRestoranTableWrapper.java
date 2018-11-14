package com.bekasidev.app.viewfx.javafxapplication.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class PajakRestoranTableWrapper {
    private SimpleStringProperty no;
    private SimpleStringProperty idWajibPajak;
    private SimpleStringProperty namaWajibPajak;
    private SimpleStringProperty jenisWp;
    private SimpleStringProperty jalan;
    private SimpleStringProperty kecamatan;
    private SimpleStringProperty desa;
    private Button button;

    public PajakRestoranTableWrapper() {
    }

    public PajakRestoranTableWrapper(
            String no,
            String idWajibPajak, 
            String namaWajibPajak, 
            String jenisWp, 
            String jalan, 
            String kecamatan, 
            String desa,
            Button button) {
        this.no = new SimpleStringProperty(no);
        this.idWajibPajak = new SimpleStringProperty(idWajibPajak);
        this.namaWajibPajak = new SimpleStringProperty(namaWajibPajak);
        this.jenisWp = new SimpleStringProperty(jenisWp);
        this.jalan = new SimpleStringProperty(jalan);
        this.kecamatan = new SimpleStringProperty(kecamatan);
        this.desa = new SimpleStringProperty(desa);
        this.button = button;
    }

    public String getJenisWp() {
        return jenisWp.get();
    }

    public void setJenisWp(String jenisWp) {
        this.jenisWp.set(jenisWp);
    }

    public String getJalan() {
        return jalan.get();
    }

    public void setJalan(String jalan) {
        this.jalan.set(jalan);
    }

    public String getKecamatan() {
        return kecamatan.get();
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan.set(kecamatan);
    }

    public String getDesa() {
        return desa.get();
    }

    public void setDesa(String desa) {
        this.desa.set(desa);
    }

    public String getIdWajibPajak() {
        return idWajibPajak.get();
    }

    public void setIdWajibPajak(String idWajibPajak) {
        this.idWajibPajak.set(idWajibPajak);
    }

    public String getNamaWajibPajak() {
        return namaWajibPajak.get();
    }

    public void setNamaWajibPajak(String namaWajibPajak) {
        this.namaWajibPajak.set(namaWajibPajak);
    }

    public String getNo() {
        return no.get();
    }

    public void setNo(String no) {
        this.no.set(no);
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
      
   
}
