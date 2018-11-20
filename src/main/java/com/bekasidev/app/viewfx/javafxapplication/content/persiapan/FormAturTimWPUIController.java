/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.WajibPajak;
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
    
    private ObservableList<PersiapanTimWPTableWrapper> dataCollection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addFromFXML();
        populateData();
        associateDataWithColumn();
        PersiapanTimWPTable.setItems(dataCollection);
    }
    
    private void addFromFXML() {
        idTim
                = TableHelper.getTableColumnByName(PersiapanTimWPTable, "Id tim");
        namaTim
                = TableHelper.getTableColumnByName(PersiapanTimWPTable, "Nama tim");
        hapusAction 
                = TableHelper.getTableColumnByName(PersiapanTimWPTable, "Hapus action");
        aturAction 
                = TableHelper.getTableColumnByName(PersiapanTimWPTable, "Atur action");
    }
    
    private void populateData() {
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        dataCollection = FXCollections.observableArrayList();
        for (TimWPWrapper obj : persiapanWrapper.getTimWPWrappers()) {
            Button hapusButton = new Button("hapus");
            Button aturButton = new Button("atur");
            
            dataCollection.add(new PersiapanTimWPTableWrapper(
                    obj.getTim().getIdTim(),
                    obj.getTim().getNamaTim(),
                    hapusButton,
                    aturButton
            ));
            
        }
        for (final PersiapanTimWPTableWrapper obj : dataCollection) {
            Button hapusButton = obj.getHapusButton();
            Button aturButton = obj.getAturButton();
            
            hapusButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {                    
                    System.out.println("hapus "+obj.getIdTim()+"clicked");
                    
                }
            });
            aturButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {                    
                    System.out.println("atur "+obj.getIdTim()+"clicked");
                    
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
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormPersiapanUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPersiapan.getChildren().add(contentPane);
    }
    
    public void openNewAturTimWP() {
        System.out.println("openNewAturTimWP");
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
        stage.show();
    }
    
    public void finishPersiapan() {
        System.out.println("finishPersiapan");
        PersiapanWrapper persiapanWrapper
                = (PersiapanWrapper) SessionProvider
                .getGlobalSessionsMap()
                .get("persiapan_wrapper");
        
        for (TimWPWrapper obj : persiapanWrapper.getTimWPWrappers()) {
            System.out.println("Tim : "+obj.getTim().getNamaTim());
            System.out.println("Penanggung jawab : "+obj.getPenanggungJawab().getNamaPegawai());
            System.out.println("Supervisor : "+obj.getSupervisor().getNamaPegawai());
            for (WajibPajak wp : obj.getWajibPajaks()) {
                System.out.println("WP "+wp.getNamaWajibPajak());
            }
        }
        
    }
    
}
