package com.bekasidev.app.dao;

import com.bekasidev.app.model.Rekapitulasi;
import com.bekasidev.app.wrapper.RekapitulasiExport;
import com.bekasidev.app.wrapper.RekapitulasiWrapper;

import java.util.List;

public interface RekapitulasiDao {

    void createRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper);

    RekapitulasiWrapper getRekapitulasi(String idSP, String idWP);

    List<RekapitulasiExport> getAllRekapitulasi();
}
