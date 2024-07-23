package com.aerolinea.airport.infrastructure.in;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aerolinea.airport.domain.entity.Airport;
import com.aerolinea.airport.domain.service.AirportService;
import com.aerolinea.city.domain.entity.City;
import com.aerolinea.city.domain.service.CityService;
import com.aerolinea.country.domain.entity.Country;
import com.aerolinea.country.domain.service.CountryService;

public class AirportRegisterUi {
    private final AirportService airportService;
    private final CountryService countryService;
    private final CityService cityService;

    private Map<String, String> countryMap;
    private Map<String, String> cityMap;

    public AirportRegisterUi(AirportService airportService, CountryService countryService, CityService cityService) {
        this.airportService = airportService;
        this.countryService = countryService;
        this.cityService = cityService;

        this.countryMap = new HashMap<>();
        this.cityMap = new HashMap<>();
    }

    public void showAirportRegisterUi() {
        JFrame frame = new JFrame("Registrar Aeropuerto");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        frame.add(panel);

        // Componentes de la interfaz
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField();

        JLabel countryLabel = new JLabel("País:");
        JComboBox<String> countryComboBox = new JComboBox<>();

        JLabel cityLabel = new JLabel("Ciudad:");
        JComboBox<String> cityComboBox = new JComboBox<>();

        JButton registerButton = new JButton("Registrar");

        JButton backButton = new JButton("Regresar");

        // Cargar países desde la base de datos
        List<Country> countries = countryService.getAllCountrys();
        for (Country country : countries) {
            countryComboBox.addItem(country.getName());
            countryMap.put(country.getName(), country.getId());
        }

        // Añadir oyente para cargar ciudades basadas en el país seleccionado
        countryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCountry = (String) countryComboBox.getSelectedItem();
                if (selectedCountry != null) {
                    String countryId = countryMap.get(selectedCountry);
                    List<City> cities = cityService.getAllCitys(countryId);
                    cityComboBox.removeAllItems();
                    cityMap.clear();
                    for (City city : cities) {
                        cityComboBox.addItem(city.getName());
                        cityMap.put(city.getName(), city.getId());
                    }
                }
            }
        });

        // Añadir componentes al panel
        panel.add(idLabel);
        panel.add(idField);

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(countryLabel);
        panel.add(countryComboBox);

        panel.add(cityLabel);
        panel.add(cityComboBox);

        panel.add(registerButton);
        panel.add(backButton);

        // Acción del botón registrar
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String cityId = cityMap.get((String) cityComboBox.getSelectedItem());

                Airport airport = new Airport();
                airport.setId(id);
                airport.setName(name);
                airport.setIdCity(cityId);

                airportService.createAirport(airport);

                JOptionPane.showMessageDialog(frame, "Aeropuerto registrado exitosamente");
                frame.dispose();
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
