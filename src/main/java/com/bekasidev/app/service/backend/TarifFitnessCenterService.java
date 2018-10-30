package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.TarifFitnessCenter;

import java.util.List;

/**
 * Created by waddi on 25/10/18.
 */
public interface TarifFitnessCenterService {

    List<TarifFitnessCenter> getAllTarifFitnessCenterByIdHotel(String idHotel);

    TarifFitnessCenter getAllTarifFitnessCenterByIdHotelAndIdTarifFitness(String idHotel, String idTarifFitness);

    void createTarifFitnessCenter(TarifFitnessCenter tarifFitnessCenter);

    void deleteTarifFitnessHotelByIdHotelAndidFitnessCenter(String idHotel, String idFitnessCenter);

}
