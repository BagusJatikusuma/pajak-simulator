package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.WajibPajak;

import java.util.List;

public interface WajibPajakService {

    List<WajibPajak> getAllWP();

    void createDataWP(WajibPajak wajibPajak);

    void deleteWP(String idRestoran);

    WajibPajak getWajibPajakById(String idWp);

    void updateWajibPajak(WajibPajak wajibPajak);

    void replace(String idWP, String namaWP);
}
