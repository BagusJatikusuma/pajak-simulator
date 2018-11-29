package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.BerkasPersiapanDao;
import com.bekasidev.app.dao.NomorBerkasDao;
import com.bekasidev.app.dao.SuratPerintahDao;
import com.bekasidev.app.dao.WajibPajakDao;
import com.bekasidev.app.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuratPerintahDaoImpl implements SuratPerintahDao {
    @Override
    public void createSuratPerintah(SuratPerintah suratPerintah) {
        String sql = "INSERT INTO surat_perintah VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, suratPerintah.getIdSP());
            pstm.setString(2, suratPerintah.getNomorSurat());
            pstm.setString(3, suratPerintah.getNomorUrut());
            pstm.setString(4, suratPerintah.getNomorSK());
            pstm.setString(5, suratPerintah.getTanggalSK());
            pstm.setString(6, suratPerintah.getPemberiSK());
            pstm.setInt(7, suratPerintah.getTahunAnggaranSK());
            pstm.setInt(8, suratPerintah.getTahunAnggaranBiaya());
            pstm.setString(9, suratPerintah.getNomorSuratBiaya());
            pstm.setString(10, suratPerintah.getTanggalBiaya());
            pstm.setString(11, setPegawaiToString(suratPerintah.getPemberiSP()));
            pstm.setString(12, suratPerintah.getMasaPajakAwal());
            pstm.setString(13, suratPerintah.getMasaPajakAkhir());
            pstm.setShort(14, suratPerintah.getTahap());
            pstm.setShort(15, suratPerintah.getLamaPelaksanaan());
            pstm.setString(16, suratPerintah.getTempat());
            pstm.setString(17, suratPerintah.getTanggalSurat());

            pstm.executeUpdate();

            setTim(suratPerintah.getListTim());
            createNomorBerkas(suratPerintah);
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
    public void setNomorUrut(String idSP, String nomorUrut, String tanggal) {
        String sql = "UPDATE surat_perintah SET nomor_urut=?, tanggal_surat=? WHERE id_sp=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nomorUrut);
            pstm.setString(2, tanggal);
            pstm.setString(3, idSP);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTim(List<TimSP> timSP) {
        String sql = "INSERT INTO tim_perintah VALUES(?,?,?,?,?,?,?)";
        for(int i = 1; i < timSP.size(); i++)
            sql += ",(?,?,?,?,?,?,?)";
        int i = 0;
        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            for(TimSP tim : timSP){
                pstm.setString(i+1, tim.getIdSP());
                pstm.setString(i+2, setPegawaiToString(tim.getPenanggungJawab()));
                pstm.setString(i+3, setPegawaiToString(tim.getSupervisor()));
                pstm.setString(i+4, tim.getIdTim());
                pstm.setString(i+5, tim.getNamaTim());
                pstm.setString(i+6, setListPegawaiToString(tim.getListAnggota()));
                pstm.setString(i+7, setListWPTpString(tim.getListWP()));
                i += 7;
            }
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
                tim.setListWP(setStringToWP(rs.getString("list_wp"), tim.getIdSP()));
                tim.setIdTim(rs.getString("id_tim"));

                listTim.add(tim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTim;
    }

    @Override
    public void updateTim(List<TimSP> timSP) {
        String sql = "DELETE FROM tim_perintah WHERE id_sp=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, timSP.get(0).getIdSP());

            pstm.executeUpdate();
            setTim(timSP);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSuratPerintah(SuratPerintah suratPerintah) {
        String sql = "UPDATE surat_perintah SET " +
                "nomor_surat=?, nomor_urut=?, nomor_sk=?, tanggal_sk=?, " +
                "pemberi_sk=?, tahun_anggaran_sk=?, tahun_anggaran_biaya=?, " +
                "nomor_surat_biaya=?, tanggal_biaya=?, pemberi_sp=?, " +
                "masa_pajak_awal=?, masa_pajak_akhir=?, tahap=?, " +
                "lama_pelaksanaan=?, tempat=?, tanggal_surat=? WHERE id_sp=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, suratPerintah.getNomorSurat());
            pstm.setString(2, suratPerintah.getNomorUrut());
            pstm.setString(3, suratPerintah.getNomorSK());
            pstm.setString(4, suratPerintah.getTanggalSK());
            pstm.setString(5, suratPerintah.getPemberiSK());
            pstm.setInt(6, suratPerintah.getTahunAnggaranSK());
            pstm.setInt(7, suratPerintah.getTahunAnggaranBiaya());
            pstm.setString(8, suratPerintah.getNomorSuratBiaya());
            pstm.setString(9, suratPerintah.getTanggalBiaya());
            pstm.setString(10, setPegawaiToString(suratPerintah.getPemberiSP()));
            pstm.setString(11, suratPerintah.getMasaPajakAwal());
            pstm.setString(12, suratPerintah.getMasaPajakAkhir());
            pstm.setShort(13, suratPerintah.getTahap());
            pstm.setShort(14, suratPerintah.getLamaPelaksanaan());
            pstm.setString(15, suratPerintah.getTempat());
            pstm.setString(16, suratPerintah.getTanggalSurat());

            pstm.setString(17, suratPerintah.getIdSP());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSuratPerintah(String idSP) {
        String sql = "DELETE FROM surat_perintah WHERE id_sp=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, idSP);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createNomorBerkas(SuratPerintah suratPerintah){
        NomorBerkasDao nomorBerkasDao = new NomorBerkasDaoImpl();
        for(TimSP tim : suratPerintah.getListTim()){
            for(WajibPajak wp : tim.getListWP()){
                nomorBerkasDao.createNomorSurat(suratPerintah.getIdSP(), wp.getNpwpd());
            }
        }
    }

    private SuratPerintah setSuratPerintah(ResultSet rs) throws SQLException {
        SuratPerintah suratPerintah = new SuratPerintah();

        suratPerintah.setIdSP(rs.getString("id_sp"));
        suratPerintah.setNomorSurat(rs.getString("nomor_surat"));
        suratPerintah.setNomorUrut(rs.getString("nomor_urut"));
        suratPerintah.setNomorSK(rs.getString("nomor_sk"));
        suratPerintah.setTanggalSK(rs.getString("tanggal_sk"));
        suratPerintah.setPemberiSK(rs.getString("pemberi_sk"));
        suratPerintah.setTahunAnggaranSK(rs.getInt("tahun_anggaran_sk"));
        suratPerintah.setTahunAnggaranBiaya(rs.getInt("tahun_anggaran_biaya"));
        suratPerintah.setNomorSuratBiaya(rs.getString("nomor_surat_biaya"));
        suratPerintah.setTanggalBiaya(rs.getString("tanggal_biaya"));
        suratPerintah.setPemberiSP(setPegawai(rs.getString("pemberi_sp")));
        suratPerintah.setMasaPajakAwal(rs.getString("masa_pajak_awal"));
        suratPerintah.setMasaPajakAkhir(rs.getString("masa_pajak_akhir"));
        suratPerintah.setTahap(rs.getShort("tahap"));
        suratPerintah.setLamaPelaksanaan(rs.getShort("lama_pelaksanaan"));
        suratPerintah.setTempat(rs.getString("tempat"));
        suratPerintah.setTanggalSurat(rs.getString("tanggal_surat"));
        suratPerintah.setListTim(getListTim(suratPerintah.getIdSP()));

        return suratPerintah;
    }

    private String setListPegawaiToString(List<Pegawai> listPegawai){
        String result = "";
        if(listPegawai.size() > 0){
            result += listPegawai.get(0).getNipPegawai() + "<b>"
                    + listPegawai.get(0).getNamaPegawai() + "<b>"
                    + listPegawai.get(0).getPangkat() + "<b>"
                    + listPegawai.get(0).getGolongan() + "<b>"
                    + listPegawai.get(0).getJabatanTim() + "<b>"
                    + listPegawai.get(0).getJabatanDinas();
            for(int i = 1; i < listPegawai.size(); i++){
                result += "<p>" + listPegawai.get(i).getNipPegawai() + "<b>"
                        + listPegawai.get(i).getNamaPegawai() + "<b>"
                        + listPegawai.get(i).getPangkat() + "<b>"
                        + listPegawai.get(i).getGolongan() + "<b>"
                        + listPegawai.get(i).getJabatanTim() + "<b>"
                        + listPegawai.get(0).getJabatanDinas();
            }
        }
        return result;
    }

    private String setPegawaiToString(Pegawai pegawai){
        String result = pegawai.getNipPegawai() + "<b>"
                + pegawai.getNamaPegawai() + "<b>"
                + pegawai.getPangkat() + "<b>"
                + pegawai.getGolongan() + "<b>"
                + pegawai.getJabatanTim() + "<b>"
                + pegawai.getJabatanDinas();

        return result;
    }

    private String setListWPTpString(List<WajibPajak> listWP){
        String result = "";
        if (listWP.size() > 0){
            result += listWP.get(0).getNpwpd();
            for (int i=1; i < listWP.size(); i++){
                result += "<p>" + listWP.get(i).getNpwpd();
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
        return new Pegawai("", attrPart[0], attrPart[1], attrPart[3], attrPart[2], attrPart[4], attrPart[5]);
    }

    private List<WajibPajak> setStringToWP(String stringWP, String idSP){
        String[] wpParts = stringWP.split("<p>");
        List<WajibPajak> listWP = new ArrayList<>();
        WajibPajakDao wajibPajakDao = new WajibPajakDaoImpl();
        BerkasPersiapanDao berkasPersiapanDao = new BerkasPersiapanImpl();
        NomorBerkasDao nomorBerkasDao = new NomorBerkasDaoImpl();

        for(String part : wpParts){
            WajibPajak wajibPajak = wajibPajakDao.getWPById(part);
            berkasPersiapanDao.getBerkasPersiapan(idSP, wajibPajak);
            wajibPajak.setNomorBerkas(nomorBerkasDao.getNomotBerkas(idSP, wajibPajak.getNpwpd()));
            listWP.add(wajibPajak);
        }

        return listWP;
    }
}
