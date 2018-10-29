package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.BenchmarkDao;
import com.bekasidev.app.model.Benchmark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BenchmarkDaoImpl implements BenchmarkDao {

    @Override
    public List<Benchmark> getBenchmark(String idRestoran, String idTransaksi) {
        String sql = "SELECT * FROM benchmark WHERE id_resotran=? AND id_transaksi=?";
        List<Benchmark> benchmarks = new ArrayList<>();

        try (Connection conn = Connect.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, idRestoran);
            pstm.setString(2, idTransaksi);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                benchmarks.add(setBenchmark(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return benchmarks;
    }

    @Override
    public void createBenchmark(Benchmark benchmark) {
        String sql = "INSERT INTO benchmark VALUES(?,?,?,?,?,?,?,?,?)";

        try (Connection conn = Connect.connect();
                PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, benchmark.getIdRestoran());
            pstm.setString(2, benchmark.getIdTransaksi());
            pstm.setString(3, benchmark.getIdBenchmark());
            pstm.setString(4, benchmark.getDeskripsi());
            pstm.setInt(5, benchmark.getPorsi());
            pstm.setFloat(6, benchmark.getJumlah());
            pstm.setString(7, benchmark.getSatuanJumlah());
            pstm.setString(8, benchmark.getTanggalBuat());
            pstm.setString(9, benchmark.getLabel());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Benchmark setBenchmark(ResultSet rs) throws SQLException {
        Benchmark benchmark = new Benchmark();

        benchmark.setIdRestoran(rs.getString("id_restoran"));
        benchmark.setIdTransaksi(rs.getString("id_transaksi"));
        benchmark.setDeskripsi(rs.getString("deskripsi"));
        benchmark.setPorsi(rs.getInt("jumlah_porsi"));
        benchmark.setJumlah(rs.getInt("jumlah_bahan"));
        benchmark.setSatuanJumlah(rs.getString("satuan_jumlah_bahan"));
        benchmark.setTanggalBuat(rs.getString("tanggal_buat"));
        benchmark.setLabel(rs.getString("label"));
        benchmark.setIdBenchmark(rs.getString("id_benchmark"));

        return benchmark;
    }
}
