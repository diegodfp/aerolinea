package com.aerolinea.common.infrastructure.in;

import javax.swing.*;

import com.aerolinea.common.domain.entity.Passenger;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatAssignmentUi extends JFrame {
    private JPanel seatsPanel;
    private JButton[][] seats;
    private Map<String, Passenger> seatAssignments;
    private int numRows;
    private int numCols;
    private List<Passenger> passengers;
    private PassengerAssignmentUi parentUi;  // Referencia a la ventana principal

    public SeatAssignmentUi(List<Passenger> passengers, PassengerAssignmentUi parentUi) {
        this.passengers = passengers;
        this.parentUi = parentUi;
        this.numRows = 10; // Número de filas de asientos
        this.numCols = 6;  // Número de columnas de asientos (A-F)
        this.seatAssignments = new HashMap<>();
        setTitle("Asignar Asientos");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        seatsPanel = new JPanel(new GridLayout(numRows, numCols));
        seats = new JButton[numRows][numCols];

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                String seatLabel = (char) ('A' + col) + String.valueOf(row + 1);
                seats[row][col] = new JButton(seatLabel);
                seats[row][col].setBackground(Color.GREEN);
                seats[row][col].addActionListener(e -> assignSeat(seatLabel));
                seatsPanel.add(seats[row][col]);
            }
        }

        add(new JScrollPane(seatsPanel), BorderLayout.CENTER);

        // Añadir el botón de regreso
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Regresar");
        backButton.addActionListener(e -> backToParent());
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void assignSeat(String seatLabel) {
        if (passengers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay pasajeros para asignar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Passenger passenger = passengers.remove(0);
        seatAssignments.put(seatLabel, passenger);
        updateSeatButton(seatLabel, passenger);

        // Verificar si todos los pasajeros han sido asignados
        if (passengers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los pasajeros han sido asignados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            backToParent();
        }
    }

    private void updateSeatButton(String seatLabel, Passenger passenger) {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (seats[row][col].getText().equals(seatLabel)) {
                    seats[row][col].setBackground(Color.RED);
                    seats[row][col].setText(seatLabel + "\n" + passenger.getName());
                    seats[row][col].setEnabled(false);
                    break;
                }
            }
        }
    }

    private void backToParent() {
        this.dispose(); // Cerrar la ventana actual
        parentUi.setVisible(true); // Mostrar la ventana principal
    }
}
