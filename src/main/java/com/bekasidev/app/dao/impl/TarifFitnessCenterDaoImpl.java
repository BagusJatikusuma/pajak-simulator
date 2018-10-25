package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.TarifFitnessCenterDao;
import com.bekasidev.app.model.TarifFitnessCenter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by waddi on 25/10/18.
 */
public class TarifFitnessCenterDaoImpl implements TarifFitnessCenterDao{

    public List<TarifFitnessCenter> getAllTarifFitnessCenterByIdHotel(String idHotel) {
        List<TarifFitnessCenter> tarifFitnessCenters = new ArrayList<>();
        String sql = "SELECT * FROM t_fitnesscenter WHERE id_hotel=?";

        try (Connection conn = Connect.connect();
             PreparedStatement pstm = conn.prepareStatement(sql);
        ){
            pstm.setString(1, idHotel);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                tarifFitnessCenters.add(setTarifFitnessCenter(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  tarifFitnessCenters;
    }

    public TarifFitnessCenter getAllTarifFitnessCenterByIdHotelAndIdTarifFitness(String idHotel, String idFitnessCenter){
        TarifFitnessCenter tarifFitnessCenter = new TarifFitnessCenter();
        String sql = "SELECT * FROM t_fitnesscenter WHERE id_hotel=? AND id_fitnesscenter=?";
        try (Connection conn = Connect.connect();
        PreparedStatement pstm = conn.prepareStatement(sql);){
            pstm.setString(1, idHotel);
            pstm.setString(2, idFitnessCenter);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                tarifFitnessCenter = setTarifFitnessCenter(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return tarifFitnessCenter;
    }

    private TarifFitnessCenter setTarifFitnessCenter(ResultSet rs){
        TarifFitnessCenter tarifFitnessCenter = new TarifFitnessCenter();
        try {
            tarifFitnessCenter.setIdHotel(rs.getString("id_hotel"));
            tarifFitnessCenter.setIdTarifFitness(rs.getString("id_fitnesscenter"));
            tarifFitnessCenter.setNamaFitnessCenter(rs.getString("nama_fitnesscenter"));
            tarifFitnessCenter.setTarifFitness(rs.getDouble("tarif_fitness"));
            tarifFitnessCenter.setJumlahPengunjung(rs.getInt("jumlah_pengunjung"));
            tarifFitnessCenter.setJumlahTotal(rs.getDouble("jumlah_total"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarifFitnessCenter;
    }

}
