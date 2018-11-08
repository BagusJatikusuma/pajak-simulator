package com.bekasidev.app.dao;

import com.bekasidev.app.model.BenchmarkHotel;

import java.util.List;

/**
 * Created by waddi on 01/11/18.
 */
public interface BenchmarkHotelDao {

    List<BenchmarkHotel> getAllBenchmark(String idHotel, String idBenchmarkHotel);

    void createBenchmarkHotel(BenchmarkHotel benchmarkHotel);
}
