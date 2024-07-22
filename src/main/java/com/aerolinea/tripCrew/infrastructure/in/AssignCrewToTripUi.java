package com.aerolinea.tripCrew.infrastructure.in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.aerolinea.employee.domain.entity.Employee;
import com.aerolinea.tripCrew.domain.entity.TripConnectionInfo;
import com.aerolinea.tripCrew.domain.service.TripCrewService;

public class AssignCrewToTripUi  extends JPanel {
    private TripCrewService tripCrewService;

    private JComboBox<String> tripConnectionComboBox;
    private JComboBox<String> employeeComboBox;
    private JButton assignButton;
    private JLabel statusLabel;

    public AssignCrewToTripUi(TripCrewService tripCrewService) {
        this.tripCrewService = tripCrewService;
    }


    private void initializeUi() {
        setLayout(null);

        JLabel tripConnectionLabel = new JLabel("Select Trip Connection:");
        tripConnectionLabel.setBounds(20, 20, 160, 25);
        add(tripConnectionLabel);

        tripConnectionComboBox = new JComboBox<>();
        tripConnectionComboBox.setBounds(200, 20, 300, 25);
        tripConnectionComboBox.setPreferredSize(new Dimension(300, 25));
        tripConnectionComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setPreferredSize(new Dimension(300, component.getPreferredSize().height));
                return component;
            }
        });
        add(tripConnectionComboBox);

        JLabel employeeLabel = new JLabel("Select Employee:");
        employeeLabel.setBounds(20, 60, 160, 25);
        add(employeeLabel);

        employeeComboBox = new JComboBox<>();
        employeeComboBox.setBounds(200, 60, 160, 25);
        add(employeeComboBox);

        assignButton = new JButton("Assign");
        assignButton.setBounds(20, 100, 340, 25);
        add(assignButton);

        statusLabel = new JLabel("");
        statusLabel.setBounds(20, 140, 340, 25);
        add(statusLabel);

        loadTripConnections();
        loadAvailableEmployees();

        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignCrewToTrip();
            }
        });
    }

    private void loadTripConnections() {
        List<TripConnectionInfo> connections = tripCrewService.getAllFlightConnections();
        for (TripConnectionInfo connection : connections) {
            tripConnectionComboBox.addItem("Trip: " + connection.getTripId() + ", Airport: "
                    + connection.getAirportName() + ", Plane: " + connection.getPlanePlates());
        }
    }

    private void loadAvailableEmployees() {
        List<Employee> employees = tripCrewService.getAvailableEmployees();
        for (Employee employee : employees) {
            employeeComboBox.addItem(employee.getName() + " (" + employee.getId() + ")");
        }
    }

    private void assignCrewToTrip() {
        int selectedConnectionIndex = tripConnectionComboBox.getSelectedIndex();
        if (selectedConnectionIndex >= 0) {
            int flightConnectionId = tripCrewService.getAllFlightConnections().get(selectedConnectionIndex).getConnectionId();
        
            String selectedEmployee = (String) employeeComboBox.getSelectedItem();
            if (selectedEmployee != null) {
                // Extraer la ID del empleado del texto seleccionado
                String employeeId = selectedEmployee.substring(selectedEmployee.indexOf("(") + 1, selectedEmployee.indexOf(")"));
                tripCrewService.assignCrewToTrip(employeeId, flightConnectionId);
                statusLabel.setText("Crew assigned successfully!");
            } else {
                statusLabel.setText("Please select an employee.");
            }
        } else {
            statusLabel.setText("Please select a trip connection.");
        }
    }
}