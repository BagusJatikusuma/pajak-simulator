/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import com.bekasidev.app.model.SuratPerintah;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sudoroot
 */
public class SPColumnPelaporan {
    private String tahap;
    private List<TimColumnPelaporan> timColumnPelaporans;

    public SPColumnPelaporan() {
        timColumnPelaporans = new ArrayList<>();
    }
    
    public SPColumnPelaporan(String tahap, List<TimColumnPelaporan> timColumnPelaporans) {
        this.tahap = tahap;
        this.timColumnPelaporans = timColumnPelaporans;
    }
    
    public String getTahap() {
        return tahap;
    }

    public void setTahap(String tahap) {
        this.tahap = tahap;
    }

    public List<TimColumnPelaporan> getTimColumnPelaporans() {
        return timColumnPelaporans;
    }

    public void setTimColumnPelaporans(List<TimColumnPelaporan> timColumnPelaporans) {
        this.timColumnPelaporans = timColumnPelaporans;
    }
    
}
