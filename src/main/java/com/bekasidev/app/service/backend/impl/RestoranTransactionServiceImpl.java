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
}
