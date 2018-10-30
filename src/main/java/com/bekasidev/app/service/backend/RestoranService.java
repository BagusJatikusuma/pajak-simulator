package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.Restoran;

import java.util.List;

public interface RestoranService {

    List<Restoran> getAllRestoran();

    void createDataRestoran(Restoran restoran);

    void deleteRestoran(String idRestoran);
}
