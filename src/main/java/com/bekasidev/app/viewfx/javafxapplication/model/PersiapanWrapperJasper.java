/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import com.bekasidev.app.model.Pegawai;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Bayu Arafli
 */
public class PersiapanWrapperJasper {
    private String dasarNomor;
    private Date dasarTanggal;
    private String dasarTahunAnggaran;
    private String nama;
    private String jabatan;
    
    private Integer masaPajakAwalBulan;
    private Integer masaPajakAwalTahun;
    private Integer masaPajakAkhirbulan;
    private Integer masaPajakAkhirTahun;
    private Integer tahapKe;
    
    private Integer lamaPelaksanaan;
    private Integer biayaTahunAPBD;
    private String biayaNomorAPBD;
    private Date biayaTanggalAPBD;
    
    private String ditetapkanDi;
    private Date tanggalPengesahan;
    private String nomorSurat;
    
    private Pegawai penandatangan;
    
    private ArrayList<TimWPWrapperJasper> timWPWrapperJaspers;
   
    public PersiapanWrapperJasper() {
        timWPWrapperJaspers = new ArrayList<TimWPWrapperJasper>();
    }

    public PersiapanWrapperJasper(String dasarNomor, Date dasarTanggal, String dasarTahunAnggaran, String nama, String jabatan, Integer masaPajakAwalBulan, Integer masaPajakAwalTahun, Integer masaPajakAkhirbulan, Integer masaPajakAkhirTahun, Integer tahapKe, Integer lamaPelaksanaan, Integer biayaTahunAPBD, String biayaNomorAPBD, Date biayaTanggalAPBD, String ditetapkanDi, Date tanggalPengesahan, String nomorSurat, Pegawai penandatangan, ArrayList<TimWPWrapperJasper> timWPWrapperJaspers) {
        this.dasarNomor = dasarNomor;
        this.dasarTanggal = dasarTanggal;
        this.dasarTahunAnggaran = dasarTahunAnggaran;
        this.nama = nama;
        this.jabatan = jabatan;
        this.masaPajakAwalBulan = masaPajakAwalBulan;
        this.masaPajakAwalTahun = masaPajakAwalTahun;
        this.masaPajakAkhirbulan = masaPajakAkhirbulan;
        this.masaPajakAkhirTahun = masaPajakAkhirTahun;
        this.tahapKe = tahapKe;
        this.lamaPelaksanaan = lamaPelaksanaan;
        this.biayaTahunAPBD = biayaTahunAPBD;
        this.biayaNomorAPBD = biayaNomorAPBD;
        this.biayaTanggalAPBD = biayaTanggalAPBD;
        this.ditetapkanDi = ditetapkanDi;
        this.tanggalPengesahan = tanggalPengesahan;
        this.nomorSurat = nomorSurat;
        this.penandatangan = penandatangan;
        this.timWPWrapperJaspers = timWPWrapperJaspers;
    }

    public String getDasarNomor() {
        return dasarNomor;
    }

    public void setDasarNomor(String dasarNomor) {
        this.dasarNomor = dasarNomor;
    }

    public Date getDasarTanggal() {
        return dasarTanggal;
    }

    public void setDasarTanggal(Date dasarTanggal) {
        this.dasarTanggal = dasarTanggal;
    }

    public String getDasarTahunAnggaran() {
        return dasarTahunAnggaran;
    }

    public void setDasarTahunAnggaran(String dasarTahunAnggaran) {
        this.dasarTahunAnggaran = dasarTahunAnggaran;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public Integer getMasaPajakAwalBulan() {
        return masaPajakAwalBulan;
    }

    public void setMasaPajakAwalBulan(Integer masaPajakAwalBulan) {
        this.masaPajakAwalBulan = masaPajakAwalBulan;
    }

    public Integer getMasaPajakAwalTahun() {
        return masaPajakAwalTahun;
    }

    public void setMasaPajakAwalTahun(Integer masaPajakAwalTahun) {
        this.masaPajakAwalTahun = masaPajakAwalTahun;
    }

    public Integer getMasaPajakAkhirbulan() {
        return masaPajakAkhirbulan;
    }

    public void setMasaPajakAkhirbulan(Integer masaPajakAkhirbulan) {
        this.masaPajakAkhirbulan = masaPajakAkhirbulan;
    }

    public Integer getMasaPajakAkhirTahun() {
        return masaPajakAkhirTahun;
    }

    public void setMasaPajakAkhirTahun(Integer masaPajakAkhirTahun) {
        this.masaPajakAkhirTahun = masaPajakAkhirTahun;
    }

    public Integer getTahapKe() {
        return tahapKe;
    }

    public void setTahapKe(Integer tahapKe) {
        this.tahapKe = tahapKe;
    }

    public Integer getLamaPelaksanaan() {
        return lamaPelaksanaan;
    }

    public void setLamaPelaksanaan(Integer lamaPelaksanaan) {
        this.lamaPelaksanaan = lamaPelaksanaan;
    }

    public Integer getBiayaTahunAPBD() {
        return biayaTahunAPBD;
    }

    public void setBiayaTahunAPBD(Integer biayaTahunAPBD) {
        this.biayaTahunAPBD = biayaTahunAPBD;
    }

    public String getBiayaNomorAPBD() {
        return biayaNomorAPBD;
    }

    public void setBiayaNomorAPBD(String biayaNomorAPBD) {
        this.biayaNomorAPBD = biayaNomorAPBD;
    }

    public Date getBiayaTanggalAPBD() {
        return biayaTanggalAPBD;
    }

    public void setBiayaTanggalAPBD(Date biayaTanggalAPBD) {
        this.biayaTanggalAPBD = biayaTanggalAPBD;
    }

    public String getDitetapkanDi() {
        return ditetapkanDi;
    }

    public void setDitetapkanDi(String ditetapkanDi) {
        this.ditetapkanDi = ditetapkanDi;
    }

    public Date getTanggalPengesahan() {
        return tanggalPengesahan;
    }

    public void setTanggalPengesahan(Date tanggalPengesahan) {
        this.tanggalPengesahan = tanggalPengesahan;
    }

    public String getNomorSurat() {
        return nomorSurat;
    }

    public void setNomorSurat(String nomorSurat) {
        this.nomorSurat = nomorSurat;
    }

    public Pegawai getPenandatangan() {
        return penandatangan;
    }

    public void setPenandatangan(Pegawai penandatangan) {
        this.penandatangan = penandatangan;
    }

    public ArrayList<TimWPWrapperJasper> getTimWPWrapperJaspers() {
        return timWPWrapperJaspers;
    }

    public void setTimWPWrapperJaspers(ArrayList<TimWPWrapperJasper> timWPWrapperJaspers) {
        this.timWPWrapperJaspers = timWPWrapperJaspers;
    }
    
    
}
