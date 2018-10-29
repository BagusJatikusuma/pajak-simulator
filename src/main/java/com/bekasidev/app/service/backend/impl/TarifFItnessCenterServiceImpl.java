package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.TarifFitnessCenterDao;
import com.bekasidev.app.dao.impl.TarifFitnessCenterDaoImpl;
import com.bekasidev.app.model.TarifFitnessCenter;
import com.bekasidev.app.service.backend.TarifFitnessCenterService;

import java.util.List;

/**
 * Created by waddi on 25/10/18.
 */
public class TarifFItnessCenterServiceImpl implements TarifFitnessCenterService{

    private TarifFitnessCenterDao tarifFitnessCenterDao = new TarifFitnessCenterDaoImpl();

    @Override
    public List<TarifFitnessCenter> getAllTarifFitnessCenterByIdHotel(String idHotel){
        return tarifFitnessCenterDao.getAllTarifFitnessCenterByIdHotel(idHotel);
    }

    @Override
    public TarifFitnessCenter getAllTarifFitnessCenterByIdHotelAndIdTarifFitness(String idHotel, String idTarifFitness) {
        return tarifFitnessCenterDao.getAllTarifFitnessCenterByIdHotelAndIdTarifFitness(idHotel,idTarifFitness);
    }
}