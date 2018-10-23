package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.RestoranTransactionDao;
import com.bekasidev.app.dao.impl.RestoranTransactionDaoImpl;
import com.bekasidev.app.model.RestoranTransaction;
import com.bekasidev.app.service.backend.RestoranTransactionService;

import java.util.List;

public class RestoranTransactionServiceImpl implements RestoranTransactionService {

    private RestoranTransactionDao restoranTransactionDao = new RestoranTransactionDaoImpl();

    @Override
    public List<RestoranTransaction> getAllRestoranTransactionByIdRestoran(String idRestoran) {
        return restoranTransactionDao.getAllRestoranTransactionByIdRestoran(idRestoran);
    }

    @Override
    public RestoranTransaction getRestoranTransactionByIdRestoranAndIdTransaction(String idRestoran, String idTransaction) {
        return restoranTransactionDao.getRestoranTransactionByIdRestoranAndIdTransaction(idRestoran, idTransaction);
    }

    @Override
    public void createRestoranTransaction(RestoranTransaction restoranTransaction) {
        restoranTransaction.setFrekuensiTotal(
                restoranTransaction.getFrekuensiRamai() +
                restoranTransaction.getFrekuesniNormal() +
                restoranTransaction.getFrekuensiSepi()
        );
        restoranTransaction.setOmzetTotal(
                restoranTransaction.getOmzetRamai() * restoranTransaction.getFrekuensiRamai() +
                restoranTransaction.getOmzetNormal() * restoranTransaction.getFrekuesniNormal() +
                restoranTransaction.getOmzetSepi() * restoranTransaction.getFrekuensiSepi()
        );
        restoranTransactionDao.createRestoranTransaction(restoranTransaction);
    }

    @Override
    public double calculatePotensiPajakRestoran(RestoranTransaction restoranTransaction) {
        double result = calculateRataRata(restoranTransaction)
                * restoranTransaction.getFrekuensiTotal()
                * 0.1;
        System.out.println(1800000*360*0.1);
        return result;
    }

    private double calculateRataRata(RestoranTransaction rt){
        System.out.println(rt.getOmzetTotal()/rt.getFrekuensiTotal());
        return rt.getOmzetTotal()/rt.getFrekuensiTotal();
    }
}
