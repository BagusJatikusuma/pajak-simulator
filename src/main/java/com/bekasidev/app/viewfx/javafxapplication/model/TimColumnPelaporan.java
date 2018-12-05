/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

/**
 *
 * @author sudoroot
 */
public class TimColumnPelaporan {
    private String namaTim;
    private int columnHeight;

    public TimColumnPelaporan() {
    }

    public TimColumnPelaporan(String namaTim, int columnHeight) {
        this.namaTim = namaTim;
        this.columnHeight = columnHeight;
    }

    public String getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(String namaTim) {
        this.namaTim = namaTim;
    }

    public int getColumnHeight() {
        return columnHeight;
    }

    public void setColumnHeight(int columnHeight) {
        this.columnHeight = columnHeight;
    }
    
    
}
