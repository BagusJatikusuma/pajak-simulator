package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.PegawaiDao;
import com.bekasidev.app.dao.impl.PegawaiDaoImpl;
import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.Tim;
import com.bekasidev.app.service.backend.PegawaiService;

import java.util.Calendar;
import java.util.List;

public class PegawaiServiceImpl implements PegawaiService {

    PegawaiDao pegawaiDao = new PegawaiDaoImpl();

    @Override
    public List<Pegawai> getAllPegawai() {
        return pegawaiDao.getAllPegawai();
    }

    @Override
    public List<Pegawai> getPegawaiByTim(String idTim) {
        return pegawaiDao.getPegawaiByTim(idTim);
    }

    @Override
    public void createPegawai(Pegawai pegawai) {
        pegawaiDao.createPegawai(pegawai);
    }

    @Override
    public void setPegawaiTim(String nipPegawai, String idTim) {
        pegawaiDao.setPegawaiTim(nipPegawai, idTim);
    }

    @Override
    public void createTim(Tim tim) {
        Calendar cal = Calendar.getInstance();
        tim.setIdTim(Long.toString(cal.getTimeInMillis()));

        pegawaiDao.createTim(tim);
    }

    @Override
    public List<Tim> getAllTim() {
        return pegawaiDao.getAllTim();
    }
}
