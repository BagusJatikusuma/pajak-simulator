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
        restoranTransaction = new RestoranTransaction();
    }
}
