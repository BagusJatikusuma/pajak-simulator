/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.model.Tim;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.master.MasterWajibPajakUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PilihSPTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PilihTimTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.TimWPWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class FormPelaksanaanTimUIController implements Initializable {
    @FXML private TableView pilihTimTable;
    @FXML private TableColumn idTim;
    @FXML private TableColumn namaTim;
    
    @FXML private Button backBtn;
    
    private ObservableList<PilihTimTableWrapper> dataCollection;
    private Map<String, Tim> timMapper = new HashMap<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateData();
        associateDataWithColumn();
        pilihTimTable.setItems(dataCollection);
    }

    public void backToPilihSuratPerintah() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        if (pelaksanaanWrapper.getTimSelected() != null)
            pelaksanaanWrapper.setTimSelected(null);
        
        Pane rootpaneFormPelaksanaan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_pelaksanaan_ui");
        rootpaneFormPelaksanaan.getChildren().remove(0);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormPelaksanaanSPUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPelaksanaan.getChildren().add(contentPane);
    }
    
    public void openPilihWP() {
        if (pilihTimTable.getSelectionModel().getSelectedItem() == null) {
            showErrorNotif();
            return;
        }
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        PilihTimTableWrapper wrapper
                = (PilihTimTableWrapper) pilihTimTable.getSelectionModel().getSelectedItem();
        Tim tim = timMapper.get(wrapper.getIdTim());
        
        pelaksanaanWrapper.setTimSelected(tim);
        
        Pane rootpaneFormPelaksanaan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_pelaksanaan_ui");
        rootpaneFormPelaksanaan.getChildren().remove(0);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormPelaksanaanWPUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPelaksanaan.getChildren().add(contentPane);
        
        
    }

    private void populateData() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        dataCollection = FXCollections.observableArrayList();
        
        for (TimWPWrapper timWP : pelaksanaanWrapper.getPersiapanWrapper().getTimWPWrappers()) {
            dataCollection.add(new PilihTimTableWrapper(
                    timWP.getTim().getIdTim(),
                    timWP.getTim().getNamaTim()
            ));
            timMapper.put(timWP.getTim().getIdTim(), timWP.getTim());
        }
        
    }

    private void associateDataWithColumn() {
        idTim.setCellValueFactory(new PropertyValueFactory<PilihTimTableWrapper, String>("idTim"));
        idTim.prefWidthProperty().bind(pilihTimTable.widthProperty().divide(2));
        namaTim.setCellValueFactory(new PropertyValueFactory<PilihTimTableWrapper, String>("namaTim"));
        namaTim.prefWidthProperty().bind(pilihTimTable.widthProperty().divide(2));
    }
    
    private void showErrorNotif() {
        SessionProvider.getGlobalSessionsMap().put("notif_message_popup", "Anda belum memilih Tim");
        Pane popup = null;
        try {
            popup = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/PopupPaneUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(popup));
        stage.show();
    }
    
}
