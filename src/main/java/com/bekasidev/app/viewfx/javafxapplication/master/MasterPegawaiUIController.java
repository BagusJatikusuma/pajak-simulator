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
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.MasterPegawaiTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.WPMasterTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.ObservableArrayList;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import javafx.scene.control.TextField;
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
    @FXML private TextField cariPegawaiField;
    private TableColumn nip, 
                        nama, 
                        pangkat, 
                        golongan,
                        jabatan,
                        action;
    private ObservableList<MasterPegawaiTableWrapper> dataCollection;
    private ObservableList<MasterPegawaiTableWrapper> filteredCollection;
    private Map<String, Pegawai> pegawaiMapper = new HashMap<>();
    private PegawaiService service;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //reset dahulu session pegawai update ketika memasuki halaman master pegawai
        SessionProvider.getGlobalSessionsMap().put("update_pegawai_selected", null);
        addFromFXML();
        populateData();
        associateDataWithColumn();
        masterPegawaiTable.setItems(dataCollection);
    }
    
    public void cariPegawai() {
        if (!cariPegawaiField.getText().equals("")) {
            if (!populateDataBasedSearch().isEmpty()) {
                masterPegawaiTable.setItems(populateDataBasedSearch());
            }
            masterPegawaiTable.refresh();
        }
    }
    
    public void resetTable() {
        masterPegawaiTable.setItems(dataCollection);
        masterPegawaiTable.refresh();
    }

    public void addPegawai() {
        SessionProvider.getGlobalSessionsMap().put("update_pegawai_selected", null);
        
        Pane formTambahPegawai = null;
        try {
            formTambahPegawai = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormTambahPegawaiUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Form Tambah Pegawai");
        stage.setScene(new Scene(formTambahPegawai));
        
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    public void updatePegawai() {
        MasterPegawaiTableWrapper pegawai
                = (MasterPegawaiTableWrapper) masterPegawaiTable.getSelectionModel().getSelectedItem();
        if (pegawai==null) {
            showErrorNotif();
            return;
        }
        
        SessionProvider.getGlobalSessionsMap().put("update_pegawai_selected", pegawaiMapper.get(pegawai.getNip()));
        
        Pane formTambahPegawai = null;
        try {
            formTambahPegawai = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormTambahPegawaiUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Form Update Pegawai");
        stage.setScene(new Scene(formTambahPegawai));
        
//        stage.initStyle(StageStyle.UTILITY);
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
    
    private ObservableList<MasterPegawaiTableWrapper> populateDataBasedSearch() {
        filteredCollection = FXCollections.observableArrayList();
        String searchText = cariPegawaiField.getText().toLowerCase();
        
        for (Iterator it = dataCollection.iterator(); it.hasNext();) {
            MasterPegawaiTableWrapper wrapper = (MasterPegawaiTableWrapper) it.next();
            if (wrapper.getNama().toLowerCase().contains(searchText)) {
                filteredCollection.add(wrapper);
            }
        }
        
        return filteredCollection;
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
            pegawaiMapper.put(obj.getNipPegawai(), obj);
        }
        
        for (final MasterPegawaiTableWrapper obj: dataCollection) {
            Button btn = obj.getAction();
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {                    
                    System.out.println("hapus "+obj.getNip()+"clicked");
                    
                    service.deletePegawai(obj.getNip());
                    
                    Pane rootpane = ComponentCollectorProvider.getComponentFXMapper().get("root_pane");
                    rootpane.getChildren().remove(1);

                    Pane contentPane = null;
                    try { 
                        contentPane
                                = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterPegawaiUI.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rootpane.getChildren().add(contentPane);
                }
            });
        }
    }
    
    private void associateDataWithColumn() {
        nip.setCellValueFactory(new PropertyValueFactory<MasterPegawaiTableWrapper, String>("nip"));
        nip.prefWidthProperty().bind(masterPegawaiTable.widthProperty().divide(6));
        nama.setCellValueFactory(new PropertyValueFactory<MasterPegawaiTableWrapper, String>("nama"));
        nama.prefWidthProperty().bind(masterPegawaiTable.widthProperty().divide(5));
        pangkat.setCellValueFactory(new PropertyValueFactory<MasterPegawaiTableWrapper, String>("pangkat"));
        pangkat.prefWidthProperty().bind(masterPegawaiTable.widthProperty().divide(6));
        golongan.setCellValueFactory(new PropertyValueFactory<MasterPegawaiTableWrapper, String>("golongan"));
        golongan.prefWidthProperty().bind(masterPegawaiTable.widthProperty().divide(10));
        jabatan.setCellValueFactory(new PropertyValueFactory<MasterPegawaiTableWrapper, String>("jabatan"));
        jabatan.prefWidthProperty().bind(masterPegawaiTable.widthProperty().divide(4));
        action.setCellValueFactory(new PropertyValueFactory<MasterPegawaiTableWrapper, String>("action"));
        action.prefWidthProperty().bind(masterPegawaiTable.widthProperty().divide(10));
    }
    
    private void showErrorNotif() {
        SessionProvider.getGlobalSessionsMap().put("notif_message_popup", "Anda belum memilih pegawai");
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
