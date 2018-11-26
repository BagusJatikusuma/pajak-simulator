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
    private TableColumn nomor,
                        bulan,
                        omzetHasilPemeriksaan,
                        omzetYangDilaporkan,
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
        nomor 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "NO");
        bulan 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "BULAN");
        omzetHasilPemeriksaan 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "OMZET HASIL PEMERIKSAAN");
        omzetYangDilaporkan 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "OMZET YANG DILAPORKAN");
        action 
                = TableHelper.getTableColumnByName(arsipPelaksanaanTable, "ACTION");
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
                    obj.getNomor(),
                    obj.getBulan(),
                    obj.getOmzetHasilPemeriksaan(),
                    obj.getOmzetYangDilaporkan(),
                    btn
            ));
            i++; 
        }
    }
    
    private void initDataFromService() {
        dataListFromService = new ArrayList<>();
        for (int i=0; i<11; i++) {
            dataListFromService.add(new ArsipTablePelaksanaanWrapper(
                    String.valueOf(i+1),
                    convertBulanIntegerIntoString(i),
                    "50000000",
                    "40000000",
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
    
    private String convertBulanIntegerIntoString(Integer bulanInt) {
        switch(bulanInt) {
            case 0: return "Januari";
            case 1: return "Februari";
            case 2: return "Maret";
            case 3: return "April";
            case 4: return "Mei";
            case 5: return "Juni";
            case 6: return "Juli";
            case 7: return "Agustus";
            case 8: return "September";
            case 9: return "Oktober";
            case 10: return "November";
            case 11: return "Desember";
        }
        return "";
    }
}
