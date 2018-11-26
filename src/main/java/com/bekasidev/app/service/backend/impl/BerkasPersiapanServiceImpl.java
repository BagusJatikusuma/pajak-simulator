package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.BerkasPersiapanDao;
import com.bekasidev.app.dao.WajibPajakDao;
import com.bekasidev.app.dao.impl.BerkasPersiapanImpl;
import com.bekasidev.app.dao.impl.WajibPajakDaoImpl;
import com.bekasidev.app.model.BerkasPersiapan;
import com.bekasidev.app.model.DokumenPinjaman;
import com.bekasidev.app.model.WP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.backend.BerkasPersiapanService;
import com.bekasidev.app.wrapper.DokumenPersiapanWrapper;

import java.util.Calendar;
import java.util.List;

public class BerkasPersiapanServiceImpl implements BerkasPersiapanService {

    BerkasPersiapanDao berkasPersiapanDao = new BerkasPersiapanImpl();
    WajibPajakDao wajibPajakDao = new WajibPajakDaoImpl();

//    @Override
//    public DokumenPersiapanWrapper getBerkasPersiapan(String idBerkas) {
//        DokumenPersiapanWrapper dokumenPersiapanWrapper = berkasPersiapanDao.getBerkasPersiapan(idBerkas);
//        dokumenPersiapanWrapper.setWp(wajibPajakDao.getWPById(dokumenPersiapanWrapper.getIdWajibPajak()));
//        return dokumenPersiapanWrapper;
//    }
//
    @Override
    public void createBerkasPersiapan(String idSP, WajibPajak wajibPajak) {
        berkasPersiapanDao.createBerkasPersiapan(wajibPajak, idSP);
    }

    @Override
    public void getDokumenPinjaman(WajibPajak wajibPajak, String masaPajakAwal, String masaPajakAkhir) {
        wajibPajak.getListPinjaman().add(new DokumenPinjaman("", ""));
        switch(wajibPajak.getJenisWp()){
            case 1: createListPinjamanHotel(wajibPajak, masaPajakAwal, masaPajakAkhir); break;
            case 0: createListPinjamanRestoran(wajibPajak, masaPajakAwal, masaPajakAkhir); break;
            case 2: createListPinjamanParkir(wajibPajak, masaPajakAwal, masaPajakAkhir);break;
        }
    }

    private void createListPinjamanRestoran(WajibPajak wajibPajak, String masaPajakAwal, String masaPajakAkhir){
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("STPD DAN SSPD bulan " + masaPajakAwal +
                                        " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Laporan Keuangan per bulan " + masaPajakAwal +
                        " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Bill/Cash Register bulan " + masaPajakAwal +
                        " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Catatan penjualan/penerimaan harian/record per kasir bulan " +
                        masaPajakAwal + " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Dokumen pemesanan makanan dan minuman bulan " +
                        masaPajakAwal + " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Buku persediaan barang dan daftar pengeluaran bulan " +
                        masaPajakAwal + " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(new DokumenPinjaman("Daftar menu/harga", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Daftar jumlah karyawan dan gaji karyawan", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Daftar komponen penghasilan yang diterima pegawai " +
                        "(termasuk pembagian service charge)", ""));
        wajibPajak.getListPinjaman().add(new DokumenPinjaman("Rekening Koran", ""));
        wajibPajak.getListPinjaman().add(new DokumenPinjaman("SPT PPh Badan Tahun 2017", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Daftar jumlah Ruangan/Meja/Kursi",""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Data Pemilik/Pemegang Paham", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Data Pengelola/Direksi", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Akte Pendirian Perusahaan terakhir", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Berkas Perizinan Perusahaan", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Struktur Organisasi Perusahaan", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Data/dokumen lain yang diperlukan tim pemeriksa", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
    }

    private void createListPinjamanHotel(WajibPajak wajibPajak, String masaPajakAwal, String masaPajakAkhir){
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("STPD DAN SSPD bulan " + masaPajakAwal +
                        " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Laporan Keuangan per bulan " + masaPajakAwal +
                        " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Bill/Cash Register bulan " + masaPajakAwal +
                        " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Catatan penjualan/penerimaan harian/record per kasir bulan " +
                        masaPajakAwal + " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Dokumen pemesanan Kamar/Ruangan bulan " +
                        masaPajakAwal + " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Daftar fasilitas Hotel bulan " +
                        masaPajakAwal + " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Daftar jumlah kamar berdasarkan kelas dan harga kamar", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Daftar jumlah karyawan dan gaji karyawan", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Daftar komponen penghasilan yang diterima pegawai " +
                        "(termasuk pembagian service charge)", ""));
        wajibPajak.getListPinjaman().add(new DokumenPinjaman("Rekening Koran", ""));
        wajibPajak.getListPinjaman().add(new DokumenPinjaman("SPT PPh Badan Tahun 2017", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Data Pemilik/Pemegang Saham", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Data Pengelola/Direksi", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Berkas Perizinan Perusahaan", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Akte Pendirian Perusahaan terakhir", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Struktur Organisasi Perusahaan", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Data/dokumen lain yang diperlukan tim pemeriksa", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
    }

    private void createListPinjamanParkir(WajibPajak wajibPajak, String masaPajakAwal, String masaPajakAkhir){
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("STPD DAN SSPD bulan " + masaPajakAwal +
                        " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Laporan Keuangan per bulan " + masaPajakAwal +
                        " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Bill/Cash Register bulan " + masaPajakAwal +
                        " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Catatan penjualan/penerimaan harian kasir bulan " +
                        masaPajakAwal + " s.d " + masaPajakAkhir, ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Daftar jumlah karyawan dan gaji karyawan", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Daftar komponen penghasilan yang diterima pegawai " +
                        "(termasuk pembagian service charge)", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Daftar harga/tarif parkir per jenis kendaraan", ""));
        wajibPajak.getListPinjaman().add(new DokumenPinjaman("Rekening Koran", ""));
        wajibPajak.getListPinjaman().add(new DokumenPinjaman("SPT PPh Badan Tahun 2017", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Data Pemilik/Pemegang Paham", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Data Pengelola/Direksi", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Berkas Perizinan Perusahaan", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Akte Pendirian Perusahaan terakhir", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Struktur Organisasi Perusahaan", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("Data/dokumen lain yang diperlukan tim pemeriksa", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        wajibPajak.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
    }
}
