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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bayu Arafli
 */
public class ContentPanel extends JPanel{

    // Variables declaration
    private JTextField tfNamaRestoran, tfPemilik, tfTotalUang;
    private JLabel labelPilihanLevel, labelPilihanRestoran;
    private JRadioButton level1, level2, level3;
    private JCheckBox goceng, ceban, gocap;
    
    private String[] level, restoran;
    private int[] uang;
    private int total = 0;
    
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
        
        // Add to panel
        this.add(labelNamaRestoran);
        this.add(tfNamaRestoran);
        this.add(labelPemilik);
        this.add(tfPemilik);
        this.add(submit);
        
        showRestaurantTable();
        
        
        // Contoh menggunakan combo box
        JLabel labelListRestoran = new JLabel("Nama Restoran");
        labelListRestoran.setOpaque(true);
        labelListRestoran.setBackground(Color.red);
        labelListRestoran.setSize(150, 20);
        labelListRestoran.setLocation(50, 150);
        
        restoran = new String[] {"Rumah Makan Cap Manjur", "Rumah Makan SK", "Masak Suka Makan Suka", "Warung Makan Minum"};
        JComboBox<String> restoranList = new JComboBox<>(restoran);
        
        // style
//        restoranList.setForeground(Color.BLUE);
//        restoranList.setFont(new Font("Arial", Font.BOLD, 14));
//        restoranList.setMaximumRowCount(5);
        
        // potition and size
        restoranList.setSize(250, 20);
        restoranList.setLocation(210, 150);
        
        labelPilihanRestoran = new JLabel();
        labelPilihanRestoran.setOpaque(true);
        labelPilihanRestoran.setBackground(Color.red);
        labelPilihanRestoran.setSize(150, 20);
        labelPilihanRestoran.setLocation(470, 150);
        
        labelPilihanRestoran.setText(restoran[0]);
        
        // get the selected item:
        restoranList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> combo = (JComboBox<String>) event.getSource();
                String selectedBook = (String) combo.getSelectedItem();

                if (selectedBook.equals(restoran[0])) {
                    labelPilihanRestoran.setText(restoran[0]);
                } else if (selectedBook.equals(restoran[1])) {
                    labelPilihanRestoran.setText(restoran[1]);
                } else if (selectedBook.equals(restoran[2])) {
                    labelPilihanRestoran.setText(restoran[2]);
                }else if (selectedBook.equals(restoran[3])) {
                    labelPilihanRestoran.setText(restoran[3]);
                }
            }
        });
        

        // add to panel
        this.add(labelListRestoran);
        this.add(labelPilihanRestoran);
        this.add(restoranList);
        
        
        //contoh menggunakan radio button
        JLabel labelLevel = new JLabel("Pilih Level");
        labelLevel.setOpaque(true);
        labelLevel.setBackground(Color.red);
        labelLevel.setSize(150, 20);
        labelLevel.setLocation(50, 190);
        
        level = new String[] {"Level 1", "Level 2", "Level 3"};
        level1 = new JRadioButton(level[0]);
        level2 = new JRadioButton(level[1]);
        level3 = new JRadioButton(level[2]);
        
        ButtonGroup group = new ButtonGroup();
        group.add(level1);
        group.add(level2);
        group.add(level3);
        
        // layout
        JPanel panelRadioButton = new JPanel(new GridBagLayout());
        
        // specify constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        panelRadioButton.add(level1, constraints);
        constraints.gridx = 1;
        panelRadioButton.add(level2, constraints);
        constraints.gridx = 2;
        panelRadioButton.add(level3, constraints);
        
        panelRadioButton.setSize(250, 20);
        panelRadioButton.setLocation(210, 190);
        
        labelPilihanLevel = new JLabel();
        labelPilihanLevel.setOpaque(true);
        labelPilihanLevel.setBackground(Color.red);
        labelPilihanLevel.setSize(140, 20);
        labelPilihanLevel.setLocation(320, 220);
        
        //button radio button
        JButton submitRB = new JButton("Cek");
        submitRB.setSize(100, 20);
        submitRB.setLocation(210, 220);
        submitRB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (level1.isSelected()) {
                    labelPilihanLevel.setText(level[0]);
                } else if (level2.isSelected()) {
                    labelPilihanLevel.setText(level[1]);
                } else if (level3.isSelected()) {
                    labelPilihanLevel.setText(level[2]);
                } else {
                    labelPilihanLevel.setText("pilih terlebih dahulu");
                }
            }
        });
        
        // add to panel
        this.add(labelLevel);
        this.add(panelRadioButton);
        this.add(submitRB);
        this.add(labelPilihanLevel);
        
        
        // Contoh menggunakan cek box
        JLabel labelUang = new JLabel("Uang");
        labelUang.setOpaque(true);
        labelUang.setBackground(Color.red);
        labelUang.setSize(150, 20);
        labelUang.setLocation(50, 250);
        
        uang = new int[] {5000, 10000, 50000};
        
        goceng = new JCheckBox(String.valueOf(uang[0]));
        ceban = new JCheckBox(String.valueOf(uang[1]));
        gocap = new JCheckBox(String.valueOf(uang[2]));
        
        // layout
        JPanel panelCheckBox = new JPanel(new GridBagLayout());
        
        // specify constraints
        GridBagConstraints constraintsBox = new GridBagConstraints();
        constraintsBox.gridx = 0;
        panelCheckBox.add(goceng, constraintsBox);
        constraintsBox.gridx = 1;
        panelCheckBox.add(ceban, constraintsBox);
        constraintsBox.gridx = 2;
        panelCheckBox.add(gocap, constraintsBox);
        
        panelCheckBox.setSize(250, 20);
        panelCheckBox.setLocation(210, 250);
        
        JLabel labelTotalUang = new JLabel("Total Rp.");
        labelTotalUang.setOpaque(true);
        labelTotalUang.setBackground(Color.red);
        labelTotalUang.setSize(100, 20);
        labelTotalUang.setLocation(210, 280);
        
        tfTotalUang = new JTextField();
        tfTotalUang.setSize(140, 20);
        tfTotalUang.setLocation(320, 280);
        
        // add to panel
        this.add(labelUang);
        this.add(labelTotalUang);
        this.add(tfTotalUang);
        this.add(panelCheckBox);
        
        // add action listener for the check boxes
        ActionListener actionListener = new ActionHandler(); 
        goceng.addActionListener(actionListener);
        ceban.addActionListener(actionListener);
        gocap.addActionListener(actionListener);
        
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
    
    
    class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JCheckBox checkbox = (JCheckBox) event.getSource();
            if (checkbox.isSelected()) {
                if (checkbox == goceng) {
                        total += uang[0];
                } else if (checkbox == ceban) {
                        total += uang[1];
                } else if (checkbox == gocap) {
                        total += uang[2];
                }
            } else {
                if (checkbox == goceng) {
                        total -= uang[0];
                } else if (checkbox == ceban) {
                        total -= uang[1];
                } else if (checkbox == gocap) {
                        total -= uang[2];
                }
            }
                tfTotalUang.setText(String.valueOf(total));
        }
    }
    
}
