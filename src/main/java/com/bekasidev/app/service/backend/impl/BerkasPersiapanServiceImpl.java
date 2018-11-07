package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.BerkasPersiapanDao;
import com.bekasidev.app.dao.impl.BerkasPersiapanImpl;
import com.bekasidev.app.model.BerkasPersiapan;
import com.bekasidev.app.service.backend.BerkasPersiapanService;

public class BerkasPersiapanServiceImpl implements BerkasPersiapanService {

    BerkasPersiapanDao berkasPersiapanDao = new BerkasPersiapanImpl();

    @Override
    public BerkasPersiapan getBerkasPersiapan(String idBerkas) {
        return berkasPersiapanDao.getBerkasPersiapan(idBerkas);
    }

    @Override
    public void createBerkasPersiapan(BerkasPersiapan berkasPersiapan) {
        berkasPersiapanDao.createBerkasPersiapan(berkasPersiapan);
    }
}
