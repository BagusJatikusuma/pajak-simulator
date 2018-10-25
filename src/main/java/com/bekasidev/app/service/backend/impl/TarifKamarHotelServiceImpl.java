package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.TarifKamarHotelDao;
import com.bekasidev.app.dao.impl.TarifKamarHotelDaoImpl;
import com.bekasidev.app.model.TarifKamarHotel;
import com.bekasidev.app.service.backend.TarifKamarHotelService;

import java.util.List;

/**
 * Created by waddi on 25/10/18.
 */
public class TarifKamarHotelServiceImpl implements TarifKamarHotelService {

    private TarifKamarHotelDao tarifKamarHotelDao = new TarifKamarHotelDaoImpl();

    @Override
    public List<TarifKamarHotel> getAllTarifKamarHotelByIdHotel(String idHotel) {
        return tarifKamarHotelDao.getAllTarifKamarHotelByIdHotel(idHotel);
    }

    @Override
    public TarifKamarHotel getAllTarifKamarHotelByIdHotelAndIdKamarHotel(String idHotel, String idKamarHotel) {
        return tarifKamarHotelDao.getAllTarifKamarHotelByIdHotelAndIdKamarHotel(idHotel, idKamarHotel);
    }

}
