package com.aerolinea.tripCrew.infrastructure.in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.aerolinea.employee.domain.entity.Employee;
import com.aerolinea.tripCrew.domain.entity.TripConnectionInfo;
import com.aerolinea.tripCrew.domain.service.TripCrewService;

public class AssignCrewToTripUi {
    private TripCrewService tripCrewService;

    private JFrame frame;
    private JComboBox<String> tripConnectionComboBox;
    private JComboBox<String> employeeComboBox;
    private JButton assignButton;
    private JLabel statusLabel;

    public AssignCrewToTripUi(TripCrewService tripCrewService) {
        this.tripCrewService = tripCrewService;
    }

    public void showAssignCrewToTripUi() {
        frame = new JFrame("Assign Crew to Trip");
        frame.setSize(550, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(null);
        frame.add(panel);

        JLabel tripConnectionLabel = new JLabel("Select Trip Connection:");
        tripConnectionLabel.setBounds(20, 20, 160, 25);
        panel.add(tripConnectionLabel);

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
        panel.add(tripConnectionComboBox);

        JLabel employeeLabel = new JLabel("Select Employee:");
        employeeLabel.setBounds(20, 60, 160, 25);
        panel.add(employeeLabel);

        employeeComboBox = new JComboBox<>();
        employeeComboBox.setBounds(200, 60, 300, 25);
        panel.add(employeeComboBox);

        assignButton = new JButton("Assign");
        assignButton.setBounds(20, 100, 480, 25);
        panel.add(assignButton);

        statusLabel = new JLabel("");
        statusLabel.setBounds(20, 140, 480, 25);
        panel.add(statusLabel);

        loadTripConnections();
        loadAvailableEmployees();

        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    assignCrewToTrip();
                } catch (SQLIntegrityConstraintViolationException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
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

    private void loadAvailableEmployees() {
        List<Employee> employees = tripCrewService.getAvailableEmployees();
        for (Employee employee : employees) {
            employeeComboBox.addItem(employee.getName() + " (" + employee.getId() + ")");
        }
    }

    private void assignCrewToTrip() throws SQLIntegrityConstraintViolationException {
        int selectedConnectionIndex = tripConnectionComboBox.getSelectedIndex();
        if (selectedConnectionIndex >= 0) {
            int flightConnectionId = tripCrewService.getAllFlightConnections().get(selectedConnectionIndex)
                    .getConnectionId();
    
            String selectedEmployee = (String) employeeComboBox.getSelectedItem();
            if (selectedEmployee != null) {
                String employeeId = selectedEmployee.substring(selectedEmployee.indexOf("(") + 1,
                        selectedEmployee.indexOf(")"));
    
                // Verificar si el empleado ya está asignado a este trayecto
                if (tripCrewService.isEmployeeAssignedToConnection(employeeId, flightConnectionId)) {
                    JOptionPane.showMessageDialog(
                        frame,
                        "This employee is already assigned to this connection.",
                        "Duplicate Assignment",
                        JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    try {
                        tripCrewService.assignCrewToTrip(employeeId, flightConnectionId);
                        statusLabel.setText("Crew assigned successfully!");
                        // Actualizar las listas después de una asignación exitosa
                        loadTripConnections();
                        loadAvailableEmployees();
                    } catch (Exception e) {
                        statusLabel.setText("An error occurred while assigning the crew.");
                        e.printStackTrace();
                    }
                }
            } else {
                statusLabel.setText("Please select an employee.");
            }
        } else {
            statusLabel.setText("Please select a trip connection.");
        }
    }
}