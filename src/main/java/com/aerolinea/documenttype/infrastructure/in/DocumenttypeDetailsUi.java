package com.aerolinea.documenttype.infrastructure.in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.aerolinea.documenttype.domain.entity.Documenttype;
import com.aerolinea.documenttype.domain.service.DocumenttypeService;

public class DocumenttypeDetailsUi {
    private final DocumenttypeService documenttypeService;

    public DocumenttypeDetailsUi(DocumenttypeService documenttypeService) {
        this.documenttypeService = documenttypeService;
    }

    public void showDocumenttypeDetailsUi() {
        JFrame frame = new JFrame("Detalles del Tipo de Documento");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        frame.add(panel);

        JLabel idLabel = new JLabel("ID del Tipo de Documento:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Nombre del Tipo de Documento:");
        JTextField nameField = new JTextField();
        nameField.setEditable(false);

        JButton searchButton = new JButton("Buscar");
        JButton backButton = new JButton("Regresar");

        // Añadir componentes al panel
        panel.add(idLabel);
        panel.add(idField);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(searchButton);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(backButton);

        // Acción del botón buscar
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                Documenttype documenttype = documenttypeService.findDocumenttypeById(id);
                if (documenttype == null) {
                    JOptionPane.showMessageDialog(frame, "Tipo de Documento no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                nameField.setText(documenttype.getName());
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
