package com.aerolinea.documenttype.infrastructure.in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.aerolinea.documenttype.domain.service.DocumenttypeService;

public class DocumenttypeDeleteUi {
    private final DocumenttypeService documenttypeService;

    public DocumenttypeDeleteUi(DocumenttypeService documenttypeService) {
        this.documenttypeService = documenttypeService;
    }

    public void showDocumenttypeDeleteUi() {
        JFrame frame = new JFrame("Eliminar Tipo de Documento");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        frame.add(panel);

        JLabel idLabel = new JLabel("ID del Tipo de Documento:");
        JTextField idField = new JTextField();

        JButton deleteButton = new JButton("Eliminar");
        JButton backButton = new JButton("Regresar");

        // Añadir componentes al panel
        panel.add(idLabel);
        panel.add(idField);
        panel.add(deleteButton);
        panel.add(backButton);

        // Acción del botón eliminar
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                if (documenttypeService.findDocumenttypeById(id) == null) {
                    JOptionPane.showMessageDialog(frame, "El ID no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                documenttypeService.deleteDocumenttype(id);
                JOptionPane.showMessageDialog(frame, "Tipo de Documento eliminado exitosamente");
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
