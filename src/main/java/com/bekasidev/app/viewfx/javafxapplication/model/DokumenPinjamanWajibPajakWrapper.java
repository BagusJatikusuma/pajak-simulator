/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import com.bekasidev.app.model.WajibPajak;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sudoroot
 */
public class DokumenPinjamanWajibPajakWrapper {
    private WajibPajak wajibPajak;
    private List<String> dokumenDiPinjamList;

    public DokumenPinjamanWajibPajakWrapper() {
        dokumenDiPinjamList = new ArrayList<>();
    }

    public DokumenPinjamanWajibPajakWrapper(WajibPajak wajibPajak, List<String> dokumenDiPinjamList) {
        this.wajibPajak = wajibPajak;
        this.dokumenDiPinjamList = dokumenDiPinjamList;
    }

    public WajibPajak getWajibPajak() {
        return wajibPajak;
    }

    public void setWajibPajak(WajibPajak wajibPajak) {
        this.wajibPajak = wajibPajak;
    }

    public List<String> getDokumenDiPinjamList() {
        return dokumenDiPinjamList;
    }

    public void setDokumenDiPinjamList(List<String> dokumenDiPinjamList) {
        this.dokumenDiPinjamList = dokumenDiPinjamList;
    }
    
    
    
}
