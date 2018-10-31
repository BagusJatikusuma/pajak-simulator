package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.Potensi;
import com.bekasidev.app.wrapper.SptpdWrapper;

import java.util.List;

public interface PotensiService {

    SptpdWrapper getAllPotensi(String idRestoran, String idTransaksi);

    void createPotensi(List<Potensi> potensi);

    void calculatePenjualan(List<Potensi> listPotensi);

//    void calculatePotensi(List<SptpdWrapper> sptpdWrappers);
}
