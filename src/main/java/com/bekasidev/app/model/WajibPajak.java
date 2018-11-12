package com.bekasidev.app.model;

public class WajibPajak {

    private String idWajibPajak;
    private String namaWajibPajak;
    private short jenisWp;
    private String jalan;
    private String kecamatan;
    private String desa;

    public short getJenisWp() {
        return jenisWp;
    }

    public void setJenisWp(short jenisWp) {
        this.jenisWp = jenisWp;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getIdWajibPajak() {
        return idWajibPajak;
    }

    public void setIdWajibPajak(String idWajibPajak) {
        this.idWajibPajak = idWajibPajak;
    }

    public String getNamaWajibPajak() {
        return namaWajibPajak;
    }

    public void setNamaWajibPajak(String namaWajibPajak) {
        this.namaWajibPajak = namaWajibPajak;
    }
    
    @Override
    public String toString() {
        return namaWajibPajak;
    }
}
