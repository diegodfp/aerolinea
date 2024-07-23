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

public class FlightFareDeleteUi {

    private final FlightFaresService flightFaresService;
    private Map<Integer, FlightFares> flightFareMap;

    public FlightFareDeleteUi(FlightFaresService flightFaresService) {
        this.flightFaresService = flightFaresService;
        this.flightFareMap = new HashMap<>();
    }

    public void showFlightFareDeleteUi() {
        JFrame frame = new JFrame("Eliminar Tarifa de Vuelo");
        frame.setSize(450, 200);
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

        // Panel inferior
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton deleteButton = new JButton("Eliminar");
        JButton backButton = new JButton("Regresar");
        bottomPanel.add(deleteButton);
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Estilizar componentes
        styleComponents(frame, flightFareComboBox, deleteButton, backButton);

        // Cargar datos y configurar listeners
        loadData(flightFareComboBox);
        setupListeners(frame, flightFareComboBox, deleteButton, backButton);

        frame.setVisible(true);
    }

    private void styleComponents(JFrame frame, JComboBox<Integer> comboBox, JButton... buttons) {
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        comboBox.setPreferredSize(new Dimension(200, 25));
        comboBox.setBackground(Color.WHITE);

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

    private void setupListeners(JFrame frame, JComboBox<Integer> comboBox, JButton deleteButton, JButton backButton) {
        deleteButton.addActionListener(e -> {
            int selectedId = (Integer) comboBox.getSelectedItem();
            if (selectedId != -1) {
                int confirmation = JOptionPane.showConfirmDialog(frame, "¿Estás seguro de que deseas eliminar esta tarifa de vuelo?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    flightFaresService.deleteFlightFare(selectedId);
                    JOptionPane.showMessageDialog(frame, "Tarifa de vuelo eliminada correctamente");
                    frame.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Selecciona una tarifa de vuelo para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> frame.dispose());
    }
}
