package com.bekasidev.app.dao;

import com.bekasidev.app.model.TarifKamarHotel;

import java.util.List;

/**
 * Created by waddi on 24/10/18.
 */
public interface TarifKamarHotelDao {

    List<TarifKamarHotel> getAllTarifKamarHotelByIdHotel(String idHotel);

    TarifKamarHotel getAllTarifKamarHotelByIdHotelAndIdKamarHotel(String idHotel, String idKamarHotel);
}
