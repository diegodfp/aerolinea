package com.aerolinea.customer.infrastructure.in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aerolinea.customer.domain.entity.Customer;
import com.aerolinea.customer.domain.service.CustomerService;
import com.aerolinea.documenttype.domain.entity.Documenttype;
import com.aerolinea.documenttype.domain.service.DocumenttypeService;

public class CustomerUpdateUi {
    private final CustomerService customerService;
    private final DocumenttypeService documenttypeService;

    private Map<String, Integer> documentTypeMap;

    public CustomerUpdateUi(CustomerService customerService, DocumenttypeService documenttypeService) {
        this.customerService = customerService;
        this.documenttypeService = documenttypeService;

        this.documentTypeMap = new HashMap<>();
    }

    public void showCustomerUpdateUi() {
        JFrame frame = new JFrame("Actualizar Cliente");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        frame.add(panel);

        JLabel searchIdLabel = new JLabel("ID del Cliente:");
        JTextField searchIdField = new JTextField();

        JButton searchButton = new JButton("Buscar");

        JLabel idLabel = new JLabel("Nuevo ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField();

        JLabel ageLabel = new JLabel("Edad:");
        JTextField ageField = new JTextField();

        JLabel documentTypeLabel = new JLabel("Tipo de Documento:");
        JComboBox<String> documentTypeComboBox = new JComboBox<>();

        JLabel documentNumberLabel = new JLabel("Número de Documento:");
        JTextField documentNumberField = new JTextField();

        JButton updateButton = new JButton("Actualizar");
        JButton backButton = new JButton("Regresar");

        // Cargar datos desde la base de datos
        List<Documenttype> documentTypes = documenttypeService.getAllDocumenttypes();
        for (Documenttype documentType : documentTypes) {
            documentTypeComboBox.addItem(documentType.getName());
            documentTypeMap.put(documentType.getName(), documentType.getId());
        }

        // Añadir componentes al panel
        panel.add(searchIdLabel);
        panel.add(searchIdField);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(searchButton);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(documentTypeLabel);
        panel.add(documentTypeComboBox);
        panel.add(documentNumberLabel);
        panel.add(documentNumberField);
        panel.add(updateButton);
        panel.add(backButton);

        // Acción del botón buscar
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchId = searchIdField.getText();
                Customer customer = customerService.findCustomerById(searchId);
                if (customer == null) {
                    JOptionPane.showMessageDialog(frame, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                idField.setText(customer.getId());
                nameField.setText(customer.getName());
                ageField.setText(String.valueOf(customer.getAge()));
                documentTypeComboBox.setSelectedItem(documentTypeMap.entrySet().stream()
                        .filter(entry -> entry.getValue().equals(customer.getIdDocType()))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(null));
                documentNumberField.setText(customer.getDocumentNumber());
            }
        });

        // Acción del botón actualizar
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String originalId = searchIdField.getText();
                String id = idField.getText();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                int documentTypeId = documentTypeMap.get((String) documentTypeComboBox.getSelectedItem());
                String documentNumber = documentNumberField.getText();

                Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customer.setAge(age);
                customer.setIdDocType(documentTypeId);
                customer.setDocumentNumber(documentNumber);

                customerService.updateCustomer(customer, originalId);

                JOptionPane.showMessageDialog(frame, "Cliente actualizado exitosamente");
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
