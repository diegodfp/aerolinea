package com.aerolinea.documenttype.infrastructure.in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.aerolinea.documenttype.domain.entity.Documenttype;
import com.aerolinea.documenttype.domain.service.DocumenttypeService;

public class DocumenttypeUpdateUi {
    private final DocumenttypeService documenttypeService;

    public DocumenttypeUpdateUi(DocumenttypeService documenttypeService) {
        this.documenttypeService = documenttypeService;
    }

    public void showDocumenttypeUpdateUi() {
        JFrame frame = new JFrame("Actualizar Tipo de Documento");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        frame.add(panel);

        JLabel searchIdLabel = new JLabel("ID del Tipo de Documento:");
        JTextField searchIdField = new JTextField();

        JButton searchButton = new JButton("Buscar");

        JLabel nameLabel = new JLabel("Nombre del Tipo de Documento:");
        JTextField nameField = new JTextField();

        JButton updateButton = new JButton("Actualizar");
        JButton backButton = new JButton("Regresar");

        // Añadir componentes al panel
        panel.add(searchIdLabel);
        panel.add(searchIdField);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(searchButton);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(updateButton);
        panel.add(backButton);

        // Acción del botón buscar
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(searchIdField.getText());
                Documenttype documenttype = documenttypeService.findDocumenttypeById(id);
                if (documenttype == null) {
                    JOptionPane.showMessageDialog(frame, "Tipo de Documento no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                nameField.setText(documenttype.getName());
            }
        });

        // Acción del botón actualizar
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(searchIdField.getText());
                String name = nameField.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "El nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Documenttype documenttype = new Documenttype();
                documenttype.setId(id);
                documenttype.setName(name);

                documenttypeService.updateDocumenttype(documenttype);

                JOptionPane.showMessageDialog(frame, "Tipo de Documento actualizado exitosamente");
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
