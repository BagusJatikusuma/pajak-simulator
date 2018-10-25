package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.TarifMenuDao;
import com.bekasidev.app.dao.impl.TarifMenuDaoImpl;
import com.bekasidev.app.model.TarifMenu;
import com.bekasidev.app.service.backend.TarifMenuService;

import java.util.List;

public class TarifMenuServiceImpl implements TarifMenuService {

    private TarifMenuDao tarifMenuDao = new TarifMenuDaoImpl();

    @Override
    public List<TarifMenu> getAllTarif(String idRestoran, String idTransaksi) {
        return tarifMenuDao.getAllTarif(idRestoran, idTransaksi);
    }

    @Override
    public void createTarifMenu(TarifMenu tarifMenu) {
        tarifMenuDao.createTarifMenu(tarifMenu);
    }
}
