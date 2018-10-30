package com.bekasidev.app.dao;

import com.bekasidev.app.model.Benchmark;

import java.util.List;

public interface BenchmarkDao {

    List<Benchmark> getBenchmark(String idRestoran, String idTransaksi);

    void createBenchmark(Benchmark benchmark);

    float getJumlah(String idBenchmark);
}
