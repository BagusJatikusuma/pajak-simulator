package com.bekasidev.app.wrapper;

import com.bekasidev.app.model.SuratPerintah;

import java.util.List;

public class ExportDokumenWrapper {

    List<SuratPerintah> listSp;
    List<RekapitulasiWrapper> listRekapitulasi;

    public ExportDokumenWrapper(List<SuratPerintah> listSp, List<RekapitulasiWrapper> listRekapitulasi) {
        this.listSp = listSp;
        this.listRekapitulasi = listRekapitulasi;
    }

    public List<SuratPerintah> getListSp() {
        return listSp;
    }

    public void setListSp(List<SuratPerintah> listSp) {
        this.listSp = listSp;
    }

    public List<RekapitulasiWrapper> getListRekapitulasi() {
        return listRekapitulasi;
    }

    public void setListRekapitulasi(List<RekapitulasiWrapper> listRekapitulasi) {
        this.listRekapitulasi = listRekapitulasi;
    }
}
