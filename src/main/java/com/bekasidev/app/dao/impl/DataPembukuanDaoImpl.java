package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.DataPembukuanDao;
import com.bekasidev.app.model.DataPembukuan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by waddi on 31/10/18.
 */
public class DataPembukuanDaoImpl implements DataPembukuanDao{

    public List<DataPembukuan> getAllDataPembukuan(String idHotel, String idDataPembukuan) {
        String sql = "SELECT * FROM datapembukuan WHERE id_hotel=? AND id_datapembukuan=?";
        List<DataPembukuan> dataPembukuans = new ArrayList<>();

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, idHotel);
            pstm.setString(2, idDataPembukuan);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                dataPembukuans.add(setDataPembukuan(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return dataPembukuans;
    }

    @Override
    public void createDataPembukuan(DataPembukuan dataPembukuan) {
        String sql = "INSERT INTO datapembukuan VALUES(?,?,?,?,?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, dataPembukuan.getIdHotel());
            pstm.setString(2, dataPembukuan.getIdDataPembukuan());
            pstm.setString(3, dataPembukuan.getUraianDataPembukuan());
            pstm.setInt(4, dataPembukuan.getJumlahDataPembukuan());
            pstm.setInt(5, dataPembukuan.getJumlahTotalDataPembukuan());
            pstm.setString(6, dataPembukuan.getSatuanBarang());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DataPembukuan setDataPembukuan(ResultSet rs) throws SQLException {
        DataPembukuan dataPembukuan = new DataPembukuan();

        dataPembukuan.setIdHotel(rs.getString("id_hotel"));
        dataPembukuan.setIdDataPembukuan(rs.getString("id_datapembukuan"));
        dataPembukuan.setUraianDataPembukuan(rs.getString("uraian_datapembukuan"));
        dataPembukuan.setJumlahDataPembukuan(rs.getInt("jumlah_datapembukuan"));
        dataPembukuan.setJumlahTotalDataPembukuan(rs.getInt("jumlah_totaldatapembukuan"));
        dataPembukuan.setSatuanBarang(rs.getString("satuan_barang"));
        dataPembukuan.setTanggalBuat(rs.getString("tanggal_buat"));

        return dataPembukuan;
    }

}
