/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.MasterAnggotaTimTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanTimWPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.ObservableArrayList;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Bayu Arafli
 */
public class FormAturTimWPUIController implements Initializable {
    @FXML private TableView PersiapanTimWPTable;
    private TableColumn idTim;
    private TableColumn namaTim;
    private TableColumn hapusAction;
    private TableColumn aturAction;
    @FXML private Button backToFormPersiapanBtn;
    @FXML private Button cancelBtn;
    
    private ReportService reportService;
    
    private ObservableList<PersiapanTimWPTableWrapper> dataCollection;
    private Map<String, TimWPWrapper> timWPMapper = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Label headerLabel = (Label) SessionProvider.getGlobalSessionsMap()
                                            .get("header_form_persiapan");
        headerLabel.setText("FORM ATUR TIM PENANGGUNG JAWAB");
        headerLabel.setLayoutX(170);
        
        addFromFXML();
        populateData();
        associateDataWithColumn();
        PersiapanTimWPTable.setItems(dataCollection);
    }
    
    private void addFromFXML() {
        idTim
                = TableHelper.getTableColumnByName(PersiapanTimWPTable, "ID TIM");
        namaTim
                = TableHelper.getTableColumnByName(PersiapanTimWPTable, "NAMA TIM");
        hapusAction 
                = TableHelper.getTableColumnByName(PersiapanTimWPTable, "TOMBOL HAPUS");
        aturAction 
                = TableHelper.getTableColumnByName(PersiapanTimWPTable, "TOMBOL ATUR");
    }
    
    private void populateData() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        dataCollection = FXCollections.observableArrayList();
        for (TimWPWrapper obj : persiapanWrapper.getTimWPWrappers()) {
            Button hapusButton = new Button("Hapus");
            Button aturButton = new Button("Atur");
            
            dataCollection.add(new PersiapanTimWPTableWrapper(
                    obj.getTim().getIdTim(),
                    obj.getTim().getNamaTim(),
                    hapusButton,
                    aturButton
            ));
            
            timWPMapper.put(obj.getTim().getIdTim(), obj);
            
        }
        for (final PersiapanTimWPTableWrapper obj : dataCollection) {
            Button hapusButton = obj.getHapusButton();
            Button aturButton = obj.getAturButton();
            
            hapusButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {                    
                    System.out.println("hapus "+obj.getIdTim()+"clicked");
                    persiapanWrapper.getTimWPWrappers().remove(timWPMapper.get(obj.getIdTim()));
                    
                    Pane rootpaneFormPersiapan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_persiapan_ui");
                    
                    rootpaneFormPersiapan.getChildren().remove(1);

                    Pane contentPane = null;
                    try { 
                        contentPane
                                = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormAturTimWPUI.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rootpaneFormPersiapan.getChildren().add(contentPane);
                    
                }
            });
            aturButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {                    
                    System.out.println("atur "+obj.getIdTim()+"clicked");
                    
                    SessionProvider
                            .getGlobalSessionsMap()
                            .put("atur_tim_wp_selected", timWPMapper.get(obj.getIdTim()));
                    
                    Pane formTambahTimWPUI = null;
                    try {
                        formTambahTimWPUI = FXMLLoader
                                .load(getClass().getClassLoader().getResource("fxml/FormTambahTimWPUI.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Form tambah Tim WP");
                    stage.setScene(new Scene(formTambahTimWPUI));
                    
//                    stage.initStyle(StageStyle.UTILITY);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    
                }
            });
            
        }
        
    }
    
    private void associateDataWithColumn() {
        idTim.setCellValueFactory(new PropertyValueFactory<PersiapanTimWPTableWrapper, String>("idTim"));
        namaTim.setCellValueFactory(new PropertyValueFactory<PersiapanTimWPTableWrapper, String>("namaTim"));
        hapusAction.setCellValueFactory(new PropertyValueFactory<PersiapanTimWPTableWrapper, String>("hapusButton"));
        aturAction.setCellValueFactory(new PropertyValueFactory<PersiapanTimWPTableWrapper, String>("aturButton"));
    }
    
    public void backToFormPersiapan() {
        Pane rootpaneFormPersiapan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_persiapan_ui");
        rootpaneFormPersiapan.getChildren().remove(1);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormPersiapanContentUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPersiapan.getChildren().add(contentPane);
    }
    
    public void openNewAturTimWP() {
        System.out.println("openNewAturTimWP");
        SessionProvider
            .getGlobalSessionsMap()
            .put("atur_tim_wp_selected", null);
        Pane formTambahTimWPUI = null;
        try {
            formTambahTimWPUI = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormTambahTimWPUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Form Tambah Tim");
        stage.setScene(new Scene(formTambahTimWPUI));
        
//        stage.initStyle(StageStyle.UTILITY);
//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    public void aturSuratPerintah() {
        Pane rootpaneFormPersiapan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_persiapan_ui");
        rootpaneFormPersiapan.setMinHeight(159);
        rootpaneFormPersiapan.setPrefHeight(159);
        rootpaneFormPersiapan.setMaxHeight(159);
        rootpaneFormPersiapan.getChildren().remove(1);
        rootpaneFormPersiapan.setMinHeight(159);
        rootpaneFormPersiapan.setPrefHeight(159);
        rootpaneFormPersiapan.setMaxHeight(159);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormAturNomorTanggalSPUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPersiapan.getChildren().add(contentPane);
//        Stage rootStage = (Stage) rootpaneFormPersiapan.getScene().getWindow();
//        
//        Pane firstChild = (Pane) rootpaneFormPersiapan.getChildren().get(0);
//        Pane secondChild = (Pane) rootpaneFormPersiapan.getChildren().get(1);
//       
//        rootStage.setHeight(firstChild.getPrefHeight() + secondChild.getPrefHeight());
    }
    
}
