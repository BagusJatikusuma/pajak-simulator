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
import java.awt.Dimension;
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
    
    public ContentPanel() {
        super();
    }
    
    public void initPanel(){
        // setting layout content panel
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        
        // restauran
        restauranContent();
                
    }
    
    public void restauranContent(){
        //===== header =====//
        Dimension screenSize = this.getToolkit().getScreenSize();
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(null);
        panelHeader.setBackground(Color.decode("#4377ca"));
        panelHeader.setSize((int) (screenSize.getWidth()-250), 50);
        panelHeader.setLocation(0, 0);
        
        JLabel labelHeader = new JLabel("FORM IDENTITAS");
        labelHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelHeader.setForeground(Color.WHITE);
        labelHeader.setSize(200, 50);
        labelHeader.setLocation(25, 0);
        
        RestoranService restoranService = new RestoranServiceImpl();
        
        // add to panelHeader
        panelHeader.add(labelHeader);
        //===== header =====//
        
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
        cbListRestoran.setSize(200,20);
        cbListRestoran.setLocation(210,70);
        
        cbListRestoran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<Restoran> combo = (JComboBox<Restoran>) event.getSource();
                Restoran selected = (Restoran) combo.getSelectedItem();
                System.out.println("Nama : " + selected.getNamaRestoran() + "\nId : " + selected.getIdRestoran());
            }
        });
        //===== combo box =====//
        

        //===== form restoran =====//
        JLabel labelNamaRestoran = new JLabel("Nama Restoran");
        labelNamaRestoran.setFont(new Font("Tahoma", 1, 16));
        labelNamaRestoran.setForeground(Color.BLACK);
        labelNamaRestoran.setSize(150, 50);
        labelNamaRestoran.setLocation(50, 70);
        
        
        // add to panel
        this.add(cbListRestoran);
        this.add(panelHeader);
        this.add(labelNamaRestoran);
    }
           
}
