/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.master;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.WajibPajakService;
import com.bekasidev.app.service.reportservice.ReportService;
import com.bekasidev.app.service.reportservice.reportserviceimpl.ReportServiceImpl;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.view.util.modelview.PersiapanPajakPOJO;
import com.bekasidev.app.view.util.modelview.WajibPajakModelView;
import com.bekasidev.app.viewfx.javafxapplication.content.pajakrestoran.PajakRestoranUIController;
import com.bekasidev.app.viewfx.javafxapplication.model.PajakRestoranTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PersiapanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.WPMasterTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.util.ObservableArrayList;
import com.bekasidev.app.viewfx.javafxapplication.util.TableHelper;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MasterWajibPajakUIController implements Initializable {
    @FXML private TableView wajibPajakTable;
    @FXML private TextField cariWPField;
    private TableColumn idWP, 
                        no, 
                        namaWP, 
                        alamat, 
                        desa, 
                        kecamatan,
                        jenisWP,
                        action;
    private ObservableList<WPMasterTableWrapper> dataCollection;
    //digunakan sebagai penampung data hasil pencarian
    private ObservableList<WPMasterTableWrapper> filteredCollection;
    private Map<String, WajibPajak> wpMapper = new HashMap<>();
    private WajibPajakService service;
    private int indexTemp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SessionProvider.getGlobalSessionsMap().put("update_wp_selected", null);
        addFromFXML();
        populateData();
        associateDataWithColumn();
        wajibPajakTable.setItems(dataCollection);
    }

    public void updateWajibPajak() {
        WPMasterTableWrapper wrapper
                = (WPMasterTableWrapper) wajibPajakTable.getSelectionModel().getSelectedItem();
        if (wrapper==null) {
            showErrorNotif();
            return;
        }
        SessionProvider.getGlobalSessionsMap().put("update_wp_selected", wpMapper.get(wrapper.getNo()));
        
        Pane formTambahWP = null;
        try {
            formTambahWP = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormTambahWPUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Form Update WP");
        stage.setScene(new Scene(formTambahWP));
        
//        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    public void addWajibPajakHandle(ActionEvent actionEvent) {
        SessionProvider.getGlobalSessionsMap().put("update_wp_selected", null);
        Pane formTambahWP = null;
        try {
            formTambahWP = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormTambahWPUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Form Tambah WP");
        stage.setScene(new Scene(formTambahWP));
        
//        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    public void cariWP() {
        if (!cariWPField.getText().equals("")) {
            if (!populateDataBasedSearch().isEmpty()) {
                wajibPajakTable.setItems(populateDataBasedSearch());
            }
            wajibPajakTable.refresh();
        }
    }
    
    public void resetTable() {
        wajibPajakTable.setItems(dataCollection);
        wajibPajakTable.refresh();
    }
    
    private void addFromFXML() {
        idWP 
                = TableHelper.getTableColumnByName(wajibPajakTable, "NPWPD");
        no
                = TableHelper.getTableColumnByName(wajibPajakTable, "No");
        namaWP 
                = TableHelper.getTableColumnByName(wajibPajakTable, "Nama WP");
        alamat 
                = TableHelper.getTableColumnByName(wajibPajakTable, "Alamat");
        desa 
                = TableHelper.getTableColumnByName(wajibPajakTable, "Desa");
        kecamatan 
                = TableHelper.getTableColumnByName(wajibPajakTable, "Kecamatan");
        jenisWP 
                = TableHelper.getTableColumnByName(wajibPajakTable, "Jenis WP");
        action 
                = TableHelper.getTableColumnByName(wajibPajakTable, "action");
    }
    
    private ObservableList<WPMasterTableWrapper> populateDataBasedSearch() {
        filteredCollection = FXCollections.observableArrayList();
        String searchText = cariWPField.getText().toLowerCase();
        
        for (Iterator it = dataCollection.iterator(); it.hasNext();) {
            WPMasterTableWrapper wrapper = (WPMasterTableWrapper) it.next();
            if (wrapper.getNamaWajibPajak().toLowerCase().contains(searchText)) {
                filteredCollection.add(wrapper);
            }
        }
        
        return filteredCollection;
    }
    
    private void populateData() {
        service = ServiceFactory.getWajibPajakService();
        List<com.bekasidev.app.model.WajibPajak> restorans = service.getAllWP();
        
        dataCollection = new ObservableArrayList<>();
        int i = 1;
        indexTemp = i;
        for (final com.bekasidev.app.model.WajibPajak obj
                : restorans) {
            Button btn = new Button("Hapus");

            String jenisWP = "";
            switch(obj.getJenisWp()) {
                case 0: 
                    jenisWP = "Restoran";
                    break;
                case 1: 
                    jenisWP = "Hotel";
                    break;
                case 2: 
                    jenisWP = "Parkir";
                    break;
                case 3:
                    jenisWP = "Hiburan";
                    break;
                case 4: 
                    jenisWP = "Penerangan Jalan";
                    break;
                default:
                    jenisWP = "Unidentified";
            }
            
            dataCollection.add(new WPMasterTableWrapper(
                            String.valueOf(i),
                            obj.getNpwpd(),
                            obj.getNamaWajibPajak(),
                            jenisWP,
                            obj.getJalan(),
                            obj.getKecamatan(),
                            obj.getDesa(),
                            btn
                    ));
            wpMapper.put(String.valueOf(i), obj);
            i++;
            indexTemp = i;
        }
        
        //set action for every button
        for (final WPMasterTableWrapper obj : dataCollection) {
            Button btn = obj.getButton();
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {                    
                    System.out.println(obj.getNamaWajibPajak()+"clicked");
                    wajibPajakTable.getItems().remove(obj);
                    //remove data from database
                    service.deleteWP(obj.getIdWajibPajak());
                    //nanti ganti method untuk mereset nomor row
                    Pane rootpane = ComponentCollectorProvider.getComponentFXMapper().get("root_pane");
                    rootpane.getChildren().remove(1);

                    Pane contentPane = null;
                    try { 
                        contentPane
                                = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterWajibPajakUI.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rootpane.getChildren().add(contentPane);
                    
                }
            });
        }
    }
    
    private void associateDataWithColumn() {
        no.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("no"));
        no.prefWidthProperty().bind(wajibPajakTable.widthProperty().divide(22));
        idWP.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("idWajibPajak"));
        idWP.prefWidthProperty().bind(wajibPajakTable.widthProperty().divide(6));
        namaWP.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("namaWajibPajak"));
        namaWP.prefWidthProperty().bind(wajibPajakTable.widthProperty().divide(5));
        alamat.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("jalan"));
        alamat.prefWidthProperty().bind(wajibPajakTable.widthProperty().divide(6));
        desa.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("desa"));
        desa.prefWidthProperty().bind(wajibPajakTable.widthProperty().divide(10));
        kecamatan.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("kecamatan"));
        kecamatan.prefWidthProperty().bind(wajibPajakTable.widthProperty().divide(6));
        jenisWP.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("jenisWp"));
        jenisWP.prefWidthProperty().bind(wajibPajakTable.widthProperty().divide(10));
        action.setCellValueFactory(new PropertyValueFactory<WPMasterTableWrapper, String>("button"));
        action.prefWidthProperty().bind(wajibPajakTable.widthProperty().divide(22));
    }
    
    private void showErrorNotif() {
        SessionProvider.getGlobalSessionsMap().put("notif_message_popup", "Anda belum memilih wajib pajak");
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
