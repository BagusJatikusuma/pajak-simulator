package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.TarifMenuDao;
import com.bekasidev.app.model.TarifMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarifMenuDaoImpl implements TarifMenuDao {
    @Override
    public List<TarifMenu> getAllTarif(String idRestoran, String idTransaksi) {
        List<TarifMenu> tarifMenus= new ArrayList<>();
        String sql = "SELECT * FROM menu WHERE id_restoran=? AND id_transaksi=?";

        try (Connection conn = Connect.connect();
             PreparedStatement pstm =  conn.prepareStatement(sql);
        ){
            pstm.setString(1, idRestoran);
            pstm.setString(2, idTransaksi);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                tarifMenus.add(setTarifMenu(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarifMenus;
    }

    @Override
    public void createTarifMenu(TarifMenu tarifMenu) {
        String sql = "INSERT INTO menu VALUES(?,?,?,?,?)";

        try (Connection conn = Connect.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, tarifMenu.getIdRestoran());
            pstm.setString(2, tarifMenu.getIdTransaksi());
            pstm.setString(3, tarifMenu.getNamaMenu());;
            pstm.setShort(4, tarifMenu.getTipeMenu());
            pstm.setDouble(5, tarifMenu.getHarga());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TarifMenu setTarifMenu(ResultSet rs) throws SQLException {
        TarifMenu tarifMenu = new TarifMenu();
        tarifMenu.setIdTransaksi(rs.getString("id_transaksi"));
        tarifMenu.setIdRestoran(rs.getString("id_restoran"));
        tarifMenu.setNamaMenu(rs.getString("nama_menu"));
        tarifMenu.setTipeMenu(rs.getShort("tipe_menu"));
        tarifMenu.setHarga(rs.getDouble("harga_satuan"));
        return tarifMenu;
    }
}
