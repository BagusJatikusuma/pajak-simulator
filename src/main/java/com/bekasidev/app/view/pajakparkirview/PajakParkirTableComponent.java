/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakparkirview;

import com.bekasidev.app.model.WajibPajak;
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
public class PajakParkirTableComponent extends JPanel {
   

    public PajakParkirTableComponent() {
        ComponentCollectorProvider.addComponent("pajak_parkir_table_commponent", this, null, null);
        init();
    }
    
    public void init() {
        JScrollPane jScrollPane1 = new JScrollPane();
        JTable restoranTable = new JTable() {
            @Override
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };
        
        DefaultTableModel dtm = new DefaultTableModel(0,0);
        String header[] = new String[] {"ID_PARKIRAN", "NAMA_PARKIRAN", ""};
        dtm.setColumnIdentifiers(header);
        restoranTable.setModel(dtm);
        
        List<WajibPajak> parkirans = new ArrayList<>();
        
        for (WajibPajak obj : parkirans) {
            dtm.addRow(new Object[] {obj.getNpwpd(), obj.getNamaWajibPajak(),"pilih"});
        }
        
        Action selectedRow = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                System.out.println(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 1).toString());
            }
            
        };
        
        ButtonColumn buttonColumn = new ButtonColumn(restoranTable, selectedRow, 2);
        restoranTable.setGridColor(new java.awt.Color(255, 255, 255));
        restoranTable.setRowHeight(22);
        
        jScrollPane1.setViewportView(restoranTable);
        
        jScrollPane1.setPreferredSize(new Dimension(500,500));
        jScrollPane1.setLocation(50, 330);
        
        this.add(jScrollPane1);
    }
    
}
