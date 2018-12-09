/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.TimSP;
import com.bekasidev.app.model.WajibPajak;

/**
 *
 * @author sudoroot
 */
public class PelaporanWrapper {
    private SuratPerintah suratPerintahSelected;
    private TimSP timSPSelected;
    private WajibPajak wpSelected;

    public PelaporanWrapper() {
    }

    public PelaporanWrapper(SuratPerintah suratPerintahSelected, TimSP timSPSelected, WajibPajak wpSelected) {
        this.suratPerintahSelected = suratPerintahSelected;
        this.timSPSelected = timSPSelected;
        this.wpSelected = wpSelected;
    }

    public SuratPerintah getSuratPerintahSelected() {
        return suratPerintahSelected;
    }

    public void setSuratPerintahSelected(SuratPerintah suratPerintahSelected) {
        this.suratPerintahSelected = suratPerintahSelected;
    }

    public TimSP getTimSPSelected() {
        return timSPSelected;
    }

    public void setTimSPSelected(TimSP timSPSelected) {
        this.timSPSelected = timSPSelected;
    }

    public WajibPajak getWpSelected() {
        return wpSelected;
    }

    public void setWpSelected(WajibPajak wpSelected) {
        this.wpSelected = wpSelected;
    }
    
    
    
}
