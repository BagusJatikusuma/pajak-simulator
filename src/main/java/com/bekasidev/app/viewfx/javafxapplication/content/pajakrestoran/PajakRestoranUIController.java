/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pajakrestoran;

import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.WajibPajakService;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.view.util.modelview.PersiapanPajakPOJO;
import com.bekasidev.app.view.util.modelview.WajibPajakModelView;
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

/**
 * FXML Controller class
 *
 * @author USER
 */
public class PajakRestoranUIController implements Initializable {

    @FXML
    private TableView pajakRestoranTable;
    private TableColumn idRestoran, 
                        no, 
                        namaRestoran, 
                        alamat, 
                        desa, 
                        kecamatan, 
                        anonX;
    private ObservableList<PajakRestoranTableWrapper> dataCollection;
    private List<PajakRestoranTableWrapper> dataListFromService;
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
        
        pajakRestoranTable.setItems(dataCollection);
    }

    private void addFromFXML() {
        idRestoran 
                = TableHelper.getTableColumnByName(pajakRestoranTable, "Id Restoran");
        no
                = TableHelper.getTableColumnByName(pajakRestoranTable, "No");
        namaRestoran 
                = TableHelper.getTableColumnByName(pajakRestoranTable, "Nama Restoran");
        alamat 
                = TableHelper.getTableColumnByName(pajakRestoranTable, "Alamat");
        desa 
                = TableHelper.getTableColumnByName(pajakRestoranTable, "Desa");
        kecamatan 
                = TableHelper.getTableColumnByName(pajakRestoranTable, "Kecamatan");
        anonX 
                = TableHelper.getTableColumnByName(pajakRestoranTable, "action");
    }
    
    private void populateData() {
        service = ServiceFactory.getWajibPajakService();
        List<com.bekasidev.app.model.WajibPajak> restorans = service.getAllWP();
        
        dataCollection = new ObservableArrayList<>();
        int i = 1;
        for (final com.bekasidev.app.model.WajibPajak obj
                : restorans) {
            if (obj.getJenisWp() == 0) {
                Button btn = new Button("pilih");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            System.out.println(obj.getNamaWajibPajak()+"clicked");
                            //add the data to session object
                            WajibPajakModelView wajibPajak = new WajibPajakModelView();
                            wajibPajak.setNpwpd(obj.getNpwpd());
                            wajibPajak.setNamaWP(obj.getNamaWajibPajak());
                            wajibPajak.setAlamatWP(obj.getJalan());
                            wajibPajak.setDesaWP(obj.getDesa());
                            wajibPajak.setKecamatanWP(obj.getKecamatan());

                            PersiapanPajakPOJO persiapanPajakPOJO = new PersiapanPajakPOJO();
                            persiapanPajakPOJO.setWajibPajak(wajibPajak);

                            Map<String, Object> persiapanPajakRetoranMap
                                    = SessionProvider.getPajakMapSession();
                            persiapanPajakRetoranMap.put("persiapan_pajak_restoran", persiapanPajakPOJO);

                            System.out.println("data pajak restoran telah disimpan");
                            Pane persiapanPajakPane
                                    = FXMLLoader
                                            .load(getClass().getClassLoader().getResource("javafxresources/PersiapanPajakRestoranUI.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("persiapan pajak restoran");
                            stage.setScene(new Scene(persiapanPajakPane));
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(PajakRestoranUIController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });

                dataCollection.add(new PajakRestoranTableWrapper(
                                String.valueOf(i),
                                obj.getNpwpd(),
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
        no.setCellValueFactory(new PropertyValueFactory<PajakRestoranTableWrapper, String>("no"));
        idRestoran.setCellValueFactory(new PropertyValueFactory<PajakRestoranTableWrapper, String>("idWajibPajak"));
        namaRestoran.setCellValueFactory(new PropertyValueFactory<PajakRestoranTableWrapper, String>("namaWajibPajak"));
        alamat.setCellValueFactory(new PropertyValueFactory<PajakRestoranTableWrapper, String>("jalan"));
        desa.setCellValueFactory(new PropertyValueFactory<PajakRestoranTableWrapper, String>("desa"));
        kecamatan.setCellValueFactory(new PropertyValueFactory<PajakRestoranTableWrapper, String>("kecamatan"));
        anonX.setCellValueFactory(new PropertyValueFactory<PajakRestoranTableWrapper, String>("button"));
    }
    
    private void initDataFromService() {
        dataListFromService = new ArrayList<>();
        for (int i=0; i<100; i++) {
            dataListFromService.add(new PajakRestoranTableWrapper(
                            String.valueOf(i),
                            "id wajib pajak "+i,
                            "nama wajib pajak "+i,
                            "0",
                            "alamat pajak "+i,
                            "kecamatan pajak "+i,
                            "desa pajak "+i,
                            null
                    ));
        }
    }
    
}
