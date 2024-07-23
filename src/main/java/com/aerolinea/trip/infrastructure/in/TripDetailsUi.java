package com.aerolinea.trip.infrastructure.in;

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
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.aerolinea.trip.domain.entity.Trip;
import com.aerolinea.trip.domain.service.TripService;

public class TripDetailsUi {

    private final TripService tripService;
    private Map<String, Trip> tripMap;

    public TripDetailsUi(TripService tripService) {
        this.tripService = tripService;
        this.tripMap = new HashMap<>();
    }

    public void showTripDetailsUi() {
        JFrame frame = new JFrame("Detalles del Trayecto");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        frame.add(panel);

        // Componentes de la interfaz
        JLabel selectTripLabel = new JLabel("Seleccionar Trayecto:");
        JComboBox<String> tripComboBox = new JComboBox<>();

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        idField.setEditable(false);

        JLabel dateLabel = new JLabel("Fecha:");
        JTextField dateField = new JTextField();
        dateField.setEditable(false);

        JLabel priceLabel = new JLabel("Precio:");
        JTextField priceField = new JTextField();
        priceField.setEditable(false);

        JLabel originLabel = new JLabel("Aeropuerto Origen:");
        JTextField originField = new JTextField();
        originField.setEditable(false);

        JLabel destinationLabel = new JLabel("Aeropuerto Destino:");
        JTextField destinationField = new JTextField();
        destinationField.setEditable(false);

        JButton backButton = new JButton("Regresar");

        // Cargar datos desde la base de datos
        List<Trip> trips = tripService.getAllTrips();
        for (Trip trip : trips) {
            tripComboBox.addItem(String.valueOf(trip.getId()));
            tripMap.put(String.valueOf(trip.getId()), trip);
        }

        // Acción al seleccionar un trayecto
        tripComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedId = (String) tripComboBox.getSelectedItem();
                Trip selectedTrip = tripMap.get(selectedId);

                if (selectedTrip != null) {
                    idField.setText(String.valueOf(selectedTrip.getId()));
                    dateField.setText(selectedTrip.getDate().toString());
                    priceField.setText(String.valueOf(selectedTrip.getPrice()));
                    originField.setText(selectedTrip.getOriginAirport());
                    destinationField.setText(selectedTrip.getDestinationAirport());
                }
            }
        });

        // Añadir componentes al panel
        panel.add(selectTripLabel);
        panel.add(tripComboBox);

        panel.add(idLabel);
        panel.add(idField);

        panel.add(dateLabel);
        panel.add(dateField);

        panel.add(priceLabel);
        panel.add(priceField);

        panel.add(originLabel);
        panel.add(originField);

        panel.add(destinationLabel);
        panel.add(destinationField);

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
