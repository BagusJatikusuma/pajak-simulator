/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.Tim;
import com.bekasidev.app.model.WajibPajak;
import java.util.List;

/**
 *
 * @author Bayu Arafli
 */
public class TimWPWrapper {
    private Pegawai penanggungJawab;
    private Pegawai supervisor;
    private Tim tim;
    private List<WajibPajak> wajibPajaks;

    public TimWPWrapper() {
    }

    public TimWPWrapper(Pegawai penanggungJawab, Pegawai supervisor, Tim tim, List<WajibPajak> wajibPajaks) {
        this.penanggungJawab = penanggungJawab;
        this.supervisor = supervisor;
        this.tim = tim;
        this.wajibPajaks = wajibPajaks;
    }

    public Pegawai getPenanggungJawab() {
        return penanggungJawab;
    }

    public void setPenanggungJawab(Pegawai penanggungJawab) {
        this.penanggungJawab = penanggungJawab;
    }

    public Pegawai getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Pegawai supervisor) {
        this.supervisor = supervisor;
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    public List<WajibPajak> getWajibPajaks() {
        return wajibPajaks;
    }

    public void setWajibPajaks(List<WajibPajak> wajibPajaks) {
        this.wajibPajaks = wajibPajaks;
    }
    
    
}
