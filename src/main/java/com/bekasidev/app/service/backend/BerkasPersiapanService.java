package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.BerkasPersiapan;
import com.bekasidev.app.model.DokumenPinjaman;
import com.bekasidev.app.model.WP;
import com.bekasidev.app.wrapper.DokumenPersiapanWrapper;

import java.util.List;

public interface BerkasPersiapanService {

    DokumenPersiapanWrapper getBerkasPersiapan(String idBerkas);

    void createBerkasPersiapan(BerkasPersiapan berkasPersiapan);

    void getDokumenPinjaman(BerkasPersiapan berkasPersiapan, WP wp);
}
