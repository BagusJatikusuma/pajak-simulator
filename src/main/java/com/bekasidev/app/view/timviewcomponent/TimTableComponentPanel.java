package com.bekasidev.app.view.timviewcomponent;

import com.bekasidev.app.model.Tim;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.view.tablecomponent.ButtonColumn;
import com.bekasidev.app.view.tablecomponent.GroupableTableHeader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TimTableComponentPanel extends JPanel {
    private PegawaiService pegawaiService;

    private JButton tambahTimBtn;

    public TimTableComponentPanel() {
        init();
    }

    public void init() {
        //for now use no layout manager
//        this.setLayout(new GridLayout(0, 1));
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(500,500));
        addButtonTimTableComponent();


//        pegawaiService = ServiceFactory.getPegawaiService();
//
//        JScrollPane jScrollPane1 = new JScrollPane();
//        JTable timTable = new JTable() {
//            @Override
//            protected JTableHeader createDefaultTableHeader() {
//                return new GroupableTableHeader(columnModel);
//            }
//        };
//
//        DefaultTableModel dtm = new DefaultTableModel(0,0);
//        String header[] = new String[] {"ID", "NAMA","",""};
//        dtm.setColumnIdentifiers(header);
//        timTable.setModel(dtm);
//
//        List<Tim> timList = pegawaiService.getAllTim();
//
//        for (Tim obj : timList) {
//            dtm.addRow(new Object[] {obj.getIdTim(), obj.getNamaTim(), "delete","lihat anggota"});
//        }
//
//        Action delete = new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JTable table = (JTable)e.getSource();
//                int modelRow = Integer.valueOf( e.getActionCommand() );
//                System.out.println(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0).toString());
//                //should delete the item in database
//                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
//            }
//        };
//        Action showContentPanel = new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("show content tim pressed");
//            }
//
//        };
//
//        ButtonColumn buttonColumn = new ButtonColumn(timTable, delete, 2);
//        ButtonColumn buttonColumn2 = new ButtonColumn(timTable, showContentPanel, 3);
//
//        timTable.setGridColor(new java.awt.Color(255, 255, 255));
//        timTable.setRowHeight(22);
//
//        jScrollPane1.setViewportView(timTable);
//
//        jScrollPane1.setSize(400, 500);
//        jScrollPane1.setLocation(100, 370);
//
//        this.add(jScrollPane1);
    }

    private void addButtonTimTableComponent() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setSize(400, 50);
        buttonPanel.setLocation(0,0);

        tambahTimBtn = new JButton("tambah tim");
        tambahTimBtn.setFont(new Font("Tahoma", 0, 16));

        buttonPanel.add(tambahTimBtn);

        this.add(buttonPanel);
    }
}
