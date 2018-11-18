package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.WajibPajakDao;
import com.bekasidev.app.dao.impl.WajibPajakDaoImpl;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.backend.WajibPajakService;

import java.util.Calendar;
import java.util.List;

public class WajibPajakServiceImpl implements WajibPajakService {

    private WajibPajakDao wajibPajakDao = new WajibPajakDaoImpl();

    @Override
    public List<WajibPajak> getAllWP() {
        return wajibPajakDao.getAllWP();
    }

    @Override
    public void createDataWP(WajibPajak wajibPajak) {
//        Calendar cal = Calendar.getInstance();
//        wajibPajak.setIdWajibPajak(Long.toString(cal.getTimeInMillis()));
        wajibPajak.setNamaWajibPajak(wajibPajak.getNamaWajibPajak().toUpperCase());
        wajibPajakDao.createDataWP(wajibPajak);
    }

    @Override
    public void deleteWP(String idRestoran) {
        wajibPajakDao.deleteWPById(idRestoran);
    }

    @Override
    public WajibPajak getWajibPajakById(String idWp) {
        return wajibPajakDao.getWPById(idWp);
    }
}
