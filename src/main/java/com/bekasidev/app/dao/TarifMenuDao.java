package com.bekasidev.app.dao;

import com.bekasidev.app.model.TarifMenu;

import java.util.List;

public interface TarifMenuDao {

    List<TarifMenu> getAllTarif(String idRestoran, String idTransaksi);

    void createTarifMenu(TarifMenu tarifMenu);
}
