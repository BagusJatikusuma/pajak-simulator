package com.bekasidev.app.dao.impl;
import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.RestoranTransactionDao;
import com.bekasidev.app.model.RestoranTransaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestoranTransactionDaoImpl implements RestoranTransactionDao {

    public List<RestoranTransaction> getAllRestoranTransactionByIdRestoran(String idRestoran) {
        List<RestoranTransaction> restoranTransactions = new ArrayList<>();
        String sql = "SELECT * FROM tr_restoran WHERE id_restoran=?";
        try (Connection conn = Connect.connect();
             PreparedStatement pstm = conn.prepareStatement(sql);) {

            pstm.setString(1, idRestoran);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                restoranTransactions.add(setRestoranTransaction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restoranTransactions;
    }

    public RestoranTransaction getRestoranTransactionByIdRestoranAndIdTransaction(String idRestoran, String idTransaction) {
        RestoranTransaction restoranTransaction = new RestoranTransaction();
        String sql = "SELECT * FROM tr_restoran WHERE id_restoran=? AND id_transaksi=?";
        try (Connection conn = Connect.connect();
             PreparedStatement pstm = conn.prepareStatement(sql);) {

            pstm.setString(1, idRestoran);
            pstm.setString(2, idTransaction);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                restoranTransaction = setRestoranTransaction(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restoranTransaction;
    }

    private RestoranTransaction setRestoranTransaction(ResultSet rs){
        RestoranTransaction restoranTransaction = new RestoranTransaction();
        try {
            restoranTransaction.setIdRestoran(rs.getString("id_restoran"));
            restoranTransaction.setIdTransaction(rs.getString("id_transaksi"));
            restoranTransaction.setFrekuensiRamai(rs.getInt("frekuensi_ramai"));
            restoranTransaction.setFrekuesniNormal(rs.getInt("frekuensi_normal"));;
            restoranTransaction.setFrekuensiSepi(rs.getInt("frekuensi_sepi"));
            restoranTransaction.setFrekuensiTotal(rs.getInt("frekuensi_total"));
            restoranTransaction.setOmzetRamai(rs.getDouble("omzet_ramai"));
            restoranTransaction.setOmzetNormal(rs.getDouble("omzet_normal"));
            restoranTransaction.setOmzetSepi(rs.getDouble("omzet_sepi"));
            restoranTransaction.setOmzetTotal(rs.getDouble("omzet_total"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restoranTransaction;
    }
}
