/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaporan;

import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.TimSP;
import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.SuratPerintahService;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipTablePelaksanaanWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.EvaluasiTableWrapper;
import com.bekasidev.app.viewfx.javafxapplication.model.PelaporanWrapper;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sudoroot
 */
public class EvaluasiUIController implements Initializable {

    @FXML private TableView evaluasiTable;
    @FXML private TableColumn no;
    @FXML private TableColumn tahapPemeriksaan;
    @FXML private TableColumn noSP;
    @FXML private TableColumn tim;
    @FXML private TableColumn wp;
    @FXML private TableColumn action;
    
    private ObservableList<EvaluasiTableWrapper> dataCollection;
    private SuratPerintahService suratPerintahService;
    private Map<String, PelaporanWrapper> pelaporanMapper = new HashMap<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        suratPerintahService = ServiceFactory.getSuratPerintahService();
        populateData();
        associateDataWithColumn();
        
        evaluasiTable.setItems(dataCollection);
    }    

    private void populateData() {
        dataCollection = FXCollections.observableArrayList();
        List<SuratPerintah> suratPerintahs = suratPerintahService.getAllSuratPerintah();
        int index = 1;
        for (SuratPerintah sp : suratPerintahs) {
            for (TimSP timSP : sp.getListTim()) {
                for (WajibPajak wp : timSP.getListWP()) {
                    Button btn = new Button("lihat detail");
                    dataCollection.add(new EvaluasiTableWrapper(
                        String.valueOf(index),
                        String.valueOf(sp.getTahap()),
                        "800/"+sp.getNomorUrut()+"/Bapenda",
                        timSP.getNamaTim(),
                        wp.getNamaWajibPajak(),
                        btn
                    ));
                    pelaporanMapper.put(
                            String.valueOf(index), new PelaporanWrapper(sp,timSP,wp));
                    index++;
                }
       
            }
            
        }
        for (EvaluasiTableWrapper etw : dataCollection) {
            Button btn = etw.getAction();
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    PelaporanWrapper wrapper = pelaporanMapper.get(etw.getNo());
                    System.out.println("sp "+wrapper.getSuratPerintahSelected().getNomorUrut()
                            +"tim "+wrapper.getTimSPSelected().getIdTim()
                            +"wp "+wrapper.getWpSelected().getNamaWajibPajak());
                }
            });
        }
        
        Collections.sort(dataCollection, new Comparator<EvaluasiTableWrapper>() {
            @Override
            public int compare(EvaluasiTableWrapper t, EvaluasiTableWrapper t1) {
                return Integer.valueOf(t.getTahapPemeriksaan())
                        .compareTo(Integer.valueOf(t1.getTahapPemeriksaan()));
            }
        });
        
    }

    private void associateDataWithColumn() {
        no.setCellValueFactory(new PropertyValueFactory<EvaluasiTableWrapper, String>("no"));
        tahapPemeriksaan.setCellValueFactory(new PropertyValueFactory<EvaluasiTableWrapper, String>("tahapPemeriksaan"));
        noSP.setCellValueFactory(new PropertyValueFactory<EvaluasiTableWrapper, String>("noSP"));
        tim.setCellValueFactory(new PropertyValueFactory<EvaluasiTableWrapper, String>("tim"));
        wp.setCellValueFactory(new PropertyValueFactory<EvaluasiTableWrapper, String>("wp"));
        action.setCellValueFactory(new PropertyValueFactory<EvaluasiTableWrapper, String>("action"));
    }
    
    public void printEvaluasi() {
        
    }
    
    public void printKopLHP() {
        
    }
    
}
