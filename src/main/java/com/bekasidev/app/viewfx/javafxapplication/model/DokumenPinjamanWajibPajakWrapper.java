/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import com.bekasidev.app.model.DokumenPinjaman;
import com.bekasidev.app.model.WajibPajak;
import java.util.ArrayList;
import java.util.List;

/**
 * update 28 november 2018 :
 * variable listPinjaman tidak terpakai
 * data berkas dokumen pinjaman ada di object wajibPajak
 * 
 * @author sudoroot
 */
public class DokumenPinjamanWajibPajakWrapper {
    private WajibPajak wajibPajak;
    private List<DokumenPinjaman> listPinjaman;
    
    public DokumenPinjamanWajibPajakWrapper() {
        listPinjaman = new ArrayList<>();
    }

    public DokumenPinjamanWajibPajakWrapper(WajibPajak wajibPajak, List<DokumenPinjaman> listPinjaman) {
        this.wajibPajak = wajibPajak;
        this.listPinjaman = listPinjaman;
    }

    public WajibPajak getWajibPajak() {
        return wajibPajak;
    }

    public void setWajibPajak(WajibPajak wajibPajak) {
        this.wajibPajak = wajibPajak;
    }

    public List<DokumenPinjaman> getListPinjaman() {
        return listPinjaman;
    }

    public void setListPinjaman(List<DokumenPinjaman> listPinjaman) {
        this.listPinjaman = listPinjaman;
    }
    
}
