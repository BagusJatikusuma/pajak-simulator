package com.bekasidev.app.dao;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.Tim;

import java.util.List;

public interface PegawaiDao {

    List<Pegawai> getAllPegawai();

    List<Pegawai> getPegawaiByTim(String idTim);

    void createPegawai(Pegawai pegawai);

    void setPegawaiTim(String nipPegawai, String idTim, String jabatan);

    void createTim(Tim tim);

    List<Tim> getAllTim();
}
