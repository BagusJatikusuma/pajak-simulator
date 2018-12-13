/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.model.Rekapitulasi;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.RekapitulasiService;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.ConverterHelper;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipPelaksanaanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipTablePelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanTimWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.wrapper.RekapitulasiWrapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class FormDaftarRekapitulasiPerbandinganPendapatanController implements Initializable {

    @FXML private TableView arsipPelaksanaanTable;
    @FXML private TableColumn no;
    @FXML private TableColumn bulan;
    @FXML private TableColumn omzetHasilPemeriksaan;
    @FXML private TableColumn omzetDiLaporkan;
    @FXML private TableColumn action;
    
    @FXML private Label namaTimLabel;
    @FXML private Label namaWPLabel;
    @FXML private Label npwpdLabel;
    @FXML private Label nomorTanggalSPField;
    
    @FXML private ChoiceBox metodeHitungDendaField;
    
    @FXML private Button backBtn;
    @FXML private Button batalBtn;
    @FXML private Button cetakCoverKKPBtn;
    
    private ObservableList<ArsipPelaksanaanTableWrapper> dataCollection;
    
    private RekapitulasiService rekapitulasiService;
    private ReportService reportService;
    private Map<String, Rekapitulasi> rekapitulasiMapper = new HashMap<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Boolean isHistory = (Boolean) SessionProvider.getGlobalSessionsMap().get("is_history");
        backBtn.setVisible(!isHistory.booleanValue());
        batalBtn.setVisible(isHistory.booleanValue());
        
        initLabel();
        populateChoiceBox();
        populateData();
        associateDataWithColumn();
        arsipPelaksanaanTable.setItems(dataCollection);
    }

    public void generate() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        rekapitulasiService = ServiceFactory.getRekapitulasiService();
        //default 10%
