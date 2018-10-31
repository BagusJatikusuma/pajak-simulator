package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.DataPembukuanDao;
import com.bekasidev.app.dao.impl.DataPembukuanDaoImpl;
import com.bekasidev.app.model.DataPembukuan;

import java.util.Calendar;
import java.util.List;

/**
 * Created by waddi on 31/10/18.
 */
public class DataPembukuanServiceImpl implements DataPembukuanDao{


    private DataPembukuanDao dataPembukuanDao = new DataPembukuanDaoImpl();

    @Override
    public List<DataPembukuan> getAllDataPembukuan(String idHotel, String idDataPembukuan) {
        return dataPembukuanDao.getAllDataPembukuan(idHotel, idDataPembukuan);
    }

    @Override
    public void createDataPembukuan(DataPembukuan dataPembukuan) {
        Calendar cal = Calendar.getInstance();
        dataPembukuan.setIdDataPembukuan(Long.toString(cal.getTimeInMillis()));
        dataPembukuanDao.createDataPembukuan(dataPembukuan);
    }
}
