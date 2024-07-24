package com.aerolinea.flightfares.infrastructure.in;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.aerolinea.flightfares.domain.entity.FlightFares;
import com.aerolinea.flightfares.domain.service.FlightFaresService;

public class FlightFareRegisterUi {
    private final FlightFaresService flightFaresService;

    public FlightFareRegisterUi(FlightFaresService flightFaresService) {
        this.flightFaresService = flightFaresService;
    }

    public void showFlightFareRegisterUi() {
        JFrame frame = new JFrame("Registrar Tarifa de Vuelo");
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        frame.add(mainPanel);

        // Panel central
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel descriptionLabel = new JLabel("Descripción:");
        JTextField descriptionField = new JTextField(20);

        JLabel detailsLabel = new JLabel("Detalles:");
        JTextField detailsField = new JTextField(20);

        JLabel valueLabel = new JLabel("Valor:");
        JTextField valueField = new JTextField(20);

        // Añadir componentes al panel central
        addComponent(centerPanel, descriptionLabel, gbc, 0, 0);
        addComponent(centerPanel, descriptionField, gbc, 1, 0);
        addComponent(centerPanel, detailsLabel, gbc, 0, 1);
        addComponent(centerPanel, detailsField, gbc, 1, 1);
        addComponent(centerPanel, valueLabel, gbc, 0, 2);
        addComponent(centerPanel, valueField, gbc, 1, 2);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Panel inferior
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton registerButton = new JButton("Registrar");
        JButton backButton = new JButton("Regresar");
        bottomPanel.add(registerButton);
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Estilizar componentes
        styleComponents(frame, new JTextField[]{descriptionField, detailsField, valueField}, registerButton, backButton);

        // Configurar listeners
        setupListeners(frame, descriptionField, detailsField, valueField, registerButton, backButton);

        frame.setVisible(true);
    }

    private void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(component, gbc);
    }

    private void styleComponents(JFrame frame, JTextField[] fields, JButton... buttons) {
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        for (JTextField field : fields) {
            field.setPreferredSize(new Dimension(200, 25));
        }

        for (JButton button : buttons) {
            button.setBackground(new Color(70, 130, 180));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(100, 30));
        }
    }

    private void setupListeners(JFrame frame, JTextField descriptionField, JTextField detailsField, 
                                JTextField valueField, JButton registerButton, JButton backButton) {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String description = descriptionField.getText();
                    String details = detailsField.getText();
                    double value = Double.parseDouble(valueField.getText());

                    FlightFares flightFares = new FlightFares();
                    flightFares.setDescription(description);
                    flightFares.setDetails(details);
                    flightFares.setValue(value);

                    flightFaresService.createFlightFare(flightFares);

                    JOptionPane.showMessageDialog(frame, "Tarifa de vuelo registrada exitosamente");
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Valor no válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
}