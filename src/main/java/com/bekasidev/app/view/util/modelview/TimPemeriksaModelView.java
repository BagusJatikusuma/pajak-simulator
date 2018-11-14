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
public class TimPemeriksaModelView {
    private String namaTim;
    private List<AnggotaTimModelView> anggotaTims;

    public TimPemeriksaModelView() {
    }

    public TimPemeriksaModelView(String namaTim, List<AnggotaTimModelView> anggotaTims) {
        this.namaTim = namaTim;
        this.anggotaTims = anggotaTims;
    }

    public String getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(String namaTim) {
        this.namaTim = namaTim;
    }

    public List<AnggotaTimModelView> getAnggotaTims() {
        return anggotaTims;
    }

    public void setAnggotaTims(List<AnggotaTimModelView> anggotaTims) {
        this.anggotaTims = anggotaTims;
    }
    
    
}
