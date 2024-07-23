package com.aerolinea.trip.infrastructure.in;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.aerolinea.trip.domain.entity.Trip;
import com.aerolinea.trip.domain.service.TripService;
import com.aerolinea.airport.domain.entity.Airport;
import com.aerolinea.airport.domain.service.AirportService;

public class TripUpdateUi {

    private final TripService tripService;
    private final AirportService airportService;
    private Map<Integer, Trip> tripMap;
    private Map<String, String> airportMap;
    private int originalId;

    public TripUpdateUi(TripService tripService, AirportService airportService) {
        this.tripService = tripService;
        this.airportService = airportService;
        this.tripMap = new HashMap<>();
        this.airportMap = new HashMap<>();
    }

    public void showTripUpdateUi() {
        JFrame frame = new JFrame("Actualizar Trayecto");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        frame.add(panel);

        JLabel selectTripLabel = new JLabel("Seleccionar Trayecto:");
        JComboBox<Integer> tripComboBox = new JComboBox<>();

        JLabel dateLabel = new JLabel("Fecha:");
        JTextField dateField = new JTextField();

        JLabel priceLabel = new JLabel("Precio:");
        JTextField priceField = new JTextField();

        JLabel originAirportLabel = new JLabel("Aeropuerto de Origen:");
        JComboBox<String> originAirportComboBox = new JComboBox<>();

        JLabel destinationAirportLabel = new JLabel("Aeropuerto de Destino:");
        JComboBox<String> destinationAirportComboBox = new JComboBox<>();

        JButton updateButton = new JButton("Actualizar");
        JButton backButton = new JButton("Regresar");

        List<Trip> trips = tripService.getAllTrips();
        for (Trip trip : trips) {
            tripComboBox.addItem(trip.getId());
            tripMap.put(trip.getId(), trip);
        }

        List<Airport> airports = airportService.getAllAirports();
        for (Airport airport : airports) {
            originAirportComboBox.addItem(airport.getName());
            destinationAirportComboBox.addItem(airport.getName());
            airportMap.put(airport.getName(), airport.getId());
        }

        tripComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer selectedId = (Integer) tripComboBox.getSelectedItem();
                Trip selectedTrip = tripMap.get(selectedId);

                if (selectedTrip != null) {
                    originalId = selectedTrip.getId();
                    dateField.setText(selectedTrip.getDate().toString());
                    priceField.setText(String.valueOf(selectedTrip.getPrice()));
                    originAirportComboBox.setSelectedItem(getKeyByValue(airportMap, selectedTrip.getOriginAirport()));
                    destinationAirportComboBox.setSelectedItem(getKeyByValue(airportMap, selectedTrip.getDestinationAirport()));
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                double price = Double.parseDouble(priceField.getText());
                String originAirport = airportMap.get((String) originAirportComboBox.getSelectedItem());
                String destinationAirport = airportMap.get((String) destinationAirportComboBox.getSelectedItem());

                Trip trip = new Trip();
                trip.setDate(Date.valueOf(date));
                trip.setPrice(price);
                trip.setOriginAirport(originAirport);
                trip.setDestinationAirport(destinationAirport);

                tripService.updateTrip(trip, originalId);

                JOptionPane.showMessageDialog(frame, "Trayecto actualizado correctamente");
                frame.dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        panel.add(selectTripLabel);
        panel.add(tripComboBox);

        panel.add(dateLabel);
        panel.add(dateField);

        panel.add(priceLabel);
        panel.add(priceField);

        panel.add(originAirportLabel);
        panel.add(originAirportComboBox);

        panel.add(destinationAirportLabel);
        panel.add(destinationAirportComboBox);

        panel.add(updateButton);
        panel.add(backButton);

        frame.setVisible(true);
    }

    private <T, E> String getKeyByValue(Map<String, T> map, E value) {
        for (Map.Entry<String, T> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
