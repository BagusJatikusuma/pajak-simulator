package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.NomorBerkasDao;
import com.bekasidev.app.dao.impl.NomorBerkasDaoImpl;
import com.bekasidev.app.model.Surat;
import com.bekasidev.app.service.backend.NomorBerkasService;

public class NomorBerkasServiceImpl implements NomorBerkasService {

    NomorBerkasDao nomorBerkasDao = new NomorBerkasDaoImpl();

    @Override
    public void setNomorBerkas(String idSP, String idWP, String nomorSurat, String tanggal, Surat jenis) {
        nomorBerkasDao.setNomorSurat(idSP, idWP, nomorSurat, tanggal, jenis);
    }
}
