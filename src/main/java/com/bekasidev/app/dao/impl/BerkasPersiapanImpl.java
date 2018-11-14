package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.BerkasPersiapanDao;
import com.bekasidev.app.dao.PegawaiDao;
import com.bekasidev.app.model.BerkasPersiapan;
import com.bekasidev.app.model.DokumenPinjaman;
import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.wrapper.DokumenPersiapanWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BerkasPersiapanImpl implements BerkasPersiapanDao {
    @Override
    public DokumenPersiapanWrapper getBerkasPersiapan(String idBerkas) {
        String sql = "SELECT * FROM berkas_persiapan WHERE id_berkas=?";
        BerkasPersiapan berkasPersiapan = new BerkasPersiapan();

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, idBerkas);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                berkasPersiapan = setDokumenPersiapan(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void createBerkasPersiapan(BerkasPersiapan berkasPersiapan) {
        String sql = "INSERT INTO berkas_persiapan VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, berkasPersiapan.getIdBerkas());
            pstm.setString(2, berkasPersiapan.getKotaTerbit());
            pstm.setString(3, berkasPersiapan.getNpwpd());
            pstm.setString(4, berkasPersiapan.getAlamatJalan());
            pstm.setString(5, berkasPersiapan.getAlamatKecamatan());
            pstm.setString(6, berkasPersiapan.getAlamatDi());
            pstm.setString(7, berkasPersiapan.getNomorSurat());
            pstm.setString(8, berkasPersiapan.getSifat());
            pstm.setString(9, berkasPersiapan.getLampiran());
            pstm.setString(10, berkasPersiapan.getPerihal());
            pstm.setString(11, berkasPersiapan.getNomorSp());
            pstm.setString(12, berkasPersiapan.getTanggalSp());
            pstm.setString(13, setPinjamanToString(berkasPersiapan.getListPinjaman()));
            pstm.setString(14, berkasPersiapan.getNamaTim());
            pstm.setString(15, setPegawaiToString(berkasPersiapan.getListPegawai()));
            pstm.setString(16, berkasPersiapan.getJabatanPenandatangan());
            pstm.setString(17, berkasPersiapan.getNamaPenandatangan());
            pstm.setString(18, berkasPersiapan.getTanggalDibuat());
            pstm.setString(19, berkasPersiapan.getMasaPajakAwal());
            pstm.setString(20, berkasPersiapan.getMasaPajakAkhir());
            pstm.setString(21, berkasPersiapan.getIdWajibPajak());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private BerkasPersiapan setDokumenPersiapan(ResultSet rs) throws SQLException {
        DokumenPersiapanWrapper berkasPersiapan = new DokumenPersiapanWrapper();
        berkasPersiapan.setIdBerkas(rs.getString("id_berkas"));
        berkasPersiapan.setKotaTerbit(rs.getString("kota_terbit"));
        berkasPersiapan.setNpwpd(rs.getString("npwpd"));
        berkasPersiapan.setAlamatJalan(rs.getString("alamat_jalan"));
        berkasPersiapan.setAlamatKecamatan(rs.getString("alamat_kecamatan"));
        berkasPersiapan.setAlamatDi(rs.getString("alamat_di"));
        berkasPersiapan.setNomorSurat(rs.getString("nomor_surat"));
        berkasPersiapan.setSifat(rs.getString("sifate"));
        berkasPersiapan.setLampiran(rs.getString("lampiran"));
        berkasPersiapan.setPerihal(rs.getString("perihal"));
        berkasPersiapan.setNomorSp(rs.getString("nomor_sp"));
        berkasPersiapan.setTanggalSp(rs.getString("tanggal_sp"));
        berkasPersiapan.setNamaTim(rs.getString("nama_tim"));
        berkasPersiapan.setJabatanPenandatangan(rs.getString("jabatan_penandatangan"));
        berkasPersiapan.setNamaPenandatangan(rs.getString("nama_penandatangan"));
        berkasPersiapan.setTanggalDibuat(rs.getString("tanggal_buat"));
        berkasPersiapan.setListPinjaman(setDokumenPinjaman(rs.getString("dokumen_pinjaman")));
        berkasPersiapan.setListPegawai(getPegawai(rs.getString("daftar_anggota")));
        berkasPersiapan.setMasaPajakAwal(rs.getString("masa_pajak_awal"));
        berkasPersiapan.setMasaPajakAkhir(rs.getString("masa_pajak_akhir"));
        berkasPersiapan.setIdWajibPajak(rs.getString("id_wp"));

        return berkasPersiapan;
    }

    private ArrayList<DokumenPinjaman> setDokumenPinjaman(String stringPinjaman){
        String[] parts = stringPinjaman.split("<p>");
        ArrayList<DokumenPinjaman> listPinjaman = new ArrayList<DokumenPinjaman>();
        for(String part : parts){
            listPinjaman.add(new DokumenPinjaman(part,""));
        }

        return listPinjaman;
    }

    private ArrayList<Pegawai> getPegawai(String stringPegawai){
        String[] parts = stringPegawai.split("<p>");
        ArrayList<Pegawai> listPegawai = new ArrayList<Pegawai>();
        for(String part : parts){
            String[] partPegawai = part.split("<b>");
            listPegawai.add(new Pegawai("", partPegawai[0],partPegawai[1],partPegawai[2],""));
        }

        return listPegawai;
    }

    private String setPinjamanToString(List<DokumenPinjaman> dokumenPinjamen){
        String result = dokumenPinjamen.get(0).getNamaDokumen();
        for(int i = 1; i<dokumenPinjamen.size();i++){
            result += "<p>" + dokumenPinjamen.get(i).getNamaDokumen();
        }

        return result;
    }

    private String setPegawaiToString(List<Pegawai> pegawaiList){
        String result = "";
        if(pegawaiList.size() > 0){
            result += pegawaiList.get(0).getNipPegawai() + "<b>" + pegawaiList.get(0).getNamaPegawai() + "<b>"
                    + pegawaiList.get(0).getGolongan();
            for(int i = 1; i < pegawaiList.size();i++){
                result += "<p>" + pegawaiList.get(i).getNipPegawai() + "<b>" + pegawaiList.get(i).getNamaPegawai() + "<b>"
                        + pegawaiList.get(i).getGolongan();
            }
        }

        return result;
    }
}
