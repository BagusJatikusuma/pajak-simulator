package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.DataPembukuan;

import java.util.List;

/**
 * Created by waddi on 31/10/18.
 */
public interface DataPembukuanService {

    List<DataPembukuan> getAllDataPembukuan(String idHotel, String idDataPembukuan);

    void createDataPembukuan(DataPembukuan dataPembukuan);
}
