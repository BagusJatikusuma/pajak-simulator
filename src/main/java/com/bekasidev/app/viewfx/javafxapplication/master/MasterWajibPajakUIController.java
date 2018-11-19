/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.master;

import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.WajibPajakService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MasterWajibPajakUIController implements Initializable {
    @FXML private TableView wajibPajakTable;
    private TableColumn idWP, 
                        no, 
                        namaWP, 
                        alamat, 
                        desa, 
                        kecamatan,
                        jenisWP,
                        action;
    private ObservableList<WPMasterTableWrapper> dataCollection;
    
    private WajibPajakService service;
    private int indexTemp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addFromFXML();
        populateData();
        associateDataWithColumn();
        wajibPajakTable.setItems(dataCollection);
    }    
    
    public void addWajibPajakHandle(ActionEvent actionEvent) {
        Pane formTambahWP = null;
        try {
            formTambahWP = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormTambahWPUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Form tambah WP");
        stage.setScene(new Scene(formTambahWP));
        stage.show();
    }
    
    private void addFromFXML() {
        idWP 
                = TableHelper.getTableColumnByName(wajibPajakTable, "NPWPD");
        no
                = TableHelper.getTableColumnByName(wajibPajakTable, "No");
        namaWP 
                = TableHelper.getTableColumnByName(wajibPajakTable, "Nama WP");
        alamat 
                = TableHelper.getTableColumnByName(wajibPajakTable, "Alamat");
        desa 
                = TableHelper.getTableColumnByName(wajibPajakTable, "Desa");
        kecamatan 
                = TableHelper.getTableColumnByName(wajibPajakTable, "Kecamatan");
        jenisWP 
                = TableHelper.getTableColumnByName(wajibPajakTable, "Jenis WP");
        action 
                = TableHelper.getTableColumnByName(wajibPajakTable, "action");
    }
    
    private void populateData() {
        service = ServiceFactory.getWajibPajakService();
        List<com.bekasidev.app.model.WajibPajak> restorans = service.getAllWP();
        
        dataCollection = new ObservableArrayList<>();
        int i = 1;
        indexTemp = i;
        for (final com.bekasidev.app.model.WajibPajak obj
                : restorans) {
            Button btn = new Button("Hapus");

            String jenisWP = "";
            switch(obj.getJenisWp()) {
                case 0: 
                    jenisWP = "Restoran";
                    break;
                case 1: 
                    jenisWP = "Hotel";
                    break;
                case 2: 
                    jenisWP = "parkir";
                    break;
                case 3:
                    jenisWP = "Hiburan";
                    break;
                case 4: 
                    jenisWP = "Penerangan Jalan";
                    break;
                default:
                    jenisWP = "Unidentified";
            }
            
            dataCollection.add(new WPMasterTableWrapper(
                            String.valueOf(i),
                            obj.getNpwpd(),
                            obj.getNamaWajibPajak(),
                            jenisWP,
                            obj.getJalan(),
                            obj.getKecamatan(),
                            obj.getDesa(),
                            btn
                    ));
            i++;
            indexTemp = i;
        }
        
        //set action for every button
        for (final WPMasterTableWrapper obj : dataCollection) {
            Button btn = obj.getButton();
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {                    
                    System.out.println(obj.getNamaWajibPajak()+"clicked");
                    wajibPajakTable.getItems().remove(obj);
                    //remove data from database
                    service.deleteWP(obj.getIdWajibPajak());
                    //nanti ganti method untuk mereset nomor row
                    Pane rootpane = ComponentCollectorProvider.getComponentFXMapper().get("root_pane");
                    rootpane.getChildren().remove(1);

                    Pane contentPane = null;
                    try { 
                        contentPane
                                = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterWajibPajakUI.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rootpane.getChildren().add(contentPane);
                    
                }
            });
        }
    }
    
    private void associateDataWithColumn() {
        no.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("no"));
        idWP.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("idWajibPajak"));
        namaWP.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("namaWajibPajak"));
        alamat.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("jalan"));
        desa.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("desa"));
        kecamatan.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("kecamatan"));
        action.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("button"));
        jenisWP.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("jenisWp"));
    }
}
