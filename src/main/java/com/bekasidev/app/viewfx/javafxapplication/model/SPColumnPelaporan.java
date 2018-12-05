/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import com.bekasidev.app.model.SuratPerintah;

/**
 *
 * @author sudoroot
 */
public class SPColumnPelaporan {
    private String tahap;
    private int columnHeight;

    public SPColumnPelaporan() {
    }

    public SPColumnPelaporan(String tahap, int columnHeight) {
        this.tahap = tahap;
        this.columnHeight = columnHeight;
    }

    public String getTahap() {
        return tahap;
    }

    public void setTahap(String tahap) {
        this.tahap = tahap;
    }

    public int getColumnHeight() {
        return columnHeight;
    }

    public void setColumnHeight(int columnHeight) {
        this.columnHeight = columnHeight;
    }
    
}
