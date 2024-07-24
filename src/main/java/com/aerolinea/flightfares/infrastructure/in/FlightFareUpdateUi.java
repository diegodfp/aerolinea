package com.aerolinea.flightfares.infrastructure.in;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aerolinea.flightfares.domain.entity.FlightFares;
import com.aerolinea.flightfares.domain.service.FlightFaresService;

public class FlightFareUpdateUi {

    private final FlightFaresService flightFaresService;
    private Map<Integer, FlightFares> flightFareMap;
    private int originalId;

    public FlightFareUpdateUi(FlightFaresService flightFaresService) {
        this.flightFaresService = flightFaresService;
        this.flightFareMap = new HashMap<>();
    }

    public void showFlightFareUpdateUi() {
        JFrame frame = new JFrame("Actualizar Tarifa de Vuelo");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        frame.add(mainPanel);

        // Panel superior
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel selectFlightFareLabel = new JLabel("Seleccionar Tarifa de Vuelo:");
        JComboBox<Integer> flightFareComboBox = new JComboBox<>();
        topPanel.add(selectFlightFareLabel);
        topPanel.add(flightFareComboBox);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel central
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        idField.setEditable(false);

        JLabel descriptionLabel = new JLabel("Descripción:");
        JTextField descriptionField = new JTextField();

        JLabel detailsLabel = new JLabel("Detalles:");
        JTextField detailsField = new JTextField();

        JLabel valueLabel = new JLabel("Valor:");
        JTextField valueField = new JTextField();

        // Añadir componentes al panel central
        addComponent(centerPanel, idLabel, gbc, 0, 0);
        addComponent(centerPanel, idField, gbc, 1, 0);
        addComponent(centerPanel, descriptionLabel, gbc, 0, 1);
        addComponent(centerPanel, descriptionField, gbc, 1, 1);
        addComponent(centerPanel, detailsLabel, gbc, 0, 2);
        addComponent(centerPanel, detailsField, gbc, 1, 2);
        addComponent(centerPanel, valueLabel, gbc, 0, 3);
        addComponent(centerPanel, valueField, gbc, 1, 3);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Panel inferior
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton updateButton = new JButton("Actualizar");
        JButton backButton = new JButton("Regresar");
        bottomPanel.add(updateButton);
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Estilizar componentes
        styleComponents(frame, flightFareComboBox,
                new JTextField[] { idField, descriptionField, detailsField, valueField },
                updateButton, backButton);

        // Cargar datos y configurar listeners
        loadData(flightFareComboBox);
        setupListeners(frame, flightFareComboBox, idField, descriptionField, detailsField, valueField, updateButton,
                backButton);

        frame.setVisible(true);
    }

    private void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(component, gbc);
    }

    private void styleComponents(JFrame frame, JComboBox<Integer> comboBox, JTextField[] fields, JButton... buttons) {
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        comboBox.setPreferredSize(new Dimension(200, 25));
        comboBox.setBackground(Color.WHITE);

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

    private void loadData(JComboBox<Integer> comboBox) {
        List<FlightFares> flightFares = flightFaresService.getAllFlightFares();
        for (FlightFares flightFare : flightFares) {
            comboBox.addItem(flightFare.getId());
            flightFareMap.put(flightFare.getId(), flightFare);
        }
    }

    private void setupListeners(JFrame frame, JComboBox<Integer> comboBox, JTextField idField,
            JTextField descriptionField, JTextField detailsField,
            JTextField valueField, JButton updateButton, JButton backButton) {
        comboBox.addActionListener(e -> {
            int selectedId = (Integer) comboBox.getSelectedItem();
            FlightFares selectedFlightFare = flightFareMap.get(selectedId);
            if (selectedFlightFare != null) {
                originalId = selectedFlightFare.getId();
                idField.setText(String.valueOf(selectedFlightFare.getId()));
                descriptionField.setText(selectedFlightFare.getDescription());
                detailsField.setText(selectedFlightFare.getDetails());
                valueField.setText(String.valueOf(selectedFlightFare.getValue()));
            }
        });

        updateButton.addActionListener(e -> {
            try {
                double value = Double.parseDouble(valueField.getText());
                FlightFares flightFare = new FlightFares();
                flightFare.setId(originalId);
                flightFare.setDescription(descriptionField.getText());
                flightFare.setDetails(detailsField.getText());
                flightFare.setValue(value);

                flightFaresService.updateFlightFare(flightFare, originalId);
                JOptionPane.showMessageDialog(frame, "Tarifa de vuelo actualizada correctamente");
                frame.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Valor no válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> frame.dispose());
    }
}