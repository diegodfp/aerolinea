package com.aerolinea.common.infrastructure.in;

import com.aerolinea.airport.domain.service.AirportService;
import com.aerolinea.flightConnections.domain.service.FlightConnectionService;
import com.aerolinea.flightConnections.domain.entity.FlightConnection;
import com.aerolinea.airport.domain.entity.Airport;
import com.aerolinea.city.domain.service.CityService;
import com.aerolinea.country.domain.service.CountryService;
import com.aerolinea.country.domain.entity.Country;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

public class ClientUi {

    private final FlightConnectionService flightConnectionService;
    private final AirportService airportService;
    private final CityService cityService;
    private final CountryService countryService;

    private JComboBox<String> originCountryComboBox;
    private JComboBox<String> originCityComboBox;
    private JComboBox<String> originAirportComboBox;
    private JComboBox<String> destinationCountryComboBox;
    private JComboBox<String> destinationCityComboBox;
    private JComboBox<String> destinationAirportComboBox;
    private JTextField departureDateField;
    private JTextField returnDateField;
    private JComboBox<String> resultComboBox;

    public ClientUi(FlightConnectionService flightConnectionService, AirportService airportService, CityService cityService, CountryService countryService) {
        this.flightConnectionService = flightConnectionService;
        this.airportService = airportService;
        this.cityService = cityService;
        this.countryService = countryService;
    }

    public void showSearchFlightsUi() {
        JFrame frame = new JFrame("Buscar Vuelos");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.CENTER);
        placeComponents(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new GridLayout(11, 2, 10, 10));

        JLabel originCountryLabel = new JLabel("País de Origen:");
        originCountryComboBox = new JComboBox<>(getCountryNames().toArray(new String[0]));
        originCountryComboBox.addActionListener(e -> updateOriginCityComboBox());

        JLabel originCityLabel = new JLabel("Ciudad de Origen:");
        originCityComboBox = new JComboBox<>();
        originCityComboBox.addActionListener(e -> updateOriginAirportComboBox());

        JLabel originAirportLabel = new JLabel("Aeropuerto de Origen:");
        originAirportComboBox = new JComboBox<>();

        JLabel destinationCountryLabel = new JLabel("País de Destino:");
        destinationCountryComboBox = new JComboBox<>(getCountryNames().toArray(new String[0]));
        destinationCountryComboBox.addActionListener(e -> updateDestinationCityComboBox());

        JLabel destinationCityLabel = new JLabel("Ciudad de Destino:");
        destinationCityComboBox = new JComboBox<>();
        destinationCityComboBox.addActionListener(e -> updateDestinationAirportComboBox());

        JLabel destinationAirportLabel = new JLabel("Aeropuerto de Destino:");
        destinationAirportComboBox = new JComboBox<>();

        JLabel departureDateLabel = new JLabel("Fecha de Salida (YYYY-MM-DD):");
        departureDateField = new JTextField();

        JLabel returnDateLabel = new JLabel("Fecha de Regreso (YYYY-MM-DD, opcional):");
        returnDateField = new JTextField();

        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(e -> searchFlights());

        resultComboBox = new JComboBox<>();
        JButton selectFlightButton = new JButton("Seleccionar Vuelo");
        selectFlightButton.addActionListener(e -> selectFlight());

        panel.add(originCountryLabel);
        panel.add(originCountryComboBox);
        panel.add(originCityLabel);
        panel.add(originCityComboBox);
        panel.add(originAirportLabel);
        panel.add(originAirportComboBox);
        panel.add(destinationCountryLabel);
        panel.add(destinationCountryComboBox);
        panel.add(destinationCityLabel);
        panel.add(destinationCityComboBox);
        panel.add(destinationAirportLabel);
        panel.add(destinationAirportComboBox);
        panel.add(departureDateLabel);
        panel.add(departureDateField);
        panel.add(returnDateLabel);
        panel.add(returnDateField);
        panel.add(searchButton);
        panel.add(resultComboBox);
        panel.add(selectFlightButton);

