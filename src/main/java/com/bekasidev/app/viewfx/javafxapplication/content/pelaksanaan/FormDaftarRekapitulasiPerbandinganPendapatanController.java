/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipPelaksanaanTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanTimWPTableWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    @FXML private TableColumn omzetHasilpemeriksaan;
    @FXML private TableColumn omzetDilaporkan;
    @FXML private TableColumn action;
    
    @FXML private Label namaTimLabel;
    @FXML private Label namaWPLabel;
    @FXML private Label npwpdLabel;
    
    private ObservableList<ArsipPelaksanaanTableWrapper> dataCollection;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initLabel();
//        populateData();
//        associateDataWithColumn();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void associateDataWithColumn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void initLabel() {
        PelaksanaanWrapper pelaksanaanWrapper
                = (PelaksanaanWrapper) SessionProvider.getGlobalSessionsMap()
                                    .get("pelaksanaan_wrapper");
        namaTimLabel.setText(pelaksanaanWrapper.getTimSelected().getNamaTim());
        namaWPLabel.setText(pelaksanaanWrapper.getWpSelected().getNamaWajibPajak());
        npwpdLabel.setText(pelaksanaanWrapper.getWpSelected().getNpwpd());
    }
}
