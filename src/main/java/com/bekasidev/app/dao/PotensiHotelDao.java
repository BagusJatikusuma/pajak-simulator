package com.bekasidev.app.dao;

import com.bekasidev.app.model.PotensiHotel;

import java.util.List;

/**
 * Created by waddi on 12/11/18.
 */
public interface PotensiHotelDao {

    List<PotensiHotel> getAllPotensiHotel(String idHotel, String idPotensiHotel);

    void createPotensiHotel(PotensiHotel potensiHotel);
}
