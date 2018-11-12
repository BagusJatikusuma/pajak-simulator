package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.RestoranDao;
import com.bekasidev.app.dao.impl.RestoranDaoImpl;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.backend.RestoranService;

import java.util.Calendar;
import java.util.List;

public class RestoranServiceImpl implements RestoranService {

    private RestoranDao restoranDao = new RestoranDaoImpl();

    @Override
    public List<WajibPajak> getAllRestoran() {
        return restoranDao.getAllRestoran();
    }

    @Override
    public void createDataRestoran(WajibPajak wajibPajak) {
        Calendar cal = Calendar.getInstance();
        wajibPajak.setIdWajibPajak(Long.toString(cal.getTimeInMillis()));
        wajibPajak.setNamaWajibPajak(wajibPajak.getNamaWajibPajak().toUpperCase());
        restoranDao.createDataRestoran(wajibPajak);
    }

    @Override
    public void deleteRestoran(String idRestoran) {
        restoranDao.deleteRestoranById(idRestoran);
    }
}
