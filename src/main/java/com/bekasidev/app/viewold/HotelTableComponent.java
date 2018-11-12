/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewold;

import com.bekasidev.app.model.Hotel;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.HotelService;

import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bagus
 */
public class HotelTableComponent extends JPanel {
    private HotelService service;
    
    public void init() {
        service = ServiceFactory.getHotelService();
        
        JScrollPane jScrollPane1 = new JScrollPane();
        JTable restoranTable = new JTable();
        DefaultTableModel dtm = new DefaultTableModel(0,0);
        String header[] = new String[] {"ID_HOTEL", "NAMA_HOTEL"};
        dtm.setColumnIdentifiers(header);
        restoranTable.setModel(dtm);
        
        List<Hotel> hotels = service.getAllHotel();
        
        for (Hotel obj : hotels) {
            dtm.addRow(new Object[] {obj.getIdHotel(), obj.getNamaHotel()});
        }
        
        restoranTable.setGridColor(new java.awt.Color(255, 255, 255));
        restoranTable.setRowHeight(22);
        jScrollPane1.setViewportView(restoranTable);
        
        jScrollPane1.setSize(400, 300);
        jScrollPane1.setLocation(50, 330);
        
        this.add(jScrollPane1);
    }
    
}
