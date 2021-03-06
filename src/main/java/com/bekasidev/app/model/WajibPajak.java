package com.bekasidev.app.model;

import com.bekasidev.app.view.util.ConverterHelper;
import java.util.ArrayList;
import java.util.List;

public class WajibPajak {

    private String npwpd;
    private String namaWajibPajak;
    private short jenisWp;
    private String jalan;
    private String kecamatan;
    private String desa;
    private String namaPemilik;
    private String telepon;
    private String fax;
    private String tahunMulaiOperasional;
    private List<DokumenPinjaman> listPinjaman = new ArrayList<>();
    private NomorBerkas nomorBerkas;

    public List<DokumenPinjaman> getListPinjaman() {
        return listPinjaman;
    }

    public void setListPinjaman(List<DokumenPinjaman> listPinjaman) {
        this.listPinjaman = listPinjaman;
    }

    public NomorBerkas getNomorBerkas() {
        return nomorBerkas;
    }

    public void setNomorBerkas(NomorBerkas nomorBerkas) {
        this.nomorBerkas = nomorBerkas;
    }

    public WajibPajak(String npwpd, String namaWajibPajak) {
        this.npwpd = npwpd;
        this.namaWajibPajak = namaWajibPajak;
    }

    public WajibPajak() {
//        this.listPinjaman = new ArrayList<>();
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTahunMulaiOperasional() {
        return tahunMulaiOperasional;
    }

    public void setTahunMulaiOperasional(String tahunMulaiOperasional) {
        this.tahunMulaiOperasional = tahunMulaiOperasional;
    }

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

    public String getNpwpd() {
        return npwpd;
    }

    public void setNpwpd(String npwpd) {
        this.npwpd = npwpd;
    }

    public String getNamaWajibPajak() {
        return namaWajibPajak;
    }

    public void setNamaWajibPajak(String namaWajibPajak) {
        this.namaWajibPajak = namaWajibPajak;
    }
    
    public String getCorrectNPWPD() {
        return ConverterHelper.correctNPWPD(this.npwpd);
    }
    
    @Override
    public String toString() {
        return namaWajibPajak;
    }
}
