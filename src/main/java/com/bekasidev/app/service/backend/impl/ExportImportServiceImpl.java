package com.bekasidev.app.service.backend.impl;

import com.bekasidev.app.dao.ExportImportDao;
import com.bekasidev.app.dao.RekapitulasiDao;
import com.bekasidev.app.dao.SuratPerintahDao;
import com.bekasidev.app.dao.impl.ExportImportDaoImpl;
import com.bekasidev.app.dao.impl.RekapitulasiDaoImpl;
import com.bekasidev.app.dao.impl.SuratPerintahDaoImpl;
import com.bekasidev.app.model.*;
import com.bekasidev.app.service.backend.ExportImportService;
import com.bekasidev.app.wrapper.ExportDokumenWrapper;
import com.bekasidev.app.wrapper.RekapitulasiExport;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExportImportServiceImpl implements ExportImportService {

    RekapitulasiDao rekapitulasiDao = new RekapitulasiDaoImpl();
    ExportImportDao exportImportDao = new ExportImportDaoImpl();
    SuratPerintahDao suratPerintahDao = new SuratPerintahDaoImpl();
    @Override
    public void exportData() throws IOException {
        List<SuratPerintah> listSP = suratPerintahDao.getSPForExport();
        ExportDokumenWrapper exportDokumenWrapper;
        List<String> lines = new ArrayList<>();
        lines.add(getIdSP(listSP));
        lines.add(getSqlSuratPerintah(listSP));
        lines.add(getSqlTimSp(listSP));
        lines.add(getSqlBerkasPersiapan(listSP));
        lines.add(getSqlNomorBerkas(listSP));
        lines.add(getSqlRekapitulasi(rekapitulasiDao.getAllRekapitulasi()));
        Path file = Paths.get("file-exported.sql");
        Files.write(file, lines, Charset.forName("UTF-8"));
        exportImportDao.setExported();
    }

    @Override
    public void importData() throws IOException {
        List<String> lines =  Files.readAllLines(Paths.get("file-exported.sql"));
        String sql = "";
        String sqlDeleteRekap = "", sqlDeleteBerkas = "", sqlDeleteNomor = "", sqlDeleteTim = "", sqlDeleteSP = "";
        String[] idSP = lines.get(0).split(",");
        for(String id : idSP){
            sqlDeleteRekap += "DELETE FROM rekapitulasi WHERE id_sp='" + id + "';";
            sqlDeleteBerkas += "DELETE FROM berkas_persiapan WHERE id_sp='" + id + "';";
            sqlDeleteNomor += "DELETE FROM nomor_berkas WHERE id_sp='" + id + "';";
            sqlDeleteTim += "DELETE FROM tim_perintah WHERE id_sp='" + id + "';";
            sqlDeleteSP += "DELETE FROM surat_perintah WHERE id_sp='" + id + "';";
        }
        exportImportDao.importData(sqlDeleteRekap);
        exportImportDao.importData(sqlDeleteBerkas);
        exportImportDao.importData(sqlDeleteNomor);
        exportImportDao.importData(sqlDeleteTim);
        exportImportDao.importData(sqlDeleteSP);
        for(int i =1; i < lines.size();i++){
            sql += lines.get(i);
        }
//        System.out.println(sql);
        exportImportDao.importData(sql);
    }

    private String getIdSP(List<SuratPerintah> listSP){
        String idSP = listSP.get(0).getIdSP();
        for(int i = 1; i < listSP.size(); i++){
            idSP += "," + listSP.get(i).getIdSP();
        }
        return idSP;
    }

    private String getSqlSuratPerintah(List<SuratPerintah> suratPerintahs){
        String sql = "";
        int count = 0;
        for(SuratPerintah sp : suratPerintahs){
            if(count != 0) sql += ",";
            else if(sql.isEmpty()) sql = "INSERT INTO surat_perintah VALUES";
            sql += "('"+ sp.getIdSP() +"',";
            if(sp.getNomorUrut() == null){
                sql += sp.getNomorSurat() +"," +
                        sp.getNomorUrut() + ",'";
            }else {
                sql += "'" + sp.getNomorSurat() +"','" +
                        sp.getNomorUrut() + "','";
            }
            sql +=  sp.getNomorSK() + "','" +
                    sp.getTanggalSK() + "','" +
                    sp.getPemberiSK() + "'," +
                    sp.getTahunAnggaranSK() + "," +
                    sp.getTahunAnggaranBiaya() + ",'" +
                    sp.getNomorSuratBiaya() + "','" +
                    sp.getTanggalBiaya() + "','" +
                    setPegawaiToString(sp.getPemberiSP()) + "','" +
                    sp.getMasaPajakAwal() + "','" +
                    sp.getMasaPajakAkhir() + "'," +
                    sp.getTahap() + "," +
                    sp.getLamaPelaksanaan() + ",'" +
                    sp.getTempat() + "','" +
                    sp.getTanggalSurat() +
                    1 + "')";
            count += 1;
        }
        if(!sql.isEmpty()) sql += ";";
        return sql;
    }

    private String getSqlTimSp(List<SuratPerintah> suratPerintahs){
        String sql = "";
        int count = 0;
        for(SuratPerintah sp : suratPerintahs){
            for(TimSP tim : sp.getListTim()){
                if(count > 0) sql += ",";
                else if(sql.isEmpty()) sql += "INSERT INTO tim_perintah VALUES";
                sql += "('"+ sp.getIdSP() +"','" +
                        setPegawaiToString(tim.getPenanggungJawab()) + "','" +
                        setPegawaiToString(tim.getSupervisor()) + "','" +
                        tim.getIdTim() + "','" +
                        tim.getNamaTim() + "','" +
                        setListPegawaiToString(tim.getListAnggota()) + "','" +
                        setListWPTpString(tim.getListWP()) + "')";
                count += 1;
            }
        }
        if(!sql.isEmpty()) sql += ";";
        return sql;
    }

    private String getSqlBerkasPersiapan(List<SuratPerintah> suratPerintahs){
        String sql = "";
        int count = 0;
        for(SuratPerintah sp : suratPerintahs){
            for(TimSP tim : sp.getListTim()){
                for(WajibPajak wp : tim.getListWP()){
                    if(wp.getListPinjaman().size() > 0){
                        if(count > 0) sql += ",";
                        else if(sql.isEmpty()) sql += "INSERT INTO berkas_persiapan VALUES";
                        sql += "('" + wp.getNpwpd() +
                                "','" + sp.getIdSP() +
                                "','" + setPinjamanToString(wp.getListPinjaman()) + "')";
                        count += 1;
                    }
                }
            }
        }
        if(!sql.isEmpty()) sql += ";";
        return sql;
    }

    private String getSqlNomorBerkas(List<SuratPerintah> suratPerintahs){
        String sql = "";
        int count = 0;
        for(SuratPerintah sp : suratPerintahs){
            for(TimSP tim : sp.getListTim()){
                for(WajibPajak wp : tim.getListWP()){
                    if(count > 0) sql += ",";
                    else if(sql.isEmpty()) sql += "INSERT INTO nomor_berkas VALUES";
                    sql += "('" + wp.getNpwpd() + "','" +
                            sp.getIdSP() + "',";
                    if(wp.getNomorBerkas().getNomorSuratHasil() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getNomorSuratHasil() + "',";
                    if(wp.getNomorBerkas().getTanggalSuratHasil() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getTanggalSuratHasil() + "',";
                    if(wp.getNomorBerkas().getNomorSuratPemberitahuan() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getNomorSuratPemberitahuan() + "',";
                    if(wp.getNomorBerkas().getTanggalSuratPemberitahuan() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getTanggalSuratPemberitahuan() + "',";
                    if(wp.getNomorBerkas().getNomorSuratPeminjaman() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getNomorSuratPeminjaman() + "',";
                    if(wp.getNomorBerkas().getTanggalSuratPeminjaman() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getTanggalSuratPeminjaman() + "',";
                    if(wp.getNomorBerkas().getNomorBeritaAcara() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getNomorBeritaAcara() + "',";
                    if(wp.getNomorBerkas().getTanggalBeritaAcara() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getTanggalBeritaAcara() + "',";
                    if(wp.getNomorBerkas().getNomorSKPD() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getNomorSKPD() + "',";
                    if(wp.getNomorBerkas().getTanggalSKPD() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getTanggalSKPD() + "',";
                    if(wp.getNomorBerkas().getNomorTeguran1() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getNomorTeguran1() + "',";
                    if(wp.getNomorBerkas().getTanggalTeguran1() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getTanggalTeguran1() + "',";
                    if(wp.getNomorBerkas().getNomorTeguran2() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getNomorTeguran2() + "',";
                    if(wp.getNomorBerkas().getTanggalTeguran2() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getTanggalTeguran2() + "',";
                    if(wp.getNomorBerkas().getJamTeguran2() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getJamTeguran2() + "',";
                    if(wp.getNomorBerkas().getTempatTeguran2() == null) sql += null + ",";
                    else sql += "'" + wp.getNomorBerkas().getTempatTeguran2() + "',";
                    if(wp.getNomorBerkas().getHariTeguran2() == null) sql += null + ")";
                    else sql += "'" + wp.getNomorBerkas().getHariTeguran2() + "')";
                    count +=  1;
                }
            }
        }
        if(!sql.isEmpty()) sql += ";";
        return sql;
    }

    private String getSqlRekapitulasi(List<Rekapitulasi> rekapitulasiExports){
        String sql = "";
        int count = 0;
        for(Rekapitulasi re : rekapitulasiExports){
            if(count > 0) sql += ",";
            else if(sql.isEmpty()) sql += "INSERT INTO rekapitulasi VALUES";
            sql += "('" + re.getIdSP() +
                    "','" + re.getIdWP() +
                    "','" + re.getBulan() +
                    "'," + re.getOmzetHasilPeriksa() +
                    "," + re.getPajakHasilPeriksa() +
                    "," + re.getOmzetLaporan() +
                    "," + re.getPajakDisetor() +
                    "," + re.getOmzet() +
                    "," + re.getPokokPajak() +
                    "," + re.getDenda() +
                    "," + re.getJumlah() +
                    "," + re.getPersentaseDenda() + ")";
            count += 1;
        }
        if(!sql.isEmpty()) sql += ";";
        return sql;
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

    private String setPinjamanToString(List<DokumenPinjaman> dokumenPinjamen){
        String result = dokumenPinjamen.get(0).getNamaDokumen();
        for(int i = 1; i<dokumenPinjamen.size();i++){
            result += "<p>" + dokumenPinjamen.get(i).getNamaDokumen();
        }

        return result;
    }
}
