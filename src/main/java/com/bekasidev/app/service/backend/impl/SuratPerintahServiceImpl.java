package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.NomorBerkasDao;
import com.bekasidev.app.dao.SuratPerintahDao;
import com.bekasidev.app.dao.impl.NomorBerkasDaoImpl;
import com.bekasidev.app.dao.impl.SuratPerintahDaoImpl;
import com.bekasidev.app.model.NomorBerkas;
import com.bekasidev.app.model.Surat;
import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.TimSP;
import com.bekasidev.app.service.backend.SuratPerintahService;

import java.util.Calendar;
import java.util.List;

public class SuratPerintahServiceImpl implements SuratPerintahService {

    SuratPerintahDao suratPerintahDao = new SuratPerintahDaoImpl();
    NomorBerkasDao nomorBerkasDao = new NomorBerkasDaoImpl();

    @Override
    public SuratPerintah createSuratPerintah(SuratPerintah suratPerintah) {
        Calendar cal = Calendar.getInstance();
        suratPerintah.setIdSP(Long.toString(cal.getTimeInMillis()));
        for(TimSP tim : suratPerintah.getListTim()){
            tim.setIdSP(suratPerintah.getIdSP());
        }

        return suratPerintahDao.createSuratPerintah(suratPerintah);
    }

    @Override
    public List<SuratPerintah> getAllSuratPerintah() {
        return suratPerintahDao.getAllSuratPerintah();
    }

    @Override
    public void setNomorUrut(String idSP, String nomorUrut, String tanggal) {
        suratPerintahDao.setNomorUrut(idSP, nomorUrut, tanggal);
    }

    @Override
    public void updateSuratPerintah(SuratPerintah suratPerintah) {
        List<NomorBerkas> nomorBerkas = nomorBerkasDao.getNomorBerkasBySP(suratPerintah.getIdSP());
        nomorBerkasDao.deleteNomorBySP(suratPerintah.getIdSP());
        suratPerintahDao.updateSuratPerintah(suratPerintah);
        for(NomorBerkas nb : nomorBerkas){
            nomorBerkasDao.setNomorSurat(nb.getIdSP(), nb.getIdWP(),
                    nb.getNomorSuratHasil(), nb.getTanggalSuratHasil(), Surat.HASIL);
            nomorBerkasDao.setNomorSurat(nb.getIdSP(), nb.getIdWP(),
                    nb.getNomorSuratPeminjaman(), nb.getTanggalSuratPeminjaman(), Surat.PEMINJAMAN);
            nomorBerkasDao.setNomorSurat(nb.getIdSP(), nb.getIdWP(),
                    nb.getNomorSuratPemberitahuan(), nb.getTanggalSuratPemberitahuan(), Surat.PEMBERITAHUAN);
            nomorBerkasDao.setNomorSurat(nb.getIdSP(), nb.getIdWP(),
                    nb.getNomorBeritaAcara(), nb.getTanggalBeritaAcara(), Surat.BERITA_ACARA);
            nomorBerkasDao.setNomorSurat(nb.getIdSP(), nb.getIdWP(),
                    nb.getNomorSKPD(), nb.getTanggalSKPD(), Surat.EVALUASI);
            nomorBerkasDao.setNomorSurat(nb.getIdSP(), nb.getIdWP(),
                    nb.getNomorTeguran1(), nb.getTanggalTeguran1(), Surat.TEGURAN_PERTAMA);
            nomorBerkasDao.setBerkasTeguran2(nb.getIdSP(), nb.getIdWP(),
                    nb.getNomorSuratHasil(), nb.getTanggalSuratHasil(), nb.getJamTeguran2(), nb.getTempatTeguran2(),
                    nb.getHariTeguran2());
        }
    }

    @Override
    public void updateTim(List<TimSP> listTim) {
        suratPerintahDao.updateTim(listTim);
    }

    @Override
    public void deleteSuratPerintah(String idSP) {
        suratPerintahDao.deleteSuratPerintah(idSP);
    }

    @Override
    public TimSP getTimSP(String idSP, String idTim) {
        return suratPerintahDao.getTimSP(idSP, idTim);
    }

    @Override
    public List<SuratPerintah> getSuratPerintahByTahun(int tahun) {
        return suratPerintahDao.getSuratPerintahByTahun(tahun);
    }
}
