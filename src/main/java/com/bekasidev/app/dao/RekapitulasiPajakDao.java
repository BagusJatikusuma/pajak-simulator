package com.bekasidev.app.dao;

import com.bekasidev.app.model.RekapitulasiPajak;

import java.util.List;

/**
 * Created by waddi on 23/11/18.
 */
public interface RekapitulasiPajakDao {

    List<RekapitulasiPajak> getRekapitulasiPajak(String idRekapitulasi);

    void createRekapitulasiPajak(RekapitulasiPajak rekapitulasiPajak);
}
