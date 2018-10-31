package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.MenuDao;
import com.bekasidev.app.model.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MenuDaoImpl implements MenuDao {
    @Override
    public List<Menu> getAllMenu(String idRestoran) {
        String sql = "SELECT * FROM menu WHERE id_restoran=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql);) {

            pstm.setString(1, idRestoran);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                setMenu(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createMenu(Menu menu) {
        String sql = "INSERT INTO menu VALUES(?,?,?,?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, menu.getIdMenu());
            pstm.setString(2, menu.getIdRestoran());
            pstm.setString(3, menu.getNamaMenu());
            pstm.setShort(4, menu.getJenisMenu());
            pstm.setDouble(5, menu.getHargaMenu());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Menu setMenu(ResultSet rs) throws SQLException {
        Menu menu = new Menu();

        menu.setIdMenu(rs.getString("id_menu"));
        menu.setNamaMenu(rs.getString("nama_menu"));
        menu.setJenisMenu(rs.getShort("jenis_menu"));
        menu.setHargaMenu(rs.getDouble("harga_menu"));
        menu.setIdRestoran(rs.getString("id_restoran"));

        return menu;
    }
}
