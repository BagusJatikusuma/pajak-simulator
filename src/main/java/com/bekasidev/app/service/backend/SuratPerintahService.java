package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.TimSP;

import java.util.List;

public interface SuratPerintahService {

    SuratPerintah createSuratPerintah(SuratPerintah suratPerintah);

    List<SuratPerintah> getAllSuratPerintah();

    void setNomorUrut(String idSP, String nomorUrut, String tanggal);

    void updateSuratPerintah(SuratPerintah suratPerintah);

    void updateTim(List<TimSP> listTim);

    void deleteSuratPerintah(String idSP);
    
    TimSP getTimSP(String idSP, String idTim);

    List<SuratPerintah> getSuratPerintahByTahun(int tahun);
}
