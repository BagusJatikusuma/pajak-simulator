package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.SuratPerintahDao;
import com.bekasidev.app.dao.impl.SuratPerintahDaoImpl;
import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.service.backend.SuratPerintahService;

import java.util.Calendar;
import java.util.List;

public class SuratPerintahServiceImpl implements SuratPerintahService {

    SuratPerintahDao suratPerintahDao = new SuratPerintahDaoImpl();

    @Override
    public void createSuratPerintah(SuratPerintah suratPerintah) {
        Calendar cal = Calendar.getInstance();
        suratPerintah.setIdSP(Long.toString(cal.getTimeInMillis()));

        suratPerintahDao.createSuratPerintah(suratPerintah);
    }

    @Override
    public List<SuratPerintah> getAllSuratPerintah() {
        return suratPerintahDao.getAllSuratPerintah();
    }

    @Override
    public void setNomorUrut(String idSP, String nomorUrut) {
        suratPerintahDao.setNomorUrut(idSP, nomorUrut);
    }
}
