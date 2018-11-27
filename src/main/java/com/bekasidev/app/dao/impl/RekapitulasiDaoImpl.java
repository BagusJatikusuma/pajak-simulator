package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.RekapitulasiDao;
import com.bekasidev.app.model.Rekapitulasi;
import com.bekasidev.app.wrapper.RekapitulasiWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RekapitulasiDaoImpl implements RekapitulasiDao {
    @Override
    public void createRekapitulasi(RekapitulasiWrapper rekapitulasiWrapper) {
        String sql = "INSERT INTO rekapitulasi VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        for(int i = 1; i < rekapitulasiWrapper.getListRekapitulasi().size(); i++){
            sql += ",(?,?,?,?,?,?,?,?,?,?,?,?)";
        }
        int i = 0;

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            for(Rekapitulasi rekapitulasi : rekapitulasiWrapper.getListRekapitulasi()){
                pstm.setString(1+i, rekapitulasiWrapper.getIdSP());
                pstm.setString(2+i, rekapitulasiWrapper.getIdWP());
                pstm.setString(3+i, rekapitulasi.getBulan());
                pstm.setDouble(4+i, rekapitulasi.getOmzetHasilPeriksa());
                pstm.setDouble(5+i, rekapitulasi.getPajakHasilPeriksa());
                pstm.setDouble(6+i, rekapitulasi.getOmzetLaporan());
                pstm.setDouble(7+i, rekapitulasi.getPajakDisetor());
                pstm.setDouble(8+i, rekapitulasi.getOmzet());
                pstm.setDouble(9+i, rekapitulasi.getPokokPajak());
                pstm.setDouble(10+i, rekapitulasi.getDenda());
                pstm.setDouble(11+i, rekapitulasi.getJumlah());
                pstm.setDouble(12+i, rekapitulasi.getPersentaseDenda());
                i += 12;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RekapitulasiWrapper getRekapitulasi(String idSP, String idWP) {
        String sql = "SELECT * FROM rekapitulasi WHERE id_sp=? AND id_wp=?";
        RekapitulasiWrapper rekapitulasiWrapper = new RekapitulasiWrapper();

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, idSP);
            pstm.setString(2, idWP);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                rekapitulasiWrapper.getListRekapitulasi().add(new Rekapitulasi(
                        rs.getString("bulan"),
                        rs.getDouble("omzet_periksa"),
                        rs.getDouble("pajak_periksa"),
                        rs.getDouble("omzet_laporan"),
                        rs.getDouble("pajak_disetor"),
                        rs.getDouble("omzet"),
                        rs.getDouble("pokok_pajak"),
                        rs.getDouble("denda"),
                        rs.getInt("persentase_denda"),
                        rs.getDouble("jumlah")
                ));
            }
            rekapitulasiWrapper.setIdSP(idSP);
            rekapitulasiWrapper.setIdWP(idWP);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rekapitulasiWrapper;
    }
}
