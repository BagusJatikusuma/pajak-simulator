/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.util.modelview;

import com.bekasidev.app.model.Pegawai;
import java.util.List;

/**
 *
 * @author Bayu Arafli
 */
public class TimPemeriksaModelView {
    private String namaTim;
    private List<Pegawai> anggotaTims;

    public TimPemeriksaModelView() {
    }

    public TimPemeriksaModelView(String namaTim, List<Pegawai> anggotaTims) {
        this.namaTim = namaTim;
        this.anggotaTims = anggotaTims;
    }

    public String getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(String namaTim) {
        this.namaTim = namaTim;
    }

    public List<Pegawai> getAnggotaTims() {
        return anggotaTims;
    }

    public void setAnggotaTims(List<Pegawai> anggotaTims) {
        this.anggotaTims = anggotaTims;
    }
    
    
}
