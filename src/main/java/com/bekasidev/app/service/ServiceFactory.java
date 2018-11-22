package com.bekasidev.app.service;

import com.bekasidev.app.service.backend.*;
import com.bekasidev.app.service.backend.impl.*;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.service.reportservice.reportserviceimpl.ReportServiceImpl;

public class ServiceFactory {

    private static WajibPajakService wajibPajakService = null;
    private static RestoranTransactionService restoranTransactionService = null;
    private static HotelService hotelService = null;
    private static TarifKamarHotelService tarifKamarHotelService = null;
    private static PegawaiService pegawaiService = null;
    private static MenuService menuService = null;
    private static BerkasPersiapanService berkasPersiapanService = null;
    private static ReportService reportService = null;
    private static SuratPerintahService suratPerintahService = null;

    public static SuratPerintahService getSuratPerintahService(){
        if(suratPerintahService == null) suratPerintahService = new SuratPerintahServiceImpl();
        return suratPerintahService;
    }

    public static BerkasPersiapanService getBerkasPersiapanService(){
        if(berkasPersiapanService == null) berkasPersiapanService = new BerkasPersiapanServiceImpl();
        return berkasPersiapanService;
    }

    public static PegawaiService getPegawaiService(){
        if(pegawaiService == null) pegawaiService = new PegawaiServiceImpl();
        return pegawaiService;
    }

    public static WajibPajakService getWajibPajakService(){
        if(wajibPajakService == null) wajibPajakService = new WajibPajakServiceImpl();
        return wajibPajakService;
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
    
    public static ReportService getReportService(){
        if(reportService == null) reportService = new ReportServiceImpl();
        return reportService;
    }
}
