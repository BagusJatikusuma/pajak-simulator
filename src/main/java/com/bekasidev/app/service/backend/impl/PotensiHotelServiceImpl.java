package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.PotensiHotelDao;
import com.bekasidev.app.dao.impl.PotensiHotelDaoImpl;
import com.bekasidev.app.model.PotensiHotel;
import com.bekasidev.app.service.backend.PotensiHotelService;

import java.util.Calendar;
import java.util.List;

/**
 * Created by waddi on 14/11/18.
 */
public class PotensiHotelServiceImpl implements PotensiHotelService {

    private PotensiHotelDao potensiHotelDao = new PotensiHotelDaoImpl();

    @Override
    public List<PotensiHotel> getAllPotensiHotel(String idHotel, String idPotensiHotel) {
        return potensiHotelDao.getAllPotensiHotel(idHotel,idPotensiHotel);
    }

    @Override
    public void createPotensiHotel(PotensiHotel potensiHotel){
        Calendar cal = Calendar.getInstance();
        potensiHotel.setTanggalBuat(Long.toString(cal.getTimeInMillis()));
        potensiHotel.setIdPotensiHotel(Long.toString(cal.getTimeInMillis()));
        potensiHotelDao.createPotensiHotel(potensiHotel);
    }
}
