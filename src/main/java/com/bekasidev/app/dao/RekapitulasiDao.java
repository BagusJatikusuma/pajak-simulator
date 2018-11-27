package com.bekasidev.app.dao;

import com.bekasidev.app.wrapper.RekapitulasiWrapper;

public interface RekapitulasiDao {

    void createRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper);

    RekapitulasiWrapper getRekapitulasi(String idSP, String idWP);
}
