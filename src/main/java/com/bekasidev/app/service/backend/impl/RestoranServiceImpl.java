package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.RestoranDao;
import com.bekasidev.app.dao.impl.RestoranDaoImpl;
import com.bekasidev.app.model.Restoran;
import com.bekasidev.app.service.backend.RestoranService;

import java.util.Calendar;
import java.util.List;

public class RestoranServiceImpl implements RestoranService {

    private RestoranDao restoranDao = new RestoranDaoImpl();

    @Override
    public List<Restoran> getAllRestoran() {
        return restoranDao.getAllRestoran();
    }

    @Override
    public void createDataRestoran(Restoran restoran) {
        Calendar cal = Calendar.getInstance();
        restoran.setIdRestoran(Long.toString(cal.getTimeInMillis()));
        restoran.setNamaRestoran(restoran.getNamaRestoran().toUpperCase());
        restoranDao.createDataRestoran(restoran);
    }
}
