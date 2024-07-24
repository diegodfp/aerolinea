package com.aerolinea.common.infrastructure.in;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.aerolinea.airline.domain.service.AirlineService;
import com.aerolinea.airport.domain.service.AirportService;
import com.aerolinea.city.domain.service.CityService;
import com.aerolinea.country.domain.service.CountryService;
import com.aerolinea.customer.domain.service.CustomerService;
import com.aerolinea.documenttype.domain.service.DocumenttypeService;
import com.aerolinea.flightConnections.domain.service.FlightConnectionService;
import com.aerolinea.model.domain.service.ModelService;
import com.aerolinea.plane.domain.service.PlaneService;
import com.aerolinea.status.domain.service.StatusService;
import com.aerolinea.trip.domain.service.TripService;
import com.aerolinea.tripCrew.domain.service.TripCrewService;
import com.aerolinea.users.domain.service.UserLoginUseCase;
import com.aerolinea.users.domain.service.UserService;

public class MainUi {

     private final UserLoginUseCase loginUseCase;
     private final UserService userService;
     private final PlaneService planeService;
     private final AirlineService airlineService;
     private final ModelService modelService;
     private final StatusService statusService;
     private final TripCrewService tripCrewService;
     private final CountryService countryService;
     private final CityService cityService;
     private final AirportService airportService;
     private final TripService tripService;
     private final FlightConnectionService flightConnectionService;
     private final CustomerService customerService;
     private final DocumenttypeService documenttypeService;
    private JFrame frame; // Variable de instancia
   
    

    public MainUi(UserLoginUseCase loginUseCase, UserService userService, PlaneService planeService,
            AirlineService airlineService, ModelService modelService, StatusService statusService,
            TripCrewService tripCrewService, CountryService countryService, CityService cityService,
            AirportService airportService, TripService tripService, FlightConnectionService flightConnectionService,
            CustomerService customerService, DocumenttypeService documenttypeService) {
        this.loginUseCase = loginUseCase;
        this.userService = userService;
        this.planeService = planeService;
        this.airlineService = airlineService;
        this.modelService = modelService;
        this.statusService = statusService;
        this.tripCrewService = tripCrewService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.airportService = airportService;
        this.tripService = tripService;
        this.flightConnectionService = flightConnectionService;
        this.customerService = customerService;
        this.documenttypeService = documenttypeService;
    }

    public void showMainUi() {
        // Crear y configurar la ventana principal (JFrame)
        frame = new JFrame("Bienvenido");
        frame.setSize(300, 200); // Establecer tamaño de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configurar cierre de la ventana

        // Crear un panel para colocar los componentes
        JPanel panel = new JPanel();
        frame.add(panel); // Agregar el panel al JFrame

        // Método para colocar los componentes en el panel
        placeComponents(panel);

        // Centrar el JFrame en la pantalla:
        frame.setLocationRelativeTo(null);

        // Hacer visible la ventana
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        // Establecer layout nulo para colocar los componentes manualmente
        panel.setLayout(null);

        // Botón para registrar usuario
        JButton registerButton = new JButton("Registrar Usuario");
        registerButton.setBounds(50, 30, 200, 25); // Establecer posición y tamaño
        panel.add(registerButton); // Agregar botón al panel

        // Botón para iniciar sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(50, 70, 200, 25); // Establecer posición y tamaño
        panel.add(loginButton); // Agregar botón al panel

        // ActionListener para el botón de registrar usuario
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cerrar la ventana actual
                showRegisterForm(); // Mostrar el formulario de registro
            }
        });

        // ActionListener para el botón de iniciar sesión
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cerrar la ventana actual
                showLoginForm(); // Mostrar el formulario de login
            }
        });
    }

    private void showRegisterForm() {
        SignInUi signInUi = new SignInUi(userService, loginUseCase, planeService, airlineService, modelService, statusService, tripCrewService, countryService, cityService, airportService, tripService, flightConnectionService, customerService, documenttypeService);
        signInUi.showSignIn();
    }

    private void showLoginForm() {
        // Crear y mostrar el formulario de login
        LoginUi loginUi = new LoginUi(loginUseCase, planeService, airlineService , modelService, statusService, tripCrewService, countryService, cityService, airportService, tripService, flightConnectionService, customerService,documenttypeService);
        loginUi.showLogin();
    }
}
