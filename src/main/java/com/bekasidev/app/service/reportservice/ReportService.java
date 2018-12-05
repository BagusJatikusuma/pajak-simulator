/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.service.reportservice;

import com.bekasidev.app.model.TimSP;
import com.bekasidev.app.model.WP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.viewfx.javafxapplication.model.NomorTanggalWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapperJasper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapperJasper;
import java.util.Date;

/**
 *
 * @author Bayu Arafli
 */
public interface ReportService {
    void createPersiapanPajakRestoranReport(
            PersiapanWrapperJasper persiapanWrapper, WajibPajak wp);
    void createPersiapanPajakRestoranReport1(
    PersiapanWrapperJasper persiapanWrapper, WajibPajak wp, TimWPWrapperJasper timWP);
    void createPersiapanPajakHotelReport();
    void createPersiapanPajakHotelReport1();
    void createPersiapanDokumenPinjamanHotel();
    void createPersiapanPajakParkirReport();
    
    //yang dipakai persiapan
    void createSuratPerintahBaru();
    void createSuratPerintah();
    void createDaftarPetugasPemeriksa();
    void createPemberitahuanPemeriksaan(
            PersiapanWrapper persiapanWrapper, WajibPajak wp, TimWPWrapperJasper timWP, int index);
    void createPemberitahuanPemeriksaanPerWP(PersiapanWrapper persiapanWrapper, 
            TimWPWrapper timWPWrapper, 
            NomorTanggalWajibPajakWrapper nomorTanggalWajibPajakWrapper, 
            WajibPajak wajibPajak);
    void createTandaTerima(WP wp, WajibPajak wajibPajak,
             PersiapanWrapperJasper persiapanWrapper);
    
    void createPersiapanPeminjamanBuku(
             PersiapanWrapper persiapanWrapper, WajibPajak wp, int index);
    void createPersiapanDokumenPinjaman(WP wp, WajibPajak wajibPajak,
             PersiapanWrapperJasper persiapanWrapper);
    
    void createPersiapanPeminjamanBukuPerWP(PersiapanWrapper persiapanWrapper, 
            TimWPWrapper timWPWrapper, 
            NomorTanggalWajibPajakWrapper nomorTanggalWajibPajakWrapper, 
            WajibPajak wajibPajak);
    void createPersiapanDokumenPinjamanPerWP(WP wp, WajibPajak wajibPajak,
             PersiapanWrapper persiapanWrapper);
    
    void createQuesionerRestoran();
    
    //yang dipakai pelaksanaan
    void createSuratPernyataan1(PelaksanaanWrapper pelaksanaanWrapper);
    void createTandaTerimaSPHP2(PelaksanaanWrapper pelaksanaanWrapper);
    void createSuratPemberitahuanHasilPemeriksaan3(PelaksanaanWrapper pelaksanaanWrapper,
            TimSP timSP);
    void createSuratPersetujuan4(PelaksanaanWrapper pelaksanaanWrapper);
    void createPernyataanPersetujuanHasilPemeriksaan5(PelaksanaanWrapper pelaksanaanWrapper);
    void createSuratPenyetaanKesanggupanMembayarPajakKurangBarang6(PelaksanaanWrapper pelaksanaanWrapper);
    void createSuratPernyataan7(PelaksanaanWrapper pelaksanaanWrapper);
    void createBeritaAcara8(PelaksanaanWrapper pelaksanaanWrapper,
            TimSP timSP);
    
    void createTemplateSuratPelaksanaan(PelaksanaanWrapper pelaksanaanWrapper);
    void createKertasPemeriksaanPajak(PelaksanaanWrapper pelaksanaanWrapper, TimSP timSP);
    void createSuratTeguran1(PelaksanaanWrapper pelaksanaanWrapper);
    void createSuratTeguran2(PelaksanaanWrapper pelaksanaanWrapper);
    void createCoverTemplate1(PelaksanaanWrapper pelaksanaanWrapper);
    void createCoverTemplate2(PelaksanaanWrapper pelaksanaanWrapper);
}
