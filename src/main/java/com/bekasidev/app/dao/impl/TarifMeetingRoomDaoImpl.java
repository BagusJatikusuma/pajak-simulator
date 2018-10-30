package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.TarifMeetingRoomDao;
import com.bekasidev.app.model.TarifKamarHotel;
import com.bekasidev.app.model.TarifMeetingRoom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by waddi on 25/10/18.
 */

public class TarifMeetingRoomDaoImpl implements TarifMeetingRoomDao{

    public List<TarifMeetingRoom> getAllTarifMeetingRoomByIdHotel(String idHotel) {
        List<TarifMeetingRoom> tarifMeetingRooms = new ArrayList<>();
        String sql = "SELECT * FROM t_meetingroom WHERE id_hotel=?";

        try (Connection conn = Connect.connect();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ){
                pstm.setString(1, idHotel);

                ResultSet rs = pstm.executeQuery();

                while (rs.next()){
                    tarifMeetingRooms.add(setTarifMeetingRoom(rs));
                }
        } catch (SQLException e){
                 e.printStackTrace();
        }
        return tarifMeetingRooms;
    }

    public TarifMeetingRoom getAllTarifMeetingRoomByIdHotelAndIdMeetingHotel(String idHotel, String idMeetingRoom){
        TarifMeetingRoom tarifMeetingRoom = new TarifMeetingRoom();
        String sql = "SELECT * FROM t_meetingroom WHERE id_hotel=? AND id_meetingroom=?";

        try (Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql);) {

            pstm.setString(1, idHotel);
            pstm.setString(2, idMeetingRoom);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                tarifMeetingRoom = setTarifMeetingRoom(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return tarifMeetingRoom;
    }

    public  void  createTarifMeetingRoom(TarifMeetingRoom tarifMeetingRoom) {
        String sql = "INSERT INTO t_meetingroom VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, tarifMeetingRoom.getIdHotel());
            pstm.setString(2, tarifMeetingRoom.getIdMeetingRoom());
            pstm.setString(3, tarifMeetingRoom.getNamaMeetingRoom());
            pstm.setInt(4, tarifMeetingRoom.getJumlahPengunjung());
            pstm.setInt(5, tarifMeetingRoom.getJumlahPengunjungSebulan());
            pstm.setDouble(6, tarifMeetingRoom.getHargaSewa());
            pstm.setDouble(7, tarifMeetingRoom.getHargaSewaSebulan());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTarifMeetingRoomByIdHotelAndidMeetingRoom(String idHotel, String idMeetingRoom) {
        String sql = "DELETE FROM t_meetingroom WHERE id_hotel=? AND id_meetingroom=?";

        try (Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, idHotel);
            pstm.setString(2, idMeetingRoom);
            pstm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private TarifMeetingRoom setTarifMeetingRoom(ResultSet rs){

        TarifMeetingRoom tarifMeetingRoom = new TarifMeetingRoom();
        try {
            tarifMeetingRoom.setIdHotel(rs.getString("id_hotel"));
            tarifMeetingRoom.setIdMeetingRoom(rs.getString("id_meetingroom"));
            tarifMeetingRoom.setNamaMeetingRoom(rs.getString("nama_meetingroom"));
            tarifMeetingRoom.setJumlahPengunjung(rs.getInt("jumlah_pengunjung"));
            tarifMeetingRoom.setJumlahPengunjungSebulan(rs.getInt("jumlah_pengunjungsebulan"));
            tarifMeetingRoom.setHargaSewa(rs.getDouble("harga_sewa"));
            tarifMeetingRoom.setHargaSewaSebulan(rs.getDouble("harga_sewasebulan"));
        } catch (SQLException e) {
            e.printStackTrace();
        } return tarifMeetingRoom;
    }
}
