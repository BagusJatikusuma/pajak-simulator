package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.HotelDao;
import com.bekasidev.app.dao.impl.HotelDaoImpl;
import com.bekasidev.app.model.Hotel;
import com.bekasidev.app.service.backend.HotelService;

import java.util.List;

/**
 * Created by waddi on 24/10/18.
 */
public class HotelServiceImpl implements HotelService {

    private HotelDao hotelDao = new HotelDaoImpl();

    public List<Hotel> getAllHotel() { return hotelDao.getAllHotel(); }
}
