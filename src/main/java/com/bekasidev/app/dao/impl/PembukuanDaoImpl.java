package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.PembukuanDao;
import com.bekasidev.app.model.Pembukuan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PembukuanDaoImpl implements PembukuanDao {
    @Override
    public List<Pembukuan> getPembukuan(String idRestoran, String idTransaksi) {
        String sql = "SELECT * FROM pembukuan WHERE id_restoran=? AND id_transaksi=?";
        List<Pembukuan> pembukuans = new ArrayList<>();

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)){

            pstm.setString(1, idRestoran);
            pstm.setString(2, idTransaksi);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                pembukuans.add(setPembukuan(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pembukuans;
    }

    @Override
    public void createPembukuan(Pembukuan pembukuan) {
        String sql = "INSERT INTO pembukuan VALUES(?,?,?,?,?,?,?,?,?)";

        try(Connection conn = Connect.connect();
                PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, pembukuan.getIdRestoran());
            pstm.setString(2, pembukuan.getIdTransaksi());
            pstm.setString(3, pembukuan.getIdBenchmark());
            pstm.setString(4, pembukuan.getDeskripsi());
            pstm.setInt(5, pembukuan.getJumlah());
            pstm.setString(6, pembukuan.getSatuanJumlah());
            pstm.setFloat(7, pembukuan.getPotensiPorsi());
            pstm.setString(8, pembukuan.getTanggalBuat());
            pstm.setShort(9, pembukuan.getStatusBahan());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Pembukuan setPembukuan(ResultSet rs) throws SQLException {
        Pembukuan pembukuan = new Pembukuan();

        pembukuan.setIdRestoran(rs.getString("id_restoran"));
        pembukuan.setIdTransaksi(rs.getString("id_transaksi"));
        pembukuan.setIdBenchmark(rs.getString("id_benchmark"));
        pembukuan.setDeskripsi(rs.getString("deskripsi"));
        pembukuan.setJumlah(rs.getInt("jumlah"));
        pembukuan.setSatuanJumlah(rs.getString("satuan_jumlah"));
        pembukuan.setPotensiPorsi(rs.getFloat("potensi_porsi"));
        pembukuan.setTanggalBuat(rs.getString("tanggal_buat"));
        pembukuan.setStatusBahan(rs.getShort("status_bahan"));

        return pembukuan;
    }
}
