package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.TarifMeetingRoomDao;
import com.bekasidev.app.dao.impl.TarifMeetingRoomDaoImpl;
import com.bekasidev.app.model.TarifMeetingRoom;
import com.bekasidev.app.service.backend.TarifMeetingRoomService;

import java.util.Calendar;
import java.util.List;

/**
 * Created by waddi on 29/10/18.
 */
public class TarifMeetingRoomServiceImpl implements TarifMeetingRoomService{

    private TarifMeetingRoomDao tarifMeetingRoomDao = new TarifMeetingRoomDaoImpl();

        @Override
        public List<TarifMeetingRoom> getAllTarifMeetingRoomByIdHotel(String idHotel) {
            return tarifMeetingRoomDao.getAllTarifMeetingRoomByIdHotel(idHotel);
        }

        @Override
        public TarifMeetingRoom getAllTarifMeetingRoomByIdHotelAndIdMeetingHotel(String idHotel, String idMeetingRoom) {
            return tarifMeetingRoomDao.getAllTarifMeetingRoomByIdHotelAndIdMeetingHotel(idHotel,idMeetingRoom);
        }

        @Override
        public void createTarifMeetingRoom(TarifMeetingRoom tarifMeetingRoom) {
            Calendar cal = Calendar.getInstance();
            tarifMeetingRoom.setIdMeetingRoom(Long.toString((cal.getTimeInMillis())));
            tarifMeetingRoomDao.createTarifMeetingRoom(tarifMeetingRoom);
        }

        @Override
        public void deleteTarifMeetingRoomByIdHotelAndidMeetingRoom(String idHotel, String idMeetingRoom) {
            tarifMeetingRoomDao.deleteTarifMeetingRoomByIdHotelAndidMeetingRoom(idHotel, idMeetingRoom);
        }

}
