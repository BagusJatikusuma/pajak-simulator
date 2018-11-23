package com.bekasidev.app.model;

public class Pegawai {

    private String idTim;
    private String nipPegawai;
    private String namaPegawai;
    private String golongan;
    private String pangkat;
    private String jabatanTim;
    private String jabatanDinas;

    public String getJabatanDinas() {
        return jabatanDinas;
    }

    public void setJabatanDinas(String jabatanDinas) {
        this.jabatanDinas = jabatanDinas;
    }

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
    }

    public Pegawai(String idTim, String nipPegawai, String namaPegawai, String golongan, String pangkat, String jabatanTim, String jabatanDinas) {
        this.idTim = idTim;
        this.nipPegawai = nipPegawai;
        this.namaPegawai = namaPegawai;
        this.golongan = golongan;
        this.pangkat = pangkat;
        this.jabatanTim = jabatanTim;
        this.jabatanDinas = jabatanDinas;
    }

    public Pegawai(String idTim, String nipPegawai, String namaPegawai, String golongan, String pangkat, String jabatanTim) {
        this.idTim = idTim;
        this.nipPegawai = nipPegawai;
        this.namaPegawai = namaPegawai;
        this.golongan = golongan;
        this.jabatanTim = jabatanTim;
        this.pangkat = pangkat;
    }

    public Pegawai() {
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

    public String getJabatanTim() {
        return jabatanTim;
    }

    public void setJabatanTim(String jabatanTim) {
        this.jabatanTim = jabatanTim;
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
