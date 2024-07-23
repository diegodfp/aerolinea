package com.aerolinea.flightConnections.infrastructure.in;

import com.aerolinea.flightConnections.domain.entity.FlightConnection;
import com.aerolinea.flightConnections.domain.service.FlightConnectionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightConnectionUpdateUi {
    private final FlightConnectionService flightConnectionService;
    private Map<String, List<FlightConnection>> connectionsMap;
    private JComboBox<String> tripComboBox;
    private JComboBox<FlightConnection> connectionComboBox;
    private JTextField numConnectionField;
    private JTextField departureAirportField;
    private JTextField arrivalAirportField;
    private JTextField departureTimeField;
    private JTextField arrivalTimeField;
    private JTextField orderNumberField;
    private JComboBox<String> connectionTypeComboBox;

    public FlightConnectionUpdateUi(FlightConnectionService flightConnectionService) {
        this.flightConnectionService = flightConnectionService;
        this.connectionsMap = new HashMap<>();
    }

    public void showFlightConnectionUpdateUi() {
        JFrame frame = new JFrame("Actualizar Escalas de un Trayecto");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10));
        frame.add(panel);

        // Componentes de la interfaz
        JLabel selectTripLabel = new JLabel("Seleccionar Trayecto:");
        tripComboBox = new JComboBox<>();

        JLabel selectConnectionLabel = new JLabel("Seleccionar Conexión:");
        connectionComboBox = new JComboBox<>();

        JLabel numConnectionLabel = new JLabel("Número de Conexión:");
        numConnectionField = new JTextField();

        JLabel departureAirportLabel = new JLabel("Aeropuerto de Salida:");
        departureAirportField = new JTextField();

        JLabel arrivalAirportLabel = new JLabel("Aeropuerto de Llegada:");
        arrivalAirportField = new JTextField();

        JLabel departureTimeLabel = new JLabel("Hora de Salida:");
        departureTimeField = new JTextField();

        JLabel arrivalTimeLabel = new JLabel("Hora de Llegada:");
        arrivalTimeField = new JTextField();

        JLabel orderNumberLabel = new JLabel("Número de Orden:");
        orderNumberField = new JTextField();

        JLabel connectionTypeLabel = new JLabel("Tipo de Conexión:");
        connectionTypeComboBox = new JComboBox<>(new String[]{"DIRECT", "LAYOVER"});

        JButton updateButton = new JButton("Actualizar");
        JButton backButton = new JButton("Regresar");

        // Cargar datos desde la base de datos
        List<Integer> tripIds = flightConnectionService.getAllTripIds();
        for (Integer tripId : tripIds) {
            tripComboBox.addItem(String.valueOf(tripId));
            List<FlightConnection> connections = flightConnectionService.getConnectionsByTripId(tripId);
            connectionsMap.put(String.valueOf(tripId), connections);
        }

        // Acción al seleccionar un trayecto
        tripComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedId = (String) tripComboBox.getSelectedItem();
                List<FlightConnection> connections = connectionsMap.get(selectedId);

                connectionComboBox.removeAllItems();
                if (connections != null) {
                    for (FlightConnection connection : connections) {
                        connectionComboBox.addItem(connection);
                    }
                }
            }
        });

        // Acción al seleccionar una conexión
        connectionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlightConnection selectedConnection = (FlightConnection) connectionComboBox.getSelectedItem();

                if (selectedConnection != null) {
                    numConnectionField.setText(selectedConnection.getNumConnection());
                    departureAirportField.setText(selectedConnection.getDepartureAirport());
                    arrivalAirportField.setText(selectedConnection.getArrivalAirport());
                    departureTimeField.setText(selectedConnection.getDepartureTime().toString());
                    arrivalTimeField.setText(selectedConnection.getArrivalTime().toString());
                    orderNumberField.setText(String.valueOf(selectedConnection.getOrderNumber()));
                    connectionTypeComboBox.setSelectedItem(selectedConnection.getConnectionType().toString());
                }
            }
        });

        // Acción del botón de actualización
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlightConnection selectedConnection = (FlightConnection) connectionComboBox.getSelectedItem();

                if (selectedConnection != null) {
                    selectedConnection.setNumConnection(numConnectionField.getText());
                    selectedConnection.setDepartureAirport(departureAirportField.getText());
                    selectedConnection.setArrivalAirport(arrivalAirportField.getText());
                    selectedConnection.setDepartureTime(java.sql.Timestamp.valueOf(departureTimeField.getText()).toLocalDateTime());
                    selectedConnection.setArrivalTime(java.sql.Timestamp.valueOf(arrivalTimeField.getText()).toLocalDateTime());
                    selectedConnection.setOrderNumber(Integer.parseInt(orderNumberField.getText()));
                    selectedConnection.setConnectionType(FlightConnection.ConnectionType.valueOf((String) connectionTypeComboBox.getSelectedItem()));

                    flightConnectionService.updateFlightConnection(selectedConnection);
                    JOptionPane.showMessageDialog(frame, "Conexión actualizada con éxito.");
                }
            }
        });

        // Añadir componentes al panel
        panel.add(selectTripLabel);
        panel.add(tripComboBox);
        panel.add(selectConnectionLabel);
        panel.add(connectionComboBox);
        panel.add(numConnectionLabel);
        panel.add(numConnectionField);
        panel.add(departureAirportLabel);
        panel.add(departureAirportField);
        panel.add(arrivalAirportLabel);
        panel.add(arrivalAirportField);
        panel.add(departureTimeLabel);
        panel.add(departureTimeField);
        panel.add(arrivalTimeLabel);
        panel.add(arrivalTimeField);
        panel.add(orderNumberLabel);
        panel.add(orderNumberField);
        panel.add(connectionTypeLabel);
        panel.add(connectionTypeComboBox);
        panel.add(updateButton);
        panel.add(backButton);

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
