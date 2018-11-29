package com.bekasidev.app.service.backend;

import com.bekasidev.app.wrapper.RekapitulasiWrapper;

import java.util.Date;

public interface RekapitulasiService {

    void createRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper);

    RekapitulasiWrapper getRekapitulasi(String idSP, String idWP);

    void calculateRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper, float persentase);

    void setBulanRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper, Date masaPajakAwal, Date masaPajakAkhir);
}
