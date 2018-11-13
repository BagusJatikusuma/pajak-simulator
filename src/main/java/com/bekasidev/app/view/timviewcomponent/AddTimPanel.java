package com.bekasidev.app.view.timviewcomponent;

import com.bekasidev.app.model.Tim;
import com.bekasidev.app.service.ServiceFactory;
import com.bekasidev.app.service.backend.PegawaiService;
import com.bekasidev.app.view.MainFrame;
import com.bekasidev.app.view.util.ComponentCollectorProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTimPanel extends JPanel {
    private PegawaiService pegawaiService;

    private JLabel idTim, namaTim;
    private JTextField idTimField, namaTimField;

    public AddTimPanel() {
        ComponentCollectorProvider.addComponent("add_tim_panel", this, null, null);
        init();
    }

    public void init() {
        pegawaiService = ServiceFactory.getPegawaiService();

        this.setLayout(new GridLayout(0,1));
        addInputContainer();
        addSaveButton();
    }

    private void addInputContainer() {
        JPanel container = new JPanel(new GridLayout(0,2));
        container.setPreferredSize(new Dimension(300, 100));

        idTim = new JLabel("Tim Id");
        namaTim = new JLabel("Nama Tim");
        idTimField = new JTextField();
        namaTimField = new JTextField();

        container.add(idTim);
        container.add(idTimField);
        container.add(namaTim);
        container.add(namaTimField);

        this.add(container);
    }

    private void addSaveButton() {

        JPanel container = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton saveBtn = new JButton("save");
        saveBtn.setFont(new Font("Tahoma", 0, 16));
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame
                        = (MainFrame) ComponentCollectorProvider
                        .getComponentMapper()
                        .get("main_frame").getComponent();
                if (!(  idTimField.getText().equals("") ||
                        namaTimField.getText().equals(""))) {
                    Tim tim = new Tim(idTimField.getText(),namaTimField.getText());
                    pegawaiService.createTim(tim);
                    JOptionPane.showMessageDialog(mainFrame ,"Berhasil simpan "+idTimField.getText()+"; "+namaTimField.getText());
                } else {
                    JOptionPane.showMessageDialog(mainFrame ,"data belum komplit");
                }
            }
        });

        container.add(saveBtn);
        this.add(container);
    }

}
