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
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
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
import java.time.LocalDate;
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
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    
    @FXML private TextField tahunAnggaranField;
    
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
        
        setFieldFormat();
        initDefaultYear();
        populateData();
        associateDataWithColumn();
        
        evaluasiTable.setItems(dataCollection);
    }

    private void setFieldFormat() {
        tahunAnggaranField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tahunAnggaranField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    
    private void initDefaultYear() {
        tahunAnggaranField.setText(String.valueOf(LocalDate.now().getYear()));
    }

    private void populateData() {
        dataCollection = FXCollections.observableArrayList();
//        List<SuratPerintah> suratPerintahs = suratPerintahService.getAllSuratPerintah();
        List<SuratPerintah> suratPerintahs 
                = suratPerintahService.getSuratPerintahByTahun(Integer.parseInt(tahunAnggaranField.getText()));
        int index = 1;
        for (SuratPerintah sp : suratPerintahs) {
            for (TimSP timSP : sp.getListTim()) {
                for (WajibPajak wp : timSP.getListWP()) {
                    Button btn = new Button("Lihat Detail");
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
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.initStyle(StageStyle.UTILITY);
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
    
    public void cariSPBasedTahun() {
        List<SuratPerintah> suratPerintahs = new ArrayList<>();
        if (!tahunAnggaranField.getText().equals(""))
            suratPerintahs = suratPerintahService.getSuratPerintahByTahun(Integer.parseInt(tahunAnggaranField.getText()));
        
        if (suratPerintahs.isEmpty()) {
            //beri popup notif bahwa data tidak ada
            showErrorSearchNotif();
            return;
        }
        //reset data collection dan pelaporan mapper
        dataCollection.removeAll(dataCollection);
        pelaporanMapper.clear();
        
        int index = 1;
        for (SuratPerintah sp : suratPerintahs) {
            for (TimSP timSP : sp.getListTim()) {
                for (WajibPajak wp : timSP.getListWP()) {
                    Button btn = new Button("Lihat Detail");
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
                    
                    stage.initStyle(StageStyle.UTILITY);
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
        evaluasiTable.setItems(dataCollection);
        evaluasiTable.refresh();
    }
    
    public void printEvaluasi() {
        SessionProvider
                .getGlobalSessionsMap()
                .put("pelaporan_mapper", pelaporanMapper);
        
        Pane formPrintLaporan = null;
        try {
            formPrintLaporan = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormPrintRoot.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Form Print Laporan");
        stage.setScene(new Scene(formPrintLaporan));
        
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    public void printEvaluasiBackUp() {
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
        
        
//        Pane contentPane = null;
//        try { 
//            contentPane
//                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/LoadingTest.fxml"));
//        } catch (IOException ex) {
//            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        Stage stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setScene(new Scene(contentPane));
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.show();
//        
//        Task<Void> task = new Task<Void>() {
//            @Override
//            public Void call() throws Exception {
//                System.out.println("run thread....");
//                for (int i = 1; i < 10; i++) {
//                    Thread.sleep(1000);
//                    System.out.println("thread process "+i);
//                }
//                return null ;
//            }
//        };
//        
//        task.setOnSucceeded(event -> {
//            System.out.println("close thread");
//        });
//        
//        new Thread(task).run();
        
    }
    
    public void printKopLHP() {
        System.out.println("KLIK CETAK COVER");
        
        if (evaluasiTable.getSelectionModel().getSelectedItem() == null) {
            showErrorNotif();
            return;
        }
        
        reportService = ServiceFactory.getReportService();
        System.out.println("finishPersiapan");
        EvaluasiTableWrapper wrapper = (EvaluasiTableWrapper) evaluasiTable.getSelectionModel().getSelectedItem();
        PelaporanWrapper pelaporanWrapper 
                = pelaporanMapper.get(wrapper.getNo());
        
        Stage stage = initLoadingScreen();
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createCoverTemplate2(pelaporanWrapper);
                
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
    
    private void showErrorNotif() {
        SessionProvider.getGlobalSessionsMap().put("notif_message_popup", "Anda belum memilih wajib pajak");
        Pane popup = null;
        try {
            popup = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/PopupPaneUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(popup));
        stage.show();
    }
    
    private void showErrorSearchNotif() {
        SessionProvider.getGlobalSessionsMap().put("notif_message_popup", "Data tidak ditemukan");
        Pane popup = null;
        try {
            popup = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/PopupPaneUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(popup));
        stage.show();
    }
    
}
