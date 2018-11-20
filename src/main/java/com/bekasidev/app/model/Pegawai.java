package com.bekasidev.app.model;

public class Pegawai {

    private String idTim;
    private String nipPegawai;
    private String namaPegawai;
    private String golongan;
    private String pangkat;
    private String jabatan;

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
    }

    public Pegawai(String idTim, String nipPegawai, String namaPegawai, String golongan, String pangkat, String jabatan) {
        this.idTim = idTim;
        this.nipPegawai = nipPegawai;
        this.namaPegawai = namaPegawai;
        this.golongan = golongan;
        this.jabatan = jabatan;
        this.pangkat = pangkat;
    }

    public String getIdTim() {
        return idTim;
    }

    public void setIdTim(String idTim) {
        this.idTim = idTim;
    }

    public String getNipPegawai() {
        return nipPegawai;
    }

    public void setNipPegawai(String nipPegawai) {
        this.nipPegawai = nipPegawai;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    @Override
    public String toString() {
        return this.getNamaPegawai();
    }
    
    public boolean compareIdTo(Pegawai obj) {
        if (this.getNipPegawai().equals(obj.getNipPegawai())) return true;
        return false;
    }
    
}
