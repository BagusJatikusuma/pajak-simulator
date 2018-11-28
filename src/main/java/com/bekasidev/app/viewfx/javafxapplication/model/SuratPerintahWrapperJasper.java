/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import com.bekasidev.app.model.NomorBerkas;
import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.TimSP;
import java.util.List;

/**
 *
 * @author Bayu Arafli
 */
public class SuratPerintahWrapperJasper {
    private String nomorUrut;
    
    private String nomorSK;
    private String tanggalSK;
    private int tahunAnggaranSK;
    private String pemberiSK;
    
    private Pegawai pemberiSP;
    
    private String masaPajakAwal;
    private String masaPajakAkhir;
    private short tahap;
    
    private short lamaPelaksanaan;
    
    private String nomorSuratBiaya;
    private String tanggalBiaya;
    private int tahunAnggaranBiaya;
    
    private String tempat;
    private String tanggalSurat;

    public SuratPerintahWrapperJasper() {
    }

    public SuratPerintahWrapperJasper(String nomorUrut, String nomorSK, String tanggalSK, int tahunAnggaranSK, String pemberiSK, Pegawai pemberiSP, String masaPajakAwal, String masaPajakAkhir, short tahap, short lamaPelaksanaan, String nomorSuratBiaya, String tanggalBiaya, int tahunAnggaranBiaya, String tempat, String tanggalSurat) {
        this.nomorUrut = nomorUrut;
        this.nomorSK = nomorSK;
        this.tanggalSK = tanggalSK;
        this.tahunAnggaranSK = tahunAnggaranSK;
        this.pemberiSK = pemberiSK;
        this.pemberiSP = pemberiSP;
        this.masaPajakAwal = masaPajakAwal;
        this.masaPajakAkhir = masaPajakAkhir;
        this.tahap = tahap;
        this.lamaPelaksanaan = lamaPelaksanaan;
        this.nomorSuratBiaya = nomorSuratBiaya;
        this.tanggalBiaya = tanggalBiaya;
        this.tahunAnggaranBiaya = tahunAnggaranBiaya;
        this.tempat = tempat;
        this.tanggalSurat = tanggalSurat;
    }

    public String getNomorUrut() {
        return nomorUrut;
    }

    public void setNomorUrut(String nomorUrut) {
        this.nomorUrut = nomorUrut;
    }

    public String getNomorSK() {
        return nomorSK;
    }

    public void setNomorSK(String nomorSK) {
        this.nomorSK = nomorSK;
    }

    public String getTanggalSK() {
        return tanggalSK;
    }

    public void setTanggalSK(String tanggalSK) {
        this.tanggalSK = tanggalSK;
    }

    public int getTahunAnggaranSK() {
        return tahunAnggaranSK;
    }

    public void setTahunAnggaranSK(int tahunAnggaranSK) {
        this.tahunAnggaranSK = tahunAnggaranSK;
    }

    public String getPemberiSK() {
        return pemberiSK;
    }

    public void setPemberiSK(String pemberiSK) {
        this.pemberiSK = pemberiSK;
    }

    public Pegawai getPemberiSP() {
        return pemberiSP;
    }

    public void setPemberiSP(Pegawai pemberiSP) {
        this.pemberiSP = pemberiSP;
    }

    public String getMasaPajakAwal() {
        return masaPajakAwal;
    }

    public void setMasaPajakAwal(String masaPajakAwal) {
        this.masaPajakAwal = masaPajakAwal;
    }

    public String getMasaPajakAkhir() {
        return masaPajakAkhir;
    }

    public void setMasaPajakAkhir(String masaPajakAkhir) {
        this.masaPajakAkhir = masaPajakAkhir;
    }

    public short getTahap() {
        return tahap;
    }

    public void setTahap(short tahap) {
        this.tahap = tahap;
    }

    public short getLamaPelaksanaan() {
        return lamaPelaksanaan;
    }

    public void setLamaPelaksanaan(short lamaPelaksanaan) {
        this.lamaPelaksanaan = lamaPelaksanaan;
    }

    public String getNomorSuratBiaya() {
        return nomorSuratBiaya;
    }

    public void setNomorSuratBiaya(String nomorSuratBiaya) {
        this.nomorSuratBiaya = nomorSuratBiaya;
    }

    public String getTanggalBiaya() {
        return tanggalBiaya;
    }

    public void setTanggalBiaya(String tanggalBiaya) {
        this.tanggalBiaya = tanggalBiaya;
    }

    public int getTahunAnggaranBiaya() {
        return tahunAnggaranBiaya;
    }

    public void setTahunAnggaranBiaya(int tahunAnggaranBiaya) {
        this.tahunAnggaranBiaya = tahunAnggaranBiaya;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTanggalSurat() {
        return tanggalSurat;
    }

    public void setTanggalSurat(String tanggalSurat) {
        this.tanggalSurat = tanggalSurat;
    }
    
    
}
