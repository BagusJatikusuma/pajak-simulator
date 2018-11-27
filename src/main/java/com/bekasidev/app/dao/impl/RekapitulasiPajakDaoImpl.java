package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.RekapitulasiPajakDao;
import com.bekasidev.app.model.RekapitulasiPajak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by waddi on 23/11/18.
 */
public class RekapitulasiPajakDaoImpl implements RekapitulasiPajakDao{

    public List<RekapitulasiPajak> getRekapitulasiPajak(String idRekapitulasi) {
        String sql = "SELECT * FROM rekapitulasipajak WHERE idRekapitulasi=?";
        List<RekapitulasiPajak> rekapitulasiPajaks = new ArrayList<>();

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, idRekapitulasi);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                rekapitulasiPajaks.add(setPajak(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rekapitulasiPajaks;
    }

    public void createRekapitulasiPajak(RekapitulasiPajak rekapitulasiPajak) {
        String sql = "INSERT INTO rekapitulasipajak VALUES(?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            pstm.setString(1, rekapitulasiPajak.getIdRekapitulasi());
            pstm.setString(2, rekapitulasiPajak.getBulanPajak());
            pstm.setDouble(3, rekapitulasiPajak.getOmzetHasilPemeriksaan());
            pstm.setDouble(4, rekapitulasiPajak.getPokokPajak());
            pstm.setDouble(5, rekapitulasiPajak.getOmzetDilaporkan());
            pstm.setDouble(6, rekapitulasiPajak.getPajakTelahSetor());
            pstm.setDouble(7, rekapitulasiPajak.getOmzet());
            pstm.setDouble(8, rekapitulasiPajak.getPajakRestoran());
            pstm.setFloat(9, rekapitulasiPajak.getDenda());
            pstm.setDouble(10, rekapitulasiPajak.getJumlahTotal());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private RekapitulasiPajak setPajak(ResultSet rs) throws  SQLException {
        RekapitulasiPajak rekapitulasiPajak = new RekapitulasiPajak();

        rekapitulasiPajak.setIdRekapitulasi(rs.getString("idRekapitulasi"));
        rekapitulasiPajak.setPokokPajak(rs.getDouble("pokokPajak"));
        rekapitulasiPajak.setBulanPajak(rs.getString("bulanPajak"));
        rekapitulasiPajak.setPajakTelahSetor(rs.getDouble("pajakTelahSetor"));
        rekapitulasiPajak.setOmzetHasilPemeriksaan(rs.getDouble("omzetHasilPemeriksaan"));
        rekapitulasiPajak.setOmzetDilaporkan(rs.getDouble("omzetDilaporkan"));
        rekapitulasiPajak.setJumlahTotal(rs.getDouble("jumlahTahun"));
        rekapitulasiPajak.setDenda(rs.getFloat("denda"));
        rekapitulasiPajak.setOmzet(rs.getDouble("omzet"));
        rekapitulasiPajak.setPajakRestoran(rs.getDouble("pajakRestoran"));

        return rekapitulasiPajak;
    }
}
