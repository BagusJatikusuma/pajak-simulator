package com.bekasidev.app.dao;

import com.bekasidev.app.model.SuratPerintah;

import java.util.List;

public interface SuratPerintahDao {

    void createSuratPerintah(SuratPerintah suratPerintah);

    List<SuratPerintah> getAllSuratPerintah();

    void setNomorUrut(String idSP, String nomorUrut);
}
