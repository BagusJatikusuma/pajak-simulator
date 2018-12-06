/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.master;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.viewfx.javafxapplication.model.MasterPegawaiTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.WPMasterTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.ObservableArrayList;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class MasterPegawaiUIController implements Initializable {
    @FXML private TableView masterPegawaiTable;
    private TableColumn nip, 
                        nama, 
                        pangkat, 
                        golongan,
                        jabatan,
                        action;
    private ObservableList<MasterPegawaiTableWrapper> dataCollection;
    private PegawaiService service;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addFromFXML();
        populateData();
        associateDataWithColumn();
        masterPegawaiTable.setItems(dataCollection);
    }

    public void addPegawai() {
        Pane formTambahPegawai = null;
        try {
            formTambahPegawai = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormTambahPegawaiUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Form tambah Pegawai");
        stage.setScene(new Scene(formTambahPegawai));
        
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    private void addFromFXML() {
        nip 
                = TableHelper.getTableColumnByName(masterPegawaiTable, "NIP");
        nama
                = TableHelper.getTableColumnByName(masterPegawaiTable, "Nama");
        pangkat 
                = TableHelper.getTableColumnByName(masterPegawaiTable, "Pangkat");
        golongan 
                = TableHelper.getTableColumnByName(masterPegawaiTable, "Golongan");
        jabatan 
                = TableHelper.getTableColumnByName(masterPegawaiTable, "Jabatan");
        action 
                = TableHelper.getTableColumnByName(masterPegawaiTable, "Action");
    }
    
    private void populateData() {
        service = ServiceFactory.getPegawaiService();
        List<Pegawai> pegawaiList = service.getAllPegawai();
        
        dataCollection = new ObservableArrayList<>();
        for (Pegawai obj: pegawaiList) {
            Button btn = new Button("Hapus");
            dataCollection.add(new MasterPegawaiTableWrapper(
                    obj.getNipPegawai(),
                    obj.getNamaPegawai(),
                    obj.getPangkat(),
                    obj.getGolongan(),
                    obj.getJabatanDinas(),
                    btn
            ));
        }
        
        for (final MasterPegawaiTableWrapper obj: dataCollection) {
            Button btn = obj.getAction();
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {                    
                    System.out.println("hapus "+obj.getNip()+"clicked");
                    
                    
                }
            });
        }
    }
    
    private void associateDataWithColumn() {
        nip.setCellValueFactory(new PropertyValueFactory<MasterPegawaiTableWrapper, String>("nip"));
        nama.setCellValueFactory(new PropertyValueFactory<MasterPegawaiTableWrapper, String>("nama"));
        pangkat.setCellValueFactory(new PropertyValueFactory<MasterPegawaiTableWrapper, String>("pangkat"));
        golongan.setCellValueFactory(new PropertyValueFactory<MasterPegawaiTableWrapper, String>("golongan"));
        jabatan.setCellValueFactory(new PropertyValueFactory<MasterPegawaiTableWrapper, String>("jabatan"));
        action.setCellValueFactory(new PropertyValueFactory<MasterPegawaiTableWrapper, String>("action"));
    }
    
}
