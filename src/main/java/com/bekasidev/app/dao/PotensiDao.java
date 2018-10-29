package com.bekasidev.app.dao;

import com.bekasidev.app.model.Potensi;

import java.util.List;

public interface PotensiDao {

    List<Potensi> getAllTarif(String idRestoran, String idTransaksi);

    void createTarifMenu(Potensi potensi);
}
