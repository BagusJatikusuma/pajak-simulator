/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.model.Restoran;
import com.bekasidev.app.service.backend.RestoranService;
import com.bekasidev.app.service.backend.impl.RestoranServiceImpl;
import static com.bekasidev.app.view.FrameAttributeConstant.MAIN_FRAME_WIDTH;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

    MainFrame mainFrame;
    
    // Variables declaration
    JTextField tfNamaRestoran;
    JPanel panelHeaderFormIdentitas;
            
    // Service
    RestoranService restoranService = new RestoranServiceImpl();
    
    public ContentPanel() {
        super();
    }
    
    public ContentPanel(MainFrame mainFrame){
        super();
        this.mainFrame = mainFrame;
    } 
    
    public void initPanel(){
        // setting layout content panel
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        
        // restauran
        restauranContent();
                
    }
    
    public void resetComponentSize() {
        panelHeaderFormIdentitas.setSize(mainFrame.getWidth(), 50);
    }
    
    public void restauranContent(){
        //===== header form identitas =====//
        panelHeaderFormIdentitas = new JPanel();
        panelHeaderFormIdentitas.setLayout(null);
        panelHeaderFormIdentitas.setBackground(Color.decode("#4377ca"));
        panelHeaderFormIdentitas.setSize(mainFrame.getWidth(), 50);
        panelHeaderFormIdentitas.setLocation(0, 0);
        panelHeaderFormIdentitas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel labelHeaderFormIdentitas = new JLabel("FORM IDENTITAS");
        labelHeaderFormIdentitas.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelHeaderFormIdentitas.setForeground(Color.WHITE);
        labelHeaderFormIdentitas.setSize(200, 50);
        
        // add to panelHeader
        panelHeaderFormIdentitas.add(labelHeaderFormIdentitas);
        
        // add to panel
        this.add(panelHeaderFormIdentitas);
        //===== header form identitas =====//
        
        
        //===== form restoran =====//
        JLabel labelNamaRestoran = new JLabel("Nama Restoran  ");
        labelNamaRestoran.setFont(new Font("Tahoma", 0, 16));
        labelNamaRestoran.setForeground(Color.BLACK);
        
        //===== combo box =====//        
        List<Restoran> listRestoran = new ArrayList<>();
        listRestoran = restoranService.getAllRestoran();
        Restoran restorans[];
        restorans = new Restoran[listRestoran.size()];
        for (int i = 0; i < listRestoran.size(); i++) {
            restorans[i] = listRestoran.get(i);
        }
        
        JComboBox<Restoran> cbListRestoran = new JComboBox<Restoran>(restorans);
        cbListRestoran.setForeground(Color.BLUE);
        cbListRestoran.setFont(new Font("Tahoma", Font.BOLD, 16));
        cbListRestoran.setMaximumRowCount(5);
        
        cbListRestoran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<Restoran> combo = (JComboBox<Restoran>) event.getSource();
                Restoran selected = (Restoran) combo.getSelectedItem();
                System.out.println("Nama : " + selected.getNamaRestoran() + "\nId : " + selected.getIdRestoran());
            }
        });
       
        //===== combo box =====//
        
        //===== panel identitas restoran =====//
        JPanel panelIdentitasRestoran = new JPanel(new GridBagLayout());
        panelIdentitasRestoran.setBackground(Color.WHITE);
        panelIdentitasRestoran.setSize(mainFrame.getWidth()-50,80);
        panelIdentitasRestoran.setLocation(50, 70);
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.LINE_END;
        panelIdentitasRestoran.add(labelNamaRestoran, constraints);
 
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        panelIdentitasRestoran.add(cbListRestoran, constraints);
        
        // set border for the panel
        panelIdentitasRestoran.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Identitas Restoran"));
         
        // add the panel to this panel
        this.add(panelIdentitasRestoran);
        
        //===== panel identitas restoran =====//
        
        //===== form restoran =====//
        
        
        //===== header input =====//
        JPanel panelHeaderFormRestoran = new JPanel();
        panelHeaderFormRestoran.setLayout(null);
        panelHeaderFormRestoran.setBackground(Color.decode("#4377ca"));
        panelHeaderFormRestoran.setSize(mainFrame.getWidth(), 50);
        panelHeaderFormRestoran.setLocation(0, 100);
        
        JLabel labelHeaderFormRestoran = new JLabel("FORM ADD RESTORAN");
        labelHeaderFormRestoran.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelHeaderFormRestoran.setForeground(Color.WHITE);
        labelHeaderFormRestoran.setSize(250, 50);
        labelHeaderFormRestoran.setLocation(25, 0);
        
        // add to panelHeader
        panelHeaderFormRestoran.add(labelHeaderFormRestoran);
        
        // add to panel
//        this.add(panelHeaderFormRestoran);
        
        //===== header input =====//
        
        //===== form add restoran =====//
        JLabel labelNamaAddRestoran = new JLabel("Nama Restoran");
        labelNamaAddRestoran.setFont(new Font("Tahoma", 0, 16));
        labelNamaAddRestoran.setForeground(Color.BLACK);
        labelNamaAddRestoran.setSize(150, 20);
        labelNamaAddRestoran.setLocation(50, 160);
        
        // add to panel
//        this.add(labelNamaAddRestoran);
        
        //===== TextField =====//
        tfNamaRestoran = new JTextField();
        tfNamaRestoran.setSize(300, 20);
        tfNamaRestoran.setLocation(210, 160);
        
        // add to panel
//        this.add(tfNamaRestoran);
        
        //===== TextField =====//
        
        //===== Button =====//
        JButton bFormRestoran = new JButton("Submit");
        bFormRestoran.setSize(100, 20);
        bFormRestoran.setLocation(520, 160);
        
        // add to panel
//        this.add(bFormRestoran);
        
        //===== Button =====//
        
        //===== Action Button =====//
        bFormRestoran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfNamaRestoran.getText().equals("")) {
                    Restoran restoran = new Restoran();
                    restoran.setNamaRestoran(tfNamaRestoran.getText());
                    
                    addRestoran(restoran);
                    JOptionPane.showMessageDialog(mainFrame ,"Berhasil Menambahkan Data Restoran");
                } else {
                    JOptionPane.showMessageDialog(mainFrame ,"Masukkan Dahulu Data Restoran");
                }
            }
        });
        //===== Action Button =====//        
        //===== form add restoran =====//
    }
   
    public void addRestoran(Restoran restoran){
        restoranService.createDataRestoran(restoran);
        tfNamaRestoran.setText("");
        System.out.println("Berhasil Add Restoran");
    }
           
}
