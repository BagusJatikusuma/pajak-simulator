/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import com.bekasidev.app.model.WajibPajak;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Bayu Arafli
 */
public class TimWPWrapperJasper {
    
    private String nipPegawaiPenanggungJawab;
    private String namaPegawaiPenanggungJawab;
    private String pangkatPenanggungJawab;
    private String golonganPenanggungJawab;
    private String jabatanPenanggungJawab;

    private String nipPegawaiSupervisor;
    private String namaPegawaiSupervisor;
    private String pangkatSupervisor;
    private String golonganSupervisor;
    private String jabatanSupervisor;
    
    private String namaTim;

    private List<AnggotaDanWajibPajakWrapper> wajibPajaks;
    private JRBeanCollectionDataSource wajibPajakJasper;

    public TimWPWrapperJasper() {
    }

    public TimWPWrapperJasper(String nipPegawaiPenanggungJawab, String namaPegawaiPenanggungJawab, String pangkatPenanggungJawab, String golonganPenanggungJawab, String jabatanPenanggungJawab, String nipPegawaiSupervisor, String namaPegawaiSupervisor, String pangkatSupervisor, String golonganSupervisor, String jabatanSupervisor, String namaTim, List<AnggotaDanWajibPajakWrapper> wajibPajaks) {
        this.nipPegawaiPenanggungJawab = nipPegawaiPenanggungJawab;
        this.namaPegawaiPenanggungJawab = namaPegawaiPenanggungJawab;
        this.pangkatPenanggungJawab = pangkatPenanggungJawab;
        this.golonganPenanggungJawab = golonganPenanggungJawab;
        this.jabatanPenanggungJawab = jabatanPenanggungJawab;
        this.nipPegawaiSupervisor = nipPegawaiSupervisor;
        this.namaPegawaiSupervisor = namaPegawaiSupervisor;
        this.pangkatSupervisor = pangkatSupervisor;
        this.golonganSupervisor = golonganSupervisor;
        this.jabatanSupervisor = jabatanSupervisor;
        this.namaTim = namaTim;
        this.wajibPajaks = wajibPajaks;
    }

    public String getNipPegawaiPenanggungJawab() {
        return nipPegawaiPenanggungJawab;
    }

    public void setNipPegawaiPenanggungJawab(String nipPegawaiPenanggungJawab) {
        this.nipPegawaiPenanggungJawab = nipPegawaiPenanggungJawab;
    }

    public String getNamaPegawaiPenanggungJawab() {
        return namaPegawaiPenanggungJawab;
    }

    public void setNamaPegawaiPenanggungJawab(String namaPegawaiPenanggungJawab) {
        this.namaPegawaiPenanggungJawab = namaPegawaiPenanggungJawab;
    }

    public String getPangkatPenanggungJawab() {
        return pangkatPenanggungJawab;
    }

    public void setPangkatPenanggungJawab(String pangkatPenanggungJawab) {
        this.pangkatPenanggungJawab = pangkatPenanggungJawab;
    }

    public String getGolonganPenanggungJawab() {
        return golonganPenanggungJawab;
    }

    public void setGolonganPenanggungJawab(String golonganPenanggungJawab) {
        this.golonganPenanggungJawab = golonganPenanggungJawab;
    }

    public String getJabatanPenanggungJawab() {
        return jabatanPenanggungJawab;
    }

    public void setJabatanPenanggungJawab(String jabatanPenanggungJawab) {
        this.jabatanPenanggungJawab = jabatanPenanggungJawab;
    }

    public String getNipPegawaiSupervisor() {
        return nipPegawaiSupervisor;
    }

    public void setNipPegawaiSupervisor(String nipPegawaiSupervisor) {
        this.nipPegawaiSupervisor = nipPegawaiSupervisor;
    }

    public String getNamaPegawaiSupervisor() {
        return namaPegawaiSupervisor;
    }

    public void setNamaPegawaiSupervisor(String namaPegawaiSupervisor) {
        this.namaPegawaiSupervisor = namaPegawaiSupervisor;
    }

    public String getPangkatSupervisor() {
        return pangkatSupervisor;
    }

    public void setPangkatSupervisor(String pangkatSupervisor) {
        this.pangkatSupervisor = pangkatSupervisor;
    }

    public String getGolonganSupervisor() {
        return golonganSupervisor;
    }

    public void setGolonganSupervisor(String golonganSupervisor) {
        this.golonganSupervisor = golonganSupervisor;
    }

    public String getJabatanSupervisor() {
        return jabatanSupervisor;
    }

    public void setJabatanSupervisor(String jabatanSupervisor) {
        this.jabatanSupervisor = jabatanSupervisor;
    }

    public String getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(String namaTim) {
        this.namaTim = namaTim;
    }

    public List<AnggotaDanWajibPajakWrapper> getWajibPajaks() {
        return wajibPajaks;
    }

    public void setWajibPajaks(List<AnggotaDanWajibPajakWrapper> wajibPajaks) {
        this.wajibPajaks = wajibPajaks;
    }

    public JRBeanCollectionDataSource getWajibPajakJasper() {
        return wajibPajakJasper;
    }

    public void setWajibPajakJasper(JRBeanCollectionDataSource wajibPajakJasper) {
        this.wajibPajakJasper = wajibPajakJasper;
    }

    
    
    
}
