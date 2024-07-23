package com.aerolinea.common.infrastructure.in;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.aerolinea.airline.domain.service.AirlineService;
import com.aerolinea.airport.domain.service.AirportService;
import com.aerolinea.city.domain.service.CityService;
import com.aerolinea.country.domain.service.CountryService;
import com.aerolinea.model.domain.service.ModelService;
//import com.aerolinea.plane.application.PlaneUseCase;
import com.aerolinea.plane.domain.service.PlaneService;
import com.aerolinea.status.domain.service.StatusService;
import com.aerolinea.tripCrew.domain.service.TripCrewService;
import com.aerolinea.users.domain.service.UserLoginUseCase;

public class LoginUi {

    private final UserLoginUseCase loginUseCase;
    private final PlaneService planeService;
    private final AirlineService airlineService;
    private final ModelService modelService;
    private final StatusService statusService;
    private final TripCrewService tripCrewService;
    private final CountryService countryService;
    private final CityService cityService;
    private final AirportService airportService;
    



    public LoginUi(UserLoginUseCase loginUseCase, PlaneService planeService, AirlineService airlineService,
            ModelService modelService, StatusService statusService, TripCrewService tripCrewService,
            CountryService countryService, CityService cityService, AirportService airportService) {
        this.loginUseCase = loginUseCase;
        this.planeService = planeService;
        this.airlineService = airlineService;
        this.modelService = modelService;
        this.statusService = statusService;
        this.tripCrewService = tripCrewService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.airportService = airportService;
    }

    public void showLogin() {
        // Crear y configurar la ventana principal (JFrame)
        JFrame frame = new JFrame("Login Usuario");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel para colocar los componentes
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, frame);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JLabel messageLabel = new JLabel("");
        messageLabel.setBounds(10, 110, 300, 25);
        panel.add(messageLabel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = userText.getText();
                String contrasena = new String(passwordText.getPassword());

                boolean loginExitoso = loginUseCase.authenticateUser(usuario, contrasena);

                if (loginExitoso) {
                    messageLabel.setText("Login exitoso!");
                    String rol = loginUseCase.obtenerRolUsuario(usuario);
                    System.out.println("Rol del usuario: " + rol);

                    if ("administrador".equals(rol)) {
                        System.out.println(" Ingreso Como Administrador");
                        frame.dispose(); // Cerrar la ventana actual
                        AdminUi adminUi = new AdminUi(planeService, airlineService, modelService, statusService, tripCrewService, countryService, cityService, airportService); // Crear instancia de AdminUi
                        adminUi.showAdminUi(); // Mostrar la ventana de administrador
                    } else if ("Agente de ventas".equals(rol)) {
                        // Lógica para vista de agente de ventas
                    } else if ("Técnico de mantenimiento".equals(rol)) {
                        // Lógica para vista de técnico de mantenimiento
                    } else if ("Cliente".equals(rol)) {
                        // Lógica para vista de cliente
                    }
                } else {
                    messageLabel.setText("Usuario o contraseña incorrectos.");
                }
            }
        });
    }
}
