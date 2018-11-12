/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.util.modelview;

import java.util.List;

/**
 *
 * @author Bayu Arafli
 */
public class TimPemeriksa {
    private String namaTim;
    private List<AnggotaTim> anggotaTims;

    public TimPemeriksa() {
    }

    public TimPemeriksa(String namaTim, List<AnggotaTim> anggotaTims) {
        this.namaTim = namaTim;
        this.anggotaTims = anggotaTims;
    }

    public String getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(String namaTim) {
        this.namaTim = namaTim;
    }

    public List<AnggotaTim> getAnggotaTims() {
        return anggotaTims;
    }

    public void setAnggotaTims(List<AnggotaTim> anggotaTims) {
        this.anggotaTims = anggotaTims;
    }
    
    
}
