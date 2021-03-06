package com.bekasidev.app.dao;

import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.TimSP;

import java.util.List;

public interface SuratPerintahDao {

    SuratPerintah createSuratPerintah(SuratPerintah suratPerintah);

    List<SuratPerintah> getAllSuratPerintah();

    void setNomorUrut(String idSP, String nomorUrut, String tanggal);

    void setTim(List<TimSP> timSP);

    List<TimSP> getListTim(String idSP);

    void updateTim(List<TimSP> timSP);

    void updateSuratPerintah(SuratPerintah suratPerintah);

    void deleteSuratPerintah(String idSP);
    
    TimSP getTimSP(String idSP, String idTim);

    List<SuratPerintah> getSuratPerintahByTahun(int tahun);

    List<SuratPerintah> getSPForExport();
}
