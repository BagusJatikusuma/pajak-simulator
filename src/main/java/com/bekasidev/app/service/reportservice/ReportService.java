/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.service.reportservice;

import com.bekasidev.app.model.WP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapperJasper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapperJasper;

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
    void createSuratPerintah();
    void createDaftarPetugasPemeriksa();
    void createPemberitahuanPemeriksaan(
            PersiapanWrapper persiapanWrapper, WajibPajak wp, TimWPWrapperJasper timWP, int index);
    void createTandaTerima(WP wp, WajibPajak wajibPajak,
             PersiapanWrapperJasper persiapanWrapper);
    void createPersiapanPeminjamanBuku(
             PersiapanWrapper persiapanWrapper, WajibPajak wp, int index);
    void createPersiapanDokumenPinjaman(WP wp, WajibPajak wajibPajak,
             PersiapanWrapperJasper persiapanWrapper);
    void createQuesionerRestoran();
    
    //yang dipakai pelaksanaan
    void createSuratPernyataan1(PelaksanaanWrapper pelaksanaanWrapper);
    void createTandaTerimaSPHP2(PelaksanaanWrapper pelaksanaanWrapper);
    void createSuratPersetujuan4(PelaksanaanWrapper pelaksanaanWrapper);
    void createPernyataanPersetujuanHasilPemeriksaan5(PelaksanaanWrapper pelaksanaanWrapper);
    void createSuratPenyetaanKesanggupanMembayarPajakKurangBarang6(PelaksanaanWrapper pelaksanaanWrapper);
    void createSuratPernyataan7(PelaksanaanWrapper pelaksanaanWrapper);
    void createKertasPemeriksaanPajak(PelaksanaanWrapper pelaksanaanWrapper);

}
