package com.bekasidev.app.service.backend;

import com.bekasidev.app.wrapper.RekapitulasiWrapper;

import java.util.Date;

public interface RekapitulasiService {

    void createRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper);

    RekapitulasiWrapper getRekapitulasi(String idSP, String idWP);

    void calculateRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper);

    void createBulanRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper, Date masaPajakAwal, Date masaPajakAkhir);
}
