/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakrestoranview;

import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.WajibPajakService;
import com.bekasidev.app.view.tablecomponent.ButtonColumn;
import com.bekasidev.app.view.tablecomponent.GroupableTableHeader;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import com.bekasidev.app.view.util.SessionProvider;
import com.bekasidev.app.view.util.modelview.PersiapanPajakPOJO;
import com.bekasidev.app.view.util.modelview.WajibPajakModelView;
import java.awt.Color;
import java.awt.Dimension;
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
 * @author Bayu Arafli
 */
public class PajakRestoranTableComponent extends JPanel {
    private WajibPajakService service;

    public PajakRestoranTableComponent() {
        ComponentCollectorProvider.addComponent("pajak_restoran_table_component", this, null, null);
        init();
    }
    
    public void init() {
        this.setBackground(Color.decode("#f3f3f3"));
        
        service = ServiceFactory.getWajibPajakService();
        
        JScrollPane jScrollPane1 = new JScrollPane();
        JTable restoranTable = new JTable() {
            @Override
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };
        
        DefaultTableModel dtm = new DefaultTableModel(0,0);
        String header[] = new String[] {"NO","ID_RESTAURANT", "NAMA_RESTAURANT", "ALAMAT","DESA","KECAMATAN",""};
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
            if (obj.getJenisWp() == 0) {
                dtm.addRow(new Object[] {
                    index++,
                    obj.getIdWajibPajak(), 
                    obj.getNamaWajibPajak(),
                    obj.getJalan(),
                    obj.getDesa(),
                    obj.getKecamatan(),
                    "pilih"});
            }
        }
        
        Action selectedRow = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                System.out.println(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 1).toString());
                
                WajibPajakModelView wajibPajak = new WajibPajakModelView();
                wajibPajak.setNpwpd(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 1).toString());
                wajibPajak.setNamaWP(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 2).toString());
                wajibPajak.setAlamatWP(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 3).toString());
                wajibPajak.setDesaWP(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 4).toString());
                wajibPajak.setKecamatanWP(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 5).toString());
                
                PersiapanPajakPOJO persiapanPajakPOJO = new PersiapanPajakPOJO();
                persiapanPajakPOJO.setWajibPajak(wajibPajak);
                
                Map<String, Object> persiapanPajakRetoranMap
                        = SessionProvider.getPajakMapSession();
                persiapanPajakRetoranMap.put("persiapan_pajak_restoran", persiapanPajakPOJO);
                
                System.out.println("data pajak restoran telah disimpan");
                
                FormPersiapanRestoranFrame formPersiapanRestoranFrame
                        = new FormPersiapanRestoranFrame();
                formPersiapanRestoranFrame.pack();
                formPersiapanRestoranFrame.setVisible(true);
            }
            
        };
        
        ButtonColumn buttonColumn = new ButtonColumn(restoranTable, selectedRow, 6);
        restoranTable.setGridColor(new java.awt.Color(255, 255, 255));
        restoranTable.setRowHeight(22);
        
        jScrollPane1.setViewportView(restoranTable);
        
        jScrollPane1.setPreferredSize(new Dimension(900,500));
        jScrollPane1.setLocation(50, 330);
        
        this.add(jScrollPane1);
    }
    
}
