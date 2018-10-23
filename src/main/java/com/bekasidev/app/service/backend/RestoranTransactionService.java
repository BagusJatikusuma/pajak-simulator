package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.RestoranTransaction;

import java.util.List;

public interface RestoranTransactionService {

    List<RestoranTransaction> getAllRestoranTransactionByIdRestoran(String idRestoran);

    RestoranTransaction getRestoranTransactionByIdRestoranAndIdTransaction(String idRestoran, String idTransaction);

    void createRestoranTransaction(RestoranTransaction restoranTransaction);

    double calculatePotensiPajakRestoran(RestoranTransaction restoranTransaction);
}
