package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.SuratPerintah;

import java.util.List;

public interface SuratPerintahService {

    void createSuratPerintah(SuratPerintah suratPerintah);

    List<SuratPerintah> getAllSuratPerintah();

    void setNomorUrut(String idSP, String nomorUrut, String tanggal);
}
