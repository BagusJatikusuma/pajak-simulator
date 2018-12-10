/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaporan;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.TimSP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.RekapitulasiService;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.ColumnsPelaporan;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaporanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.SPColumnPelaporan;
import com.bekasidev.app.viewfx.javafxapplication.model.TimColumnPelaporan;
import com.bekasidev.app.wrapper.RekapitulasiWrapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormPrintLaporanController implements Initializable {

    @FXML private RadioButton tahapRB;
    @FXML private RadioButton tahunAnggaranRB;
    
    private ReportService reportService;
    private RekapitulasiService rekapitulasiService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        reportService = ServiceFactory.getReportService();
        rekapitulasiService = ServiceFactory.getRekapitulasiService();
        setToGroup();
        setDefaultSelected();
    }

    private void setDefaultSelected() {
        tahapRB.setSelected(true);
    }
    
    private void setToGroup() {
        final ToggleGroup group = new ToggleGroup();
        
        tahapRB.setToggleGroup(group);
        tahunAnggaranRB.setToggleGroup(group);
    }
    
    public void nextStagePrintPelaporan() {
        if (tahapRB.isSelected()) {
            Pane rootpaneFormPelaksanaan = ComponentCollectorProvider.getComponentFXMapper().get("root_pane_form_print_laporan");
            rootpaneFormPelaksanaan.getChildren().remove(0);

            Pane formPrintLaporanTahapan = null;
            try { 
                formPrintLaporanTahapan
                        = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormPrintLaporanTahapan.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
            }
            rootpaneFormPelaksanaan.getChildren().add(formPrintLaporanTahapan);
        }
        else if (tahunAnggaranRB.isSelected()) {
            printWholeTahap();
        }
    }
    
    private void printWholeTahap() {
        Map<String, PelaporanWrapper> pelaporanMapper
            = (Map<String, PelaporanWrapper>) SessionProvider
                .getGlobalSessionsMap()
                .get("pelaporan_mapper");
        List<PelaporanWrapper> pelaporanWrappers
                = new ArrayList<>(pelaporanMapper.values());
        List<SuratPerintah> suratPerintahList = getSuratPerintahFromPelaporanWrapper(pelaporanWrappers);
        
        NumberFormat anotherFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
        DecimalFormat formatter = (DecimalFormat) anotherFormat;
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
        
        SessionProvider
                .getGlobalSessionsMap()
                .put("tahun_anggaran", Integer.valueOf(suratPerintahList.get(0).getTahunAnggaranSK()));
        
        List<SPColumnPelaporan> spColumns = new ArrayList<>();
        for (SuratPerintah sp : suratPerintahList) {
            SPColumnPelaporan objSPColumn = new SPColumnPelaporan();
            
            List<TimColumnPelaporan> timColumnPelaporans = new ArrayList<>();
            
            for (TimSP timSP : sp.getListTim()) {
                TimColumnPelaporan objTim = new TimColumnPelaporan();
                
                List<Pegawai> pegawais = timSP.getListAnggota();
                List<WajibPajak> wajibPajaks = timSP.getListWP();
                List<ColumnsPelaporan> columnsPelaporans = new ArrayList<>();
                
                int jumlah = pegawais.size();
                if(jumlah < wajibPajaks.size()){
                    jumlah = wajibPajaks.size();
                }
                
                for (int i = 0; i < jumlah; i++) {
                    ColumnsPelaporan obj = new ColumnsPelaporan();
                    
                    if(i < wajibPajaks.size()){
                        obj.setWajibPajak(wajibPajaks.get(i));
                        obj.setNamaWajibPajak(wajibPajaks.get(i).getNamaWajibPajak());
                        obj.setNpwpdWajibPajak(wajibPajaks.get(i).getNpwpd());
                        RekapitulasiWrapper rekapWrapper
                                = rekapitulasiService.getRekapitulasi(
                                        sp.getIdSP(), 
                                        wajibPajaks.get(i).getNpwpd());
                        if (!rekapWrapper.getListRekapitulasi().isEmpty()) {
                            rekapitulasiService.getTotalRekapitulasi(rekapWrapper);
                            obj.setTemuanHasil(formatter.format(new BigDecimal(rekapWrapper.getTotalJumlah().doubleValue())) + ",00");
                        } else obj.setTemuanHasil("Belum ada temuan");
                        
                        if (wajibPajaks.get(i).getNomorBerkas().getNomorSKPD()!= null)
                            obj.setNomorSKPD(wajibPajaks.get(i).getNomorBerkas().getNomorSKPD());
                        else
                            obj.setNomorSKPD("");
                        
                        if (wajibPajaks.get(i).getNomorBerkas().getTanggalSKPD()!=null)
                            obj.setTanggalSKPD(sdf.format(new Date(Long.valueOf(wajibPajaks.get(i).getNomorBerkas().getTanggalSKPD()))));
                        else
                            obj.setTanggalSKPD("");
                        
                        obj.setKeterangan("");
                    } else {
                        obj.setWajibPajak(null);
                        obj.setNamaWajibPajak("");
                        obj.setNpwpdWajibPajak("");
                        obj.setTemuanHasil("");
                        obj.setNomorSKPD("");
                        obj.setTanggalSKPD("");
                        obj.setKeterangan("");
                    }

                    if (i < pegawais.size()) {
                        obj.setPegawai(pegawais.get(i));
                        obj.setNamaPegawai(pegawais.get(i).getNamaPegawai());
                        obj.setNipPegawai(pegawais.get(i).getNipPegawai());
                    } else {
                        obj.setPegawai(null);
                        obj.setNamaPegawai("");
                        obj.setNipPegawai("");
                    }
                    
                    columnsPelaporans.add(obj);
                    
                }
                
                objTim.setNamaTim(timSP.getNamaTim());
                objTim.setColumnsPelaporanList(columnsPelaporans);
                
                timColumnPelaporans.add(objTim);
            }
            
            String tahapTerbilang = "";
            switch(sp.getTahap()) {
                case 1 : tahapTerbilang = "Pertama"; break;
                case 2 : tahapTerbilang = "Kedua"; break;
                case 3 : tahapTerbilang = "Ketiga"; break;
                case 4 : tahapTerbilang = "Keempat"; break;
                case 5 : tahapTerbilang = "Kelima"; break;
                case 6 : tahapTerbilang = "Keenam"; break;
                case 7 : tahapTerbilang = "Ketujuh"; break;
                case 8 : tahapTerbilang = "Kedelapan"; break;
                case 9 : tahapTerbilang = "Kesembilan"; break;
                case 10 : tahapTerbilang = "Kesepuluh"; break;
                case 11: tahapTerbilang = "Kesebelas"; break;
                case 12 : tahapTerbilang = "Keduabelas"; break;
                default : String.valueOf(sp.getTahap()); break;
            }
            objSPColumn.setTahap(tahapTerbilang);
            objSPColumn.setTimColumnPelaporans(timColumnPelaporans);
            
            spColumns.add(objSPColumn);
            
        }
        
        SessionProvider.getGlobalSessionsMap().put("evaluasi_wrapper", spColumns);
        
        Stage stage = initLoadingScreen();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createLaporanEvaluasi(new PelaporanWrapper());
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
            
        };
        t.start();
    }
    
    private List<SuratPerintah> getSuratPerintahFromPelaporanWrapper(List<PelaporanWrapper> pelaporanWrappers) {
        List<SuratPerintah> suratPerintahList = new ArrayList<>();
        
        for (PelaporanWrapper pw : pelaporanWrappers) {
            boolean isExist = false;
            for (SuratPerintah sp : suratPerintahList) {
                if (sp.getIdSP().equals(pw.getSuratPerintahSelected().getIdSP())) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                suratPerintahList.add(pw.getSuratPerintahSelected());
            }
        }
        
        return suratPerintahList;
    }
    
    private Stage initLoadingScreen() {
        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/LoadingTest.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(contentPane));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        
        return stage;
    }
    
}