//        rekapitulasiService.calculateRekapitulasi(pelaksanaanWrapper.getRekapitulasiWrapper(), (float) 0.1);
        
    }
    
    public void openPersuratan() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        rekapitulasiService = ServiceFactory.getRekapitulasiService();
        float pajakPersentase;
        switch (pelaksanaanWrapper.getWpSelected().getJenisWp()) {
            case 0 : //restoran
                pajakPersentase = (float)0.1;
                break;
            case 1 : //hotel
                pajakPersentase = (float)0.1;
                break;
            case 2 : //parkiran
                pajakPersentase = (float)0.25;
                break;
            case 3 : //hiburan default jadikan 10%
                pajakPersentase = (float)0.1;
                break;
            case 4 : //penerangan jalan default jadikan 10%
                pajakPersentase = (float)0.1;
                break;
            default://unidentified, default jadikan 10%
                pajakPersentase = (float)0.1;
                break;
        }
        
        boolean thereIsNull = false;
        for (Rekapitulasi rekap : pelaksanaanWrapper.getRekapitulasiWrapper().getListRekapitulasi()) {
            if (rekap.getOmzetHasilPeriksa() == null || rekap.getOmzetLaporan() == null) {
                thereIsNull = true;
                break;
            }
        }
        
        if (!thereIsNull) {
            rekapitulasiService.calculateRekapitulasi(
                    pelaksanaanWrapper.getRekapitulasiWrapper(), 
                    pajakPersentase,
                    (metodeHitungDendaField.getValue().equals("manual"))?true:false);
        }
        
        Pane rootpaneFormPelaksanaan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_pelaksanaan_ui");
        rootpaneFormPelaksanaan.getChildren().remove(0);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormSuratPelaksanaanUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPelaksanaan.getChildren().add(contentPane);
    }
    
    public void backToFormPelaksanaanContent() {
        Pane rootpaneFormPelaksanaan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_pelaksanaan_ui");
        rootpaneFormPelaksanaan.getChildren().remove(0);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormPelaksanaanWPUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPelaksanaan.getChildren().add(contentPane);
    }
    
    private void populateChoiceBox() {
        metodeHitungDendaField.setItems(FXCollections.observableArrayList("otomatis","manual"));
    }

    private void populateData() {
        dataCollection = FXCollections.observableArrayList();
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        rekapitulasiService = ServiceFactory.getRekapitulasiService();
        
        Boolean isHistory = (Boolean) SessionProvider.getGlobalSessionsMap().get("is_history");
        if (isHistory) {
            //indikasi manual adalah jumlah total denda == 0
            rekapitulasiService.getTotalRekapitulasi(pelaksanaanWrapper.getRekapitulasiWrapper());
            if (pelaksanaanWrapper.getRekapitulasiWrapper().getTotalDenda() == 0) {
                metodeHitungDendaField.setValue("manual");
            }
            else metodeHitungDendaField.setValue("otomatis");
        }
        else metodeHitungDendaField.setValue("otomatis");
        
        int index = 1;
        NumberFormat anotherFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
        DecimalFormat formatter = (DecimalFormat) anotherFormat;
        for (Rekapitulasi rek : pelaksanaanWrapper.getRekapitulasiWrapper().getListRekapitulasi()) {
            Button btn = new Button("atur");
            ArsipPelaksanaanTableWrapper objTable 
                    = new ArsipPelaksanaanTableWrapper(
                            String.valueOf(index),
                            rek.getBulan(),
                            (rek.getOmzetHasilPeriksa()!=null)
                                    ?"Rp"+formatter.format(new BigDecimal(rek.getOmzetHasilPeriksa().doubleValue())):"-",
                            (rek.getOmzetLaporan()!=null)
                                    ?"Rp"+formatter.format(new BigDecimal(rek.getOmzetLaporan().doubleValue())):"-",
                            btn
                    );
            dataCollection.add(objTable);
            rekapitulasiMapper.put(String.valueOf(index), rek);
            index++;
        }
        
        for (ArsipPelaksanaanTableWrapper obj:dataCollection) {
            Button btn = obj.getAction();
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    SessionProvider
                            .getGlobalSessionsMap()
                            .put("selected_bulan_rekapitulasi",
                                            rekapitulasiMapper.get(obj.getNo()));
                    SessionProvider
                            .getTableViewSessionMap()
                            .put("tabel_rekapitulasi",
                                            arsipPelaksanaanTable);
                    SessionProvider
                            .getGlobalSessionsMap()
                            .put("index_data_tabel_rekapitulasi",
                                            obj.getNo());
                    
                    Pane formAturBulanRekapitulasi = null;
                    try {
                        formAturBulanRekapitulasi = FXMLLoader
                                .load(getClass().getClassLoader().getResource("fxml/FormDaftarRekapitulasiPerbandinganPendapatanDetail.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Form Atur Omzet");
                    stage.setScene(new Scene(formAturBulanRekapitulasi));
                    
                    stage.initStyle(StageStyle.UTILITY);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    
                }
            });
        }
        
    }

    private void associateDataWithColumn() {
        no.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("no"));
        bulan.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("bulan"));
        omzetHasilPemeriksaan.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("omzethasilPemeriksaan"));
        omzetDiLaporkan.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("omzetDiLaporkan"));
        action.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("action"));
    }
    
    private void initLabel() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        namaTimLabel.setText(pelaksanaanWrapper.getTimSelected().getNamaTim());
        namaWPLabel.setText(pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
        npwpdLabel.setText(pelaksanaanWrapper.getWpSelected().getNpwpd());
        DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
        nomorTanggalSPField.setText(
                "800/"+pelaksanaanWrapper.getPersiapanWrapper().getNomorSurat()+"/Bapenda"
                +", "+formatter.format(pelaksanaanWrapper.getPersiapanWrapper().getTanggalPengesahan()));
    }
    
    public void printKKPdanSuratSurat(){
        System.out.println("KLIK GENERATE SAVE");
        
        reportService = ServiceFactory.getReportService();
        System.out.println("finishPersiapan");
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("pelaksanaan_wrapper");
        
        Map<String, RekapitulasiWrapper> rekapMapperHistory
                = (Map<String, RekapitulasiWrapper>) SessionProvider.getGlobalSessionsMap()
                        .get("rekap_wrapper_history");
        boolean savable = true;
        if (rekapMapperHistory != null) {
                if (rekapMapperHistory.get(
                        pelaksanaanWrapper.getPersiapanWrapper().getIdSP()
                        +pelaksanaanWrapper.getTimSelected().getIdTim()
                        +pelaksanaanWrapper.getWpSelected().getNpwpd()) != null)
                    savable = false;
        }
        
        //aturan : tidak bisa update rekapitulasi jika sudah diset sebelumnya
        float pajakPersentase;
        switch (pelaksanaanWrapper.getWpSelected().getJenisWp()) {
            case 0 : //restoran
                pajakPersentase = (float)0.1;
                break;
            case 1 : //hotel
                pajakPersentase = (float)0.1;
                break;
            case 2 : //parkiran
                pajakPersentase = (float)0.25;
                break;
            case 3 : //hiburan default jadikan 10%
                pajakPersentase = (float)0.1;
                break;
            case 4 : //penerangan jalan default jadikan 10%
                pajakPersentase = (float)0.1;
                break;
            default://unidentified, default jadikan 10%
                pajakPersentase = (float)0.1;
                break;
        }
        rekapitulasiService.calculateRekapitulasi(
                pelaksanaanWrapper.getRekapitulasiWrapper(), 
                pajakPersentase,
                (metodeHitungDendaField.getValue().equals("manual"))?true:false);
        if (savable) {
            rekapitulasiService = ServiceFactory.getRekapitulasiService();
            
            rekapitulasiService.createRekapitulasi(pelaksanaanWrapper.getRekapitulasiWrapper());
            //edit disini
            rekapMapperHistory.put(
                    pelaksanaanWrapper.getPersiapanWrapper().getIdSP()
                        +pelaksanaanWrapper.getTimSelected().getIdTim()
                        +pelaksanaanWrapper.getWpSelected().getNpwpd(), 
                    pelaksanaanWrapper.getRekapitulasiWrapper());
        }
        //update
        else {
            rekapitulasiService.deleteRekapitulasi(
                    pelaksanaanWrapper.getPersiapanWrapper().getIdSP(), 
                    pelaksanaanWrapper.getWpSelected().getNpwpd());
            
            rekapitulasiService.createRekapitulasi(pelaksanaanWrapper.getRekapitulasiWrapper());
            
            rekapMapperHistory.put(
                    pelaksanaanWrapper.getPersiapanWrapper().getIdSP()
                        +pelaksanaanWrapper.getTimSelected().getIdTim()
                        +pelaksanaanWrapper.getWpSelected().getNpwpd(), 
                    pelaksanaanWrapper.getRekapitulasiWrapper());
        }
        
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
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createKertasPemeriksaanPajak(pelaksanaanWrapper, 
                        ServiceFactory.getSuratPerintahService().getTimSP(
                        pelaksanaanWrapper.getPersiapanWrapper().getIdSP(), 
                        pelaksanaanWrapper.getTimSelected().getIdTim()));
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Pane rootpane = ComponentCollectorProvider.getComponentFXMapper().get("root_pane");
                        rootpane.getChildren().remove(1);
                        Pane contentPane = null;
                        try {
                            contentPane
                                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/PelaksanaanUI.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        rootpane.getChildren().add(contentPane);
                        
                        stage.close();                    
                        
                    }
                });
            }
            
        };
        t.start();
        
    }
    
    public void cetakCoverKKP(){
        System.out.println("KLIK CETAK COVER KKP");
        
        reportService = ServiceFactory.getReportService();
        System.out.println("finishPersiapan");
        final PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("pelaksanaan_wrapper");
        
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
        Thread t = new Thread(){
            @Override
            public void run() {
                reportService.createCoverTemplate1(pelaksanaanWrapper);
                
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
       
    public void batalEvent(){
        //remove session
        SessionProvider.getGlobalSessionsMap().remove("pelaksanaan_wrapper");
        Stage stage = (Stage) batalBtn.getScene().getWindow();
        stage.close();
    }
    
    private int getDifferenceDatePersiapanWrapperinMonth(PersiapanWrapper persiapanWrapper) {
        int diffMonth = 0;
        
        if (persiapanWrapper.getMasaPajakAwalTahun()
                .equals(persiapanWrapper.getMasaPajakAkhirTahun())) {
            diffMonth = persiapanWrapper.getMasaPajakAkhirbulan() - persiapanWrapper.getMasaPajakAwalBulan();
        }
        else {
            diffMonth = (11-persiapanWrapper.getMasaPajakAwalBulan()) + (persiapanWrapper.getMasaPajakAkhirbulan()+1);
        }
        
        return diffMonth;
    }
    
}

//baris kode pengetesan
//System.out.println("awal "
//                +pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()
//                +" bulan "
//                +pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun()
//                +" tahun ");
//        System.out.println("akhir "
//                +pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()
//                +" bulan "
//                +pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun()
//                +" tahun ");omzetDilaporkan
//        System.out.println(getDifferenceDatePersiapanWrapperinMonth(pelaksanaanWrapper.getPersiapanWrapper()));
