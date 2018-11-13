package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.WajibPajakDao;
import com.bekasidev.app.model.WajibPajak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WajibPajakDaoImpl implements WajibPajakDao {

    public List<WajibPajak> getAllWP() {
        String sql = "SELECT * FROM wajib_pajak";
        List<WajibPajak> listWajibPajak = new ArrayList<>();

        try (Connection conn = Connect.connect();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            while(rs.next()){
                WajibPajak wajibPajak = new WajibPajak();
                wajibPajak.setIdWajibPajak(rs.getString("id_wp"));
                wajibPajak.setNamaWajibPajak(rs.getString("nama_wp"));
                wajibPajak.setJalan(rs.getString("alamat_jalan"));
                wajibPajak.setKecamatan(rs.getString("alamat_kecamatan"));
                wajibPajak.setDesa(rs.getString("alamat_desa"));
                wajibPajak.setJenisWp(rs.getShort("jenis_wp"));
                listWajibPajak.add(wajibPajak);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listWajibPajak;
    }

    public void createDataWP(WajibPajak wajibPajak){
        String sql = "INSERT INTO wajib_pajak VALUES(?,?,?,?,?,?)";

        try (Connection conn = Connect.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, wajibPajak.getIdWajibPajak());
            pstm.setString(2, wajibPajak.getNamaWajibPajak());
            pstm.setShort(3, wajibPajak.getJenisWp());
            pstm.setString(4, wajibPajak.getJalan());
            pstm.setString(5, wajibPajak.getKecamatan());
            pstm.setString(6, wajibPajak.getDesa());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWPById(String idRestoran) {
        String sql = "DELETE FROM wajib_pajak WHERE id_wp=?";

        try(Connection conn = Connect.connect();
                PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, idRestoran);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WajibPajak getWPById(String idWp) {
        String sql = "SELECT * FROM wajib_pajak WHERE id_wp=?";
        WajibPajak wp = new WajibPajak();

        try (Connection conn = Connect.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, idWp);

            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                wp.setIdWajibPajak(rs.getString("id_wp"));
                wp.setNamaWajibPajak(rs.getString("nama_wp"));
                wp.setJalan(rs.getString("alamat_jalan"));
                wp.setKecamatan(rs.getString("alamat_kecamatan"));
                wp.setDesa(rs.getString("alamat_desa"));
                wp.setJenisWp(rs.getShort("jenis_wp"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wp;
    }
}
