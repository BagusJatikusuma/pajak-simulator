package com.bekasidev.app.dao;

import com.bekasidev.app.model.Potensi;
import com.bekasidev.app.wrapper.PotensiJoinWrapper;

import java.util.List;

public interface PotensiDao {

    List<Potensi> getAllPotensi(String idRestoran, String idTransaksi);

    void createPotensi(Potensi potensi);

    List<PotensiJoinWrapper> getPotensiJoinMenu(String idRestoran, String idTransaksi);
}
