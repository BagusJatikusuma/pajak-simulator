/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.masterview;

import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.WajibPajakService;
import com.bekasidev.app.view.pajakrestoranview.FormPersiapanRestoranFrame;
import com.bekasidev.app.view.tablecomponent.ButtonColumn;
import com.bekasidev.app.view.tablecomponent.GroupableTableHeader;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.view.util.modelview.PersiapanPajakPOJO;
import com.bekasidev.app.view.util.modelview.WajibPajak;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author USER
 */
public class MasterWpTableComponent extends JPanel {
    private WajibPajakService service;
    
    public MasterWpTableComponent() {
        ComponentCollectorProvider.addComponent("master_wp_table_component", this, null, null);
        init();
    }
    
    public void init() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        service = ServiceFactory.getWajibPajakService();
        
        JScrollPane jScrollPane1 = new JScrollPane();
        JTable restoranTable = new JTable() {
            @Override
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };
        
        DefaultTableModel dtm = new DefaultTableModel(0,0);
        String header[] = new String[] {"NO","ID WP", "NAMA WP", "ALAMAT","DESA","KECAMATAN","JENIS WP",""};
        dtm.setColumnIdentifiers(header);
        restoranTable.setModel(dtm);
        
        restoranTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        restoranTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        restoranTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        restoranTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        restoranTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        restoranTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        
        List<com.bekasidev.app.model.WajibPajak> restorans = service.getAllWP();
        
        int index = 1;
        for (com.bekasidev.app.model.WajibPajak obj : restorans) {
            String jenisWP = "";
            switch (obj.getJenisWp()) {
                case 0 : 
                    jenisWP = "Restoran";
                    break;
                case 1 : 
                    jenisWP = "Hotel";
                    break;
                default : break;
            }
            dtm.addRow(new Object[] {
                index++,
                obj.getIdWajibPajak(), 
                obj.getNamaWajibPajak(),
                obj.getJalan(),
                obj.getDesa(),
                obj.getKecamatan(),
                jenisWP,
                "hapus"});
            
        }
        
        Action selectedRow = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                System.out.println(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 1).toString());
                
                service.deleteWP(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 1).toString());
                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
            }
            
        };
        
        ButtonColumn buttonColumn = new ButtonColumn(restoranTable, selectedRow, 7);
        restoranTable.setGridColor(new java.awt.Color(255, 255, 255));
        restoranTable.setRowHeight(22);
        
        jScrollPane1.setViewportView(restoranTable);
        
        jScrollPane1.setPreferredSize(new Dimension(1000,500));
        jScrollPane1.setLocation(100, 330);
        
        this.add(jScrollPane1);
    }
}
