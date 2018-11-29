/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.SuratPerintahService;
import com.bekasidev.app.view.util.ConverterHelper;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipTablePelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PajakRestoranTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
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
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "Id");
        idSP 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "Id SP");
        namaTim 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "Nama Tim");
        namaWP 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "Nama WP");
        tanggalDiBuat 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "Tanggal Dibuat");
        action 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "Action");
    }
    
    private void populateData() {
        dataCollection = new ObservableArrayList<>();
        int i = 1;
        for (final ArsipTablePelaksanaanWrapper obj
                : dataListFromService) {
            Button btn = new Button("lihat detail");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            });

            dataCollection.add(new ArsipTablePelaksanaanWrapper(
                    obj.getId(),
                    obj.getIdSP(),
                    obj.getNamaTim(),
                    obj.getNamaWP(),
                    obj.getTanggalDiBuat(),
                    btn
            ));
            i++; 
        }
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
        stage.show();
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
    
}
