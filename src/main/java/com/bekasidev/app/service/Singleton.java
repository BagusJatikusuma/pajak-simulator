package com.bekasidev.app.service;

import com.bekasidev.app.model.RestoranTransaction;
import com.bekasidev.app.service.backend.HotelService;
import com.bekasidev.app.service.backend.RestoranService;
import com.bekasidev.app.service.backend.RestoranTransactionService;
import com.bekasidev.app.service.backend.TarifKamarHotelService;
import com.bekasidev.app.service.backend.impl.HotelServiceImpl;
import com.bekasidev.app.service.backend.impl.RestoranServiceImpl;
import com.bekasidev.app.service.backend.impl.RestoranTransactionServiceImpl;
import com.bekasidev.app.service.backend.impl.TarifKamarHotelServiceImpl;

public class Singleton {

    private static RestoranService restoranService = null;
    private static RestoranTransactionService restoranTransactionService = null;
    private static HotelService hotelService = null;
    private static TarifKamarHotelService tarifKamarHotelService = null;

    public static RestoranService getRestoranService(){
        if(restoranService == null) restoranService = new RestoranServiceImpl();
        return restoranService;
    }

    public static RestoranTransactionService getRestoranTransactionService(){
        if(restoranTransactionService == null) restoranTransactionService = new RestoranTransactionServiceImpl();
        return restoranTransactionService;
    }

    public static HotelService getHotelService(){
        if(hotelService == null) hotelService = new HotelServiceImpl();
        return hotelService;
    }

    public static TarifKamarHotelService getTarifKamarHotelService(){
        if(tarifKamarHotelService == null) tarifKamarHotelService = new TarifKamarHotelServiceImpl();
        return tarifKamarHotelService;
    }
}
