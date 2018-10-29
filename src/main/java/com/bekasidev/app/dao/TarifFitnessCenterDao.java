package com.bekasidev.app.dao;

import com.bekasidev.app.model.TarifFitnessCenter;

import java.util.List;

/**
 * Created by waddi on 25/10/18.
 */
public interface TarifFitnessCenterDao {

    List<TarifFitnessCenter> getAllTarifFitnessCenterByIdHotel(String idHotel);

    TarifFitnessCenter getAllTarifFitnessCenterByIdHotelAndIdTarifFitness(String idHotel, String idTarifFitness);
}
