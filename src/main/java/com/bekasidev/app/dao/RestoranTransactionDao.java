package com.bekasidev.app.dao;

import com.bekasidev.app.model.RestoranTransaction;

import java.util.List;

public interface RestoranTransactionDao {

    List<RestoranTransaction> getAllRestoranTransaction();

    RestoranTransaction getRestoranTransactionByIdHotel(String idHotel);
}
