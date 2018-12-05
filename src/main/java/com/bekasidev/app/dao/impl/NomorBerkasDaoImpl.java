package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.NomorBerkasDao;
import com.bekasidev.app.model.NomorBerkas;
import com.bekasidev.app.model.Surat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NomorBerkasDaoImpl implements NomorBerkasDao {
    @Override
    public NomorBerkas getNomotBerkas(String idSP, String idWp) {
        String sql = "SELECT * FROM nomor_berkas WHERE id_sp=? AND id_wp=?";
        NomorBerkas nomorBerkas = new NomorBerkas();

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, idSP);
            pstm.setString(2, idWp);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                nomorBerkas.setNomorSuratHasil(rs.getString("nomor_hasil"));
                nomorBerkas.setNomorSuratPemberitahuan(rs.getString("nomor_pemberitahuan"));
                nomorBerkas.setNomorSuratPeminjaman(rs.getString("nomor_peminjaman"));
                nomorBerkas.setNomorBeritaAcara(rs.getString("nomor_berita_acara"));
                nomorBerkas.setNomorSKPD(rs.getString("nomor_SKPD"));
                nomorBerkas.setNomorTeguran1(rs.getString("nomor_teguran1"));
                nomorBerkas.setNomorTeguran2(rs.getString("nomor_teguran2"));
                
                nomorBerkas.setTanggalSuratPemberitahuan(rs.getString("tanggal_pemberitahuan"));
                nomorBerkas.setTanggalSuratHasil(rs.getString("tanggal_hasil"));
                nomorBerkas.setTanggalSuratPeminjaman(rs.getString("tanggal_peminjaman"));
                nomorBerkas.setTanggalBeritaAcara(rs.getString("tanggal_berita_acara"));
                nomorBerkas.setTanggalSKPD(rs.getString("tanggal_SKPD"));
                nomorBerkas.setTanggalTeguran1(rs.getString("tanggal_teguran1"));
                nomorBerkas.setTanggalTeguran2(rs.getString("tanggal_teguran2"));
                nomorBerkas.setJamTeguran2(rs.getString("jam_teguran2"));
                nomorBerkas.setTempatTeguran2(rs.getString("tempat_teguran2"));
                nomorBerkas.setHariTeguran2(rs.getString("hari_teguran2"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomorBerkas;
    }

    @Override
    public void setNomorSurat(String idSP, String idWP, String nomorSurat, String tanggal, Surat jenis) {
        String sql = "UPDATE nomor_berkas SET ";
        switch (jenis){
            case HASIL: sql += "nomor_hasil=?, tanggal_hasil=?"; break;
            case PEMINJAMAN: sql += "nomor_peminjaman=?, tanggal_peminjaman=?"; break;
            case PEMBERITAHUAN: sql += "nomor_pemberitahuan=?, tanggal_pemberitahuan=?"; break;
            case BERITA_ACARA: sql += "nomor_berita_acara=?, tanggal_berita_acara=?"; break;
            case EVALUASI: sql += "nomor_SKPD=?, tanggal_SKPD=?"; break;
            case TEGURAN_PERTAMA: sql += "nomor_teguran1=?, tanggal_teguran1=?"; break;
        }
        sql += " WHERE id_sp=? AND id_wp=?";
        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nomorSurat);
            pstm.setString(2, tanggal);
            pstm.setString(3, idSP);
            pstm.setString(4, idWP);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createNomorSurat(String idSP, String idWP) {
        String sql = "INSERT INTO nomor_berkas(id_wp, id_sp) VALUES(?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, idWP);
            pstm.setString(2, idSP);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setBerkasTeguran2(String idSP, String idWP, String nomorTeguran, String tanggalTeguran,
                                  String jam, String tempat, String hari) {
        String sql = "UPDATE nomor_berkas SET nomor_teguran2=?, tanggal_teguran2=?, jam_teguran2=?, " +
                "tempat_teguran2=?, hari_teguran2=? WHERE id_sp=? AND id_wp=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nomorTeguran);
            pstm.setString(2, tanggalTeguran);
            pstm.setString(3, jam);
            pstm.setString(4, tempat);
            pstm.setString(5, hari);
            pstm.setString(6, idSP);
            pstm.setString(7, idWP);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setNomorTanggalSKPD(String idSP, String idWP, String nomorSKPD, String tanggalSKPD) {
        String sql = "UPDATE nomor_berkas SET nomor_SKPD=?, tanggal_SKPD=? " +
                "WHERE id_sp=? AND id_wp=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nomorSKPD);
            pstm.setString(2, tanggalSKPD);
            pstm.setString(3, idSP);
            pstm.setString(4, idWP);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
