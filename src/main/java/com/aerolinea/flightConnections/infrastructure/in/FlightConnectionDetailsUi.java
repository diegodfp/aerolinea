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

public class FlightConnectionDetailsUi {
    private final FlightConnectionService flightConnectionService;
    private Map<String, List<FlightConnection>> connectionsMap;

    public FlightConnectionDetailsUi(FlightConnectionService flightConnectionService) {
        this.flightConnectionService = flightConnectionService;
        this.connectionsMap = new HashMap<>();
    }

    public void showFlightConnectionDetailsUi() {
        JFrame frame = new JFrame("Consultar Escalas de un Trayecto");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        frame.add(panel);

        // Componentes de la interfaz
        JLabel selectTripLabel = new JLabel("Seleccionar Trayecto:");
        JComboBox<String> tripComboBox = new JComboBox<>();

        JLabel connectionLabel = new JLabel("Número de Conexión:");
        JTextField connectionField = new JTextField();
        connectionField.setEditable(false);

        JLabel departureAirportLabel = new JLabel("Aeropuerto de Salida:");
        JTextField departureAirportField = new JTextField();
        departureAirportField.setEditable(false);

        JLabel arrivalAirportLabel = new JLabel("Aeropuerto de Llegada:");
        JTextField arrivalAirportField = new JTextField();
        arrivalAirportField.setEditable(false);

        JLabel departureTimeLabel = new JLabel("Hora de Salida:");
        JTextField departureTimeField = new JTextField();
        departureTimeField.setEditable(false);

        JLabel arrivalTimeLabel = new JLabel("Hora de Llegada:");
        JTextField arrivalTimeField = new JTextField();
        arrivalTimeField.setEditable(false);

        JLabel connectionTypeLabel = new JLabel("Tipo de Conexión:");
        JTextField connectionTypeField = new JTextField();
        connectionTypeField.setEditable(false);

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

                if (connections != null && !connections.isEmpty()) {
                    FlightConnection firstConnection = connections.get(0);
                    connectionField.setText(firstConnection.getNumConnection());
                    departureAirportField.setText(firstConnection.getDepartureAirport());
                    arrivalAirportField.setText(firstConnection.getArrivalAirport());
                    departureTimeField.setText(firstConnection.getDepartureTime().toString());
                    arrivalTimeField.setText(firstConnection.getArrivalTime().toString());
                    connectionTypeField.setText(firstConnection.getConnectionType().toString());
                } else {
                    clearFields();
                }
            }
        });

        // Añadir componentes al panel
        panel.add(selectTripLabel);
        panel.add(tripComboBox);
        panel.add(connectionLabel);
        panel.add(connectionField);
        panel.add(departureAirportLabel);
        panel.add(departureAirportField);
        panel.add(arrivalAirportLabel);
        panel.add(arrivalAirportField);
        panel.add(departureTimeLabel);
        panel.add(departureTimeField);
        panel.add(arrivalTimeLabel);
        panel.add(arrivalTimeField);
        panel.add(connectionTypeLabel);
        panel.add(connectionTypeField);
        panel.add(new JLabel()); // Espacio en blanco
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

    private void clearFields() {
        // Método para limpiar los campos cuando no hay conexiones
    }
}
