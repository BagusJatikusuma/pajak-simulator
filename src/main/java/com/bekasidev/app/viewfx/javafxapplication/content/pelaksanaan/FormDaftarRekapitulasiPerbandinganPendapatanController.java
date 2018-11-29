/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.RekapitulasiService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.ConverterHelper;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipPelaksanaanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipTablePelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanTimWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.wrapper.RekapitulasiWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class FormDaftarRekapitulasiPerbandinganPendapatanController implements Initializable {

    @FXML private TableView arsipPelaksanaanTable;
    @FXML private TableColumn no;
    @FXML private TableColumn bulan;
    @FXML private TableColumn omzetHasilPemeriksaan;
    @FXML private TableColumn omzetDiLaporkan;
    @FXML private TableColumn action;
    
    @FXML private Label namaTimLabel;
    @FXML private Label namaWPLabel;
    @FXML private Label npwpdLabel;
    
    private ObservableList<ArsipPelaksanaanTableWrapper> dataCollection;
    
    private RekapitulasiService rekapitulasiService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initLabel();
        populateData();
        associateDataWithColumn();
        arsipPelaksanaanTable.setItems(dataCollection);
    }    
    
    public void backToFormPelaksanaanContent() {
        Pane rootpaneFormPelaksanaan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_pelaksanaan_ui");
        rootpaneFormPelaksanaan.getChildren().remove(1);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormPelaksanaanContentUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPelaksanaan.getChildren().add(contentPane);
    }

    private void populateData() {
        dataCollection = FXCollections.observableArrayList();
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        rekapitulasiService = ServiceFactory.getRekapitulasiService();
        
        RekapitulasiWrapper wrapper 
                = rekapitulasiService
                        .getRekapitulasi(
                                pelaksanaanWrapper.getPersiapanWrapper().getIdSP(), 
                                pelaksanaanWrapper.getWpSelected().getNpwpd());
        long rangeDiff = getDifferenceDatePersiapanWrapperinMonth(pelaksanaanWrapper.getPersiapanWrapper());
        int nextMonth = pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan();
        int nextYear = pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun();
        for (int i = 0; i <= rangeDiff; i++) {
            Button btn = new Button("button");
            ArsipPelaksanaanTableWrapper objTable 
                    = new ArsipPelaksanaanTableWrapper(
                            String.valueOf(i+1),
                            ConverterHelper.convertBulanIntegerIntoString(nextMonth)+" "+nextYear,
                            "-",
                            "-",
                            btn
                    );
            dataCollection.add(objTable);
            
            nextMonth++;
            if (nextMonth > 11) {
                nextMonth = 0;
                nextYear++;
            }
            
        }
        
        for (ArsipPelaksanaanTableWrapper obj:dataCollection) {
            Button btn = obj.getAction();
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    System.out.println("atur "+obj.getNo());
                }
            });
        }
        
    }

    private void associateDataWithColumn() {
        no.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("no"));
        bulan.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("bulan"));
        omzetHasilPemeriksaan.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("omzethasilPemeriksaan"));
        omzetDiLaporkan.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("omzetDiLaporkan"));
        action.setCellValueFactory(new PropertyValueFactory<ArsipPelaksanaanTableWrapper, String>("action"));
    }
    
    private void initLabel() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        namaTimLabel.setText(pelaksanaanWrapper.getTimSelected().getNamaTim());
        namaWPLabel.setText(pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
        npwpdLabel.setText(pelaksanaanWrapper.getWpSelected().getNpwpd());
    }
    
    private int getDifferenceDatePersiapanWrapperinMonth(PersiapanWrapper persiapanWrapper) {
        int diffMonth = 0;
        
        if (persiapanWrapper.getMasaPajakAwalTahun()
                .equals(persiapanWrapper.getMasaPajakAkhirTahun())) {
            diffMonth = persiapanWrapper.getMasaPajakAkhirbulan() - persiapanWrapper.getMasaPajakAwalBulan();
        }
        else {
            diffMonth = (11-persiapanWrapper.getMasaPajakAwalBulan()) + (persiapanWrapper.getMasaPajakAkhirbulan()+1);
        }
        
        return diffMonth;
    }
    
}

//baris kode pengetesan
//System.out.println("awal "
//                +pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalBulan()
//                +" bulan "
//                +pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAwalTahun()
//                +" tahun ");
//        System.out.println("akhir "
//                +pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirbulan()
//                +" bulan "
//                +pelaksanaanWrapper.getPersiapanWrapper().getMasaPajakAkhirTahun()
//                +" tahun ");omzetDilaporkan
//        System.out.println(getDifferenceDatePersiapanWrapperinMonth(pelaksanaanWrapper.getPersiapanWrapper()));
