package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.SuratPerintahDao;
import com.bekasidev.app.model.SuratPerintah;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuratPerintahDaoImpl implements SuratPerintahDao {
    @Override
    public void createSuratPerintah(SuratPerintah suratPerintah) {
        String sql = "INSERT INTO surat_perintah VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, suratPerintah.getIdSP());
            pstm.setInt(2, suratPerintah.getNomorSurat());
            pstm.setString(3, suratPerintah.getKodeSkpd());
            pstm.setString(4, suratPerintah.getNomorUrut());
            pstm.setString(5, suratPerintah.getNomorSP());
            pstm.setString(6, suratPerintah.getTanggalSP());
            pstm.setInt(7, suratPerintah.getTahunAnggatan());
            pstm.setString(8, suratPerintah.getNamaPemberi());
            pstm.setString(9, suratPerintah.getJabatanPemberi());
            pstm.setString(10, suratPerintah.getMasaPajak());
            pstm.setShort(11, suratPerintah.getTahap());
            pstm.setString(12, suratPerintah.getLamaPelaksanaan());
            pstm.setString(13, suratPerintah.getTempat());
            pstm.setString(14, suratPerintah.getTanggalSurat());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SuratPerintah> getAllSuratPerintah() {
        String sql = "SELECT * FROM surat_perintah";
        List<SuratPerintah> listSP = new ArrayList<>();

        try(Connection conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)) {

            while (rs.next()){
                listSP.add(setSuratPerintah(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listSP;
    }

    @Override
    public void setNomorUrut(String idSP, String nomorUrut) {
        String sql = "UPDATE surat_perintah SET nomor_urut=? WHERE id_sp=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nomorUrut);
            pstm.setString(2, idSP);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private SuratPerintah setSuratPerintah(ResultSet rs) throws SQLException {
        SuratPerintah suratPerintah = new SuratPerintah();

        suratPerintah.setIdSP(rs.getString("id_sp"));
        suratPerintah.setNomorSurat(rs.getInt("nomor_surat"));
        suratPerintah.setKodeSkpd(rs.getString("kode_skpd"));
        suratPerintah.setNomorUrut(rs.getString("nomor_urut"));
        suratPerintah.setNomorSP(rs.getString("nomor_sp"));
        suratPerintah.setTanggalSP(rs.getString("tanggal_sp"));
        suratPerintah.setTahunAnggatan(rs.getInt("tahun_anggaran"));
        suratPerintah.setNamaPemberi(rs.getString("nama_pemberi"));
        suratPerintah.setJabatanPemberi(rs.getString("jabatan_pemberi"));
        suratPerintah.setMasaPajak(rs.getString(rs.getString("masa_pajak")));
        suratPerintah.setTahap(rs.getShort("masa_pajak"));
        suratPerintah.setLamaPelaksanaan(rs.getString("lama_pelaksanaan"));
        suratPerintah.setTempat(rs.getString("tempat"));
        suratPerintah.setTanggalSurat(rs.getString("tanggal_surat"));

        return suratPerintah;
    }
}
