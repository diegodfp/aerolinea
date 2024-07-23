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
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.aerolinea.airport.domain.entity.Airport;
import com.aerolinea.airport.domain.service.AirportService;
import com.aerolinea.city.domain.entity.City;
import com.aerolinea.city.domain.service.CityService;
import com.aerolinea.country.domain.entity.Country;
import com.aerolinea.country.domain.service.CountryService;

public class AirportDetailsUi {

    private final AirportService airportService;
    private final CountryService countryService;
    private final CityService cityService;

    private Map<String, String> countryMap;
    private Map<String, String> cityMap;
    private Map<String, Airport> airportMap;

    public AirportDetailsUi(AirportService airportService, CountryService countryService, CityService cityService) {
        this.airportService = airportService;
        this.countryService = countryService;
        this.cityService = cityService;

        this.countryMap = new HashMap<>();
        this.cityMap = new HashMap<>();
        this.airportMap = new HashMap<>();
    }

    public void showAirportDetailsUi() {
        JFrame frame = new JFrame("Detalles del Aeropuerto");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        frame.add(panel);

        // Componentes de la interfaz
        JLabel selectAirportLabel = new JLabel("Seleccionar Aeropuerto:");
        JComboBox<String> airportComboBox = new JComboBox<>();

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        idField.setEditable(false);

        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField();
        nameField.setEditable(false);

        JLabel countryLabel = new JLabel("País:");
        JComboBox<String> countryComboBox = new JComboBox<>();
        countryComboBox.setEnabled(false);

        JLabel cityLabel = new JLabel("Ciudad:");
        JComboBox<String> cityComboBox = new JComboBox<>();
        cityComboBox.setEnabled(false);

        JButton backButton = new JButton("Regresar");

        // Cargar datos desde la base de datos
        List<Country> countries = countryService.getAllCountrys();
        for (Country country : countries) {
            countryComboBox.addItem(country.getName());
            countryMap.put(country.getName(), country.getId());
        }

        List<Airport> airports = airportService.getAllAirports();
        for (Airport airport : airports) {
            airportComboBox.addItem(airport.getId());
            airportMap.put(airport.getId(), airport);
        }

        // Acción al seleccionar un aeropuerto
        airportComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedId = (String) airportComboBox.getSelectedItem();
                Airport selectedAirport = airportMap.get(selectedId);

                if (selectedAirport != null) {
                    idField.setText(selectedAirport.getId());
                    nameField.setText(selectedAirport.getName());

                    // Cargar país y ciudad basados en la ciudad seleccionada
                    City city = cityService.getCityById(selectedAirport.getIdCity());
                    if (city != null) {
                        String countryId = city.getIdCountry();
                        Country country = countryService.getCountryById(countryId);
                        if (country != null) {
                            countryComboBox.setSelectedItem(country.getName());
                        }

                        cityComboBox.removeAllItems();
                        cityComboBox.addItem(city.getName());
                        cityMap.put(city.getName(), city.getId());
                        cityComboBox.setSelectedItem(city.getName());
                    }
                }
            }
        });

        // Añadir componentes al panel
        panel.add(selectAirportLabel);
        panel.add(airportComboBox);

        panel.add(idLabel);
        panel.add(idField);

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(countryLabel);
        panel.add(countryComboBox);

        panel.add(cityLabel);
        panel.add(cityComboBox);

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

    // private <T, E> String getKeyByValue(Map<String, T> map, E value) {
    //     for (Map.Entry<String, T> entry : map.entrySet()) {
    //         if (entry.getValue().equals(value)) {
    //             return entry.getKey();
    //         }
    //     }
    //     return null;
    // }
}
