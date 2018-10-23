package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.RestoranDao;
import com.bekasidev.app.model.Restoran;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RestoranDaoImpl implements RestoranDao {

    public List<Restoran> getAllRestoran() {
        String sql = "SELECT * FROM restoran";
        List<Restoran> listRestoran= new ArrayList<>();

        try (Connection conn = Connect.connect();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            while(rs.next()){
                Restoran restoran = new Restoran();
                restoran.setIdRestoran(rs.getString("id_restoran"));
                restoran.setNamaRestoran(rs.getString("nama_restoran"));
                listRestoran.add(restoran);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listRestoran;
    }
}
