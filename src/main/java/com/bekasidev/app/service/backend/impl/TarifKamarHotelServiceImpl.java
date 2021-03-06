package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.TarifKamarHotelDao;
import com.bekasidev.app.dao.impl.TarifKamarHotelDaoImpl;
import com.bekasidev.app.model.TarifKamarHotel;
import com.bekasidev.app.service.backend.TarifKamarHotelService;

import java.util.Calendar;
import java.util.List;

import static java.lang.Math.round;
/**
 * Created by waddi on 25/10/18.
 */
public class TarifKamarHotelServiceImpl implements TarifKamarHotelService {

    private TarifKamarHotelDao tarifKamarHotelDao = new TarifKamarHotelDaoImpl();

    @Override
    public List<TarifKamarHotel> getAllTarifKamarHotelByIdHotel(String idHotel) {
        return tarifKamarHotelDao.getAllTarifKamarHotelByIdHotel(idHotel);
    }

    @Override
    public TarifKamarHotel getAllTarifKamarHotelByIdHotelAndIdKamarHotel(String idHotel, String idKamarHotel) {
        return tarifKamarHotelDao.getAllTarifKamarHotelByIdHotelAndIdKamarHotel(idHotel, idKamarHotel);
    }

    @Override
    public void createTarifKamarHotel(TarifKamarHotel tarifKamarHotel) {
        Calendar cal = Calendar.getInstance();
        tarifKamarHotel.setIdKamarHotel(Long.toString(cal.getTimeInMillis()));
        tarifKamarHotelDao.createTarifKamarHotel(tarifKamarHotel);
    }

    @Override
    public void deleteTarifKamarHotelByIdHotelAndidKamarHotel(String idKamarhotel) {
        tarifKamarHotelDao.deleteTarifKamarHotelByIdHotelAndidKamarHotel(idKamarhotel);
    }

    private void calculateHargaPerKamar(TarifKamarHotel tk) {
        tk.setRataHargaKamarHotel((double) round(tk.getJumlahKamar()*tk.getTotalHargaPerKamar()/tk.getRataKamarHotel()));
    }

    public Double calculateTotalHargaPerkamar(List<TarifKamarHotel> listTarifHargaPerKamar){
        double jumlahTotalHargaPerKamar = 0;
        for (TarifKamarHotel tarifKamarHotel : listTarifHargaPerKamar){
            calculateHargaPerKamar(tarifKamarHotel);
            jumlahTotalHargaPerKamar = jumlahTotalHargaPerKamar+tarifKamarHotel.getRataHargaKamarHotel();
        }
        return jumlahTotalHargaPerKamar;
    }

    public int calculateJumlahPemakaianKamarSebulan(List<TarifKamarHotel> listPemakaianKamarSebulan) {
        int jumlahPemakaianKamarSebulan = 0;
        for (TarifKamarHotel tarifKamarHotel : listPemakaianKamarSebulan) {
            jumlahPemakaianKamarSebulan = jumlahPemakaianKamarSebulan+tarifKamarHotel.getJumlahPemakaianKamarSebulan();
        }
        return jumlahPemakaianKamarSebulan;
    }

    private void calculateTotalHargaPerKamar(TarifKamarHotel tkh) {
        tkh.setTotalHargaPerKamar(tkh.getJumlahPemakaianKamarSebulan()*tkh.getHargaPerKamar());
    }

    public Double calculateTotalHargaSeluruhKamar(List<TarifKamarHotel> listTotalHargaSeluruhKamar) {
        double jumlahTotalHargaSeluruhKamar = 0;
        for (TarifKamarHotel tarifKamarHotel : listTotalHargaSeluruhKamar) {
            calculateTotalHargaPerKamar(tarifKamarHotel);
            jumlahTotalHargaSeluruhKamar = jumlahTotalHargaSeluruhKamar+tarifKamarHotel.getTotalHargaPerKamar();
        }
        return jumlahTotalHargaSeluruhKamar;
    }

}
