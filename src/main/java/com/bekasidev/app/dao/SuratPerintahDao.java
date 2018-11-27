package com.bekasidev.app.dao;

import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.TimSP;

import java.util.List;

public interface SuratPerintahDao {

    void createSuratPerintah(SuratPerintah suratPerintah);

    List<SuratPerintah> getAllSuratPerintah();

    void setNomorUrut(String idSP, String nomorUrut, String tanggal);

    void setTim(List<TimSP> timSP);

    List<TimSP> getListTim(String idSP);
}
