package com.bekasidev.app.model;

import java.util.List;

public class TimSP {
    private String idSP;
    private Pegawai penanggungJawab;
    private Pegawai supervisor;
    private String namaTim;
    private List<Pegawai> listAnggota;
    private List<WajibPajak> listWP;

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public List<WajibPajak> getListWP() {
        return listWP;
    }

    public void setListWP(List<WajibPajak> listWP) {
        this.listWP = listWP;
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

    public String getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(String namaTim) {
        this.namaTim = namaTim;
    }

    public List<Pegawai> getListAnggota() {
        return listAnggota;
    }

    public void setListAnggota(List<Pegawai> listAnggota) {
        this.listAnggota = listAnggota;
    }
}
