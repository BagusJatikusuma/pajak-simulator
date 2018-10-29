package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.PembukuanDao;
import com.bekasidev.app.dao.impl.PembukuanDaoImpl;
import com.bekasidev.app.model.Pembukuan;
import com.bekasidev.app.service.backend.PembukuanService;

import java.util.Calendar;
import java.util.List;

public class PembukuanServiceImpl implements PembukuanService {

    private PembukuanDao pembukuanDao = new PembukuanDaoImpl();

    @Override
    public List<Pembukuan> getPembukuan(String idRestoran, String idTransaksi) {
        return pembukuanDao.getPembukuan(idRestoran, idTransaksi);
    }

    @Override
    public void createPembukuan(Pembukuan pembukuan) {
        Calendar cal = Calendar.getInstance();
        pembukuan.setTanggalBuat(Long.toString(cal.getTimeInMillis()));

        pembukuanDao.createPembukuan(pembukuan);
    }
}
