package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.RestoranDao;
import com.bekasidev.app.model.WajibPajak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestoranDaoImpl implements RestoranDao {

    public List<WajibPajak> getAllRestoran() {
        String sql = "SELECT * FROM restoran";
        List<WajibPajak> listWajibPajak = new ArrayList<>();

        try (Connection conn = Connect.connect();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            while(rs.next()){
                WajibPajak wajibPajak = new WajibPajak();
                wajibPajak.setIdWajibPajak(rs.getString("id_wp"));
                wajibPajak.setNamaWajibPajak(rs.getString("nama_wp"));
                listWajibPajak.add(wajibPajak);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listWajibPajak;
    }

    public void createDataRestoran(WajibPajak wajibPajak){
        String sql = "INSERT INTO wajib_pajak VALUES(?,?)";

        try (Connection conn = Connect.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, wajibPajak.getIdWajibPajak());
            pstm.setString(2, wajibPajak.getNamaWajibPajak());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestoranById(String idRestoran) {
        String sql = "DELETE FROM wajib_pajak WHERE id_wp=?";

        try(Connection conn = Connect.connect();
                PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, idRestoran);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
