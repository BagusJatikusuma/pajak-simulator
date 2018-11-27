/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.viewfx.javafxapplication.model.ArsipTablePelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.ObservableArrayList;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class PelaksanaanUIController implements Initializable {
    @FXML private TableView arsipPelaksanaanTable;
    private TableColumn id,
                        tanggalDiBuat,
                        status,
                        action;
    private ObservableList<ArsipTablePelaksanaanWrapper> dataCollection;
    private List<ArsipTablePelaksanaanWrapper> dataListFromService;
    private List<Button> btnList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initDataFromService();
        addFromFXML();
        populateData();
        
        arsipPelaksanaanTable.setItems(dataCollection);
    }
    
    private void addFromFXML() {
        id 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "Id");
        tanggalDiBuat 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "Tanggal Dibuat");
        status 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "Status");
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
                    obj.getTanggalDiBuat(),
                    obj.getStatus(),
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
                    "30 Februari 2012",
                    "Selesai",
                    null
            ));
        }
    }
    
//    public void addDokumenPersiapan() {
//        PersiapanWrapper persiapanWrapper
//                = new PersiapanWrapper();
//        SessionProvider.getGlobalSessionsMap()
//                        .put("persiapan_wrapper", persiapanWrapper);
//        
//        Pane formPersiapanUI = null;
//        try {
//            formPersiapanUI = FXMLLoader
//                    .load(getClass().getClassLoader().getResource("fxml/FormPersiapanUI.fxml"));
//        } catch (IOException ex) {
//            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Stage stage = new Stage();
//        stage.setTitle("Form Persiapan Pemeriksaan WP");
//        stage.setScene(new Scene(formPersiapanUI));
//        stage.show();
//    }
    
}
