/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewold;

import com.bekasidev.app.model.Restoran;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.RestoranService;
import com.bekasidev.app.view.tablecomponent.ButtonColumn;
import com.bekasidev.app.view.tablecomponent.ColumnGroup;
import com.bekasidev.app.view.tablecomponent.GroupableTableHeader;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author bagus
 */
public class RestaurantTableComponent extends JPanel {
    private RestoranService service;
    private JPanel subContentMainPanel;
    private MainFrame mainFrame;

    public RestaurantTableComponent() {
    }

    public RestaurantTableComponent(MainFrame mainFrame, JPanel subContentMainPanel) {
        this.mainFrame = mainFrame;
        this.subContentMainPanel = subContentMainPanel;
    }
    
    public void init() {
        service = ServiceFactory.getRestoranService();
        
        JScrollPane jScrollPane1 = new JScrollPane();
        JTable restoranTable = new JTable() {
            @Override
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };
        
        DefaultTableModel dtm = new DefaultTableModel(0,0);
        String header[] = new String[] {"ID_RESTAURANT", "NAMA_RESTAURANT", "",""};
        dtm.setColumnIdentifiers(header);
        restoranTable.setModel(dtm);
        
        List<Restoran> restorans = service.getAllRestoran();
        
        for (Restoran obj : restorans) {
            dtm.addRow(new Object[] {obj.getIdRestoran(), obj.getNamaRestoran(), "delete","pilih"});
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
        Action showContentPanel = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                System.out.println(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 1).toString());
                
                SubContentMain subContentMain = (SubContentMain)subContentMainPanel;
                JScrollPane scroll = (JScrollPane)subContentMain.getComponent(0);
                scroll.setVisible(true);
                
                scroll.getViewport().remove(0);
                ContentPanel contentPanel = new ContentPanel(mainFrame);
                contentPanel.setNamaRestoran(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 1).toString());
                contentPanel.setIdRestoran(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0).toString());
                contentPanel.initPanel();
                contentPanel.setPreferredSize(new Dimension(contentPanel.getWidth(), 500));
                
                scroll.setViewportView(contentPanel);
                
                subContentMain.invalidate();
                subContentMain.revalidate();
            }
            
        };

        ButtonColumn buttonColumn = new ButtonColumn(restoranTable, delete, 2);
        ButtonColumn buttonColumn2 = new ButtonColumn(restoranTable, showContentPanel, 3);

        restoranTable.setGridColor(new java.awt.Color(255, 255, 255));
        restoranTable.setRowHeight(22);
        
        TableColumnModel cm = restoranTable.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("Restoran Entity");
        g_name.add(cm.getColumn(0));
        g_name.add(cm.getColumn(1));
        
        GroupableTableHeader headerTable = (GroupableTableHeader)restoranTable.getTableHeader();
        headerTable.addColumnGroup(g_name);
        
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
