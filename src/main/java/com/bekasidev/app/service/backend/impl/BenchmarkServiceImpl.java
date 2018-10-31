package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.BenchmarkDao;
import com.bekasidev.app.dao.impl.BenchmarkDaoImpl;
import com.bekasidev.app.model.Benchmark;
import com.bekasidev.app.service.backend.BenchmarkService;

import java.util.Calendar;
import java.util.List;

public class BenchmarkServiceImpl implements BenchmarkService {

    private BenchmarkDao benchmarkDao = new BenchmarkDaoImpl();

    @Override
    public List<Benchmark> getBenchmark(String idRestoran, String idTransaksi) {
        return benchmarkDao.getBenchmark(idRestoran, idTransaksi);
    }

    @Override
    public void createBenchmark(Benchmark benchmark) {
        Calendar cal = Calendar.getInstance();
        benchmark.setTanggalBuat(Long.toString(cal.getTimeInMillis()));
        benchmark.setIdBenchmark(Long.toString(cal.getTimeInMillis()));

        benchmarkDao.createBenchmark(benchmark);
    }
}
