/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakhiburanview;

import com.bekasidev.app.model.Hotel;
import com.bekasidev.app.view.tablecomponent.ButtonColumn;
import com.bekasidev.app.view.tablecomponent.GroupableTableHeader;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
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
public class PajakHiburanTableComponent extends JPanel {

    public PajakHiburanTableComponent() {
        ComponentCollectorProvider.addComponent("pajak_hiburan_table_component", this, null, null);
        init();
    }
    
    public void init() {
        JScrollPane jScrollPane1 = new JScrollPane();
        JTable hotelTable = new JTable() {
            @Override
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };
        
        DefaultTableModel dtm = new DefaultTableModel(0,0);
        String header[] = new String[] {"ID_HOTEL", "NAMA_HOTEL", ""};
        dtm.setColumnIdentifiers(header);
        hotelTable.setModel(dtm);
        
        List<Hotel> hotels = new ArrayList<>();
        
        for (Hotel obj : hotels) {
            dtm.addRow(new Object[] {obj.getIdHotel(), obj.getNamaHotel(),"pilih"});
        }
        
        Action selectedRow = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                System.out.println(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 1).toString());
            }
            
        };
        
        ButtonColumn buttonColumn = new ButtonColumn(hotelTable, selectedRow, 2);
        hotelTable.setGridColor(new java.awt.Color(255, 255, 255));
        hotelTable.setRowHeight(22);
        
        jScrollPane1.setViewportView(hotelTable);
        
        jScrollPane1.setPreferredSize(new Dimension(500,500));
        jScrollPane1.setLocation(50, 330);
        
        this.add(jScrollPane1);
    }
    
}
