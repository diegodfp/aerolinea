package com.aerolinea.common.infrastructure.in;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.aerolinea.airline.domain.service.AirlineService;
import com.aerolinea.model.domain.service.ModelService;
import com.aerolinea.plane.domain.service.PlaneService;
import com.aerolinea.status.domain.service.StatusService;
import com.aerolinea.users.domain.service.UserLoginUseCase;
import com.aerolinea.users.domain.service.UserService;

public class MainUi {

     private final UserLoginUseCase loginUseCase;
     private final UserService userService;
     private final PlaneService planeService;
     private final AirlineService airlineService;
     private final StatusService statusService;
     private final ModelService modelService;
    private JFrame frame; // Variable de instancia
   

    

    public MainUi(UserLoginUseCase loginUseCase, UserService userService, PlaneService planeService,
            AirlineService airlineService,  ModelService modelService, StatusService statusService) {
        this.loginUseCase = loginUseCase;
        this.userService = userService;
        this.planeService = planeService;
        this.airlineService = airlineService;
        this.statusService = statusService;
        this.modelService = modelService;
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
        SignInUi signInUi = new SignInUi(userService, loginUseCase, planeService, airlineService, modelService, statusService);
        signInUi.showSignIn();
    }

    private void showLoginForm() {
        // Crear y mostrar el formulario de login
        LoginUi loginUi = new LoginUi(loginUseCase, planeService, airlineService , modelService, statusService);
        loginUi.showLogin();
    }
}
