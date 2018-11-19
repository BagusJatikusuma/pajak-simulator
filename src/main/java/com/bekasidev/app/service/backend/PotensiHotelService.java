package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.PotensiHotel;

import java.util.List;

/**
 * Created by waddi on 14/11/18.
 */
public interface PotensiHotelService {

    List<PotensiHotel> getAllPotensiHotel(String idHotel, String idPotensiHotel);

    void createPotensiHotel(PotensiHotel potensiHotel);
}
