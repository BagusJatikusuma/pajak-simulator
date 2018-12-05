/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.DokumenPinjaman;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.BerkasPersiapanService;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.DokumenPinjamanWajibPajakWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.DokumenWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanAturDokumenPinjamTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormAturDokumenWPController implements Initializable {
    @FXML private TableView DokumenWPTable;
    private TableColumn dokumen;
    private TableColumn no;
    private TableColumn keterangan;
    private TableColumn action;
    @FXML private Button cancelBtn;
    
    private ObservableList<DokumenWPTableWrapper> dataCollection;
    private Map<String, DokumenPinjaman> dokumenPinjamanMapper = new HashMap<>();
    private BerkasPersiapanService berkasPersiapanService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        berkasPersiapanService = ServiceFactory.getBerkasPersiapanService();
        addFromFXML();
        populateData();
        associateDataWithColumn();
        DokumenWPTable.setItems(dataCollection);
    }

    public void finishDokumenWP() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        List<DokumenPinjaman> dokumenTempList
                = (List<DokumenPinjaman>) SessionProvider
                    .getGlobalSessionsMap()
                    .get("dokumen_temp_list");
        
        String selectedWP 
                = (String) SessionProvider
                    .getGlobalSessionsMap()
                    .get("selected_dokumen_wp");
        
        for (DokumenPinjamanWajibPajakWrapper obj
                :persiapanWrapper.getDokumenPinjamanWajibPajakWrappers()) {
            if (obj.getWajibPajak().getNpwpd()
                    .equals(selectedWP)) {
                obj.getListPinjaman().clear();
                System.out.println("before size "+obj.getListPinjaman().size());
                System.out.println("before size "+dokumenTempList.size());
                for (DokumenPinjaman objDoc : dokumenTempList) {
                    obj.getListPinjaman().add(objDoc);
                }
                berkasPersiapanService
                    .createBerkasPersiapan(
                            persiapanWrapper.getIdSP(), 
                            obj.getWajibPajak());
                break;
            }
        }
        
        
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void cancelOperation() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void tambahDokumen() {
        Pane formTambahDokumenWP = null;
        try {
            formTambahDokumenWP = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormInputDokumenWPUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Form Tambah Dokumen");
        stage.setScene(new Scene(formTambahDokumenWP));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void addFromFXML() {
        dokumen
                = TableHelper.getTableColumnByName(DokumenWPTable, "Dokumen");
        no
                = TableHelper.getTableColumnByName(DokumenWPTable, "No");
        action 
                = TableHelper.getTableColumnByName(DokumenWPTable, "Action");
    }

    private void populateData() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        String selectedWP 
                = (String) SessionProvider
                    .getGlobalSessionsMap()
                    .get("selected_dokumen_wp");
        dataCollection = FXCollections.observableArrayList();
        List<DokumenPinjaman>dokumenTempList 
                = (List<DokumenPinjaman>) SessionProvider
                    .getGlobalSessionsMap()
                    .get("dokumen_temp_list");
        
        for (DokumenPinjamanWajibPajakWrapper obj
                :persiapanWrapper.getDokumenPinjamanWajibPajakWrappers()) {
            if (obj.getWajibPajak().getNpwpd()
                    .equals(selectedWP)) {
                if (dokumenTempList.isEmpty())
                    for (DokumenPinjaman objTemp : obj.getWajibPajak().getListPinjaman()) {
                        dokumenTempList.add(objTemp);
                    }
                //ada bug index ke 0 tidak ada isi
                if (dokumenTempList.get(0).getNamaDokumen().equals(""))
                    dokumenTempList.remove(0);
                    
                int index = 1;
                for (DokumenPinjaman dokumen 
                        : dokumenTempList) {
                    Button btn = new Button("Hapus");
                    dataCollection.add(new DokumenWPTableWrapper(
                            String.valueOf(index),
                            dokumen.getNamaDokumen(),
                            "",
                            btn
                    ));
                    dokumenPinjamanMapper.put(String.valueOf(index), dokumen);
                    index++;
                }
   
                break;
            }
            
        }
        
        for (final DokumenWPTableWrapper obj : dataCollection) {
            Button btn = obj.getAction();
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    System.out.println("hapus "+dokumenPinjamanMapper.get(obj.getNo()).getNamaDokumen());
                    dokumenTempList.remove(dokumenPinjamanMapper.get(obj.getNo()));
                    
                    Stage stageAturDokumenWP 
                            = (Stage) SessionProvider
                                .getGlobalSessionsMap()
                                .get("stage_atur_dokumen_wp");
                    Pane formAturDokumenWP = null;
                    try {
                        formAturDokumenWP = FXMLLoader
                                .load(getClass().getClassLoader().getResource("fxml/FormAturDokumenWP.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    stageAturDokumenWP.setScene(new Scene(formAturDokumenWP));
                    
                }
            });
        }
        
    }

    private void associateDataWithColumn() {
        dokumen.setCellValueFactory(new PropertyValueFactory<PersiapanAturDokumenPinjamTableWrapper, String>("dokumen"));
        no.setCellValueFactory(new PropertyValueFactory<PersiapanAturDokumenPinjamTableWrapper, String>("no"));
        action.setCellValueFactory(new PropertyValueFactory<PersiapanAturDokumenPinjamTableWrapper, String>("action"));
    }
    
}
