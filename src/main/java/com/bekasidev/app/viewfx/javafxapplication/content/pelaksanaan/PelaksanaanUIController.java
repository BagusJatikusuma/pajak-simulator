/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.model.Rekapitulasi;
import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.Tim;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.RekapitulasiService;
import com.bekasidev.app.service.backend.SuratPerintahService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.ConverterHelper;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipPelaksanaanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipTablePelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.NomorTanggalWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PajakRestoranTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.ObservableArrayList;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import com.bekasidev.app.wrapper.RekapitulasiWrapper;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
 * @author Bayu Arafli
 */
public class PelaksanaanUIController implements Initializable {
    @FXML private TableView arsipPelaksanaanTable;
    private TableColumn id,
                        idSP,
                        namaTim,
                        namaWP,
                        tanggalDiBuat,
                        action;
    private ObservableList<ArsipTablePelaksanaanWrapper> dataCollection;
    private List<ArsipTablePelaksanaanWrapper> dataListFromService;
    private List<Button> btnList;

    private SuratPerintahService suratPerintahService;
    private RekapitulasiService rekapitulasiService;
    private Map<String, PersiapanWrapper> persiapanWrapperMapper = new HashMap<>();
    private Map<String, PersiapanWrapper> persiapanWrapperMapper2 = new HashMap<>();
    private Map<String, Tim> timMapper = new HashMap<>();
    private Map<String, WajibPajak> wpMapper = new HashMap<>();
    private Map<String, RekapitulasiWrapper> rekapMapper = new HashMap<>();
    private Map<String, RekapitulasiWrapper> rekapMapperHistory = new HashMap<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initDataFromService();
        addFromFXML();
        populateData();
        associateDataWithColumn();
        initPersiapanWrapperList();
        
        arsipPelaksanaanTable.setItems(dataCollection);
    }
    
    private void addFromFXML() {
        id 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "ID");
        idSP 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "NO SP");
        namaTim 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "NAMA TIM");
        namaWP 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "NAMA WP");
        tanggalDiBuat 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "TANGGAL DIBUAT");
        action 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "Action");
    }
    
    private void populateData() {
        dataCollection = new ObservableArrayList<>();
        rekapitulasiService = ServiceFactory.getRekapitulasiService();
        suratPerintahService = ServiceFactory.getSuratPerintahService();
        
        List<PersiapanWrapper> persiapanWrappers = new ArrayList<>();
        int index = 1;
        for (SuratPerintah sp : suratPerintahService.getAllSuratPerintah()) {
            PersiapanWrapper persiapanWrapper = ConverterHelper.convertSuratPerintahToPersiapanWrapper(sp);
            persiapanWrappers.add(persiapanWrapper);
            
            for (TimWPWrapper timWP : persiapanWrapper.getTimWPWrappers()) {
                for (WajibPajak wp : timWP.getWajibPajaks()) {
                    
                    RekapitulasiWrapper rekapWrapper 
                        = rekapitulasiService.getRekapitulasi(
                                persiapanWrapper.getIdSP(), 
                                wp.getNpwpd());
                    if (!rekapWrapper.getListRekapitulasi().isEmpty()) {
                        System.out.println("test rekap "+rekapWrapper.getIdSP());
                        Button btn = new Button("lihat detail");

                        dataCollection.add(new ArsipTablePelaksanaanWrapper(
                                String.valueOf(index),
                                "800/"+persiapanWrapper.getNomorSurat()+"/Bapenda",
                                timWP.getTim().getNamaTim(),
                                wp.getNamaWajibPajak(),
                                "",
                                btn
                        ));
                        
                        persiapanWrapperMapper.put(rekapWrapper.getIdSP(), persiapanWrapper);
                        persiapanWrapperMapper2.put(String.valueOf(index), persiapanWrapper);
                        timMapper.put(String.valueOf(index), timWP.getTim());
                        wpMapper.put(String.valueOf(index), wp);
                        rekapMapper.put(String.valueOf(index), rekapWrapper);
                        
                        rekapMapperHistory.put(sp.getIdSP()+timWP.getTim().getIdTim()+wp.getNpwpd(), rekapWrapper);
                        
                        index++;
                    }
                    
                }
                
            }
            
        }
        //insert rekapWrapper history to session
        SessionProvider.getGlobalSessionsMap()
                        .put("rekap_wrapper_history", rekapMapperHistory);
        for (final ArsipTablePelaksanaanWrapper obj:dataCollection) {
            Button btn = obj.getAction();
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    openAturRekapitulasi(obj);
                }
            });
            
        }
        
