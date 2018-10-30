package com.bekasidev.app.dao;

import com.bekasidev.app.model.Pembukuan;

import java.util.List;

public interface PembukuanDao {
    List<Pembukuan> getPembukuan(String idRestoran, String idTransaksi);

    void createPembukuan(Pembukuan pembukuan);
}
