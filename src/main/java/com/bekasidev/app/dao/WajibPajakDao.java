package com.bekasidev.app.dao;

import com.bekasidev.app.model.WajibPajak;

import java.util.List;

public interface WajibPajakDao {

    List<WajibPajak> getAllWP();

    void createDataWP(WajibPajak wajibPajak);

    void deleteWPById(String idRestoran);

    WajibPajak getWPById(String idWp);

    void updateWp(WajibPajak wajibPajak);
}
