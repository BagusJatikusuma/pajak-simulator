package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.TarifKamarHotelDao;
import com.bekasidev.app.model.TarifKamarHotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by waddi on 24/10/18.
 */
public class TarifKamarHotelDaoImpl implements TarifKamarHotelDao {

    public List<TarifKamarHotel> getAllTarifKamarHotelByIdHotel(String idHotel) {

        List<TarifKamarHotel> tarifKamarHotels = new ArrayList<>();
        String sql = "SELECT * FROM t_kamarhotel WHERE id_hotel=?";

        try (Connection conn = Connect.connect();
             PreparedStatement pstm =  conn.prepareStatement(sql);
        ){
            pstm.setString(1, idHotel);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                tarifKamarHotels.add(setTarifKamarHotel(rs));
            }
        } catch (SQLException e) {
                 e.printStackTrace();
        }
        return tarifKamarHotels;
    }

    public TarifKamarHotel getAllTarifKamarHotelByIdHotelAndIdKamarHotel(String idHotel, String idKamarHotel){
        TarifKamarHotel tarifKamarHotel= new TarifKamarHotel();
        String sql = "SELECT * FROM t_kamarhotel WHERE id_hotel=? AND id_kamarhotel=?";
        try (Connection conn = Connect.connect();
             PreparedStatement pstm = conn.prepareStatement(sql);){
                pstm.setString(1, idHotel);
                pstm.setString(2, idKamarHotel);

                ResultSet rs = pstm.executeQuery();

                while (rs.next()){
                    tarifKamarHotel = setTarifKamarHotel(rs);
                }

        } catch (SQLException e){
                 e.printStackTrace();
        }
        return tarifKamarHotel;
    }

    public void createTarifKamarHotel(TarifKamarHotel tarifKamarHotel) {
        String sql = "INSERT INTO t_kamarhotel VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, tarifKamarHotel.getIdHotel());
            pstm.setString(2, tarifKamarHotel.getIdKamarHotel());
            pstm.setString(3, tarifKamarHotel.getTipeKamar());
            pstm.setInt(4, tarifKamarHotel.getJumlahPemakaianKamarSebulan());
            pstm.setInt(5, tarifKamarHotel.getJumlahTotalPemakaianKamarSebulan());
            pstm.setDouble(6, tarifKamarHotel.getHargaPerKamar());
            pstm.setDouble(7, tarifKamarHotel.getJumlahHargaSewaKamar());
            pstm.setDouble(8, tarifKamarHotel.getJumlahTotalKeseluruhanHargaSewa());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTarifKamarHotelByIdHotelAndidKamarHotel(String idHotel, String idKamarHotel) {
        String sql = "DELETE FROM t_kamarhotel WHERE id_hotel=? AND id_kamarhotel=?";

        try (Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, idHotel);
            pstm.setString(2, idKamarHotel);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TarifKamarHotel setTarifKamarHotel(ResultSet rs){
        TarifKamarHotel tarifKamarHotel = new TarifKamarHotel();
        try {
            tarifKamarHotel.setIdHotel(rs.getString("id_hotel"));
            tarifKamarHotel.setIdKamarHotel(rs.getString("id_kamarhotel"));
            tarifKamarHotel.setTipeKamar(rs.getString("tipe_kamar"));
            tarifKamarHotel.setJumlahKamar(rs.getInt("jumlah_kamar"));
            tarifKamarHotel.setJumlahTotalKamar(rs.getInt("jumlah_totalkamar"));
            tarifKamarHotel.setJumlahPemakaianKamarSebulan(rs.getInt("jumlah_kamar"));
            tarifKamarHotel.setJumlahTotalPemakaianKamarSebulan(rs.getInt("jumlah_totalkamar"));
            tarifKamarHotel.setHargaPerKamar(rs.getDouble("harga_perkamar"));
            tarifKamarHotel.setJumlahHargaSewaKamar(rs.getDouble("jumlah_hargasewakamar"));
            tarifKamarHotel.setJumlahTotalKeseluruhanHargaSewa(rs.getDouble("jumlah_totalkeseluruhanhargasewa"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarifKamarHotel;
    }
}
