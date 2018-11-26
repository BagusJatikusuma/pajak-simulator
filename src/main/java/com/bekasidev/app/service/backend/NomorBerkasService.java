package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.Surat;

public interface NomorBerkasService {
    void setNomorBerkas(String idSP, String idWP, String nomorSurat, String tanggal, Surat jenis);
}
