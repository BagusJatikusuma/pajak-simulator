/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakrestoranview;

import com.bekasidev.app.view.MainFrame;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Bayu Arafli
 */
public class PajakRestoranFormPanel extends JPanel{

    public PajakRestoranFormPanel() {
        ComponentCollectorProvider.addComponent("pajak_restoran_form_panel", this, null, null);
        init();
    }
    
    public void init(){
//        MainFrame mainFrame
//                = (MainFrame) ComponentCollectorProvider
//                .getComponentMapper()
//                .get("main_frame")
//                .getComponent();
//        
//        int widthPajakResoranFormPanel = mainFrame.getWidth()/2;
//        int heigthPajakResoranFormPanel = mainFrame.getHeight()/2;
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
        this.setBackground(Color.decode("#e6e6e6"));
        
        FormPersiapanRestoranPanel formPersiapanPanel = new FormPersiapanRestoranPanel();
        formPersiapanPanel.setLocation(this.getWidth()/2, this.getHeight()/2);
        formPersiapanPanel.setSize(500, 500);
        this.add(formPersiapanPanel);
    }
    
    
    
    
}
