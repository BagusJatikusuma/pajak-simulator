package com.bekasidev.app.dao;

import com.bekasidev.app.model.BerkasPersiapan;

public interface BerkasPersiapanDao {

    BerkasPersiapan getBerkasPersiapan(String idBerkas);

    void createBerkasPersiapan(BerkasPersiapan berkasPersiapan);
}
