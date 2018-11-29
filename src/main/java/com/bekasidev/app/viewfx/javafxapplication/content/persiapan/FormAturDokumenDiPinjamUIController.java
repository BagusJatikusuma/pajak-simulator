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
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanAturDokumenPinjamTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanNomorTanggalSuratPemberitahuanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class FormAturDokumenDiPinjamUIController implements Initializable {
    @FXML private TableView AturDokumenPinjamTable;
    private TableColumn idWP;
    private TableColumn namaWP;
    private TableColumn action;
    @FXML private Button cancelBtn;
    
    private BerkasPersiapanService berkasPersiapanService;

    private ObservableList<PersiapanAturDokumenPinjamTableWrapper> dataCollection;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addFromFXML();
        populateData();
        associateDataWithColumn();
        AturDokumenPinjamTable.setItems(dataCollection);
    }    
    
    public void simpanDokumenPinjam() {
        System.out.println("simpan dokumen yang dipinjam");
        berkasPersiapanService = ServiceFactory.getBerkasPersiapanService();
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        
        for (DokumenPinjamanWajibPajakWrapper obj
                :persiapanWrapper.getDokumenPinjamanWajibPajakWrappers()) {
            berkasPersiapanService
                    .createBerkasPersiapan(
                            persiapanWrapper.getIdSP(), 
                            obj.getWajibPajak());
        }
        
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void cancelOperation() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();  
    }
    
    public void printSuratPeminjaman() {
        
    }

    private void addFromFXML() {
        idWP
                = TableHelper.getTableColumnByName(AturDokumenPinjamTable, "Id WP");
        namaWP
                = TableHelper.getTableColumnByName(AturDokumenPinjamTable, "Nama WP");
        action 
                = TableHelper.getTableColumnByName(AturDokumenPinjamTable, "Action");
    }

    private void populateData() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        dataCollection = FXCollections.observableArrayList();
        for (DokumenPinjamanWajibPajakWrapper obj
                :persiapanWrapper.getDokumenPinjamanWajibPajakWrappers()) {
            Button aturBtn = new Button("Atur dokumen");
            
            dataCollection.add(new PersiapanAturDokumenPinjamTableWrapper(
                    obj.getWajibPajak().getNpwpd(),
                    obj.getWajibPajak().getNamaWajibPajak(),
                    aturBtn
            ));
            
        }
        
        for (PersiapanAturDokumenPinjamTableWrapper obj 
                :dataCollection) {
            Button aturBtn = obj.getAction();
            
            aturBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    System.out.println("atur "+obj.getNamaWP());
                    SessionProvider
                            .getGlobalSessionsMap()
                            .put("selected_dokumen_wp", obj.getIdWP());
                    
                    List<DokumenPinjaman> dokumenTempList = new ArrayList<>();
                    SessionProvider
                        .getGlobalSessionsMap()
                        .put("dokumen_temp_list",dokumenTempList);
                    
                    Pane formAturDokumenWP = null;
                    try {
                        formAturDokumenWP = FXMLLoader
                                .load(getClass().getClassLoader().getResource("fxml/FormAturDokumenWP.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Stage stage = new Stage();
                    SessionProvider
                            .getGlobalSessionsMap()
                            .put("stage_atur_dokumen_wp", stage);
                    stage.setTitle("Form Atur Dokumen Wajib Pajak");
                    stage.setScene(new Scene(formAturDokumenWP));
                    stage.show();
                }
            });
            
        }
        
    }

    private void associateDataWithColumn() {
        idWP.setCellValueFactory(new PropertyValueFactory<PersiapanAturDokumenPinjamTableWrapper, String>("idWP"));
        namaWP.setCellValueFactory(new PropertyValueFactory<PersiapanAturDokumenPinjamTableWrapper, String>("namaWP"));
        action.setCellValueFactory(new PropertyValueFactory<PersiapanAturDokumenPinjamTableWrapper, String>("action"));
    }
    
}
