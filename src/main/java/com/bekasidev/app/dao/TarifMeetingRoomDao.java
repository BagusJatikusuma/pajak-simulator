package com.bekasidev.app.dao;

import com.bekasidev.app.model.TarifMeetingRoom;

import java.util.List;

/**
 * Created by waddi on 25/10/18.
 */
public interface TarifMeetingRoomDao {

    List<TarifMeetingRoom> getAllTarifMeetingRoomByIdHotel(String idHotel);

    TarifMeetingRoom getAllTarifMeetingRoomByIdHotelAndIdMeetingHotel(String idHotel, String idMeetingRoom);
}
