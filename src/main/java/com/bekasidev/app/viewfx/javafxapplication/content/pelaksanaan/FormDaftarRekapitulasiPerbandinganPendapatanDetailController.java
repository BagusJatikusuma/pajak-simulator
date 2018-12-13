/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.content.pelaksanaan;

import com.bekasidev.app.model.Rekapitulasi;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.viewfx.javafxapplication.mainmenu.UIController;
import com.bekasidev.app.viewfx.javafxapplication.model.ArsipPelaksanaanTableWrapper;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bayu Arafli
 */
public class FormDaftarRekapitulasiPerbandinganPendapatanDetailController implements Initializable {

    @FXML private Button cancelBtn;
    @FXML private TextField omzetHasilField;
    @FXML private TextField omzetDiLaporkanField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initData();
        initListener();
    }

    public void cancelOperation() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    private void initListener() {
        omzetHasilField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) {
                NumberFormat anotherFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
                DecimalFormat formatter = (DecimalFormat) anotherFormat;
                String newValueStr = formatter.format(Double.parseDouble(newValue));

                omzetHasilField.setText(newValueStr);
            }
            else if (newValue.matches("^-?\\d{1,}(?:\\.\\d{1,4})*(?:,\\d+)?$")) {
                NumberFormat anotherFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
                DecimalFormat formatter = (DecimalFormat) anotherFormat;
                newValue = newValue.replace(".", "");
                String newValueStr = formatter.format(Double.parseDouble(newValue));

                omzetHasilField.setText(newValueStr);
            }
         });
        omzetDiLaporkanField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) {
                NumberFormat anotherFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
                DecimalFormat formatter = (DecimalFormat) anotherFormat;
                String newValueStr = formatter.format(Double.parseDouble(newValue));

                omzetDiLaporkanField.setText(newValueStr);
            }
            else if (newValue.matches("^-?\\d{1,}(?:\\.\\d{1,4})*(?:,\\d+)?$")) {
                System.out.println("val "+newValue);
                NumberFormat anotherFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
                DecimalFormat formatter = (DecimalFormat) anotherFormat;
                System.out.println("val "+newValue);
                newValue = newValue.replace(".", "");
                String newValueStr = formatter.format(Double.parseDouble(newValue));

                omzetDiLaporkanField.setText(newValueStr);
            }
         });

    }
    
    private void initData() {
        Rekapitulasi rek
                = (Rekapitulasi) SessionProvider
                    .getGlobalSessionsMap()
                    .get("selected_bulan_rekapitulasi");
        NumberFormat anotherFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
        DecimalFormat formatter = (DecimalFormat) anotherFormat;
        
        if (rek.getOmzetLaporan() != null)
            omzetDiLaporkanField.setText(formatter.format(rek.getOmzetLaporan()));
        if (rek.getOmzetHasilPeriksa() != null)
            omzetHasilField.setText(formatter.format(rek.getOmzetHasilPeriksa()));
    }
    
    public void finishOperation() {
        Rekapitulasi rek
                = (Rekapitulasi) SessionProvider
                    .getGlobalSessionsMap()
                    .get("selected_bulan_rekapitulasi");
        TableView arsipPelaksanaanTable
                =  SessionProvider
                    .getTableViewSessionMap()
                    .get("tabel_rekapitulasi");
        String indexTableRow
                = (String) SessionProvider
                    .getGlobalSessionsMap()
                    .get("index_data_tabel_rekapitulasi");
        
        if (!(omzetDiLaporkanField.getText().equals("") 
                ||omzetHasilField.getText().equals(""))) {
            System.out.println("setter now table index "+indexTableRow);
            //update back data
            rek.setOmzetHasilPeriksa(Double.valueOf(omzetHasilField.getText().replace(".", "")));
            rek.setOmzetLaporan(Double.valueOf(omzetDiLaporkanField.getText().replace(".", "")));
            //update front table data
            ArsipPelaksanaanTableWrapper wrapper
                    = (ArsipPelaksanaanTableWrapper) arsipPelaksanaanTable.getItems().get(Integer.parseInt(indexTableRow)-1);
            wrapper.setOmzethasilPemeriksaan("Rp"+omzetHasilField.getText());
            wrapper.setOmzetDiLaporkan("Rp"+omzetDiLaporkanField.getText());
            arsipPelaksanaanTable.getItems().set(Integer.parseInt(indexTableRow)-1, wrapper);
        }
        
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
}
