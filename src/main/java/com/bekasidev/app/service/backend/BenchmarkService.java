package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.Benchmark;

import java.util.List;

public interface BenchmarkService {

    List<Benchmark> getBenchmark(String idRestoran, String idTransaksi);

    void createBenchmark(Benchmark benchmark);
}
