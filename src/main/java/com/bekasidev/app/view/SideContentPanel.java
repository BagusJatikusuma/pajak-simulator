/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Bayu Arafli
 */
public class SideContentPanel extends JPanel{

    public SideContentPanel() {
        super();
    }
    
    public void initSideContentPanel(){
        this.setPreferredSize(new Dimension(250, 250));
        this.setBackground(Color.decode("#4377ca"));
        
        //panel logo dan title
        JPanel logoPanel = new JPanel();
        logoPanel.setPreferredSize(new Dimension(250, 150));
        logoPanel.setBackground(Color.decode("#517ed3"));
        
        JLabel labelLogo = new JLabel("Logo dan Judul Pajak");
        labelLogo.setForeground(Color.WHITE);
        labelLogo.setFont(new Font("Tahoma", 1, 20));
        
        logoPanel.add(labelLogo);
        
        //panel menu
        // menu Identas
        JPanel menuIdentitas = new JPanel(new BorderLayout());
        menuIdentitas.setPreferredSize(new Dimension(250, 40));
        menuIdentitas.setBackground(Color.decode("#517ed3"));
        
        JButton buttonMenuIdentitas = new JButton("Form Identitas", new ImageIcon("star.git"));
        buttonMenuIdentitas.setPreferredSize(new Dimension(250, 40));
        buttonMenuIdentitas.setBackground(Color.decode("#517ed3"));
        buttonMenuIdentitas.setForeground(Color.WHITE);
     
        menuIdentitas.add(buttonMenuIdentitas, BorderLayout.CENTER);
        
        //add menu
        this.add(logoPanel);
        this.add(menuIdentitas);
    }
    
    
}
