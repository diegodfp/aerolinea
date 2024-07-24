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
    private JComboBox<String> resultsComboBox;

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

        JLabel resultsLabel = new JLabel("Resultados:");
        resultsComboBox = new JComboBox<>();

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
        panel.add(resultsLabel);
        panel.add(resultsComboBox);

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

    private List<String> getAirportNames(String cityId) {
        return airportService.getAllAirports().stream()
                .filter(airport -> airport.getIdCity().equals(cityId))
                .map(Airport::getName)
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
        originAirportComboBox.setModel(new DefaultComboBoxModel<>(getAirportNames(originCityId).toArray(new String[0])));
    }

    private void updateDestinationAirportComboBox() {
        String destinationCountryId = getCountryId((String) destinationCountryComboBox.getSelectedItem());
        String destinationCityId = getCityId((String) destinationCityComboBox.getSelectedItem(), destinationCountryId);
        destinationAirportComboBox.setModel(new DefaultComboBoxModel<>(getAirportNames(destinationCityId).toArray(new String[0])));
    }

    private boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void searchFlights() {
        // Obtiene el nombre de los aeropuertos seleccionados
        String originAirportName = (String) originAirportComboBox.getSelectedItem();
        String destinationAirportName = (String) destinationAirportComboBox.getSelectedItem();
        
        // Obtiene los IDs de los aeropuertos a partir de sus nombres
        String originAirportId = getAirportIdByName(originAirportName);
        String destinationAirportId = getAirportIdByName(destinationAirportName);
        
        // Verifica si se encontraron los IDs de los aeropuertos
        if (originAirportId == null || destinationAirportId == null) {
            JOptionPane.showMessageDialog(null, "Error al obtener los IDs de los aeropuertos seleccionados.");
            return;
        }
    
        // Obtiene todos los IDs de trayectos y filtra los vuelos que coinciden con los aeropuertos seleccionados
        List<Integer> allTripIds = flightConnectionService.getAllTripIds();
        List<FlightConnection> matchingFlights = allTripIds.stream()
            .flatMap(tripId -> flightConnectionService.getConnectionsByTripId(tripId).stream())
            .filter(flight -> flight.getDepartureAirport().equals(originAirportId) &&
                              flight.getArrivalAirport().equals(destinationAirportId))
            .collect(Collectors.toList());
    
        // Actualiza el JComboBox con los resultados encontrados
        updateResultsComboBox(matchingFlights);
    }
    
    private String getAirportIdByName(String airportName) {
        return airportService.getAllAirports().stream()
            .filter(airport -> airport.getName().equals(airportName))
            .map(Airport::getId)
            .findFirst()
            .orElse(null);
    }
    
    
    private void updateResultsComboBox(List<FlightConnection> flights) {
        if (flights.isEmpty()) {
            resultsComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"No se encontraron vuelos"}));
        } else {
            resultsComboBox.setModel(new DefaultComboBoxModel<>(flights.stream()
                .map(flight -> "Vuelo: " + flight.getNumConnection() + ", Salida: " + flight.getDepartureAirport() + ", Llegada: " + flight.getArrivalAirport())
                .toArray(String[]::new)));
        }
    }
}
