package com.aerolinea.customer.infrastructure.in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.aerolinea.customer.domain.entity.Customer;
import com.aerolinea.customer.domain.service.CustomerService;

public class CustomerDetailsUi {
    private final CustomerService customerService;

    public CustomerDetailsUi(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void showCustomerDetailsUi() {
        JFrame frame = new JFrame("Detalles del Cliente");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        frame.add(panel);

        JLabel idLabel = new JLabel("ID del Cliente:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField();
        nameField.setEditable(false);

        JLabel ageLabel = new JLabel("Edad:");
        JTextField ageField = new JTextField();
        ageField.setEditable(false);

        JLabel documentTypeLabel = new JLabel("Tipo de Documento:");
        JTextField documentTypeField = new JTextField();
        documentTypeField.setEditable(false);

        JLabel documentNumberLabel = new JLabel("Número de Documento:");
        JTextField documentNumberField = new JTextField();
        documentNumberField.setEditable(false);

        JButton searchButton = new JButton("Buscar");
        JButton backButton = new JButton("Regresar");

        // Añadir componentes al panel
        panel.add(idLabel);
        panel.add(idField);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(searchButton);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(documentTypeLabel);
        panel.add(documentTypeField);
        panel.add(documentNumberLabel);
        panel.add(documentNumberField);
        panel.add(backButton);

        // Acción del botón buscar
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                Customer customer = customerService.findCustomerById(id);
                if (customer == null) {
                    JOptionPane.showMessageDialog(frame, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                nameField.setText(customer.getName());
                ageField.setText(String.valueOf(customer.getAge()));
                documentTypeField.setText(String.valueOf(customer.getIdDocType())); // Podrías necesitar mapear el ID al nombre del tipo de documento
                documentNumberField.setText(customer.getDocumentNumber());
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
