package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.BenchmarkHotel;

import java.util.List;

/**
 * Created by waddi on 05/11/18.
 */
public interface BenchmarkHotelService {

    List<BenchmarkHotel> getAllBenchmark(String idHotel, String idBenchmarkHotel);

    void createBenchmarkHotel(BenchmarkHotel benchmarkHotel);
}
