/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.TimSP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.SuratPerintahService;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.ConverterHelper;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.view.util.modelview.PersiapanPajakPOJO;
import com.bekasidev.app.view.util.modelview.WajibPajakModelView;
import com.bekasidev.app.viewfx.javafxapplication.content.pajakrestoran.PajakRestoranUIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipTablePersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PajakRestoranTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.ObservableArrayList;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class PersiapanUIController implements Initializable {
    @FXML private TableView arsipPersiapanTable;
    private TableColumn id,
                        tanggalDiBuat,
                        statusSelesai,
                        action;
    private ObservableList<ArsipTablePersiapanWrapper> dataCollection;
    private List<ArsipTablePersiapanWrapper> dataListFromService;
    private List<Button> btnList;
    private Map<String, SuratPerintah> suratPerintahMapper = new HashMap<>();
    
    private SuratPerintahService suratPerintahService;
    private ReportService reportService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("window height "+SessionProvider.getGlobalSessionsMap().get("screen_height"));
        
        suratPerintahService = ServiceFactory.getSuratPerintahService();
        
        initDataFromService();
        addFromFXML();
        populateData();
        associateDataWithColumn();
        arsipPersiapanTable.setItems(dataCollection);

    }

    private void addFromFXML() {
        id 
                = TableHelper.getTableColumnByName(arsipPersiapanTable, "ID");
        tanggalDiBuat 
                = TableHelper.getTableColumnByName(arsipPersiapanTable, "TANGGAL SURAT PERINTAH");
        statusSelesai 
                = TableHelper.getTableColumnByName(arsipPersiapanTable, "NOMOR SURAT PERINTAH");
        action 
                = TableHelper.getTableColumnByName(arsipPersiapanTable, "Action");
    }
    
    private void populateData() {
        dataCollection = new ObservableArrayList<>();
        int i = 1;
        for (final ArsipTablePersiapanWrapper obj
                : dataListFromService) {
            Button btn = new Button("Lihat Detail");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    openSPHandler(event, obj);
                }
            });

            dataCollection.add(new ArsipTablePersiapanWrapper(
                    obj.getId(),
                    obj.getTanggalDiBuat(),
                    obj.getStatusSelesai(),
                    btn
            ));
            i++; 
        }
    }
    
    private void associateDataWithColumn() {
        id.setCellValueFactory(new PropertyValueFactory<PajakRestoranTableWrapper, String>("id"));
        tanggalDiBuat.setCellValueFactory(new PropertyValueFactory<PajakRestoranTableWrapper, String>("tanggalDiBuat"));
        statusSelesai.setCellValueFactory(new PropertyValueFactory<PajakRestoranTableWrapper, String>("statusSelesai"));
        action.setCellValueFactory(new PropertyValueFactory<PajakRestoranTableWrapper, String>("action"));
    }
    
    private void initDataFromService() {
        dataListFromService = new ArrayList<>();
        List<SuratPerintah> suratPerintahList
                = suratPerintahService.getAllSuratPerintah();
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM YYYY", Locale.forLanguageTag("id-ID"));
        for (SuratPerintah sp : suratPerintahList) {
            dataListFromService.add(new ArsipTablePersiapanWrapper(
                    sp.getIdSP(),
                    (sp.getTanggalSurat()!=null)?dateFormat.format(new Date(Long.valueOf(sp.getTanggalSurat()))):"-",
                    (sp.getNomorUrut()!=null)?"800/"+sp.getNomorUrut()+"/Bapenda":"-",
                    null
            ));
            suratPerintahMapper.put(sp.getIdSP(), sp);
        }
//        for (int i=1; i<=100; i++) {
//            dataListFromService.add(new ArsipTablePersiapanWrapper(
//                    "id "+i,
//                    "30 Februari 2012",
//                    "Selesai",
//                    null
//            ));
//        }
        
    }
    
    public void addDokumenPersiapan() {
        PersiapanWrapper persiapanWrapper
                = new PersiapanWrapper();
        SessionProvider.getGlobalSessionsMap()
                        .put("persiapan_wrapper", persiapanWrapper);
        
        Pane formPersiapanUI = null;
        try {
            formPersiapanUI = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormPersiapanUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        formPersiapanUI.setMaxWidth(primaryScreenBounds.getWidth()*0.513);
        formPersiapanUI.setPrefWidth(primaryScreenBounds.getWidth()*0.513);
        formPersiapanUI.setMinWidth(primaryScreenBounds.getWidth()*0.513);
        stage.setTitle("Form Persiapan Pemeriksaan WP");
        stage.setScene(new Scene(formPersiapanUI));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    public void cariArsip() {
        System.out.println("masuk tombol");
        reportService.testGamber();
    }
    
    public void openSPHandler(ActionEvent event, ArsipTablePersiapanWrapper obj) {
        initDataFromService();
        
        PersiapanWrapper persiapanWrapper
                = ConverterHelper
                        .convertSuratPerintahToPersiapanWrapper(suratPerintahMapper.get(obj.getId()));
        if (persiapanWrapper.getTanggalPengesahan() != null)
            System.out.println("tanggal "+persiapanWrapper.getTanggalPengesahan().getTime());
        else
            System.out.println("tanggal is null");
        SessionProvider.getGlobalSessionsMap()
                        .put("persiapan_wrapper", persiapanWrapper);
        
        Pane formPersiapanUI = null;
        try {
            formPersiapanUI = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormPersiapanUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        formPersiapanUI.setMaxWidth(primaryScreenBounds.getWidth()*0.513);
        formPersiapanUI.setPrefWidth(primaryScreenBounds.getWidth()*0.513);
        formPersiapanUI.setMinWidth(primaryScreenBounds.getWidth()*0.513);
        
        stage.setTitle("Form Persiapan Pemeriksaan WP");
        stage.setScene(new Scene(formPersiapanUI));
        System.out.println("tinggi scene : " + stage.getScene().getHeight());
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
}
