/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.view;

import com.bekasidev.app.view.util.ComponentCollector;
import com.bekasidev.app.view.util.ComponentCollectorProvider;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author bagus
 */
public class MenuBarPanel extends JPanel {
    private MainFrame mainFrame;

    public MenuBarPanel() {
        super();
    }

    public MenuBarPanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }
    
    public void init() {
                
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        
        JMenuBar menuBar;
        JMenu menuFile, menuHelp, submenu;
        
        JMenuItem menuItem;
        
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);

        //Build the first menu.
        menuFile = new JMenu("File");
        menuFile.getAccessibleContext().setAccessibleDescription("Open File menu");
        menuBar.add(menuFile);
        
        //Build the first menu.
        menuHelp = new JMenu("Help");
        menuHelp.getAccessibleContext().setAccessibleDescription("Open Help menu");
        menuBar.add(menuHelp);
        
        //menuFile item
        menuItem = new JMenuItem("Atur Restoran",
                         KeyEvent.VK_T);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Atur Restoran");
        menuItem.setBackground(Color.WHITE);
        menuItem.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                restaurantItemClicked(evt);
            }
        });
        menuFile.add(menuItem);
        
        menuItem = new JMenuItem("Atur Hotel",
                         KeyEvent.VK_T);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Atur Hotel");
        menuItem.setBackground(Color.WHITE);
        menuItem.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hotelItemClicked(evt);
            }
        });
        menuFile.add(menuItem);
        
        menuItem = new JMenuItem("Atur Parkiran",
                         KeyEvent.VK_T);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "close application");
        menuItem.setBackground(Color.WHITE);
        menuItem.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                parkiranItemClicked(evt);
            }
        });
        menuFile.add(menuItem);
        
        
        Image imgExit = null;
        try {
            imgExit
                = ImageIO.read(getClass().getClassLoader().getResource("images/icons8-delete-48.png"));
        } catch (IOException ex) {
            Logger.getLogger(LandingPagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        menuItem = new JMenuItem("Exit",
                         new ImageIcon(getScaledImage(imgExit, 20, 20)));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "close application");
        menuItem.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitItemClicked(evt);
            }
        });
        menuFile.add(menuItem);
        
        //menuHelp About
        menuItem = new JMenuItem("About",
                         KeyEvent.VK_T);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "See about us");
        menuItem.setBackground(Color.WHITE);
        menuItem.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                aboutItemClicked(evt);
            }
        });
        menuHelp.add(menuItem);
        
        this.add(menuBar, BorderLayout.CENTER);
    }
    
    private void restaurantItemClicked(MouseEvent evt) {
        System.out.println("restoran clicked");
        
        SubContentMain contentMain = new SubContentMain(mainFrame);
        contentMain.initSubContentMain();
        
        mainFrame.getContentPane().remove(1);
        mainFrame.getContentPane().add(contentMain, BorderLayout.CENTER);
        
        mainFrame.invalidate();
        mainFrame.validate();
    }
    
    private void hotelItemClicked(MouseEvent evt) {
        System.out.println("hotel clicked");
        
//        RestaurantInputFrame restaurantInputFrame = new RestaurantInputFrame();
//        restaurantInputFrame.init();
//        restaurantInputFrame.setVisible(true);
    }
    
    private void parkiranItemClicked(MouseEvent evt) {
        System.out.println("parkiran clicked");
        
        TestPanel testPanel = new TestPanel(mainFrame);
        testPanel.init();
        
        mainFrame.getContentPane().remove(1);
        mainFrame.getContentPane().add(testPanel, BorderLayout.CENTER);
        
        mainFrame.invalidate();
        mainFrame.validate();
    }
    
    private void exitItemClicked(MouseEvent evt) {
        System.out.println("exit clicked");
//        mainFrame.dispose();
        JFrame mainF = (JFrame)ComponentCollectorProvider
                                .getComponentMapper()
                                .get("main_frame")
                                .getComponent();
        mainF.dispose();
    }
    
    private void aboutItemClicked(MouseEvent evt) {
        System.out.println("about clicked");
    }
    
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
