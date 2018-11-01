package com.bekasidev.app.view;

import com.bekasidev.app.model.Menu;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.MenuService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInputFrame extends JFrame {
    private ContentPanel rootPanel;
    private String idRestoran;

    public MenuInputFrame() {
        super();
    }

    public MenuInputFrame(ContentPanel rootPanel) {
        super();
        this.rootPanel = rootPanel;
    }

    public void init() {
        this.setResizable(false);
        Dimension screenSize = this.getToolkit().getScreenSize();

        this.setTitle("Form input menu");
        this.setSize(
                (int)(screenSize.getWidth()-(screenSize.getWidth()/2)),
                (int) (screenSize.getHeight()-(screenSize.getWidth()/3)));
        this.setLocation(350, 250);

        MenuInputPanel panel = new MenuInputPanel(this);
        panel.init();

        this.add(panel);
    }

    private class MenuInputPanel extends JPanel {
        private JTextField tfNamaMenu, tfHarga;
        private JComboBox<String> cbJenisMenu;
        private MenuInputFrame menuInputFrame;
        private MenuService menuService;

        private String[] pilihanJenisMenu;
        private short jenis = 0;

        public MenuInputPanel() {
        }
        public MenuInputPanel(MenuInputFrame menuInputFrame) {
            this.menuInputFrame = menuInputFrame;
        }

        public void init() {
            this.setLayout(null);

            pilihanJenisMenu = new String[] {"Makanan", "Minuman"};

            JLabel labelNamaMenu = new JLabel("Nama Menu");
            labelNamaMenu.setFont(new Font("Tahoma", 0, 14));
            labelNamaMenu.setBounds(225, 30, 100, 20);
            this.add(labelNamaMenu);

            tfNamaMenu = new JTextField(20);
            tfNamaMenu.setBounds(225, 55,250, 20);
            this.add(tfNamaMenu);

            JLabel labelJenisMenu = new JLabel("Jenis Menu");
            labelJenisMenu.setFont(new Font("Tahoma", 0, 14));
            labelJenisMenu.setBounds(225, 85, 100, 20);
            this.add(labelJenisMenu);

            cbJenisMenu = new JComboBox<>(pilihanJenisMenu);
            cbJenisMenu.setBounds(225, 110,250, 20);
            this.add(cbJenisMenu);

            cbJenisMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JComboBox<String> combo = (JComboBox<String>) event.getSource();
                    String selectedJenis = (String) combo.getSelectedItem();

                    if (selectedJenis.equals(pilihanJenisMenu[0])) {
                        jenis = 0;
                    } else if (selectedJenis.equals(pilihanJenisMenu[1])) {
                        jenis = 1;
                    }
                }
            });

            JLabel labelHarga = new JLabel("Harga (Rp.)");
            labelHarga.setFont(new Font("Tahoma", 0, 14));
            labelHarga.setBounds(225, 140, 100, 20);
            this.add(labelHarga);

            tfHarga = new JTextField(20);
            tfHarga.setBounds(225, 165,250, 20);
            this.add(tfHarga);


            JButton bFormMenu = new JButton("Submit");
            bFormMenu.setBounds(375, 200,100, 20);
            this.add(bFormMenu);
            // action
            bFormMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!(tfNamaMenu.getText().equals("") || tfHarga.getText().equals(""))) {
                        Menu menuModel = new Menu();

                        menuModel.setIdRestoran(idRestoran);
                        menuModel.setNamaMenu(tfNamaMenu.getText());
                        menuModel.setJenisMenu(jenis);
                        menuModel.setHargaMenu((double) Integer.parseInt(tfHarga.getText()));

                        addMenu(menuModel);
                        JOptionPane.showMessageDialog(menuInputFrame,"Berhasil Menambahkan Data Menu");
                    } else {
                        JOptionPane.showMessageDialog(menuInputFrame,"Masukkan Dahulu Data Menu");
                    }
                }
            });

            this.add(bFormMenu);

        }

        public void addMenu(Menu menu){
            menuService = ServiceFactory.getMenuService();

            menuService.createMenu(menu);
            tfNamaMenu.setText("");
            cbJenisMenu.setSelectedIndex(0);
            tfHarga.setText("");
            System.out.println("Menu Behasil ditambahkan");

//            ContentPanel rootPanel = this.menuInputFrame.rootPanel;
//            rootPanel.resetRestaurantTable();

        }

    }

    public void setIdRestoran(String idRestoran) {
        this.idRestoran = idRestoran;
    }
}
