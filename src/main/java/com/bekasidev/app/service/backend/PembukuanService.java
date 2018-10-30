package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.Pembukuan;

import java.util.List;

public interface PembukuanService {
    List<Pembukuan> getPembukuan(String idRestoran, String idTransaksi);

    void createPembukuan(Pembukuan pembukuan);
}
