package com.aerolinea.customer.infrastructure.in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.aerolinea.customer.domain.service.CustomerService;

public class CustomerDeleteUi {
    private final CustomerService customerService;

    public CustomerDeleteUi(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void showCustomerDeleteUi() {
        JFrame frame = new JFrame("Eliminar Cliente");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        frame.add(panel);

        JLabel idLabel = new JLabel("ID del Cliente:");
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
                String id = idField.getText();
                if (!customerService.isCustomerIdExists(id)) {
                    JOptionPane.showMessageDialog(frame, "El ID no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                customerService.deleteCustomer(id);
                JOptionPane.showMessageDialog(frame, "Cliente eliminado exitosamente");
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
