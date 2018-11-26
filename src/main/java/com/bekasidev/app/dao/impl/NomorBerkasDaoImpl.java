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
        String sql = "SELECT * FROM nomor_bekas WHERE id_sp=? AND id_wp=?";
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
                nomorBerkas.setTanggalSuratPemberitahuan(rs.getString("tanggal_pemberitahuan"));
                nomorBerkas.setTanggalSuratHasil(rs.getString("tanggal_hasil"));
                nomorBerkas.setTanggalSuratPeminjaman("tanggal_peminjaman");
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
}
