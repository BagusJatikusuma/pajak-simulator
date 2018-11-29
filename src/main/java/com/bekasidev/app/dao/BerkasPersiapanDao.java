package com.bekasidev.app.dao;

import com.bekasidev.app.model.BerkasPersiapan;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.wrapper.DokumenPersiapanWrapper;

public interface BerkasPersiapanDao {

    void getBerkasPersiapan(String idSP, WajibPajak wajibPajak);

    void createBerkasPersiapan(WajibPajak wajibPajak, String idSP);

    void updateBerkasPersiapan(WajibPajak wajibPajak, String idSP);
}
