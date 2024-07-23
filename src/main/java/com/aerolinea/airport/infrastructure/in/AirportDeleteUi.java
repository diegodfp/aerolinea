package com.aerolinea.airport.infrastructure.in;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.aerolinea.airport.domain.entity.Airport;
import com.aerolinea.airport.domain.service.AirportService;

public class AirportDeleteUi {

    private final AirportService airportService;
    private Map<String, Airport> airportMap;

    public AirportDeleteUi(AirportService airportService) {
        this.airportService = airportService;
        this.airportMap = new HashMap<>();
    }

    public void showAirportDeleteUi() {
        JFrame frame = new JFrame("Eliminar Aeropuerto");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        frame.add(panel);

        // Componentes de la interfaz
        JLabel selectAirportLabel = new JLabel("Seleccionar Aeropuerto:");
        JComboBox<String> airportComboBox = new JComboBox<>();

        // Cargar datos desde la base de datos
        List<Airport> airports = airportService.getAllAirports();
        for (Airport airport : airports) {
            // Mostrar el nombre y el ID en el JComboBox
            String displayText = airport.getId() + " - " + airport.getName();
            airportComboBox.addItem(displayText);
            airportMap.put(displayText, airport);
        }

        JButton deleteButton = new JButton("Eliminar");
        JButton backButton = new JButton("Regresar");

        // Añadir componentes al panel
        panel.add(selectAirportLabel);
        panel.add(airportComboBox);
        panel.add(deleteButton);
        panel.add(backButton);

        // Acción del botón de eliminar
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedText = (String) airportComboBox.getSelectedItem();
                if (selectedText != null) {
                    Airport selectedAirport = airportMap.get(selectedText);
                    if (selectedAirport != null) {
                        int confirm = JOptionPane.showConfirmDialog(frame, "¿Está seguro de que desea eliminar el aeropuerto con ID " + selectedAirport.getId() + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            airportService.deleteAirport(selectedAirport.getId());
                            JOptionPane.showMessageDialog(frame, "Aeropuerto eliminado correctamente");
                            frame.dispose();
                        }
                    }
                }
            }
        });

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
