package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.SuratPerintahDao;
import com.bekasidev.app.model.*;

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

    @Override
    public void setTim(TimSP timSP) {
        String sql = "INSERT INTO tim_perintah VALUES(?,?,?,?,?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, timSP.getIdSP());
            pstm.setString(2, setPegawaiToString(timSP.getPenanggungJawab()));
            pstm.setString(3, setPegawaiToString(timSP.getSupervisor()));
            pstm.setString(4, timSP.getNamaTim());
            pstm.setString(5, setListPegawaiToString(timSP.getListAnggota()));
            pstm.setString(6, setListWPTpString(timSP.getListWP()));

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TimSP> getListTim(String idSP) {
        String sql = "SELECT * FROM tim_perintah WHERE id_sp=?";
        List<TimSP> listTim = new ArrayList<>();

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, idSP);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                TimSP tim = new TimSP();

                tim.setIdSP(rs.getString("id_sp"));
                tim.setNamaTim(rs.getString("nama_tim"));
                tim.setPenanggungJawab(setPegawai(rs.getString("penanggung_jawab")));
                tim.setSupervisor(setPegawai(rs.getString("supervisor")));
                tim.setListAnggota(setStringToPegawai(rs.getString("list_anggota")));
                tim.setListWP(setStringToWP(rs.getString("list_wp")));

                listTim.add(tim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTim;
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

    private String setListPegawaiToString(List<Pegawai> listPegawai){
        String result = "";
        if(listPegawai.size() > 0){
            result += listPegawai.get(0).getNipPegawai() + "<b>"
                    + listPegawai.get(0).getNamaPegawai() + "<b>"
                    + listPegawai.get(0).getPangkat() + "<b>"
                    + listPegawai.get(0).getGolongan() + "<b>"
                    + listPegawai.get(0).getJabatan();
            for(int i = 1; i < listPegawai.size(); i++){
                result += "<p>" + listPegawai.get(i).getNipPegawai() + "<b>"
                        + listPegawai.get(i).getNamaPegawai() + "<b>"
                        + listPegawai.get(i).getPangkat() + "<b>"
                        + listPegawai.get(i).getGolongan() + "<b>"
                        + listPegawai.get(i).getJabatan();
            }
        }
        return result;
    }

    private String setPegawaiToString(Pegawai pegawai){
        String result = pegawai.getNipPegawai() + "<b>"
                + pegawai.getNamaPegawai() + "<b>"
                + pegawai.getPangkat() + "<b>"
                + pegawai.getGolongan() + "<b>"
                + pegawai.getJabatan();

        return result;
    }

    private String setListWPTpString(List<WajibPajak> listWP){
        String result = "";
        if (listWP.size() > 0){
            result += listWP.get(0).getNpwpd() + "<b>"
                    + listWP.get(0).getNamaWajibPajak();
            for (int i=1; i < listWP.size(); i++){
                result += "<p>" + listWP.get(i).getNpwpd() + "<b>"
                        + listWP.get(i).getNamaWajibPajak();
            }
        }
        return result;
    }

    private List<Pegawai> setStringToPegawai(String stringPegawai){
        String[] pegawaiParts = stringPegawai.split("<p>");
        List<Pegawai> listPegawai = new ArrayList<>();

        for(String part : pegawaiParts){
            listPegawai.add(setPegawai(part));
        }
        return listPegawai;
    }

    private Pegawai setPegawai(String pegawaiString){
        String[] attrPart = pegawaiString.split("<b>");
        return new Pegawai("", attrPart[0], attrPart[1], attrPart[3], attrPart[2], attrPart[4]);
    }

    private List<WajibPajak> setStringToWP(String stringWP){
        String[] wpParts = stringWP.split("<p>");
        List<WajibPajak> listWP = new ArrayList<>();

        for(String part : wpParts){
            String[] attrPart = part.split("<b>");
            listWP.add(new WajibPajak(attrPart[0], attrPart[1]));
        }

        return listWP;
    }
}
