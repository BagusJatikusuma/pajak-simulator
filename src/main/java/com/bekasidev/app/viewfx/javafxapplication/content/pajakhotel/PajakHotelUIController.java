/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pajakhotel;

import com.bekasidev.app.viewfx.javafxapplication.content.pajakrestoran.*;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.WajibPajakService;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.view.util.modelview.PersiapanPajakPOJO;
import com.bekasidev.app.view.util.modelview.WajibPajakModelView;
import com.bekasidev.app.viewfx.javafxapplication.model.PajakHotelTableWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import com.bekasidev.app.viewfx.javafxapplication.model.PajakRestoranTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.ObservableArrayList;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class PajakHotelUIController implements Initializable {

    @FXML
    private TableView pajakHotelTable;
    private TableColumn idHotel, 
                        no, 
                        namaHotel, 
                        alamat, 
                        desa, 
                        kecamatan, 
                        anonX;
    private ObservableList<PajakHotelTableWrapper> dataCollection;
    private List<PajakHotelTableWrapper> dataListFromService;
    private List<Button> btnList;
    
    private WajibPajakService service;
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
        
        pajakHotelTable.setItems(dataCollection);
    }

    private void addFromFXML() {
        idHotel 
                = TableHelper.getTableColumnByName(pajakHotelTable, "Id Hotel");
        no
                = TableHelper.getTableColumnByName(pajakHotelTable, "No");
        namaHotel 
                = TableHelper.getTableColumnByName(pajakHotelTable, "Nama Hotel");
        alamat 
                = TableHelper.getTableColumnByName(pajakHotelTable, "Alamat");
        desa 
                = TableHelper.getTableColumnByName(pajakHotelTable, "Desa");
        kecamatan 
                = TableHelper.getTableColumnByName(pajakHotelTable, "Kecamatan");
        anonX 
                = TableHelper.getTableColumnByName(pajakHotelTable, "action");
    }
    
    private void populateData() {
        service = ServiceFactory.getWajibPajakService();
        List<com.bekasidev.app.model.WajibPajak> restorans = service.getAllWP();
        
        dataCollection = new ObservableArrayList<>();
        int i = 1;
        for (final com.bekasidev.app.model.WajibPajak obj
                : restorans) {
            if (obj.getJenisWp() == 1) {
                Button btn = new Button("pilih");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            System.out.println(obj.getNamaWajibPajak()+"clicked");
                            //add the data to session object
                            WajibPajakModelView wajibPajak = new WajibPajakModelView();
                            wajibPajak.setNpwpd(obj.getIdWajibPajak());
                            wajibPajak.setNamaWP(obj.getNamaWajibPajak());
                            wajibPajak.setAlamatWP(obj.getJalan());
                            wajibPajak.setDesaWP(obj.getDesa());
                            wajibPajak.setKecamatanWP(obj.getKecamatan());

                            PersiapanPajakPOJO persiapanPajakPOJO = new PersiapanPajakPOJO();
                            persiapanPajakPOJO.setWajibPajak(wajibPajak);

                            Map<String, Object> persiapanPajakRetoranMap
                                    = SessionProvider.getPajakMapSession();
                            persiapanPajakRetoranMap.put("persiapan_pajak_hotel", persiapanPajakPOJO);

                            System.out.println("data pajak hotel telah disimpan");
                            Pane persiapanPajakPane
                                    = FXMLLoader
                                            .load(getClass().getClassLoader().getResource("fxml/PersiapanPajakHotelUI.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("persiapan pajak hotel");
                            stage.setScene(new Scene(persiapanPajakPane));
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(PajakHotelUIController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });

                dataCollection.add(new PajakHotelTableWrapper(
                                String.valueOf(i),
                                obj.getIdWajibPajak(),
                                obj.getNamaWajibPajak(),
                                "0",
                                obj.getJalan(),
                                obj.getKecamatan(),
                                obj.getDesa(),
                                btn
                        ));
                i++;
            } 
        }
        
    }
    
    private void associateDataWithColumn() {
        no.setCellValueFactory(new PropertyValueFactory<PajakHotelTableWrapper, String>("no"));
        idHotel.setCellValueFactory(new PropertyValueFactory<PajakHotelTableWrapper, String>("idWajibPajak"));
        namaHotel.setCellValueFactory(new PropertyValueFactory<PajakHotelTableWrapper, String>("namaWajibPajak"));
        alamat.setCellValueFactory(new PropertyValueFactory<PajakHotelTableWrapper, String>("jalan"));
        desa.setCellValueFactory(new PropertyValueFactory<PajakHotelTableWrapper, String>("desa"));
        kecamatan.setCellValueFactory(new PropertyValueFactory<PajakHotelTableWrapper, String>("kecamatan"));
        anonX.setCellValueFactory(new PropertyValueFactory<PajakHotelTableWrapper, String>("button"));
    }
    
    private void initDataFromService() {
        dataListFromService = new ArrayList<>();
        for (int i=0; i<100; i++) {
            dataListFromService.add(new PajakHotelTableWrapper(
                            String.valueOf(i),
                            "id wajib pajak "+i,
                            "nama wajib pajak "+i,
                            "1",
                            "alamat pajak "+i,
                            "kecamatan pajak "+i,
                            "desa pajak "+i,
                            null
                    ));
        }
    }
    
}
