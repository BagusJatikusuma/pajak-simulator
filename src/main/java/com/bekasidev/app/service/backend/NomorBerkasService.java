package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.Surat;

public interface NomorBerkasService {
    void setNomorTanggalSKPD(String idSP, String idWP, String nomorSKPD, String tanggalSKPD);
    void setNomorBerkas(String idSP, String idWP, String nomorSurat, String tanggal, Surat jenis);
    void setBerkasTeguran2(String idSP, String idWP, String nomorTeguran, String tanggalTeguran,
                           String jam, String tempat, String hari);
}
