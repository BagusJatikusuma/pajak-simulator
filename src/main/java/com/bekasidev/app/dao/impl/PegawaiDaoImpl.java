package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.PegawaiDao;
import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.Tim;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PegawaiDaoImpl implements PegawaiDao {
    @Override
    public List<Pegawai> getAllPegawai() {
        String sql = "SELECT * FROM pegawai";
        List<Pegawai> listPegawai = new ArrayList<>();

        try(Connection conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)) {
            while(rs.next()){
                listPegawai.add(setPegawai(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listPegawai;
    }

    @Override
    public List<Pegawai> getPegawaiByTim(String idTim) {
        String sql = "SELECT * FROM pegawai WHERE id_tim=?";
        List<Pegawai> listPegawai = new ArrayList<>();

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, idTim);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                listPegawai.add(setPegawai(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("pegawai tim "+idTim+" is "+listPegawai.size());
        return listPegawai;
    }

    @Override
    public void createPegawai(Pegawai pegawai) {
        String sql = "INSERT INTO pegawai VALUES(?,?,?,?,?,?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, "");
            pstm.setString(2, pegawai.getNipPegawai());
            pstm.setString(3, pegawai.getNamaPegawai());
            pstm.setString(4, pegawai.getGolongan());
            pstm.setString(5, pegawai.getJabatanTim());
            pstm.setString(6, pegawai.getPangkat());
            pstm.setString(7, pegawai.getJabatanDinas());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPegawaiTim(String nipPegawai, String idTim, String jabatan) {
        String sql = "UPDATE pegawai SET id_tim=?, jabatan_tim=? WHERE nip_pegawai=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, idTim);
            pstm.setString(2, jabatan);
            pstm.setString(3, nipPegawai);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTim(Tim tim) {
        String sql = "INSERT INTO tim VALUES(?,?)";
        System.out.println("id "+tim.getIdTim()+"; nama "+tim.getNamaTim());
        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, tim.getIdTim());
            pstm.setString(2, tim.getNamaTim());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tim> getAllTim() {
        String sql = "SELECT * FROM tim";
        List<Tim> tims = new ArrayList<>();

        try(Connection conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)) {
            while(rs.next()){
                tims.add(new Tim(rs.getString("id_tim"), rs.getString("nama_tim")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tims;
    }

    @Override
    public void updatePegawai(Pegawai pegawai) {
        String sql = "UPDATE pegawai SET " +
                "nama_pegawai=?, golongan=?, pangkat=?, " +
                "jabatan_dinas=? WHERE nip_pegawai=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, pegawai.getNamaPegawai());
            pstm.setString(2, pegawai.getGolongan());
            pstm.setString(3, pegawai.getPangkat());
            pstm.setString(4, pegawai.getJabatanDinas());
            pstm.setString(5, pegawai.getNipPegawai());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTim(Tim tim) {
        String sql = "UPDATE tim SET nama_tim=? WHERE id_tim=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, tim.getNamaTim());
            pstm.setString(2, tim.getIdTim());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePegawai(String nip) {
        String sql = "DELETE FROM pegawai WHERE nip_pegawai=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nip);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTim(String id) {
        String sql = "DELETE FROM tim WHERE id_tim=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, id);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Pegawai setPegawai(ResultSet rs) throws SQLException {
        return new Pegawai(rs.getString("id_tim"),
                                        rs.getString("nip_pegawai"),
                                        rs.getString("nama_pegawai"),
                                        rs.getString("golongan"),
                                        rs.getString("pangkat"),
                                        rs.getString("jabatan_tim"),
                                        rs.getString("jabatan_dinas"));
    }
}
