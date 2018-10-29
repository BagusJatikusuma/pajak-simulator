package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.TarifMenu;

import java.util.List;

public interface TarifMenuService {

    List<TarifMenu> getAllTarif(String idRestoran, String idTransaksi);

    void createTarifMenu(TarifMenu tarifMenu);
}
