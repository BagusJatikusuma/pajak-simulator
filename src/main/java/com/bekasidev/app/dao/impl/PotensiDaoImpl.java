package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.PotensiDao;
import com.bekasidev.app.model.Potensi;
import com.bekasidev.app.wrapper.PotensiJoinWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PotensiDaoImpl implements PotensiDao {
    @Override
    public List<Potensi> getAllPotensi(String idRestoran, String idTransaksi) {
        List<Potensi> potensis = new ArrayList<>();
        String sql = "SELECT * FROM potensi_menu WHERE id_restoran=? AND id_transaksi=?";

        try (Connection conn = Connect.connect();
             PreparedStatement pstm =  conn.prepareStatement(sql);
        ){
            pstm.setString(1, idRestoran);
            pstm.setString(2, idTransaksi);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                potensis.add(setPotensi(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return potensis;
    }

    @Override
    public void createPotensi(List<Potensi> listPotensi) {
        String sql = "INSERT INTO potensi_menu VALUES(?,?,?,?,?,?,?)";
        for(int i = 1; i < listPotensi.size(); i++){
            sql += ",(?,?,?,?,?,?,?)";
        }

        try (Connection conn = Connect.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            int j = 0;
            for(Potensi potensi : listPotensi){
                j += 1;
                pstm.setString(j, potensi.getIdRestoran()); j += 1;
                pstm.setString(j, potensi.getIdTransaksi());j += 1;
                pstm.setString(j, potensi.getIdMenu());j += 1;
                pstm.setDouble(j, potensi.getHargaSatuan());j += 1;
                pstm.setInt(j, potensi.getFrekuensiPenjualan());j += 1;
                pstm.setDouble(j, potensi.getJumlahPenjualan());j += 1;
                pstm.setString(j, potensi.getTanggalBuat());
            }
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PotensiJoinWrapper> getPotensiJoinMenu(String idRestoran, String idTransaksi) {
        String sql = "SELECT * FROM potensi_menu pt JOIN menu USING(id_menu) JOIN pembukuan USING (id_transaksi) " +
                "       WHERE pt.id_restoran=? AND pt.id_transaksi=?";
        List<PotensiJoinWrapper> potensiJoinWrappers = new ArrayList<>();

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, idRestoran);
            pstm.setString(2, idTransaksi);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                PotensiJoinWrapper potensiJoinWrapper = setPotensiJoin(rs);
                potensiJoinWrapper.setJenisMenu(rs.getShort("jenis_menu"));
                potensiJoinWrapper.setNamaMenu(rs.getString("nama_menu"));
                potensiJoinWrapper.setFrekuensiPotensi(rs.getFloat("potensi_porsi"));

                potensiJoinWrappers.add(potensiJoinWrapper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return potensiJoinWrappers;
    }

    private Potensi setPotensi(ResultSet rs) throws SQLException {
        Potensi potensi = new Potensi();
        potensi.setIdTransaksi(rs.getString("id_transaksi"));
        potensi.setIdRestoran(rs.getString("id_restoran"));
        potensi.setIdMenu(rs.getString("id_menu"));
        potensi.setHargaSatuan(rs.getDouble("harga_satuan"));
        potensi.setFrekuensiPenjualan(rs.getInt("frekuensi_penjualan"));
        potensi.setJumlahPenjualan(rs.getDouble("jumlah_penjualan"));
        potensi.setTanggalBuat(rs.getString("tanggal_buat"));
        return potensi;
    }

    private PotensiJoinWrapper setPotensiJoin(ResultSet rs) throws SQLException {
        PotensiJoinWrapper potensiJoinWrapper = new PotensiJoinWrapper();
        potensiJoinWrapper.setIdTransaksi(rs.getString("id_transaksi"));
        potensiJoinWrapper.setIdRestoran(rs.getString("id_restoran"));
        potensiJoinWrapper.setIdMenu(rs.getString("id_menu"));
        potensiJoinWrapper.setHargaSatuan(rs.getDouble("harga_satuan"));
        potensiJoinWrapper.setFrekuensiPenjualan(rs.getInt("frekuensi_penjualan"));
        potensiJoinWrapper.setJumlahPenjualan(rs.getDouble("jumlah_penjualan"));
        potensiJoinWrapper.setTanggalBuat(rs.getString("tanggal_buat"));
        return potensiJoinWrapper;
    }
}
