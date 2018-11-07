package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.RestoranTransactionDao;
import com.bekasidev.app.dao.impl.RestoranTransactionDaoImpl;
import com.bekasidev.app.model.RestoranTransaction;
import com.bekasidev.app.service.backend.RestoranTransactionService;

import java.util.Calendar;
import java.util.List;

import static java.lang.Math.round;

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
//        calculateTotal(restoranTransaction);
        Calendar cal = Calendar.getInstance();
        restoranTransaction.setIdTransaction(Long.toString(cal.getTimeInMillis()));
        restoranTransaction.setTanggalBuat(Long.toString(cal.getTimeInMillis()));
        restoranTransactionDao.createRestoranTransaction(restoranTransaction);
    }

    @Override
    public void calculatePotensiPajakRestoran(RestoranTransaction restoranTransaction) {
        calculateTotal(restoranTransaction);
        double result = calculateRataRata(restoranTransaction)
                * restoranTransaction.getFrekuensiTotal()
                * 0.1;
        restoranTransaction.setPajakSetahun(round(result));
        restoranTransaction.setPajakPerBulan(round(result/12));
    }

    private double calculateRataRata(RestoranTransaction rt){
        rt.setRatarataOmzet(round(rt.getOmzetTotal()/rt.getFrekuensiTotal()));
        return rt.getRatarataOmzet();
    }

    private void calculateTotal(RestoranTransaction restoranTransaction){
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
    }
}
