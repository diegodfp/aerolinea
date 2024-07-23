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

public class FlightConnectionDeleteUi {
    private final FlightConnectionService flightConnectionService;
    private Map<String, List<FlightConnection>> connectionsMap;
    private JComboBox<String> tripComboBox;
    private JComboBox<FlightConnection> connectionComboBox;

    public FlightConnectionDeleteUi(FlightConnectionService flightConnectionService) {
        this.flightConnectionService = flightConnectionService;
        this.connectionsMap = new HashMap<>();
    }

    public void showFlightConnectionDeleteUi() {
        JFrame frame = new JFrame("Eliminar Escalas de un Trayecto");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        frame.add(panel);

        // Componentes de la interfaz
        JLabel selectTripLabel = new JLabel("Seleccionar Trayecto:");
        tripComboBox = new JComboBox<>();

        JLabel selectConnectionLabel = new JLabel("Seleccionar Conexión:");
        connectionComboBox = new JComboBox<>();

        JButton deleteButton = new JButton("Eliminar");
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

        // Acción del botón de eliminación
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlightConnection selectedConnection = (FlightConnection) connectionComboBox.getSelectedItem();

                if (selectedConnection != null) {
                    int confirm = JOptionPane.showConfirmDialog(frame, "¿Está seguro de que desea eliminar esta escala?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        flightConnectionService.deleteFlightConnection(selectedConnection.getId());
                        JOptionPane.showMessageDialog(frame, "Conexión eliminada con éxito.");
                        // Actualizar la lista después de la eliminación
                        connectionsMap.get(tripComboBox.getSelectedItem()).remove(selectedConnection);
                        connectionComboBox.removeItem(selectedConnection);
                    }
                }
            }
        });

        // Añadir componentes al panel
        panel.add(selectTripLabel);
        panel.add(tripComboBox);
        panel.add(selectConnectionLabel);
        panel.add(connectionComboBox);
        panel.add(deleteButton);
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
