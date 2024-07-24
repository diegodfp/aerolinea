package com.aerolinea.trip.infrastructure.in;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.aerolinea.flightConnections.domain.entity.FlightConnection;
import com.aerolinea.flightConnections.domain.service.FlightConnectionService;
import com.aerolinea.trip.domain.entity.Trip;
import com.aerolinea.trip.domain.service.TripService;
import com.aerolinea.plane.domain.entity.Plane;
import com.aerolinea.plane.domain.service.PlaneService;

public class InfoFlyUi {

    private final FlightConnectionService flightConnectionService;
    private final TripService tripService;
    private final PlaneService planeService;

    private Map<String, FlightConnection> flightConnectionMap;
    private Map<Integer, Trip> tripMap; // Use Map<Integer, Trip> for tripId

    public InfoFlyUi(FlightConnectionService flightConnectionService, TripService tripService, PlaneService planeService) {
        this.flightConnectionService = flightConnectionService;
        this.tripService = tripService;
        this.planeService = planeService;

        this.flightConnectionMap = new HashMap<>();
        this.tripMap = new HashMap<>();
    }

    public void showInfoFlyUi() {
        JFrame frame = new JFrame("Información del Vuelo");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(13, 2, 10, 10));
        frame.add(panel);

        // Componentes de la interfaz
        JLabel selectFlightLabel = new JLabel("Seleccionar Vuelo:");
        JComboBox<String> flightComboBox = new JComboBox<>();

        JLabel flightNumberLabel = new JLabel("Número de Vuelo:");
        JTextField flightNumberField = new JTextField();
        flightNumberField.setEditable(false);

        JLabel flightTypeLabel = new JLabel("Tipo de Vuelo:");
        JTextField flightTypeField = new JTextField();
        flightTypeField.setEditable(false);

        JLabel dateLabel = new JLabel("Fecha:");
        JTextField dateField = new JTextField();
        dateField.setEditable(false);

        JLabel priceLabel = new JLabel("Precio:");
        JTextField priceField = new JTextField();
        priceField.setEditable(false);

        JLabel originLabel = new JLabel("Origen:");
        JTextField originField = new JTextField();
        originField.setEditable(false);

        JLabel destinationLabel = new JLabel("Destino:");
        JTextField destinationField = new JTextField();
        destinationField.setEditable(false);

        JLabel departureLabel = new JLabel("Salida:");
        JTextField departureField = new JTextField();
        departureField.setEditable(false);

        JLabel arrivalLabel = new JLabel("Llegada:");
        JTextField arrivalField = new JTextField();
        arrivalField.setEditable(false);

        JLabel durationLabel = new JLabel("Duración:");
        JTextField durationField = new JTextField();
        durationField.setEditable(false);

        JLabel planeTypeLabel = new JLabel("Matricula del avión:");
        JTextField planeTypeField = new JTextField();
        planeTypeField.setEditable(false);

        JLabel capacityLabel = new JLabel("Capacidad:");
        JTextField capacityField = new JTextField();
        capacityField.setEditable(false);

        JButton backButton = new JButton("Regresar");

        // Cargar datos desde la base de datos
        List<Trip> trips = tripService.getAllTrips();
        for (Trip trip : trips) {
            tripMap.put(trip.getId(), trip);
            List<FlightConnection> flightConnections = flightConnectionService.getConnectionsByTripId(trip.getId());
            for (FlightConnection flightConnection : flightConnections) {
                flightComboBox.addItem(String.valueOf(flightConnection.getId()));
                flightConnectionMap.put(String.valueOf(flightConnection.getId()), flightConnection);
            }
        }

        // Añadir componentes al panel
        panel.add(selectFlightLabel);
        panel.add(flightComboBox);

        panel.add(flightNumberLabel);
        panel.add(flightNumberField);

        panel.add(flightTypeLabel);
        panel.add(flightTypeField);

        panel.add(dateLabel);
        panel.add(dateField);

        panel.add(priceLabel);
        panel.add(priceField);

        panel.add(originLabel);
        panel.add(originField);

        panel.add(destinationLabel);
        panel.add(destinationField);

        panel.add(departureLabel);
        panel.add(departureField);

        panel.add(arrivalLabel);
        panel.add(arrivalField);

        panel.add(durationLabel);
        panel.add(durationField);

        panel.add(planeTypeLabel);
        panel.add(planeTypeField);

        panel.add(capacityLabel);
        panel.add(capacityField);

        panel.add(backButton);

        // Acción al seleccionar un vuelo
        flightComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFlightId = (String) flightComboBox.getSelectedItem();
                FlightConnection selectedFlight = flightConnectionMap.get(selectedFlightId);
                Trip selectedTrip = tripMap.get(selectedFlight.getIdTrip());

                if (selectedFlight != null && selectedTrip != null) {
                    flightNumberField.setText(selectedFlight.getNumConnection());
                    flightTypeField.setText(selectedFlight.getConnectionType().name());
                    dateField.setText(selectedTrip.getDate().toString());
                    priceField.setText(String.valueOf(selectedTrip.getPrice()));
                    originField.setText(selectedTrip.getOriginAirport());
                    destinationField.setText(selectedTrip.getDestinationAirport());
                    departureField.setText(selectedFlight.getDepartureTime().toString());
                    arrivalField.setText(selectedFlight.getArrivalTime().toString());

                    // Calcular duración del vuelo
                    Duration duration = Duration.between(selectedFlight.getDepartureTime(), selectedFlight.getArrivalTime());
                    durationField.setText(String.format("%d horas %d minutos", 
                        duration.toHoursPart(), duration.toMinutesPart()));

                    Plane plane = planeService.findPlaneById(selectedFlight.getIdPlane());
                    if (plane != null) {
                        planeTypeField.setText(String.valueOf(plane.getPlates())); // Assuming idModel represents the type
                        capacityField.setText(String.valueOf(plane.getCapacity()));
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
