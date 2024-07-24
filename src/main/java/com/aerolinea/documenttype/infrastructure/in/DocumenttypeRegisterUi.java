package com.aerolinea.documenttype.infrastructure.in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.aerolinea.documenttype.domain.entity.Documenttype;
import com.aerolinea.documenttype.domain.service.DocumenttypeService;

public class DocumenttypeRegisterUi {
    private final DocumenttypeService documenttypeService;

    public DocumenttypeRegisterUi(DocumenttypeService documenttypeService) {
        this.documenttypeService = documenttypeService;
    }

    public void showDocumenttypeRegisterUi() {
        JFrame frame = new JFrame("Registrar Tipo de Documento");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        frame.add(panel);

        JLabel nameLabel = new JLabel("Nombre del Tipo de Documento:");
        JTextField nameField = new JTextField();

        JButton registerButton = new JButton("Registrar");
        JButton backButton = new JButton("Regresar");

        // Añadir componentes al panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(registerButton);
        panel.add(backButton);

        // Acción del botón registrar
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "El nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Documenttype documenttype = new Documenttype();
                documenttype.setName(name);

                documenttypeService.createDocumenttype(documenttype);

                JOptionPane.showMessageDialog(frame, "Tipo de Documento registrado exitosamente");
                frame.dispose();
            }
        });

        // Acción del botón de regreso
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
