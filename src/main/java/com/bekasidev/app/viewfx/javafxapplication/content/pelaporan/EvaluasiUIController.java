/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaporan;

import com.bekasidev.app.model.NomorBerkas;
import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.Rekapitulasi;
import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.TimSP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.NomorBerkasService;
import com.bekasidev.app.service.backend.RekapitulasiService;
import com.bekasidev.app.service.backend.SuratPerintahService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipTablePelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.ColumnsPelaporan;
import com.bekasidev.app.viewfx.javafxapplication.model.EvaluasiTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class EvaluasiUIController implements Initializable {

    @FXML private TableView evaluasiTable;
    @FXML private TableColumn no;
    @FXML private TableColumn tahapPemeriksaan;
    @FXML private TableColumn noSP;
    @FXML private TableColumn tim;
    @FXML private TableColumn wp;
    @FXML private TableColumn action;
    
    private ReportService reportService;
    private RekapitulasiService rekapitulasiService;
    private NomorBerkasService nomorBerkasService;
    
    private ObservableList<EvaluasiTableWrapper> dataCollection;
    private SuratPerintahService suratPerintahService;
    private Map<String, PelaporanWrapper> pelaporanMapper = new HashMap<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        suratPerintahService = ServiceFactory.getSuratPerintahService();
        reportService = ServiceFactory.getReportService();
        rekapitulasiService = ServiceFactory.getRekapitulasiService();
        nomorBerkasService = ServiceFactory.getNomorBerkasService();
        
        populateData();
        associateDataWithColumn();
        
        evaluasiTable.setItems(dataCollection);
    }    

    private void populateData() {
        dataCollection = FXCollections.observableArrayList();
        List<SuratPerintah> suratPerintahs = suratPerintahService.getAllSuratPerintah();
        int index = 1;
        for (SuratPerintah sp : suratPerintahs) {
            for (TimSP timSP : sp.getListTim()) {
                for (WajibPajak wp : timSP.getListWP()) {
                    Button btn = new Button("lihat detail");
                    dataCollection.add(new EvaluasiTableWrapper(
                        String.valueOf(index),
                        String.valueOf(sp.getTahap()),
                        "800/"+sp.getNomorUrut()+"/Bapenda",
                        timSP.getNamaTim(),
                        wp.getNamaWajibPajak(),
                        btn
                    ));
                    pelaporanMapper.put(
                            String.valueOf(index), new PelaporanWrapper(sp,timSP,wp));
                    index++;
                }
       
            }
            
        }
        for (EvaluasiTableWrapper etw : dataCollection) {
            Button btn = etw.getAction();
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    PelaporanWrapper wrapper = pelaporanMapper.get(etw.getNo());
                    SessionProvider
                            .getGlobalSessionsMap()
                            .put("pelaporan_wrapper", wrapper);
                    
                    Pane contentPane = null;
                    try { 
                        contentPane
                                = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/DetailEvaluasiUI.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Stage stage = new Stage();
                    stage.setTitle("Form Detail Evaluasi");
                    stage.setScene(new Scene(contentPane));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    
                }
            });
        }
        
        Collections.sort(dataCollection, new Comparator<EvaluasiTableWrapper>() {
            @Override
            public int compare(EvaluasiTableWrapper t, EvaluasiTableWrapper t1) {
                return Integer.valueOf(t.getTahapPemeriksaan())
                        .compareTo(Integer.valueOf(t1.getTahapPemeriksaan()));
            }
        });
        
    }

    private void associateDataWithColumn() {
        no.setCellValueFactory(new PropertyValueFactory<EvaluasiTableWrapper, String>("no"));
        tahapPemeriksaan.setCellValueFactory(new PropertyValueFactory<EvaluasiTableWrapper, String>("tahapPemeriksaan"));
        noSP.setCellValueFactory(new PropertyValueFactory<EvaluasiTableWrapper, String>("noSP"));
        tim.setCellValueFactory(new PropertyValueFactory<EvaluasiTableWrapper, String>("tim"));
        wp.setCellValueFactory(new PropertyValueFactory<EvaluasiTableWrapper, String>("wp"));
        action.setCellValueFactory(new PropertyValueFactory<EvaluasiTableWrapper, String>("action"));
    }
    
    public void printEvaluasi() {
        List<SuratPerintah> suratPerintahList = suratPerintahService.getAllSuratPerintah();
        
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
        
        for (SPColumnPelaporan spCol : spColumns) {
            for (TimColumnPelaporan timCol : spCol.getTimColumnPelaporans()) {
                for (ColumnsPelaporan col :timCol.getColumnsPelaporanList()) {
                        
                    System.out.println(
                            spCol.getTahap()
                            +"-"
                            +timCol.getNamaTim()
                            +"-"
                            +col.getNamaPegawai()
                            +"-"
                            +col.getNamaWajibPajak()
                            +"-"
                            +col.getTemuanHasil()
                            +"-"
                            +col.getNomorSKPD());
                    
                }
            }
            
        }
        SessionProvider.getGlobalSessionsMap().put("evaluasi_wrapper", spColumns);
        
        
        reportService.createLaporanEvaluasi(new PelaporanWrapper());
        
    }
    
    public void printKopLHP() {
        System.out.println("KLIK CETAK COVER");
        
        reportService = ServiceFactory.getReportService();
        System.out.println("finishPersiapan");
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("pelaksanaan_wrapper");
        
        reportService.createCoverTemplate2(pelaksanaanWrapper);
    }
    
}
