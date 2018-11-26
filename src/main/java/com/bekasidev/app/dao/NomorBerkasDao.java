package com.bekasidev.app.dao;

import com.bekasidev.app.model.NomorBerkas;
import com.bekasidev.app.model.Surat;

public interface NomorBerkasDao {

    NomorBerkas getNomotBerkas(String idSP, String idWp);
    void setNomorSurat(String idSP, String idWP, String nomorSurat, String tanggal, Surat jenis);
    void createNomorSurat(String idSP, String idWP);
}
