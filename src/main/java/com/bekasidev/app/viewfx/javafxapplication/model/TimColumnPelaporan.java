/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sudoroot
 */
public class TimColumnPelaporan {
    private String namaTim;
    private List<ColumnsPelaporan> columnsPelaporanList;

    public TimColumnPelaporan() {
        columnsPelaporanList = new ArrayList<>();
    }

    public TimColumnPelaporan(String namaTim, List<ColumnsPelaporan> columnsPelaporanList) {
        this.namaTim = namaTim;
        this.columnsPelaporanList = columnsPelaporanList;
    }
    
    public String getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(String namaTim) {
        this.namaTim = namaTim;
    }

    public List<ColumnsPelaporan> getColumnsPelaporanList() {
        return columnsPelaporanList;
    }

    public void setColumnsPelaporanList(List<ColumnsPelaporan> columnsPelaporanList) {
        this.columnsPelaporanList = columnsPelaporanList;
    }
    
    
}
