package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.Potensi;

import java.util.List;

public interface PotensiService {

    List<Potensi> getAllTarif(String idRestoran, String idTransaksi);

    void createTarifMenu(Potensi potensi);
}
