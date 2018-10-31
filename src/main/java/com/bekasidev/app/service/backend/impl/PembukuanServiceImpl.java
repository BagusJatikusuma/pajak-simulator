package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.BenchmarkDao;
import com.bekasidev.app.dao.PembukuanDao;
import com.bekasidev.app.dao.impl.BenchmarkDaoImpl;
import com.bekasidev.app.dao.impl.PembukuanDaoImpl;
import com.bekasidev.app.model.Benchmark;
import com.bekasidev.app.model.Pembukuan;
import com.bekasidev.app.service.backend.PembukuanService;

import java.util.Calendar;
import java.util.List;

import static java.lang.Math.round;

public class PembukuanServiceImpl implements PembukuanService {

    private PembukuanDao pembukuanDao = new PembukuanDaoImpl();
    private BenchmarkDao benchmarkDao = new BenchmarkDaoImpl();

    @Override
    public List<Pembukuan> getPembukuan(String idRestoran, String idTransaksi) {
        return pembukuanDao.getPembukuan(idRestoran, idTransaksi);
    }

    @Override
    public void createPembukuan(Pembukuan pembukuan) {
        Calendar cal = Calendar.getInstance();
        pembukuan.setTanggalBuat(Long.toString(cal.getTimeInMillis()));
        Benchmark benchmark = benchmarkDao.getBenchmarkById(pembukuan.getIdBenchmark());
        pembukuan.setPotensiPorsi(round(benchmark.getPorsi()*pembukuan.getJumlah()/benchmark.getJumlah()));
        pembukuanDao.createPembukuan(pembukuan);
    }
}