        updateOriginCityComboBox();
        updateDestinationCityComboBox();
    }

    private List<String> getCountryNames() {
        return countryService.getAllCountrys().stream()
                .map(Country::getName)
                .collect(Collectors.toList());
    }

    private List<String> getCityNames(String countryId) {
        return cityService.getAllCitys(countryId).stream()
                .map(city -> city.getName())
                .collect(Collectors.toList());
    }

    private List<Airport> getAirportNames(String cityId) {
        return airportService.getAllAirports().stream()
                .filter(airport -> airport.getIdCity().equals(cityId))
                .collect(Collectors.toList());
    }

    private String getCountryId(String countryName) {
        return countryService.getAllCountrys().stream()
                .filter(country -> country.getName().equals(countryName))
                .map(Country::getId)
                .findFirst()
                .orElse(null);
    }

    private String getCityId(String cityName, String countryId) {
        return cityService.getAllCitys(countryId).stream()
                .filter(city -> city.getName().equals(cityName))
                .map(city -> city.getId())
                .findFirst()
                .orElse(null);
    }

    private void updateOriginCityComboBox() {
        String originCountryId = getCountryId((String) originCountryComboBox.getSelectedItem());
        originCityComboBox.setModel(new DefaultComboBoxModel<>(getCityNames(originCountryId).toArray(new String[0])));
        updateOriginAirportComboBox();
    }

    private void updateDestinationCityComboBox() {
        String destinationCountryId = getCountryId((String) destinationCountryComboBox.getSelectedItem());
        destinationCityComboBox.setModel(new DefaultComboBoxModel<>(getCityNames(destinationCountryId).toArray(new String[0])));
        updateDestinationAirportComboBox();
    }

    private void updateOriginAirportComboBox() {
        String originCountryId = getCountryId((String) originCountryComboBox.getSelectedItem());
        String originCityId = getCityId((String) originCityComboBox.getSelectedItem(), originCountryId);
        List<Airport> airports = getAirportNames(originCityId);
        originAirportComboBox.setModel(new DefaultComboBoxModel<>(airports.stream().map(Airport::getName).toArray(String[]::new)));
    }

    private void updateDestinationAirportComboBox() {
        String destinationCountryId = getCountryId((String) destinationCountryComboBox.getSelectedItem());
        String destinationCityId = getCityId((String) destinationCityComboBox.getSelectedItem(), destinationCountryId);
        List<Airport> airports = getAirportNames(destinationCityId);
        destinationAirportComboBox.setModel(new DefaultComboBoxModel<>(airports.stream().map(Airport::getName).toArray(String[]::new)));
    }

    private void searchFlights() {
        String originAirport = (String) originAirportComboBox.getSelectedItem();
        String destinationAirport = (String) destinationAirportComboBox.getSelectedItem();

        // Obtener el ID del aeropuerto de origen y destino
        String originAirportId = airportService.getAllAirports().stream()
                .filter(airport -> airport.getName().equals(originAirport))
                .map(Airport::getId)
                .findFirst()
                .orElse(null);

        String destinationAirportId = airportService.getAllAirports().stream()
                .filter(airport -> airport.getName().equals(destinationAirport))
                .map(Airport::getId)
                .findFirst()
                .orElse(null);

        List<Integer> allTripIds = flightConnectionService.getAllTripIds();
        List<FlightConnection> matchingFlights = allTripIds.stream()
            .flatMap(tripId -> flightConnectionService.getConnectionsByTripId(tripId).stream())
            .filter(flight -> flight.getDepartureAirport().equals(originAirportId) &&
                              flight.getArrivalAirport().equals(destinationAirportId))
            .collect(Collectors.toList());

        displayResults(matchingFlights);
    }

    private void displayResults(List<FlightConnection> flights) {
        if (flights.isEmpty()) {
            resultComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"No se encontraron vuelos."}));
        } else {
            String[] flightOptions = flights.stream()
                    .map(flight -> "Vuelo: " + flight.getId() + ", Origen: " + flight.getDepartureAirport() + ", Destino: " + flight.getArrivalAirport())
                    .toArray(String[]::new);
            resultComboBox.setModel(new DefaultComboBoxModel<>(flightOptions));
        }
    }

    private void selectFlight() {
        String selectedFlight = (String) resultComboBox.getSelectedItem();
        if (selectedFlight != null && !selectedFlight.equals("No se encontraron vuelos.")) {
            // Abre la ventana para añadir pasajeros
            String[] options = {"1", "2", "3", "4", "5"}; // Opciones de número de pasajeros
            String numberOfPassengers = (String) JOptionPane.showInputDialog(
                    null,
                    "¿Cuántos pasajeros desea añadir?",
                    "Añadir Pasajeros",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (numberOfPassengers != null) {
                int numPassengers = Integer.parseInt(numberOfPassengers);
                PassengerAssignmentUi passengerAssignmentUi = new PassengerAssignmentUi(this, numPassengers);
                passengerAssignmentUi.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un vuelo.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
