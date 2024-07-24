package com.aerolinea.common.infrastructure.in;

import javax.swing.*;

import com.aerolinea.common.domain.entity.Passenger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerAssignmentUi extends JFrame {
    private JTextField[] nameFields;
    private JTextField[] ageFields;
    private JComboBox<String>[] documentTypeComboBoxes;
    private JTextField[] documentNumberFields;
    private JButton addButton;
    private JButton assignSeatsButton;
    private JTextArea passengersArea;
    private List<Passenger> passengers;
    private ClientUi clientUi;

    public PassengerAssignmentUi(ClientUi clientUi, int numPassengers) {
        this.clientUi = clientUi;
        this.passengers = new ArrayList<>();
        setTitle("Añadir Pasajeros");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel passengerPanel = new JPanel(new GridBagLayout());
        JScrollPane passengerScrollPane = new JScrollPane(passengerPanel);
        mainPanel.add(passengerScrollPane, BorderLayout.CENTER);

        nameFields = new JTextField[numPassengers];
        ageFields = new JTextField[numPassengers];
        documentTypeComboBoxes = new JComboBox[numPassengers];
        documentNumberFields = new JTextField[numPassengers];

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < numPassengers; i++) {
            gbc.gridy = i;
            gbc.gridx = 0;
            passengerPanel.add(new JLabel("Pasajero " + (i + 1)), gbc);

            gbc.gridx = 1;
            passengerPanel.add(new JLabel("Nombre:"), gbc);
            gbc.gridx = 2;
            nameFields[i] = new JTextField(15);
            passengerPanel.add(nameFields[i], gbc);

            gbc.gridx = 3;
            passengerPanel.add(new JLabel("Edad:"), gbc);
            gbc.gridx = 4;
            ageFields[i] = new JTextField(3);
            passengerPanel.add(ageFields[i], gbc);

            gbc.gridx = 5;
            passengerPanel.add(new JLabel("Tipo de Documento:"), gbc);
            gbc.gridx = 6;
            documentTypeComboBoxes[i] = new JComboBox<>(new String[] { "DNI", "Pasaporte", "Otro" });
            passengerPanel.add(documentTypeComboBoxes[i], gbc);

            gbc.gridx = 7;
            passengerPanel.add(new JLabel("Número:"), gbc);
            gbc.gridx = 8;
            documentNumberFields[i] = new JTextField(10);
            passengerPanel.add(documentNumberFields[i], gbc);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Añadir Pasajeros");
        addButton.addActionListener(e -> addPassengers());
        buttonPanel.add(addButton);

        assignSeatsButton = new JButton("Asignar Asientos");
        assignSeatsButton.setEnabled(false);
        assignSeatsButton.addActionListener(e -> assignSeats());
        buttonPanel.add(assignSeatsButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        passengersArea = new JTextArea(10, 40);
        passengersArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(passengersArea);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainPanel, scrollPane);
        splitPane.setResizeWeight(0.7);
        add(splitPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addPassengers() {
        for (int i = 0; i < nameFields.length; i++) {
            String name = nameFields[i].getText();
            String ageText = ageFields[i].getText();
            String documentType = (String) documentTypeComboBoxes[i].getSelectedItem();
            String documentNumber = documentNumberFields[i].getText();

            if (name.isEmpty() || ageText.isEmpty() || documentNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int age = Integer.parseInt(ageText);
                Passenger passenger = new Passenger(name, age, documentType, documentNumber);
                passengers.add(passenger);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La edad debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        updatePassengerList();
        clearFields();
        assignSeatsButton.setEnabled(!passengers.isEmpty());
    }

    private void updatePassengerList() {
        StringBuilder sb = new StringBuilder();
        for (Passenger passenger : passengers) {
            sb.append(passenger.toString()).append("\n");
        }
        passengersArea.setText(sb.toString());
    }

    private void clearFields() {
        for (int i = 0; i < nameFields.length; i++) {
            nameFields[i].setText("");
            ageFields[i].setText("");
            documentNumberFields[i].setText("");
        }
    }

    private void assignSeats() {
        new SeatAssignmentUi(passengers, this);
        this.setVisible(false); // Ocultar la ventana principal mientras se asignan los asientos
    }
}