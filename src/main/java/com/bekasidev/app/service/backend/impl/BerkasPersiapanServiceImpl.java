package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.BerkasPersiapanDao;
import com.bekasidev.app.dao.impl.BerkasPersiapanImpl;
import com.bekasidev.app.model.BerkasPersiapan;
import com.bekasidev.app.model.DokumenPinjaman;
import com.bekasidev.app.model.WP;
import com.bekasidev.app.service.backend.BerkasPersiapanService;

import java.util.List;

public class BerkasPersiapanServiceImpl implements BerkasPersiapanService {

    BerkasPersiapanDao berkasPersiapanDao = new BerkasPersiapanImpl();

    @Override
    public BerkasPersiapan getBerkasPersiapan(String idBerkas) {
        return berkasPersiapanDao.getBerkasPersiapan(idBerkas);
    }

    @Override
    public void createBerkasPersiapan(BerkasPersiapan berkasPersiapan) {
        berkasPersiapanDao.createBerkasPersiapan(berkasPersiapan);
    }

    @Override
    public void getDokumenPinjaman(BerkasPersiapan berkasPersiapan, WP wp) {

    }

    private void createListPinjamanRestoran(BerkasPersiapan berkasPersiapan){
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("STPD DAN SSPD bulan " + berkasPersiapan.getMasaPajakAwal() +
                                        " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Laporan Keuangan per bulan " + berkasPersiapan.getMasaPajakAwal() +
                        " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Bill/Cash Register bulan " + berkasPersiapan.getMasaPajakAwal() +
                        " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Catatan penjualan/penerimaan harian/record per kasir bulan " +
                        berkasPersiapan.getMasaPajakAwal() + " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Dokumen pemesanan makanan dan minuman bulan " +
                        berkasPersiapan.getMasaPajakAwal() + " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Buku persediaan barang dan daftar pengeluaran bulan " +
                        berkasPersiapan.getMasaPajakAwal() + " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(new DokumenPinjaman("Daftar menu/harga", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Daftar jumlah karyawan dan gaji karyawan", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Daftar komponen penghasilan yang diterima pegawai " +
                        "(termasuk pembagian service charge)", ""));
        berkasPersiapan.getListPinjaman().add(new DokumenPinjaman("Rekening Koran", ""));
        berkasPersiapan.getListPinjaman().add(new DokumenPinjaman("SPT PPh Badan Tahun 2017", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Daftar jumlah Ruangan/Meja/Kursi",""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Data Pemilik/Pemegang Paham", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Data Pengelola/Direksi", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Akte Pendirian Perusahaan terakhir", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Berkas Perizinan Perusahaan", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Struktur Organisasi Perusahaan", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Data/dokumen lain yang diperlukan tim pemeriksa", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
    }

    private void createListPinjamanHotel(BerkasPersiapan berkasPersiapan){
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("STPD DAN SSPD bulan " + berkasPersiapan.getMasaPajakAwal() +
                        " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Laporan Keuangan per bulan " + berkasPersiapan.getMasaPajakAwal() +
                        " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Bill/Cash Register bulan " + berkasPersiapan.getMasaPajakAwal() +
                        " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Catatan penjualan/penerimaan harian/record per kasir bulan " +
                        berkasPersiapan.getMasaPajakAwal() + " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Dokumen pemesanan Kamar/Ruangan bulan " +
                        berkasPersiapan.getMasaPajakAwal() + " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Daftar fasilitas Hotel bulan " +
                        berkasPersiapan.getMasaPajakAwal() + " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Daftar jumlah kamar berdasarkan kelas dan harga kamar", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Daftar jumlah karyawan dan gaji karyawan", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Daftar komponen penghasilan yang diterima pegawai " +
                        "(termasuk pembagian service charge)", ""));
        berkasPersiapan.getListPinjaman().add(new DokumenPinjaman("Rekening Koran", ""));
        berkasPersiapan.getListPinjaman().add(new DokumenPinjaman("SPT PPh Badan Tahun 2017", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Data Pemilik/Pemegang Saham", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Data Pengelola/Direksi", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Berkas Perizinan Perusahaan", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Akte Pendirian Perusahaan terakhir", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Struktur Organisasi Perusahaan", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Data/dokumen lain yang diperlukan tim pemeriksa", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
    }

    private void createListPinjamanParkir(BerkasPersiapan berkasPersiapan){
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("STPD DAN SSPD bulan " + berkasPersiapan.getMasaPajakAwal() +
                        " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Laporan Keuangan per bulan " + berkasPersiapan.getMasaPajakAwal() +
                        " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Bill/Cash Register bulan " + berkasPersiapan.getMasaPajakAwal() +
                        " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Catatan penjualan/penerimaan harian kasir bulan " +
                        berkasPersiapan.getMasaPajakAwal() + " s.d " + berkasPersiapan.getMasaPajakAkhir(), ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Daftar jumlah karyawan dan gaji karyawan", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Daftar komponen penghasilan yang diterima pegawai " +
                        "(termasuk pembagian service charge)", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Daftar harga/tarif parkir per jenis kendaraan", ""));
        berkasPersiapan.getListPinjaman().add(new DokumenPinjaman("Rekening Koran", ""));
        berkasPersiapan.getListPinjaman().add(new DokumenPinjaman("SPT PPh Badan Tahun 2017", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Data Pemilik/Pemegang Paham", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Data Pengelola/Direksi", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Berkas Perizinan Perusahaan", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Akte Pendirian Perusahaan terakhir", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Struktur Organisasi Perusahaan", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("Data/dokumen lain yang diperlukan tim pemeriksa", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
        berkasPersiapan.getListPinjaman().add(
                new DokumenPinjaman("...............................................", ""));
    }
}
