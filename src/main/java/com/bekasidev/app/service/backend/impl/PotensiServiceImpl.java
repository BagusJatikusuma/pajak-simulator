package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.PotensiDao;
import com.bekasidev.app.dao.impl.PotensiDaoImpl;
import com.bekasidev.app.model.Potensi;
import com.bekasidev.app.service.backend.PotensiService;

import java.util.List;

public class PotensiServiceImpl implements PotensiService {

    private PotensiDao potensiDao = new PotensiDaoImpl();

    @Override
    public List<Potensi> getAllTarif(String idRestoran, String idTransaksi) {
        return potensiDao.getAllTarif(idRestoran, idTransaksi);
    }

    @Override
    public void createTarifMenu(Potensi potensi) {
        potensiDao.createTarifMenu(potensi);
    }
}
