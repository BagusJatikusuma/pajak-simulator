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
        pegawai.setNipPegawai(setNip(pegawai.getNipPegawai()));
        pegawaiDao.createPegawai(pegawai);
    }

    @Override
    public void setPegawaiTim(String nipPegawai, String jabatanTim, String idTim) {
        pegawaiDao.setPegawaiTim(nipPegawai, idTim, jabatanTim);
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

    @Override
    public void updatePegawai(Pegawai pegawai, String nip) {
        if(pegawai.getNipPegawai().length() != nip.length()) pegawai.setNipPegawai(setNip(pegawai.getNipPegawai()));
        pegawaiDao.updatePegawai(pegawai, nip);
    }

    @Override
    public void updateTim(Tim tim) {
        pegawaiDao.updateTim(tim);
    }

    @Override
    public void deletePegawai(String nipPegawai) {
        pegawaiDao.deletePegawai(nipPegawai);
    }

    @Override
    public void deleteTim(String idTim) {
        pegawaiDao.deleteTim(idTim);
    }

    @Override
    public void deletePegawaiFromTim(String idPegawai, String idTim) {
        pegawaiDao.deletePegawaiFromTim(idPegawai, idTim);
    }

    private String setNip(String nip){
        StringBuilder sb = new StringBuilder();
        sb.append(nip);
        sb.insert(8," ");
        sb.insert(15," ");
        sb.insert(17," ");
        return sb.toString();
    }
}
