package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.Tim;

import java.util.List;

public interface PegawaiService {
    List<Pegawai> getAllPegawai();

    List<Pegawai> getPegawaiByTim(String idTim);

    void createPegawai(Pegawai pegawai);

    void setPegawaiTim(String nipPegawai, String jabatanTim, String idTim);

    void createTim(Tim tim);

    List<Tim> getAllTim();
}
