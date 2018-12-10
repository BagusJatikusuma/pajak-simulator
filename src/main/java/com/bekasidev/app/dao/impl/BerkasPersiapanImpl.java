package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.BerkasPersiapanDao;
import com.bekasidev.app.model.BerkasPersiapan;
import com.bekasidev.app.model.DokumenPinjaman;
import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.util.LogException;
import com.bekasidev.app.wrapper.DokumenPersiapanWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BerkasPersiapanImpl implements BerkasPersiapanDao {
    @Override
    public void getBerkasPersiapan(String idSP, WajibPajak wajibPajak) {
        String sql = "SELECT * FROM berkas_persiapan WHERE id_wp=? AND id_sp=?";
        BerkasPersiapan berkasPersiapan = new BerkasPersiapan();

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, wajibPajak.getNpwpd());
            pstm.setString(2, idSP);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                wajibPajak.setListPinjaman(setDokumenPinjaman(rs.getString("dokumen_pinjaman")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new LogException(e);
        }
    }

    @Override
    public void createBerkasPersiapan(WajibPajak wajibPajak, String idSP) {
        String sql = "INSERT INTO berkas_persiapan VALUES(?,?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, wajibPajak.getNpwpd());
            pstm.setString(2, idSP);
            pstm.setString(3, setPinjamanToString(wajibPajak.getListPinjaman()));

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            new LogException(e);
        }
    }

    @Override
    public void updateBerkasPersiapan(WajibPajak wajibPajak, String idSP) {
        String sql = "UPDATE berkas_persiapan SET dokumen_pinjaman=? WHERE id_sp=? AND id_wp=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, setPinjamanToString(wajibPajak.getListPinjaman()));
            pstm.setString(2, idSP);
            pstm.setString(3, wajibPajak.getNpwpd());
        } catch (SQLException e) {
            e.printStackTrace();
            new LogException(e);
        }
    }


//    private BerkasPersiapan setDokumenPersiapan(ResultSet rs) throws SQLException {
//        DokumenPersiapanWrapper berkasPersiapan = new DokumenPersiapanWrapper();
//        berkasPersiapan.setIdBerkas(rs.getString("id_berkas"));
//        berkasPersiapan.setKotaTerbit(rs.getString("kota_terbit"));
//        berkasPersiapan.setNpwpd(rs.getString("npwpd"));
//        berkasPersiapan.setAlamatJalan(rs.getString("alamat_jalan"));
//        berkasPersiapan.setAlamatKecamatan(rs.getString("alamat_kecamatan"));
//        berkasPersiapan.setAlamatDi(rs.getString("alamat_di"));
//        berkasPersiapan.setNomorSurat(rs.getString("nomor_surat"));
//        berkasPersiapan.setSifat(rs.getString("sifate"));
//        berkasPersiapan.setLampiran(rs.getString("lampiran"));
//        berkasPersiapan.setPerihal(rs.getString("perihal"));
//        berkasPersiapan.setNomorSp(rs.getString("nomor_sp"));
//        berkasPersiapan.setTanggalSp(rs.getString("tanggal_sp"));
//        berkasPersiapan.setNamaTim(rs.getString("nama_tim"));
//        berkasPersiapan.setJabatanPenandatangan(rs.getString("jabatan_penandatangan"));
//        berkasPersiapan.setNamaPenandatangan(rs.getString("nama_penandatangan"));
//        berkasPersiapan.setTanggalDibuat(rs.getString("tanggal_buat"));
//        berkasPersiapan.setListPinjaman(setDokumenPinjaman(rs.getString("dokumen_pinjaman")));
//        berkasPersiapan.setListPegawai(getPegawai(rs.getString("daftar_anggota")));
//        berkasPersiapan.setMasaPajakAwal(rs.getString("masa_pajak_awal"));
//        berkasPersiapan.setMasaPajakAkhir(rs.getString("masa_pajak_akhir"));
//        berkasPersiapan.setIdWajibPajak(rs.getString("id_wp"));
//
//        return berkasPersiapan;
//    }

    private ArrayList<DokumenPinjaman> setDokumenPinjaman(String stringPinjaman){
        String[] parts = stringPinjaman.split("<p>");
        ArrayList<DokumenPinjaman> listPinjaman = new ArrayList<DokumenPinjaman>();
        for(String part : parts){
            listPinjaman.add(new DokumenPinjaman(part,""));
        }

        return listPinjaman;
    }

//    private ArrayList<Pegawai> getPegawai(String stringPegawai){
//        String[] parts = stringPegawai.split("<p>");
//        ArrayList<Pegawai> listPegawai = new ArrayList<Pegawai>();
//        for(String part : parts){
//            String[] partPegawai = part.split("<b>");
//            listPegawai.add(new Pegawai("", partPegawai[0],partPegawai[1],partPegawai[2], partPegawai[3], partPegawai[4]));
//        }
//
//        return listPegawai;
//    }

    private String setPinjamanToString(List<DokumenPinjaman> dokumenPinjamen){
        String result = dokumenPinjamen.get(0).getNamaDokumen();
        for(int i = 1; i<dokumenPinjamen.size();i++){
            result += "<p>" + dokumenPinjamen.get(i).getNamaDokumen();
        }

        return result;
    }

//    private String setPegawaiToString(List<Pegawai> pegawaiList){
//        String result = "";
//        if(pegawaiList.size() > 0){
//            result += pegawaiList.get(0).getNipPegawai() + "<b>" + pegawaiList.get(0).getNamaPegawai() + "<b>"
//                    + pegawaiList.get(0).getGolongan() + "<b>" + pegawaiList.get(0).getPangkat() + "<b>"
//                    + pegawaiList.get(0).getJabatanTim();
//            for(int i = 1; i < pegawaiList.size();i++){
//                result += "<p>" + pegawaiList.get(i).getNipPegawai() + "<b>" + pegawaiList.get(i).getNamaPegawai() + "<b>"
//                        + pegawaiList.get(i).getGolongan() + "<b>" + pegawaiList.get(i).getPangkat() + "<b>"
//                        + pegawaiList.get(i).getJabatanTim();
//            }
//        }
//
//        return result;
//    }
}