//        int i = 1;
//        for (final ArsipTablePelaksanaanWrapper obj
//                : dataListFromService) {
//            Button btn = new Button("lihat detail");
//            btn.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//
//                }
//            });
//
//            dataCollection.add(new ArsipTablePelaksanaanWrapper(
//                    obj.getId(),
//                    obj.getIdSP(),
//                    obj.getNamaTim(),
//                    obj.getNamaWP(),
//                    obj.getTanggalDiBuat(),
//                    btn
//            ));
//            i++; 
//        }
    }
    
    private void initDataFromService() {
        dataListFromService = new ArrayList<>();
        for (int i=1; i<=100; i++) {
            dataListFromService.add(new ArsipTablePelaksanaanWrapper(
                    "id "+i,
                    "id sp "+i,
                    "nama tim "+i,
                    "nama wp "+i,
                    "30 Februari 2012",
                    null
            ));
        }
    }
    
    public void addDokumenPelaksanaan() {
        PelaksanaanWrapper pelaksanaanWrapper
                = new PelaksanaanWrapper();
        SessionProvider.getGlobalSessionsMap()
                        .put("pelaksanaan_wrapper", pelaksanaanWrapper);
        PersiapanWrapper persiapanWrapper
                = new PersiapanWrapper();
        SessionProvider.getGlobalSessionsMap()
                        .put("persiapan_wrapper", persiapanWrapper);
        SessionProvider.getGlobalSessionsMap()
                        .put("is_history", new Boolean(false));
        
        Pane formPelaksanaanUI = null;
        try {
            formPelaksanaanUI = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormPelaksanaanUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Form Pelaksanaan Pemeriksaan WP");
        stage.setScene(new Scene(formPelaksanaanUI));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void associateDataWithColumn() {
        id.setCellValueFactory(new PropertyValueFactory<ArsipTablePelaksanaanWrapper, String>("id"));
        idSP.setCellValueFactory(new PropertyValueFactory<ArsipTablePelaksanaanWrapper, String>("idSP"));
        namaTim.setCellValueFactory(new PropertyValueFactory<ArsipTablePelaksanaanWrapper, String>("namaTim"));
        namaWP.setCellValueFactory(new PropertyValueFactory<ArsipTablePelaksanaanWrapper, String>("namaWP"));
        tanggalDiBuat.setCellValueFactory(new PropertyValueFactory<ArsipTablePelaksanaanWrapper, String>("tanggalDiBuat"));
        action.setCellValueFactory(new PropertyValueFactory<ArsipTablePelaksanaanWrapper, String>("action"));
    }
    
    private void initPersiapanWrapperList() {
        suratPerintahService = ServiceFactory.getSuratPerintahService();
        List<SuratPerintah> suratPerintahList
                = suratPerintahService.getAllSuratPerintah();
        List<PersiapanWrapper> persiapanWrappers
                = new ArrayList<>();
        for (SuratPerintah sp : suratPerintahList) {
            PersiapanWrapper persiapanWrapper
                = ConverterHelper
                        .convertSuratPerintahToPersiapanWrapper(sp);
            persiapanWrappers.add(persiapanWrapper);
        }
        
        SessionProvider.getGlobalSessionsMap()
                        .put("daftar_persiapan_wrapper", persiapanWrappers);
    }
    
    public void openAturRekapitulasi(ArsipTablePelaksanaanWrapper wrapperParam) {
        PelaksanaanWrapper pelaksanaanWrapper
                = new PelaksanaanWrapper();
        SessionProvider.getGlobalSessionsMap()
                        .put("pelaksanaan_wrapper", pelaksanaanWrapper);
        SessionProvider.getGlobalSessionsMap()
                        .put("is_history", new Boolean(true));
        
        ArsipTablePelaksanaanWrapper wrapper
                = wrapperParam;
        PersiapanWrapper persiapanWrapper
                = persiapanWrapperMapper2.get(wrapper.getId());
        Tim timSelected
                = timMapper.get(wrapper.getId());
        WajibPajak wpSelected
                = wpMapper.get(wrapper.getId());
        RekapitulasiWrapper rekapWrapper
                = rekapMapper.get(wrapper.getId());
        pelaksanaanWrapper.setPersiapanWrapper(persiapanWrapper);
        pelaksanaanWrapper.setTimSelected(timSelected);
        pelaksanaanWrapper.setWpSelected(wpSelected);
        pelaksanaanWrapper.setRekapitulasiWrapper(rekapWrapper);

        Pane formPelaksanaanUI = null;
        try {
            formPelaksanaanUI = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormPelaksanaanUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormDaftarRekapitulasiPerbandinganPendapatan.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ComponentCollectorProvider
                .addFxComponent("root_form_persiapan_ui", formPelaksanaanUI);
        formPelaksanaanUI.getChildren().remove(0);
        formPelaksanaanUI.getChildren().add(contentPane);
        
        
        Stage stage = new Stage();
        stage.setTitle("Form Pelaksanaan Pemeriksaan WP");
        stage.setScene(new Scene(formPelaksanaanUI));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
    }
    
}
