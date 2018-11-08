/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.view.pajakrestoranview.PajakRestoranPanel;
import com.bekasidev.app.view.timviewcomponent.TimContentPanel;
import com.bekasidev.app.view.util.ComponentCollectorProvider;

import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author bagus
 */
public class MenuBarPanel extends JPanel {
    private JPanel filePanel,
                    restoranPanel,
                    hotelpanel,
                    parkiranPanel,
                    masterMenuPanel;
    private JLabel file, restoran, hotel, parkiran, masterMenu;
    private Color defaultColor = Color.decode("#f3f3f3");

    public MenuBarPanel() {
        init();
    }
    
    public void init() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.decode("#144429"));
        MainFrame mainFrame 
                = (MainFrame)ComponentCollectorProvider
                        .getComponentMapper()
                        .get("main_frame")
                        .getComponent();
        this.setPreferredSize(new Dimension(mainFrame.getWidth(), 40));
        
        addFileMenu();
        addRestoranMenu();
        addHotelMenu();
        addParkiranMenu();
        addMasterMenu();
    }
    
    private void addFileMenu() {
        filePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        filePanel.setPreferredSize(new Dimension(130, 40));
        file = new JLabel("Dashboard");
        file.setFont(new Font("Tahoma", 0, 13));
        file.setForeground(Color.decode("#4574c6"));
        
        filePanel.add(file);
        filePanel.setBackground(defaultColor);
        filePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fileMenuPressed(evt);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (filePanel.getBackground().getRGB() != defaultColor.getRGB()) {
                    filePanel.setBackground(Color.decode("#9fd4b8"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (filePanel.getBackground().getRGB() != defaultColor.getRGB()) {
                    filePanel.setBackground(Color.decode("#144429"));
                }
            }
        });
        
        
        this.add(filePanel);
    }
    
    private void addRestoranMenu() {
        restoranPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10)); 
        restoranPanel.setPreferredSize(new Dimension(130, 40));
        restoranPanel.setBackground(this.getBackground());
        restoran = new JLabel("Restoran");
        restoran.setFont(new Font("Tahoma", 0, 13));
        restoran.setForeground(Color.WHITE);
        
        restoranPanel.add(restoran);
        restoranPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                restoranMenuPressed(evt);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (restoranPanel.getBackground().getRGB() != defaultColor.getRGB()) {
                    restoranPanel.setBackground(Color.decode("#9fd4b8"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (restoranPanel.getBackground().getRGB() != defaultColor.getRGB()) {
                    restoranPanel.setBackground(Color.decode("#144429"));
                }
            }
            
        });
        
        this.add(restoranPanel);
    }
    
    private void addHotelMenu() {
        hotelpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10)); 
        hotelpanel.setPreferredSize(new Dimension(130, 40));
        hotelpanel.setBackground(this.getBackground());
        hotel = new JLabel("Hotel");
        hotel.setFont(new Font("Tahoma", 0, 13));
        hotel.setForeground(Color.WHITE);
        
        hotelpanel.add(hotel);
        hotelpanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hotelMenupressed(evt);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (hotelpanel.getBackground().getRGB() != defaultColor.getRGB()) {
                    hotelpanel.setBackground(Color.decode("#9fd4b8"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (hotelpanel.getBackground().getRGB() != defaultColor.getRGB()) {
                    hotelpanel.setBackground(Color.decode("#144429"));
                }
            }
        });
        
        this.add(hotelpanel);
    }
    
    private void addParkiranMenu() {
        parkiranPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10)); 
        parkiranPanel.setPreferredSize(new Dimension(130, 40));
        parkiranPanel.setBackground(this.getBackground());
        parkiran = new JLabel("Parkiran");
        parkiran.setFont(new Font("Tahoma", 0, 13));
        parkiran.setForeground(Color.WHITE);
        
        parkiranPanel.add(parkiran);
        parkiranPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                parkiranMenuPressed(evt);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (parkiranPanel.getBackground().getRGB() != defaultColor.getRGB()) {
                    parkiranPanel.setBackground(Color.decode("#9fd4b8"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (parkiranPanel.getBackground().getRGB() != defaultColor.getRGB()) {
                    parkiranPanel.setBackground(Color.decode("#144429"));
                }
            }
        });
        
        this.add(parkiranPanel);
    }

    private void addMasterMenu() {
        masterMenuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        masterMenuPanel.setPreferredSize(new Dimension(130, 40));
        masterMenuPanel.setBackground(this.getBackground());
        masterMenu = new JLabel("master menu");
        masterMenu.setFont(new Font("Tahoma", 0, 13));
        masterMenu.setForeground(Color.WHITE);

        masterMenuPanel.add(masterMenu);
        masterMenuPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                masterMenuPressed(evt);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (masterMenuPanel.getBackground().getRGB() != defaultColor.getRGB()) {
                    masterMenuPanel.setBackground(Color.decode("#9fd4b8"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (masterMenuPanel.getBackground().getRGB() != defaultColor.getRGB()) {
                    masterMenuPanel.setBackground(Color.decode("#144429"));
                }
            }
        });

        this.add(masterMenuPanel);
    }
    
    private void fileMenuPressed(MouseEvent evt) {
        filePanel.setBackground(defaultColor);
        file.setForeground(Color.decode("#144429"));
        resetColor(new JPanel[]{restoranPanel, hotelpanel, parkiranPanel, masterMenuPanel},
                new JLabel[]{parkiran, hotel, restoran, masterMenu});
        System.out.println("file menu clicked");

        ContentPanel contentPanel
                = (ContentPanel) ComponentCollectorProvider
                .getComponentMapper()
                .get("content_panel").getComponent();
        if (contentPanel.getComponents().length > 0) {
            contentPanel.remove(0);
        }
        contentPanel.invalidate();
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void restoranMenuPressed(MouseEvent evt) {
        System.out.println("restoran menu clicked");
        restoranPanel.setBackground(defaultColor);
        restoran.setForeground(Color.decode("#144429"));
        resetColor(new JPanel[]{filePanel, hotelpanel, parkiranPanel, masterMenuPanel},
                new JLabel[]{file, hotel, parkiran, masterMenu});

        PajakRestoranPanel pajakRestoranPanel
                = (PajakRestoranPanel) ComponentCollectorProvider
                .getComponentMapper()
                .get("pajak_restoran_panel").getComponent();
        ContentPanel contentPanel
                = (ContentPanel) ComponentCollectorProvider
                .getComponentMapper()
                .get("content_panel").getComponent();
        if (contentPanel.getComponents().length > 0) {
            contentPanel.remove(0);
        }
        contentPanel.add(pajakRestoranPanel);
        contentPanel.invalidate();
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void hotelMenupressed(MouseEvent evt) {
        hotelpanel.setBackground(defaultColor);
        hotel.setForeground(Color.decode("#144429"));
        resetColor(new JPanel[]{filePanel, restoranPanel, parkiranPanel, masterMenuPanel},
                new JLabel[]{file, parkiran, restoran, masterMenu});
        System.out.println("hotel menu clicked");
        ContentPanel contentPanel
                = (ContentPanel) ComponentCollectorProvider
                .getComponentMapper()
                .get("content_panel").getComponent();
        if (contentPanel.getComponents().length > 0) {
            contentPanel.remove(0);
        }
        contentPanel.invalidate();
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void parkiranMenuPressed(MouseEvent evt) {
        parkiranPanel.setBackground(defaultColor);
        parkiran.setForeground(Color.decode("#144429") );
        resetColor(new JPanel[]{filePanel, hotelpanel, restoranPanel, masterMenuPanel},
                new JLabel[]{file, hotel, restoran, masterMenu});
        System.out.println("parkiran menu clicked");
        ContentPanel contentPanel
                = (ContentPanel) ComponentCollectorProvider
                .getComponentMapper()
                .get("content_panel").getComponent();
        if (contentPanel.getComponents().length > 0) {
            System.out.println("remove content panel now");
            contentPanel.remove(0);
            System.out.println("size now "+contentPanel.getComponents().length);
        }

        contentPanel.invalidate();
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void masterMenuPressed(MouseEvent evt) {
        masterMenuPanel.setBackground(defaultColor);
        masterMenu.setForeground(Color.decode("#144429") );
        resetColor(new JPanel[]{filePanel, hotelpanel, restoranPanel, parkiranPanel},
                new JLabel[]{file, hotel, restoran, parkiran});
        System.out.println("master menu clicked");

        TimContentPanel timContentPanel
                = (TimContentPanel) ComponentCollectorProvider
                .getComponentMapper()
                .get("tim_content_panel").getComponent();

        ContentPanel contentPanel
                = (ContentPanel) ComponentCollectorProvider
                .getComponentMapper()
                .get("content_panel").getComponent();
        if (contentPanel.getComponents().length > 0) {
            contentPanel.remove(0);
        }
        contentPanel.add(timContentPanel, BorderLayout.LINE_START);
        contentPanel.invalidate();
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void resetColor(JPanel [] pane, JLabel[] lable) {
        for(int i=0;i<pane.length;i++){
           pane[i].setBackground(this.getBackground());
        }
        for (int i=0;i<lable.length;i++) {
            lable[i].setForeground(Color.WHITE);
        }
    }
    
}
