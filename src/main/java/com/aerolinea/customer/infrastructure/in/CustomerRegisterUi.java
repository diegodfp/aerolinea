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

public class CustomerRegisterUi {
    private final CustomerService customerService;
    private final DocumenttypeService documenttypeService;

    private Map<String, Integer> documentTypeMap;

    public CustomerRegisterUi(CustomerService customerService, DocumenttypeService documenttypeService) {
        this.customerService = customerService;
        this.documenttypeService = documenttypeService;

        this.documentTypeMap = new HashMap<>();
    }

    public void showCustomerRegisterUi() {
        JFrame frame = new JFrame("Registrar Cliente");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        frame.add(panel);

        // Componentes de la interfaz
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField();

        JLabel ageLabel = new JLabel("Edad:");
        JTextField ageField = new JTextField();

        JLabel documentTypeLabel = new JLabel("Tipo de Documento:");
        JComboBox<String> documentTypeComboBox = new JComboBox<>();

        JLabel documentNumberLabel = new JLabel("Número de Documento:");
        JTextField documentNumberField = new JTextField();

        JButton registerButton = new JButton("Registrar");
        JButton backButton = new JButton("Regresar");

        // Cargar datos desde la base de datos
        List<Documenttype> documentTypes = documenttypeService.getAllDocumenttypes();
        for (Documenttype documentType : documentTypes) {
            documentTypeComboBox.addItem(documentType.getName());
            documentTypeMap.put(documentType.getName(), documentType.getId());
        }

        // Añadir componentes al panel
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

        panel.add(registerButton);
        panel.add(backButton);

        // Acción del botón registrar
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validación del ID
                String id = idField.getText();
                if (customerService.isCustomerIdExists(id)) {
                    JOptionPane.showMessageDialog(frame, "El ID ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Obtener y validar los detalles del cliente
                String name = nameField.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "El nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int age;
                try {
                    age = Integer.parseInt(ageField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "La edad debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String documentNumber = documentNumberField.getText();
                if (documentNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "El número de documento es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int documentTypeId = documentTypeMap.get((String) documentTypeComboBox.getSelectedItem());

                Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customer.setAge(age);
                customer.setIdDocType(documentTypeId);
                customer.setDocumentNumber(documentNumber);

                customerService.createCustomer(customer);

                JOptionPane.showMessageDialog(frame, "Cliente registrado exitosamente");
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
