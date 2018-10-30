package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.PotensiDao;
import com.bekasidev.app.model.Potensi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PotensiDaoImpl implements PotensiDao {
    @Override
    public List<Potensi> getAllTarif(String idRestoran, String idTransaksi) {
        List<Potensi> potensis = new ArrayList<>();
        String sql = "SELECT * FROM potensi_menu WHERE id_restoran=? AND id_transaksi=?";

        try (Connection conn = Connect.connect();
             PreparedStatement pstm =  conn.prepareStatement(sql);
        ){
            pstm.setString(1, idRestoran);
            pstm.setString(2, idTransaksi);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                potensis.add(setTarifMenu(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return potensis;
    }

    @Override
    public void createTarifMenu(Potensi potensi) {
        String sql = "INSERT INTO potensi_menu VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = Connect.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, potensi.getIdRestoran());
            pstm.setString(2, potensi.getIdTransaksi());
            pstm.setString(3, potensi.getNamaMenu());;
            pstm.setShort(4, potensi.getTipeMenu());
            pstm.setDouble(5, potensi.getHargaSatuan());
            pstm.setInt(6, potensi.getFrekuensiPenjualan());
            pstm.setDouble(7, potensi.getJumlahPenjualan());
            pstm.setDouble(8, potensi.getTotalPenjualan());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Potensi setTarifMenu(ResultSet rs) throws SQLException {
        Potensi potensi = new Potensi();
        potensi.setIdTransaksi(rs.getString("id_transaksi"));
        potensi.setIdRestoran(rs.getString("id_restoran"));
        potensi.setNamaMenu(rs.getString("nama_menu"));
        potensi.setTipeMenu(rs.getShort("tipe_menu"));
        potensi.setHargaSatuan(rs.getDouble("harga_satuan"));
        potensi.setFrekuensiPenjualan(rs.getInt("frekuensi_penjualan"));
        potensi.setJumlahPenjualan(rs.getDouble("jumlah_penjualan"));
        potensi.setTotalPenjualan(rs.getDouble("total_penjualan"));
        return potensi;
    }
}
