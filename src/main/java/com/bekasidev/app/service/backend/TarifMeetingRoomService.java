package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.TarifMeetingRoom;

import java.util.List;

/**
 * Created by waddi on 29/10/18.
 */
public interface TarifMeetingRoomService {

    List<TarifMeetingRoom> getAllTarifMeetingRoomByIdHotel(String idHotel);

    TarifMeetingRoom getAllTarifMeetingRoomByIdHotelAndIdMeetingHotel(String idHotel, String IdMeetingRoom);

    void createTarifMeetingRoom(TarifMeetingRoom tarifMeetingRoom);
}
