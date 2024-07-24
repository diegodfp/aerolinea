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
import com.aerolinea.users.domain.entity.User;
import com.aerolinea.users.domain.service.UserLoginUseCase;
import com.aerolinea.users.domain.service.UserService;

public class SignInUi {

    private final UserService userService;
    private final UserLoginUseCase userLoginUseCase;
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

    

    public SignInUi(UserService userService, UserLoginUseCase userLoginUseCase, PlaneService planeService,
            AirlineService airlineService, ModelService modelService, StatusService statusService,
            TripCrewService tripCrewService, CountryService countryService, CityService cityService,
            AirportService airportService, TripService tripService, FlightConnectionService flightConnectionService,
            CustomerService customerService, DocumenttypeService documenttypeService) {
        this.userService = userService;
        this.userLoginUseCase = userLoginUseCase;
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

    public void showSignIn() {
        // Crear y configurar la ventana principal (JFrame)
        JFrame frame = new JFrame("Registrar Usuario");
        frame.setSize(400, 300); // Establecer tamaño de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configurar cierre de la ventana

        // Crear un panel para colocar los componentes
        JPanel panel = new JPanel();
        frame.add(panel); // Agregar el panel al JFrame

        // Método para colocar los componentes en el panel
        placeComponents(panel, frame);

        // Centrar el JFrame en la pantalla:
        frame.setLocationRelativeTo(null);

        // Hacer visible la ventana
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(100, 20, 165, 25);
        panel.add(nameText);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 50, 80, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(100, 50, 165, 25);
        panel.add(emailText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 80, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 80, 165, 25);
        panel.add(passwordText);

        JLabel roleLabel = new JLabel("Rol:");
        roleLabel.setBounds(10, 110, 80, 25);
        panel.add(roleLabel);

        String[] roles = { "Agente de ventas", "Técnico de mantenimiento", "Cliente" };
        JComboBox<String> roleList = new JComboBox<>(roles);
        roleList.setBounds(100, 110, 165, 25);
        panel.add(roleList);

        JButton registerButton = new JButton("Registrar");
        registerButton.setBounds(10, 150, 150, 25);
        panel.add(registerButton);

        JButton backButton = new JButton("Regresar");
        backButton.setBounds(170, 150, 150, 25);
        panel.add(backButton);

        JLabel messageLabel = new JLabel("");
        messageLabel.setBounds(10, 180, 300, 25);
        panel.add(messageLabel);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String email = emailText.getText();
                String password = new String(passwordText.getPassword());
                int idRol = roleList.getSelectedIndex() + 2; // Mapeo de índice a idRol

                User user = new User();
                user.setNombre(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setIdRol(idRol);

                try {
                    userService.createUser(user);
                    messageLabel.setText("Usuario registrado exitosamente!");
                    Timer timer = new Timer(2000, new ActionListener() { // 2000 milisegundos = 2 segundos
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame.dispose(); // Cerrar la ventana actual correctamente
                            MainUi mainUi = new MainUi(userLoginUseCase, userService, planeService, airlineService,
                                    modelService, statusService, tripCrewService, countryService, cityService,
                                    airportService, tripService, flightConnectionService, customerService, documenttypeService); // Crear instancia de MainUi
                            mainUi.showMainUi(); // Mostrar la ventana principal
                        }
                    });
                    timer.setRepeats(false); // No se repite
                    timer.start();
                } catch (Exception ex) {
                    messageLabel.setText("Error al registrar usuario: " + ex.getMessage());
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cerrar la ventana actual
                MainUi mainUi = new MainUi(userLoginUseCase, userService, planeService, airlineService, modelService,
                        statusService, tripCrewService, countryService, cityService, airportService, tripService, flightConnectionService, customerService,documenttypeService); // Crear instancia
                                                                                                      // de MainUi
                mainUi.showMainUi(); // Mostrar la ventana principal
            }
        });

    }
}
