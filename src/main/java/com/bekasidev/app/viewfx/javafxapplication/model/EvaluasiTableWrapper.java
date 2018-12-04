/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author sudoroot
 */
public class EvaluasiTableWrapper {
    private SimpleStringProperty no;
    private SimpleStringProperty tahapPemeriksaan;
    private SimpleStringProperty noSP;
    private SimpleStringProperty tim;
    private SimpleStringProperty wp;
    private Button action;

    public EvaluasiTableWrapper() {
    }

    public EvaluasiTableWrapper(
            String no, 
            String tahapPemeriksaan, 
            String noSP, 
            String tim, 
            String wp, 
            Button action) {
        this.no = new SimpleStringProperty(no);
        this.tahapPemeriksaan = new SimpleStringProperty(tahapPemeriksaan);
        this.noSP = new SimpleStringProperty(noSP);
        this.tim = new SimpleStringProperty(tim);
        this.wp = new SimpleStringProperty(wp);
        this.action = action;
    }

    public String getNo() {
        return no.get();
    }

    public void setNo(String no) {
        this.no.set(no);
    }

    public String getTahapPemeriksaan() {
        return tahapPemeriksaan.get();
    }

    public void setTahapPemeriksaan(String tahapPemeriksaan) {
        this.tahapPemeriksaan.set(tahapPemeriksaan);
    }

    public String getNoSP() {
        return noSP.get();
    }

    public void setNoSP(String noSP) {
        this.noSP.set(noSP);
    }

    public String getTim() {
        return tim.get();
    }

    public void setTim(String tim) {
        this.tim.set(tim);
    }

    public String getWp() {
        return wp.get();
    }

    public void setWp(String wp) {
        this.wp.set(wp);
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }
    
    
    
    
}
