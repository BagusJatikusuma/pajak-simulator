package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.BenchmarkHotelDao;
import com.bekasidev.app.dao.impl.BenchmarkHotelDaoImpl;
import com.bekasidev.app.model.BenchmarkHotel;
import com.bekasidev.app.service.backend.BenchmarkHotelService;

import java.util.Calendar;
import java.util.List;

/**
 * Created by waddi on 08/11/18.
 */
public class BenchmarkHotelServiceImpl implements BenchmarkHotelService{

    private BenchmarkHotelDao benchmarkHotelDao = new BenchmarkHotelDaoImpl();

    @Override
    public List<BenchmarkHotel> getAllBenchmark(String idHotel, String idBenchmarkHotel) {
        return benchmarkHotelDao.getAllBenchmark(idHotel,idBenchmarkHotel);
    }

    @Override
    public void createBenchmarkHotel(BenchmarkHotel benchmarkHotel) {
        Calendar cal = Calendar.getInstance();
        benchmarkHotel.setTanggalBuat(Long.toString(cal.getTimeInMillis()));
        benchmarkHotel.setIdBenchmarkHotel(Long.toString(cal.getTimeInMillis()));

        benchmarkHotelDao.createBenchmarkHotel(benchmarkHotel);
    }
}
