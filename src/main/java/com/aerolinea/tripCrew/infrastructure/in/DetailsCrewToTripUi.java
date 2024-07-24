package com.aerolinea.tripCrew.infrastructure.in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.aerolinea.employee.domain.entity.Employee;
import com.aerolinea.tripCrew.domain.entity.TripConnectionInfo;
import com.aerolinea.tripCrew.domain.service.TripCrewService;

public class DetailsCrewToTripUi {
    private TripCrewService tripCrewService;

    private JFrame frame;
    private JComboBox<String> tripConnectionComboBox;
    private JList<String> employeeList;
    private DefaultListModel<String> employeeListModel;

    public DetailsCrewToTripUi(TripCrewService tripCrewService) {
        this.tripCrewService = tripCrewService;
    }

    public void showDetailsCrewToTripUi() {
        frame = new JFrame("Consultar Asignación de Tripulación");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        frame.add(panel);

        // Espaciado entre componentes
        gbc.insets = new Insets(10, 10, 10, 10);

        // Etiqueta de selección de trayecto
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel tripConnectionLabel = new JLabel("Seleccionar Trayecto:");
        panel.add(tripConnectionLabel, gbc);

        // ComboBox de trayectos
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        tripConnectionComboBox = new JComboBox<>();
        panel.add(tripConnectionComboBox, gbc);

        // Lista de empleados asignados
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        employeeListModel = new DefaultListModel<>();
        employeeList = new JList<>(employeeListModel);
        JScrollPane scrollPane = new JScrollPane(employeeList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Empleados Asignados"));
        panel.add(scrollPane, gbc);

        // Botón de regreso
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton backButton = new JButton("Regresar");
        panel.add(backButton, gbc);

        loadTripConnections();

        tripConnectionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAssignedEmployees();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void loadTripConnections() {
        List<TripConnectionInfo> connections = tripCrewService.getAllFlightConnections();
        for (TripConnectionInfo connection : connections) {
            tripConnectionComboBox.addItem("Trip: " + connection.getTripId() + ", Airport: "
                    + connection.getOriginAirport() + ", Plane: " + connection.getPlaneId());
        }
    }

    private void loadAssignedEmployees() {
        employeeListModel.clear();
        int selectedConnectionIndex = tripConnectionComboBox.getSelectedIndex();
        if (selectedConnectionIndex >= 0) {
            int flightConnectionId = tripCrewService.getAllFlightConnections().get(selectedConnectionIndex).getConnectionId();
            List<Employee> assignedEmployees = tripCrewService.getAssignedEmployees(flightConnectionId);
            for (Employee employee : assignedEmployees) {
                employeeListModel.addElement(employee.getName() + " (" + employee.getId() + ")");
            }
        }
    }
}
