package com.bekasidev.app.dao;

import com.bekasidev.app.model.RestoranTransaction;

import java.util.List;

public interface RestoranTransactionDao {

    List<RestoranTransaction> getAllRestoranTransactionByIdRestoran(String idRestoran);

    RestoranTransaction getRestoranTransactionByIdRestoranAndIdTransaction(String idRestoran, String idTransaction);

    void createRestoranTransaction(RestoranTransaction restoranTransaction);
}
