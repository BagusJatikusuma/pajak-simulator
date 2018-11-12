package com.bekasidev.app.dao;

import com.bekasidev.app.model.BerkasPersiapan;
import com.bekasidev.app.wrapper.DokumenPersiapanWrapper;

public interface BerkasPersiapanDao {

    DokumenPersiapanWrapper getBerkasPersiapan(String idBerkas);

    void createBerkasPersiapan(BerkasPersiapan berkasPersiapan);
}
