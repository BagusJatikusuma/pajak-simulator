/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.master;

import com.bekasidev.app.model.Pegawai;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.MasterAnggotaTimTableWrapper;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class MasterPegawaiTimUIController implements Initializable {
    @FXML private TableView masterTimAnggotaTable;
    private TableColumn nip, 
                        nama, 
                        jabatan,
                        action;
    private PegawaiService service;
     private ObservableList<MasterAnggotaTimTableWrapper> dataCollection;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addFromFXML();
        populateData();
        associateDataWithColumn();
        masterTimAnggotaTable.setItems(dataCollection);
    }    
    
    public void addTimAnggota() {
        Pane formTambahWP = null;
        try {
            formTambahWP = FXMLLoader
                    .load(getClass().getClassLoader().getResource("fxml/FormTambahAnggotaTimUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterWajibPajakUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle("Form tambah anggota tim");
        stage.setScene(new Scene(formTambahWP));
        stage.show();
    }
    
    public void backToMasterTim() {
        Pane rootpane = ComponentCollectorProvider.getComponentFXMapper().get("root_pane");
        rootpane.getChildren().remove(1);

        Pane contentPane = null;
        try { 
            contentPane
                    = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MasterTimUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootpane.getChildren().add(contentPane);
    }
    
    private void addFromFXML() {
        nip 
                = TableHelper.getTableColumnByName(masterTimAnggotaTable, "NIP");
        nama
                = TableHelper.getTableColumnByName(masterTimAnggotaTable, "Nama");
        jabatan 
                = TableHelper.getTableColumnByName(masterTimAnggotaTable, "Jabatan");
        action 
                = TableHelper.getTableColumnByName(masterTimAnggotaTable, "Action");
    }
    
    private void populateData() {
        service = ServiceFactory.getPegawaiService();
        String idTim = (String) SessionProvider
                .getSessionAturAnggotaTimUIMap()
                .get("selected_tim");
        System.out.println("in master anggota tim "+idTim);
        List<Pegawai> pegawaiList 
                = service.getPegawaiByTim(idTim);
        
        dataCollection = new ObservableArrayList<>();
        for (Pegawai obj: pegawaiList) {
            System.out.println("pegawai"+ obj.getNamaPegawai()+" jabatan "+obj.getJabatanTim());
            Button btn = new Button("Hapus");
            dataCollection.add(new MasterAnggotaTimTableWrapper(
                    obj.getNipPegawai(),
                    obj.getNamaPegawai(),
                    obj.getJabatanTim(),
                    btn
            ));
        }
        
        for (final MasterAnggotaTimTableWrapper obj: dataCollection) {
            Button btn = obj.getAction();
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {                    
                    System.out.println("hapus "+obj.getNip()+"clicked");
                    
                    
                }
            });
        }
    }
    
    private void associateDataWithColumn() {
        nip.setCellValueFactory(new PropertyValueFactory<MasterAnggotaTimTableWrapper, String>("nip"));
        nama.setCellValueFactory(new PropertyValueFactory<MasterAnggotaTimTableWrapper, String>("nama"));
        jabatan.setCellValueFactory(new PropertyValueFactory<MasterAnggotaTimTableWrapper, String>("jabatan"));
        action.setCellValueFactory(new PropertyValueFactory<MasterAnggotaTimTableWrapper, String>("action"));
    }
    
}
