package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.TarifKamarHotel;

import java.util.List;

/**
 * Created by waddi on 25/10/18.
 */
public interface TarifKamarHotelService {

    List<TarifKamarHotel> getAllTarifKamarHotelByIdHotel(String idHotel);

    TarifKamarHotel getAllTarifKamarHotelByIdHotelAndIdKamarHotel(String idHotel, String idKamarHotel);
}
