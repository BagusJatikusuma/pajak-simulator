/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.model.Restoran;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.RestoranService;
import com.bekasidev.app.service.backend.impl.RestoranServiceImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bagus
 */
public class RestaurantTableComponent extends JPanel {
    private RestoranService service;
    
    public void init() {
        service = ServiceFactory.getRestoranService();
        
        JScrollPane jScrollPane1 = new JScrollPane();
        JTable restoranTable = new JTable();
        DefaultTableModel dtm = new DefaultTableModel(0,0);
        String header[] = new String[] {"ID_RESTAURANT", "NAMA_RESTAURANT", ""};
        dtm.setColumnIdentifiers(header);
        restoranTable.setModel(dtm);
        
        List<Restoran> restorans = service.getAllRestoran();
        
        for (Restoran obj : restorans) {
            dtm.addRow(new Object[] {obj.getIdRestoran(), obj.getNamaRestoran(), "delete"});
        }
        Action delete = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                System.out.println(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0).toString());
                service.deleteRestoran(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0).toString());
                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
            }
        };
        
        ButtonColumn buttonColumn = new ButtonColumn(restoranTable, delete, 2);
                
        restoranTable.setGridColor(new java.awt.Color(255, 255, 255));
        restoranTable.setRowHeight(22);
        jScrollPane1.setViewportView(restoranTable);
        
        jScrollPane1.setSize(400, 300);
        jScrollPane1.setLocation(50, 330);
        
        this.add(jScrollPane1);
        
    }
    
    public int getTableWidth() {
        return this.getComponent(0).getWidth();
    }
    
    public int getTableHeight() {
        return this.getComponent(0).getHeight();
    }
    
    
    
}
