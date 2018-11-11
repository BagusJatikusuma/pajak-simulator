/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.service.reportservice.reportserviceimpl;

import com.bekasidev.app.service.reportservice.ReportService;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Bayu Arafli
 */
public class ReportServiceImpl implements ReportService {
    private JasperReport jasperReport;
    
    @Override
    public void createPersiapanPajakRestoranReport() {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        
        InputStream reportFile
                = getClass().getResourceAsStream("jasperxml/SuratPemberitahuanPemeriksaan.jrxml");
        try {
            jasperReport
                    = JasperCompileManager.compileReport(reportFile);
        } catch (JRException ex) {
            Logger.getLogger(ReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void createPersiapanPajakHotelReport() {
        
    }
    @Override
    public void createPersiapanPajakParkirReport() {
        
    }
    
}
