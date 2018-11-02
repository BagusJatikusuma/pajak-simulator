/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author bagus
 */
public class TestPanel extends JPanel {
    private MainFrame mainFrame;
    
    private JPanel mainContent,
                    secondContent;
    
    private List<String> menus = new ArrayList<>();

    public TestPanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }

    public TestPanel() {
        super();
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        
        mainContent = new JPanel(new GridLayout(0, 2));
        secondContent = new JPanel(new BorderLayout());
        
        JButton btnTambahField = new JButton("Tambah Menu");
        btnTambahField.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addMenuField(evt);
            }
        });
        
        JLabel label_space = new JLabel(" ");
        JLabel label_menu = new JLabel("menu");
        JTextField fieldMenu = new JTextField();
        
        mainContent.add(label_space);
        mainContent.add(btnTambahField);
        mainContent.add(label_menu);
        mainContent.add(fieldMenu);

        JButton appendBtn = new JButton("Append");
        appendBtn.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                appendMenuList(evt);
            }
        });
        secondContent.add(appendBtn, BorderLayout.CENTER);
        JButton showValuesBtn = new JButton("Show values btn");
        showValuesBtn.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                for (String obj : menus)
                    System.out.println(obj);
            }
        });
        secondContent.add(showValuesBtn, BorderLayout.PAGE_END);
        
        this.add(mainContent, BorderLayout.PAGE_START);
        this.add(secondContent, BorderLayout.PAGE_END);
    } 
    
    private void addMenuField(MouseEvent evt) {
        JLabel label_menu = new JLabel("menu");
        JTextField fieldMenu = new JTextField();

        mainContent.add(label_menu);
        mainContent.add(fieldMenu);
        
        this.invalidate();
        this.revalidate();
    }
    
    private void appendMenuList(MouseEvent evt) {
        menus.removeAll(menus);
        for (int i=0; i < mainContent.getComponents().length; i++) {
            if (mainContent.getComponent(i) instanceof JTextField) {
                JTextField obj = (JTextField)mainContent.getComponent(i);
                menus.add(obj.getText());
            }
        }
        
    }
    
}
