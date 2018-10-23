package com.bekasidev.app.dao;

import com.bekasidev.app.model.HotelTransaction;

import java.util.List;

public interface HotelDao {

    List<HotelTransaction> getAllHotel();

    HotelTransaction getHotelById(String id);
}
