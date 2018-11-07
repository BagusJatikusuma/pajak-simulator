/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.util;

import com.bekasidev.app.model.RestoranTransaction;

/**
 *  
 * @author bagus
 */
public class SessionProvider {
    private static RestoranTransaction restoranTransaction;
    
    public static RestoranTransaction getRestoranTransaction() {
        if (restoranTransaction == null) resetRestoranTranstion();
        return restoranTransaction;
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
