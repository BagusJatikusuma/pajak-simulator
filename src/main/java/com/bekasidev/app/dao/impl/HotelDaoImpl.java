package com.bekasidev.app.dao.impl;

/**
 * Created by waddi on 24/10/18.
 */

import com.bekasidev.app.model.Hotel;
import com.bekasidev.app.dao.HotelDao;
import com.bekasidev.app.config.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
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
}
