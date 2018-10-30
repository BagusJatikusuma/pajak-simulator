package com.bekasidev.app.dao.impl;

/**
 * Created by waddi on 24/10/18.
 */

import com.bekasidev.app.model.Hotel;
import com.bekasidev.app.dao.HotelDao;
import com.bekasidev.app.config.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDaoImpl implements HotelDao {

    public List<Hotel> getAllHotel() {
        String sql = "SELECT * FROM hotel";
        List<Hotel> listHotel = new  ArrayList<>();

        try(Connection conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)
        ){
            while (rs.next()){
                Hotel hotel = new Hotel();
                hotel.setIdHotel(rs.getString("id_hotel"));
                hotel.setNamaHotel(rs.getNString("nama_hotel"));
                listHotel.add(hotel);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return listHotel;
    }

    public void createDataHotel(Hotel hotel) {
        String sql = "INSERT INTO hotel VALUES(?,?)";

        try (Connection conn = Connect.connect();
             PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, hotel.getIdHotel());
            pstm.setString(2, hotel.getNamaHotel());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  void  deleteHotelById(String idHotel) {
        String sql = "DELETE FROM hotel WHERE id_hotel=?";

        try (Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, idHotel);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
