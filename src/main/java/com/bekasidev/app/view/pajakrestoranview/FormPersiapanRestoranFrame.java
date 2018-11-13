/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view.pajakrestoranview;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author USER
 */
public class FormPersiapanRestoranFrame extends JFrame {

    public FormPersiapanRestoranFrame() {
        init();
    }
    
    public void init() {
        this.setSize(new Dimension(500, 570));
        Dimension screenSize = this.getToolkit().getScreenSize();
        int x = (int) screenSize.getWidth() / 2 - this.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - this.getHeight() / 2;
        this.setLocation(x, y);
        
        this.setTitle("Form persiapan pajak restoran");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        addFormpersiapanRestoranPanel();
    }
    
    private void addFormpersiapanRestoranPanel() {
        FormPersiapanRestoranPanel formPersiapanRestoranPanel
                = new FormPersiapanRestoranPanel();
        this.add(formPersiapanRestoranPanel);
    }
}
