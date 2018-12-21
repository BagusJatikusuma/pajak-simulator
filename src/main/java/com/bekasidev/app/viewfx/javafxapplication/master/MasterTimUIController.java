/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.master;

import com.bekasidev.app.model.Tim;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.MasterTimTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.WPMasterTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.ObservableArrayList;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class MasterTimUIController implements Initializable {
    @FXML private TableView masterTimTable;
    private TableColumn id,
                        nama;
    @FXML private TableColumn aturAction;
    @FXML private TableColumn hapusAction;
    private ObservableList<MasterTimTableWrapper> dataCollection;
    private PegawaiService service;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addFromFXML();
        populateData();
        associateDataWithColumn();
        masterTimTable.setItems(dataCollection);
    }

    private void addFromFXML() {
        id 
                = TableHelper.getTableColumnByName(masterTimTable, "Id");
        nama 
                = TableHelper.getTableColumnByName(masterTimTable, "Nama");
    }
    
    private void populateData() {
        service = ServiceFactory.getPegawaiService();
        List<Tim> tims = service.getAllTim();
        
        dataCollection = new ObservableArrayList<>();
        for (final Tim obj
                : tims) {
            Button btn = new Button("Atur Anggota");
            Button hapusBtn = new Button("Hapus");
            dataCollection.add(new MasterTimTableWrapper(
                    obj.getIdTim(),
                    obj.getNamaTim(),
                    btn,
                    hapusBtn
            ));
        }
        for (final MasterTimTableWrapper obj : dataCollection) {
            Button btn = obj.getButton();
            Button hapusBtn = obj.getHapusButton();
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {                    
                    System.out.println(obj.getId()+"clicked");
                    SessionProvider
                            .getSessionAturAnggotaTimUIMap()
                            .put("selected_tim", obj.getId());
                    Pane rootpane = ComponentCollectorProvider.getComponentFXMapper().get("root_pane");
                    rootpane.getChildren().remove(1);

                    Pane contentPane = null;
                    try { 
                        contentPane
                                = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterPegawaiTimUI.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rootpane.getChildren().add(contentPane);
                }
            });
            hapusBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {                    
                    System.out.println("hapus"+obj.getId()+"clicked");
                    
                    service.deleteTim(obj.getId());
                    
                    Pane rootpane = ComponentCollectorProvider.getComponentFXMapper().get("root_pane");
                    rootpane.getChildren().remove(1);

                    Pane contentPane = null;
                    try { 
                        contentPane
                                = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterTimUI.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rootpane.getChildren().add(contentPane);
                    
                }
            });
            
        }
        
    }
    
    private void associateDataWithColumn() {
        id.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("id"));
        id.prefWidthProperty().bind(masterTimTable.widthProperty().divide(3));
        nama.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("nama"));
        nama.prefWidthProperty().bind(masterTimTable.widthProperty().divide(3));
        aturAction.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("button"));
        aturAction.prefWidthProperty().bind(masterTimTable.widthProperty().divide(4));
        hapusAction.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("hapusButton"));
        hapusAction.prefWidthProperty().bind(masterTimTable.widthProperty().divide(4));
    }
    
    public void addTim() {
        Pane formTambahTim = null;
        try {
            formTambahTim = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormTambahTimUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Form Tambah Tim");
        stage.setScene(new Scene(formTambahTim));
        
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
