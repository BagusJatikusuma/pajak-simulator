/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.util.modelview;

/**
 *
 * @author Bayu Arafli
 */
public class WajibPajak {
    private String npwpd;
    private String namaWP;
    private String jenisWP;
    private String alamatWP;
    private String desaWP;
    private String kecamatanWP;

    public WajibPajak() {
    }

    public WajibPajak(String npwpd, String namaWP, String jenisWP, String alamatWP, String desaWP, String kecamatanWP) {
        this.npwpd = npwpd;
        this.namaWP = namaWP;
        this.jenisWP = jenisWP;
        this.alamatWP = alamatWP;
        this.desaWP = desaWP;
        this.kecamatanWP = kecamatanWP;
    }

    public String getNpwpd() {
        return npwpd;
    }

    public void setNpwpd(String npwpd) {
        this.npwpd = npwpd;
    }

    public String getNamaWP() {
        return namaWP;
    }

    public void setNamaWP(String namaWP) {
        this.namaWP = namaWP;
    }

    public String getJenisWP() {
        return jenisWP;
    }

    public void setJenisWP(String jenisWP) {
        this.jenisWP = jenisWP;
    }

    public String getAlamatWP() {
        return alamatWP;
    }

    public void setAlamatWP(String alamatWP) {
        this.alamatWP = alamatWP;
    }

    public String getDesaWP() {
        return desaWP;
    }

    public void setDesaWP(String desaWP) {
        this.desaWP = desaWP;
    }

    public String getKecamatanWP() {
        return kecamatanWP;
    }

    public void setKecamatanWP(String kecamatanWP) {
        this.kecamatanWP = kecamatanWP;
    }

    
    
}
