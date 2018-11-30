/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.persiapan;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.Tim;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class FormPersiapanUIController implements Initializable {
    @FXML private Pane formDokumenPersiapanPane;
    
    @FXML private TextField dasarNomorField;
    @FXML private DatePicker dasarTanggalField;
    @FXML private TextField dasarTahunAnggaranField;
    
    @FXML private ChoiceBox penandatanganField;
    @FXML private ChoiceBox masaPajakAwalBulan;
    @FXML private TextField masaPajakAwalTahun;
    @FXML private ChoiceBox masaPajakAkhirBulan;
    @FXML private TextField masaPajakAkhirTahun;
    
    @FXML private TextField tahapKeField;
    @FXML private TextField tahunAnggaranAPBDField;
    @FXML private DatePicker tanggalAPBDField;
    @FXML private TextField nomorAPBDField;
    @FXML private TextField ditetapkanDiField;
    @FXML private DatePicker tanggalPengesahanField;
    @FXML private TextField nomorSuratField;
    @FXML private TextField lamaPelaksanaanField;
    
    @FXML private Button cancelBtn;
    private PegawaiService service;
    @FXML private Label headerFormPersiapanLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ComponentCollectorProvider
                .addFxComponent("root_form_persiapan_ui", formDokumenPersiapanPane);
        SessionProvider.getGlobalSessionsMap()
                .put("header_form_persiapan", headerFormPersiapanLabel);
        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FormPersiapanContentUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        formDokumenPersiapanPane.getChildren().add(contentPane);
    }
    
}
