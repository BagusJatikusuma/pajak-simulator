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
public class AnggotaTimModelView {
    private String nipAnggota;
    private String namaAnggota;
    private String pangkatGol;
    private String jabatanDiTim;

    public AnggotaTimModelView() {
    }

    public AnggotaTimModelView(String nipAnggota, String namaAnggota, String pangkatGol, String jabatanDiTim) {
        this.nipAnggota = nipAnggota;
        this.namaAnggota = namaAnggota;
        this.pangkatGol = pangkatGol;
        this.jabatanDiTim = jabatanDiTim;
    }

    public String getNipAnggota() {
        return nipAnggota;
    }

    public void setNipAnggota(String nipAnggota) {
        this.nipAnggota = nipAnggota;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    public String getPangkatGol() {
        return pangkatGol;
    }

    public void setPangkatGol(String pangkatGol) {
        this.pangkatGol = pangkatGol;
    }

    public String getJabatanDiTim() {
        return jabatanDiTim;
    }

    public void setJabatanDiTim(String jabatanDiTim) {
        this.jabatanDiTim = jabatanDiTim;
    }
    
    
}
