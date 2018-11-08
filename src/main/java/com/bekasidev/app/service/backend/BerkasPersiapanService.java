package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.BerkasPersiapan;

public interface BerkasPersiapanService {

    BerkasPersiapan getBerkasPersiapan(String idBerkas);

    void createBerkasPersiapan(BerkasPersiapan berkasPersiapan);
}
