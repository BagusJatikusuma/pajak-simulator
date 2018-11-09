package com.bekasidev.app.model;

import java.util.ArrayList;
import java.util.List;

public class BerkasPersiapan {

    private String idBerkas;

    private String namaWP;
    private String kotaTerbit;
    private String npwpd;
    private String alamatJalan;
    private String alamatKecamatan;
    private String alamatDi;
    private String masaPajakAwal;
    private String masaPajakAkhir;

    private String nomorSurat;
    private String sifat;
    private String lampiran;
    private String perihal;

    private String nomorSp;
    private String tanggalSp;

    private List<DokumenPinjaman> listPinjaman = new ArrayList<>();
    private String namaTim;
    private List<Pegawai> listPegawai = new ArrayList<>();

    private String jabatanPenandatangan;
    private String namaPenandatangan;
    private String tanggalDibuat;

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

    public String getNamaWP() { return namaWP; }

    public void setNamaWP(String namaWP) { this.namaWP = namaWP; }

    public String getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(String tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }

    public String getIdBerkas() {
        return idBerkas;
    }

    public void setIdBerkas(String idBerkas) {
        this.idBerkas = idBerkas;
    }

    public String getKotaTerbit() {
        return kotaTerbit;
    }

    public void setKotaTerbit(String kotaTerbit) {
        this.kotaTerbit = kotaTerbit;
    }

    public String getNpwpd() {
        return npwpd;
    }

    public void setNpwpd(String npwpd) {
        this.npwpd = npwpd;
    }

    public String getAlamatJalan() {
        return alamatJalan;
    }

    public void setAlamatJalan(String alamatJalan) {
        this.alamatJalan = alamatJalan;
    }

    public String getAlamatKecamatan() {
        return alamatKecamatan;
    }

    public void setAlamatKecamatan(String alamatKecamatan) {
        this.alamatKecamatan = alamatKecamatan;
    }

    public String getAlamatDi() {
        return alamatDi;
    }

    public void setAlamatDi(String alamatDi) {
        this.alamatDi = alamatDi;
    }

    public String getNomorSurat() {
        return nomorSurat;
    }

    public void setNomorSurat(String nomorSurat) {
        this.nomorSurat = nomorSurat;
    }

    public String getSifat() {
        return sifat;
    }

    public void setSifat(String sifat) {
        this.sifat = sifat;
    }

    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getNomorSp() {
        return nomorSp;
    }

    public void setNomorSp(String nomorSp) {
        this.nomorSp = nomorSp;
    }

    public String getTanggalSp() {
        return tanggalSp;
    }

    public void setTanggalSp(String tanggalSp) {
        this.tanggalSp = tanggalSp;
    }

    public List<DokumenPinjaman> getListPinjaman() {
        return listPinjaman;
    }

    public void setListPinjaman(List<DokumenPinjaman> listPinjaman) {
        this.listPinjaman = listPinjaman;
    }

    public String getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(String namaTim) {
        this.namaTim = namaTim;
    }

    public List<Pegawai> getListPegawai() {
        return listPegawai;
    }

    public void setListPegawai(List<Pegawai> listPegawai) {
        this.listPegawai = listPegawai;
    }

    public String getJabatanPenandatangan() {
        return jabatanPenandatangan;
    }

    public void setJabatanPenandatangan(String jabatanPenandatangan) {
        this.jabatanPenandatangan = jabatanPenandatangan;
    }

    public String getNamaPenandatangan() {
        return namaPenandatangan;
    }

    public void setNamaPenandatangan(String namaPenandatangan) {
        this.namaPenandatangan = namaPenandatangan;
    }
}
