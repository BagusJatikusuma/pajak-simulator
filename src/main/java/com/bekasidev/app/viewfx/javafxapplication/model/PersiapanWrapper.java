/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.model;

import com.bekasidev.app.model.Pegawai;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Bayu Arafli
 */
public class PersiapanWrapper {
    private String idSP;
    private String dasarNomor;
    private Date dasarTanggal;
    private String dasarTahunAnggaran;
    private String pemberiSK;
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
    
    private ArrayList<TimWPWrapper> timWPWrappers;
    private List<NomorTanggalWajibPajakWrapper> nomorTanggalWPList;
    private List<DokumenPinjamanWajibPajakWrapper> dokumenPinjamanWajibPajakWrappers;

    public PersiapanWrapper() {
        this.timWPWrappers = new ArrayList<TimWPWrapper>();
        this.nomorTanggalWPList = new ArrayList<>();
        this.dokumenPinjamanWajibPajakWrappers = new ArrayList<>();
    }

    public PersiapanWrapper(String dasarNomor, Date dasarTanggal, String dasarTahunAnggaran, String nama, String jabatan, Integer masaPajakAwalBulan, Integer masaPajakAwalTahun, Integer masaPajakAkhirbulan, Integer masaPajakAkhirTahun, Integer tahapKe, Integer lamaPelaksanaan, Integer biayaTahunAPBD, String biayaNomorAPBD, Date biayaTanggalAPBD, String ditetapkanDi, Date tanggalPengesahan, String nomorSurat, Pegawai penandatangan, ArrayList<TimWPWrapper> timWPWrappers) {
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
        this.timWPWrappers = timWPWrappers;
        this.nomorTanggalWPList = new ArrayList<>();
        this.dokumenPinjamanWajibPajakWrappers = new ArrayList<>();
    }

    public PersiapanWrapper(String dasarNomor, Date dasarTanggal, String dasarTahunAnggaran, String nama, String jabatan, Integer masaPajakAwalBulan, Integer masaPajakAwalTahun, Integer masaPajakAkhirbulan, Integer masaPajakAkhirTahun, Integer tahapKe, Integer lamaPelaksanaan, Integer biayaTahunAPBD, String biayaNomorAPBD, Date biayaTanggalAPBD, String ditetapkanDi, Date tanggalPengesahan, String nomorSurat, Pegawai penandatangan, ArrayList<TimWPWrapper> timWPWrappers, List<NomorTanggalWajibPajakWrapper> nomorTanggalWPList) {
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
        this.timWPWrappers = timWPWrappers;
        this.nomorTanggalWPList = nomorTanggalWPList;
        this.dokumenPinjamanWajibPajakWrappers = new ArrayList<>();
    }

    public PersiapanWrapper(String dasarNomor, Date dasarTanggal, String dasarTahunAnggaran, String nama, String jabatan, Integer masaPajakAwalBulan, Integer masaPajakAwalTahun, Integer masaPajakAkhirbulan, Integer masaPajakAkhirTahun, Integer tahapKe, Integer lamaPelaksanaan, Integer biayaTahunAPBD, String biayaNomorAPBD, Date biayaTanggalAPBD, String ditetapkanDi, Date tanggalPengesahan, String nomorSurat, Pegawai penandatangan, ArrayList<TimWPWrapper> timWPWrappers, List<NomorTanggalWajibPajakWrapper> nomorTanggalWPList, List<DokumenPinjamanWajibPajakWrapper> dokumenPinjamanWajibPajakWrappers) {
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
        this.timWPWrappers = timWPWrappers;
        this.nomorTanggalWPList = nomorTanggalWPList;
        this.dokumenPinjamanWajibPajakWrappers = dokumenPinjamanWajibPajakWrappers;
    }

    public PersiapanWrapper(String dasarNomor, Date dasarTanggal, String dasarTahunAnggaran, String pemberiSK, String nama, String jabatan, Integer masaPajakAwalBulan, Integer masaPajakAwalTahun, Integer masaPajakAkhirbulan, Integer masaPajakAkhirTahun, Integer tahapKe, Integer lamaPelaksanaan, Integer biayaTahunAPBD, String biayaNomorAPBD, Date biayaTanggalAPBD, String ditetapkanDi, Date tanggalPengesahan, String nomorSurat, Pegawai penandatangan, ArrayList<TimWPWrapper> timWPWrappers, List<NomorTanggalWajibPajakWrapper> nomorTanggalWPList, List<DokumenPinjamanWajibPajakWrapper> dokumenPinjamanWajibPajakWrappers) {
        this.dasarNomor = dasarNomor;
        this.dasarTanggal = dasarTanggal;
        this.dasarTahunAnggaran = dasarTahunAnggaran;
        this.pemberiSK = pemberiSK;
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
        this.timWPWrappers = timWPWrappers;
        this.nomorTanggalWPList = nomorTanggalWPList;
        this.dokumenPinjamanWajibPajakWrappers = dokumenPinjamanWajibPajakWrappers;
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

    public ArrayList<TimWPWrapper> getTimWPWrappers() {
        return timWPWrappers;
    }

    public void setTimWPWrappers(ArrayList<TimWPWrapper> timWPWrappers) {
        this.timWPWrappers = timWPWrappers;
    }

    public List<NomorTanggalWajibPajakWrapper> getNomorTanggalWPList() {
        return nomorTanggalWPList;
    }

    public void setNomorTanggalWPList(List<NomorTanggalWajibPajakWrapper> nomorTanggalWPList) {
        this.nomorTanggalWPList = nomorTanggalWPList;
    }

    public List<DokumenPinjamanWajibPajakWrapper> getDokumenPinjamanWajibPajakWrappers() {
        return dokumenPinjamanWajibPajakWrappers;
    }

    public void setDokumenPinjamanWajibPajakWrappers(List<DokumenPinjamanWajibPajakWrapper> dokumenPinjamanWajibPajakWrappers) {
        this.dokumenPinjamanWajibPajakWrappers = dokumenPinjamanWajibPajakWrappers;
    }

    public String getPemberiSK() {
        return pemberiSK;
    }

    public void setPemberiSK(String pemberiSK) {
        this.pemberiSK = pemberiSK;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    @Override
    public String toString() {
        if (this.getNomorSurat() != null) return this.getIdSP()+" : "+"800/"+this.getNomorSurat()+"/BAPENDA";
        return this.getIdSP()+" : "+"800/ /BAPENDA";
    }
    
}
