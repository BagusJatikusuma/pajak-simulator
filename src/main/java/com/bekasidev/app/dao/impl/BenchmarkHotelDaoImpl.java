package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.BenchmarkHotelDao;
import com.bekasidev.app.model.BenchmarkHotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by waddi on 05/11/18.
 */
public class BenchmarkHotelDaoImpl implements BenchmarkHotelDao{

    public List<BenchmarkHotel> getAllBenchmark(String idHotel, String idBenchmarkHotel) {
        String sql = "SELECT * FROM benchmarkhotel WHERE id_hotel=? AND id_benchmarhotel=?";
        List<BenchmarkHotel> benchmarkHotels = new ArrayList<>();

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, idHotel);
            pstm.setString(2, idBenchmarkHotel);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                benchmarkHotels.add(setBenchmarkHotel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return benchmarkHotels;
    }


    @Override
    public void createBenchmarkHotel(BenchmarkHotel benchmarkHotel) {
        String sql = "INSERT INTO benchmarkhotel VALUES(?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, benchmarkHotel.getIdHotel());
            pstm.setString(2, benchmarkHotel.getIdBenchmarkHotel());
            pstm.setString(3, benchmarkHotel.getIdDataPembukuan());
            pstm.setInt(4, benchmarkHotel.getJumlahDataPembukuan());
            pstm.setInt(5, benchmarkHotel.getJumlahTerpakai());
            pstm.setString(6, benchmarkHotel.getLabelBarang());
            pstm.setString(7, benchmarkHotel.getLabelFasilitas());
            pstm.setString(8, benchmarkHotel.getSatuan());
            pstm.setString(9, benchmarkHotel.getTanggalBuat());
            pstm.setString(10, benchmarkHotel.getUraianDataPembukuan());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private BenchmarkHotel setBenchmarkHotel(ResultSet rs) throws SQLException {
        BenchmarkHotel benchmarkHotel = new BenchmarkHotel();

        benchmarkHotel.setIdHotel(rs.getString("id_hotel"));
        benchmarkHotel.setIdBenchmarkHotel(rs.getString("id_benchmarkhotel"));
        benchmarkHotel.setIdDataPembukuan(rs.getString("id_datapembukuan"));
        benchmarkHotel.setJumlahDataPembukuan(rs.getInt("jumlah_datapembukuan"));
        benchmarkHotel.setJumlahTerpakai(rs.getInt("jumlah_terpakai"));
        benchmarkHotel.setLabelBarang(rs.getString("label_barang"));
        benchmarkHotel.setLabelFasilitas(rs.getString("label_fasilitas"));
        benchmarkHotel.setSatuan(rs.getString("satuan"));
        benchmarkHotel.setTanggalBuat(rs.getString("tanggal_buat"));
        benchmarkHotel.setUraianDataPembukuan(rs.getString("uraian_datapembukuan"));

        return benchmarkHotel;
    }
}
