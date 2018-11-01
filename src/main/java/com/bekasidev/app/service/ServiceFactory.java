package com.bekasidev.app.service;

import com.bekasidev.app.model.RestoranTransaction;
import com.bekasidev.app.service.backend.*;
import com.bekasidev.app.service.backend.impl.*;

public class ServiceFactory {

    private static RestoranService restoranService = null;
    private static RestoranTransactionService restoranTransactionService = null;
    private static HotelService hotelService = null;
    private static TarifKamarHotelService tarifKamarHotelService = null;
    private static MenuService menuService = null;

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

    public static MenuService getMenuService(){
        if(menuService == null) menuService = new MenuServiceImpl();
        return menuService;
    }
}
