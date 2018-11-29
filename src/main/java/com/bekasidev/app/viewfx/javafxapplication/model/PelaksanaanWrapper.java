/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import com.bekasidev.app.model.Tim;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.wrapper.RekapitulasiWrapper;

/**
 *
 * @author sudoroot
 */
public class PelaksanaanWrapper {
    private String idPelaksanaan;
    private PersiapanWrapper persiapanWrapper;
    private Tim timSelected;
    private WajibPajak wpSelected;
    private RekapitulasiWrapper rekapitulasiWrapper;

    public PelaksanaanWrapper() {
    }

    public PelaksanaanWrapper(String idPelaksanaan, PersiapanWrapper persiapanWrapper, Tim timSelected, WajibPajak wpSelected) {
        this.idPelaksanaan = idPelaksanaan;
        this.persiapanWrapper = persiapanWrapper;
        this.timSelected = timSelected;
        this.wpSelected = wpSelected;
    }

    public String getIdPelaksanaan() {
        return idPelaksanaan;
    }

    public void setIdPelaksanaan(String idPelaksanaan) {
        this.idPelaksanaan = idPelaksanaan;
    }

    public PersiapanWrapper getPersiapanWrapper() {
        return persiapanWrapper;
    }

    public void setPersiapanWrapper(PersiapanWrapper persiapanWrapper) {
        this.persiapanWrapper = persiapanWrapper;
    }

    public Tim getTimSelected() {
        return timSelected;
    }

    public void setTimSelected(Tim timSelected) {
        this.timSelected = timSelected;
    }

    public WajibPajak getWpSelected() {
        return wpSelected;
    }

    public void setWpSelected(WajibPajak wpSelected) {
        this.wpSelected = wpSelected;
    }

    public RekapitulasiWrapper getRekapitulasiWrapper() {
        return rekapitulasiWrapper;
    }

    public void setRekapitulasiWrapper(RekapitulasiWrapper rekapitulasiWrapper) {
        this.rekapitulasiWrapper = rekapitulasiWrapper;
    }
        
    
}
