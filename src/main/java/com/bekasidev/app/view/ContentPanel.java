/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.model.Restoran;
import com.bekasidev.app.service.backend.RestoranService;
import com.bekasidev.app.service.backend.impl.RestoranServiceImpl;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bayu Arafli
 */
public class ContentPanel extends JPanel{

    // Variables declaration
    private JTextField tfNamaRestoran, tfPemilik;
    
    public ContentPanel() {
        super();
    }
    
    public void initPanel(){
        //setting layout content panel
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        
        //form
        JLabel labelNamaRestoran = new JLabel("Nama Restoran");
        labelNamaRestoran.setOpaque(true);
        labelNamaRestoran.setBackground(Color.red);
        labelNamaRestoran.setSize(150, 20);
        labelNamaRestoran.setLocation(50, 50);
        
        tfNamaRestoran = new JTextField();
        tfNamaRestoran.setSize(250, 20);
        tfNamaRestoran.setLocation(210, 50);
        
        JLabel labelPemilik = new JLabel("Pemilik");
        labelPemilik.setOpaque(true);
        labelPemilik.setBackground(Color.red);
        labelPemilik.setSize(150, 20);
        labelPemilik.setLocation(50, 80);
        
        tfPemilik = new JTextField();
        tfPemilik.setSize(250, 20);
        tfPemilik.setLocation(210, 80);

        JButton submit = new JButton("Submit");
        submit.setSize(100, 20);
        submit.setLocation(210, 110);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {             
                System.out.println("Nama Restoran : " + tfNamaRestoran.getText() + "\nPemilik : " + tfPemilik.getText());
            }
        });
        
        // Add buttons to the frame (and spaces between  buttons)
        this.add(labelNamaRestoran);
        this.add(tfNamaRestoran);
        this.add(labelPemilik);
        this.add(tfPemilik);
        this.add(submit);
        
        showRestaurantTable();
    }
    
    public void addTable() {
        JScrollPane jScrollPane1 = new JScrollPane();
        JTable jTable1 = new JTable();
        
//        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "ROM Gen", "US", null},
                {"12/1/2018", "Text Ed", "UK", null},
                {"12/1/2018", "Mola Con", "China", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null},
                {"12/1/2018", "Expresso POS", "Kenya", null}
            },
            new String [] {
                "Date", "Item", "Location", "Completed"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setRowHeight(22);
        jScrollPane1.setViewportView(jTable1);
        
        jScrollPane1.setSize(400, 300);
        jScrollPane1.setLocation(200, 200);
        
        this.add(jScrollPane1);
    }
    
    public void showRestaurantTable() {
        JScrollPane jScrollPane1 = new JScrollPane();
        JTable jTable1 = new JTable();
        
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String header[] = new String[] {"ID_RESTAURANT", "NAMA_RESTAURANT"};
        dtm.setColumnIdentifiers(header);
        jTable1.setModel(dtm);
        
        RestoranService service = new RestoranServiceImpl();
        
        List<Restoran> restorans = service.getAllRestoran();
//        for (int i=0; i<20; i++) {
//            Restoran obj = new Restoran();
//            obj.setIdRestoran("id "+1);
//            obj.setNamaRestoran("Restoran "+i);
//            
//            restorans.add(obj);
//        }

        for (Restoran obj : restorans) {
            dtm.addRow(new Object[] {obj.getIdRestoran(), obj.getNamaRestoran()});
        }
        
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setRowHeight(22);
        jScrollPane1.setViewportView(jTable1);
        
        jScrollPane1.setSize(400, 300);
        jScrollPane1.setLocation(200, 200);
        
        this.add(jScrollPane1);
    }
    
}
