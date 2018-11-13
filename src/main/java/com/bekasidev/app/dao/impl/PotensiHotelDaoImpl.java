package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.PotensiHotelDao;
import com.bekasidev.app.model.PotensiHotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by waddi on 12/11/18.
 */
public class PotensiHotelDaoImpl implements PotensiHotelDao{

    @Override
    public List<PotensiHotel> getAllPotensiHotel(String idHotel, String idPotensiHotel) {
        String sql = "SELECT * FROM potensihotel WHERE id_hotel=? AND id_potensihotel=?";
        List<PotensiHotel> potensiHotels = new ArrayList<>();

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, idHotel);
            pstm.setString(2, idPotensiHotel);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                potensiHotels.add(setPotensiHotel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return potensiHotels;
    }

    @Override
    public void createPotensiHotel(PotensiHotel potensiHotel) {
        String sql = "INSERT INTO potensihotel VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, potensiHotel.getIdBenchmarkHotel());
            pstm.setString(2, potensiHotel.getIdFitnessCenter());
            pstm.setString(3, potensiHotel.getIdHotel());
            pstm.setString(4, potensiHotel.getIdKamarHotel());
            pstm.setString(5, potensiHotel.getIdMeetingRoom());
            pstm.setString(6, potensiHotel.getIdPotensiHotel());
            pstm.setInt(7, potensiHotel.getJumlahTerpakai());
            pstm.setInt(8, potensiHotel.getSptpd());
            pstm.setString(9, potensiHotel.getLabel());
            pstm.setDouble(10, potensiHotel.getPotensiTotal());
            pstm.setDouble(11, potensiHotel.getSptpdTotal());
            pstm.setDouble(12, potensiHotel.getTarifAwal());
            pstm.setString(13, potensiHotel.getTanggalBuat());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private  PotensiHotel setPotensiHotel(ResultSet rs) throws SQLException {
        PotensiHotel potensiHotel = new PotensiHotel();

        potensiHotel.setIdHotel(rs.getString("id_hotel"));
        potensiHotel.setIdBenchmarkHotel(rs.getString("id_benchmarkhotel"));
        potensiHotel.setIdFitnessCenter(rs.getString("id_fitnesscenter"));
        potensiHotel.setIdMeetingRoom(rs.getString("id_meetingroom"));
        potensiHotel.setIdKamarHotel(rs.getString("id_kamarhotel"));
        potensiHotel.setIdPotensiHotel(rs.getString("id_potensihotel"));
        potensiHotel.setJumlahTerpakai(rs.getInt("jumlah_terpakai"));
        potensiHotel.setLabel(rs.getString("label"));
        potensiHotel.setPotensiTotal(rs.getDouble("potensi_hotel"));
        potensiHotel.setSptpd(rs.getInt("sptpd"));
        potensiHotel.setSptpdTotal(rs.getDouble("sptpdtotal"));
        potensiHotel.setTarifAwal(rs.getDouble("tarif_awal"));
        potensiHotel.setTanggalBuat(rs.getString("tanggal_buat"));

        return potensiHotel;
    }
}
