/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    }
}
