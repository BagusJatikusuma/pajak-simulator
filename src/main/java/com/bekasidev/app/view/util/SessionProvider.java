/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.util;

import com.bekasidev.app.model.RestoranTransaction;
import java.util.HashMap;
import java.util.Map;

/**
 *  
 * @author bagus
 */
public class SessionProvider {
    private static RestoranTransaction restoranTransaction;
    private static Map<String, Object> sessionPajakMap;
    private static Map<String, Object> sessionAturAnggotaTimUIMap;
    
    public static RestoranTransaction getRestoranTransaction() {
        if (restoranTransaction == null) resetRestoranTranstion();
        return restoranTransaction;
    }
    
    public static Map<String, Object> getPajakMapSession() {
        if (sessionPajakMap == null) sessionPajakMap = new HashMap<>();
        return sessionPajakMap;
    }
    
    public static Map<String, Object> getSessionAturAnggotaTimUIMap() {
        if (sessionAturAnggotaTimUIMap == null) sessionAturAnggotaTimUIMap = new HashMap<>();
        return sessionAturAnggotaTimUIMap;
    }
    
    public static void resetRestoranTranstion() {
        if (restoranTransaction != null) {
            restoranTransaction.setFrekuensiRamai(0);
            restoranTransaction.setFrekuensiSepi(0);
            restoranTransaction.setFrekuensiTotal(0);
            restoranTransaction.setFrekuesniNormal(0);
            restoranTransaction.setIdRestoran(null);
            restoranTransaction.setIdTransaction(null);
            restoranTransaction.setOmzetNormal(0);
            restoranTransaction.setOmzetRamai(0);
            restoranTransaction.setOmzetSepi(0);
            restoranTransaction.setOmzetTotal(0);
            restoranTransaction.setPajakPerBulan(0);
            restoranTransaction.setPajakSetahun(0);
            restoranTransaction.setRatarataOmzet(0);
            restoranTransaction.setTanggalBuat(null);
        }
        else restoranTransaction = new RestoranTransaction();
    }
}
