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

    public void createRestoranTransaction(RestoranTransaction restoranTransaction) {
        String sql = "INSERT INTO tr_restoran VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = Connect.connect();
                PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, restoranTransaction.getIdRestoran());
            pstm.setString(2, restoranTransaction.getIdTransaction());
            pstm.setDouble(3, restoranTransaction.getFrekuensiRamai());
            pstm.setDouble(4, restoranTransaction.getFrekuesniNormal());
            pstm.setDouble(5, restoranTransaction.getFrekuensiSepi());
            pstm.setDouble(6, restoranTransaction.getFrekuensiTotal());
            pstm.setDouble(7, restoranTransaction.getOmzetRamai());
            pstm.setDouble(8, restoranTransaction.getOmzetNormal());
            pstm.setDouble(9, restoranTransaction.getOmzetSepi());
            pstm.setDouble(10, restoranTransaction.getOmzetTotal());
            pstm.setDouble(11, restoranTransaction.getRatarataOmzet());
            pstm.setDouble(12, restoranTransaction.getPajakSetahun());
            pstm.setDouble(13, restoranTransaction.getPajakPerBulan());
            pstm.setString(14, restoranTransaction.getTanggalBuat());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            restoranTransaction.setRatarataOmzet(rs.getDouble("ratarata_omzet"));
            restoranTransaction.setPajakSetahun(rs.getDouble("pajak_setahun"));
            restoranTransaction.setPajakPerBulan(rs.getDouble("pajak_perbulan"));
            restoranTransaction.setTanggalBuat(rs.getString("tanggal_buat"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restoranTransaction;
    }
}
