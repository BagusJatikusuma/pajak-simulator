/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.SuratPerintahService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.ConverterHelper;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipPelaksanaanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PilihSPTableWrapper;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class FormPelaksanaanSPUIController implements Initializable {
    @FXML private TableView pilihSPTable;
    @FXML private TableColumn idSP;
    @FXML private TableColumn nomorSP;
    @FXML private TableColumn tanggalSP;
    @FXML private Button cancelBtn;
    
    private SuratPerintahService suratPerintahService;
    private ObservableList<PilihSPTableWrapper> dataCollection;
    private Map<String, SuratPerintah> suratPerintahMapper = new HashMap<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        suratPerintahService = ServiceFactory.getSuratPerintahService();
        populateData();
        associateDataWithColumn();
        pilihSPTable.setItems(dataCollection);
    }

    public void cancelOperation() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void openPilihTim() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        PilihSPTableWrapper wrapper 
                = (PilihSPTableWrapper) pilihSPTable.getSelectionModel().getSelectedItem();
        PersiapanWrapper persiapanWrapper
                = ConverterHelper.convertSuratPerintahToPersiapanWrapper(suratPerintahMapper.get(wrapper.getIdSP()));
        pelaksanaanWrapper.setPersiapanWrapper(persiapanWrapper);
        
        Pane rootpaneFormPelaksanaan = ComponentCollectorProvider.getComponentFXMapper().get("root_form_pelaksanaan_ui");
        rootpaneFormPelaksanaan.getChildren().remove(0);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormPelaksanaanTimUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpaneFormPelaksanaan.getChildren().add(contentPane);
        
    }

    private void populateData() {
        List<SuratPerintah> suratPerintahList 
                = suratPerintahService.getAllSuratPerintah();
        dataCollection = FXCollections.observableArrayList();
        DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"));
        
        for (SuratPerintah sp : suratPerintahList) {
            if (sp.getTanggalSurat() != null) {
                dataCollection.add(new PilihSPTableWrapper(
                        sp.getIdSP(),
                        "800/"+sp.getNomorUrut()+"/Bapenda",
                        formatter.format(new Date(Long.valueOf(sp.getTanggalSurat())))
                ));
                suratPerintahMapper.put(sp.getIdSP(), sp);
            }
        }
        
    }

    private void associateDataWithColumn() {
        idSP.setCellValueFactory(new PropertyValueFactory<PilihSPTableWrapper, String>("idSP"));
        nomorSP.setCellValueFactory(new PropertyValueFactory<PilihSPTableWrapper, String>("nomorSP"));
        tanggalSP.setCellValueFactory(new PropertyValueFactory<PilihSPTableWrapper, String>("tanggalSP"));
    }
    
}
