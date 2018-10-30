package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.HotelDao;
import com.bekasidev.app.dao.impl.HotelDaoImpl;
import com.bekasidev.app.model.Hotel;
import com.bekasidev.app.service.backend.HotelService;

import java.util.Calendar;
import java.util.List;

/**
 * Created by waddi on 24/10/18.
 */
public class HotelServiceImpl implements HotelService {

    private HotelDao hotelDao = new HotelDaoImpl();

    public List<Hotel> getAllHotel() { return hotelDao.getAllHotel(); }

    @Override
    public  void createDataHotel(Hotel hotel) {
        Calendar cal = Calendar.getInstance();
        hotel.setIdHotel(Long.toString(cal.getTimeInMillis()));
        hotel.setNamaHotel(hotel.getNamaHotel().toUpperCase());
        hotelDao.createDataHotel(hotel);
    }

    @Override
    public void deleteHotelById(String idHotel) { hotelDao.deleteHotelById(idHotel);}


}
