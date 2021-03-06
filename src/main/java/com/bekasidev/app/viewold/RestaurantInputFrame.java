/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewold;

import com.bekasidev.app.model.WajibPajak;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.WajibPajakService;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author bagus
 */
public class RestaurantInputFrame extends JFrame {
    private SideContentPanel rootPanel;
    
    public RestaurantInputFrame() {
        super();
    }
    
    public RestaurantInputFrame(SideContentPanel rootPanel) {
        super();
        this.rootPanel = rootPanel;
    }
    
    public void init() {
        Dimension screenSize = this.getToolkit().getScreenSize();
        
        this.setTitle("Form input restoran");
        this.setSize(
                (int)(screenSize.getWidth()-(screenSize.getWidth()/2)), 
                (int) (screenSize.getHeight()-(screenSize.getWidth()/3)));
        
        RestaurantInputPanel panel = new RestaurantInputPanel(this);
        panel.init();
        
        this.add(panel);
    }
    
    private class RestaurantInputPanel extends JPanel {
        private JTextField tfNamaRestoran;
        private RestaurantInputFrame restaurantInputFrame;
        private WajibPajakService wajibPajakService;

        public RestaurantInputPanel() {
        }
        public RestaurantInputPanel(RestaurantInputFrame restaurantInputFrame) {
            this.restaurantInputFrame = restaurantInputFrame;
        }
        
        public void init() {
            this.setLayout(null);
            
            //===== form add restoran =====//
            JLabel labelNamaAddRestoran = new JLabel("Nama WajibPajak");
            labelNamaAddRestoran.setFont(new Font("Tahoma", 0, 16));
            labelNamaAddRestoran.setForeground(Color.BLACK);
            labelNamaAddRestoran.setSize(150, 20);
            labelNamaAddRestoran.setLocation(50, 160);
            
            this.add(labelNamaAddRestoran);

            //===== TextField =====//
            tfNamaRestoran = new JTextField();
            tfNamaRestoran.setSize(300, 20);
            tfNamaRestoran.setLocation(210, 160);
            
            this.add(tfNamaRestoran);

            JButton bFormRestoran = new JButton("Submit");
            bFormRestoran.setSize(100, 20);
            bFormRestoran.setLocation(520, 160);
            bFormRestoran.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!tfNamaRestoran.getText().equals("")) {
                        WajibPajak wajibPajak = new WajibPajak();
                        wajibPajak.setNamaWajibPajak(tfNamaRestoran.getText());

                        addRestoran(wajibPajak);
                        JOptionPane.showMessageDialog(restaurantInputFrame ,"Berhasil Menambahkan Data WajibPajak");
                    } else {
                        JOptionPane.showMessageDialog(restaurantInputFrame ,"Masukkan Dahulu Data WajibPajak");
                    }
                }
            });
            
            this.add(bFormRestoran);
        
        }
        
        public void addRestoran(WajibPajak wajibPajak){
            wajibPajakService = ServiceFactory.getWajibPajakService();
            
            wajibPajakService.createDataWP(wajibPajak);
            tfNamaRestoran.setText("");
            System.out.println("Berhasil Add WajibPajak");
            
            SideContentPanel rootPanel = this.restaurantInputFrame.rootPanel;
            rootPanel.resetRestaurantTable();
            
        }
    }
    
}
