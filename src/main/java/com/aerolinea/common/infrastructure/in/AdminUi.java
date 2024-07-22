package com.aerolinea.common.infrastructure.in;

import javax.swing.*;

import com.aerolinea.airline.domain.service.AirlineService;
import com.aerolinea.model.domain.service.ModelService;
//import com.aerolinea.plane.application.PlaneUseCase;
import com.aerolinea.plane.domain.service.PlaneService;
import com.aerolinea.plane.infrastructure.in.PlaneDeleteUi;
import com.aerolinea.plane.infrastructure.in.PlaneDetailsUi;
import com.aerolinea.plane.infrastructure.in.PlaneRegisterUi;
import com.aerolinea.plane.infrastructure.in.PlaneUpdateUi;
import com.aerolinea.status.domain.service.StatusService;

import java.awt.*;

public class AdminUi {

    private final PlaneService planeService;
    private final AirlineService airlineService;
    private final ModelService modelService;
    private final StatusService statusService;

    

    public AdminUi(PlaneService planeService, AirlineService airlineService, ModelService modelService,
            StatusService statusService) {
        this.planeService = planeService;
        this.airlineService = airlineService;
        this.modelService = modelService;
        this.statusService = statusService;
    }

    public void showAdminUi() {
        JFrame frame = new JFrame("Airline Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs for different categories
        tabbedPane.addTab("Aviones", createPlanePanel());
        tabbedPane.addTab("Tripulación", createCrewPanel());
        tabbedPane.addTab("Clientes", createClientPanel());
        tabbedPane.addTab("Reservas", createReservationPanel());
        tabbedPane.addTab("Mantenimiento", createMaintenancePanel());
        tabbedPane.addTab("Aeropuertos", createAirportPanel());
        tabbedPane.addTab("Trayectos", createRoutePanel());
        tabbedPane.addTab("Tarifas", createFarePanel());
        tabbedPane.addTab("Documentos", createDocumentPanel());

        // Add the tabbed pane to the frame
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    private JPanel createPlanePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JButton registerButton = createMenuButton("Registrar Avión");
        registerButton.addActionListener(e -> {
            PlaneRegisterUi planeRegisterUi = new PlaneRegisterUi(planeService, airlineService, modelService, statusService);
            planeRegisterUi.showPlaneRegisterUi();
        });
        panel.add(registerButton);
        // BOTON DE ACTUALIZAR
        JButton updateButton = createMenuButton("Actualizar Información de Avión");
        updateButton.addActionListener(e -> {
            System.out.println(" Actulizar informacion de avion");
            PlaneUpdateUi planeUpdateUi = new PlaneUpdateUi(planeService, airlineService, modelService, statusService);
            planeUpdateUi.showPlaneUpdateUi();
        });
        panel.add(updateButton);
        // 
        // BOTON DE ELIMINAR 
        JButton deleteButton = createMenuButton("Eliminar Avión");
        deleteButton.addActionListener(e -> {
           // System.out.println(" eliminar  avion");
            PlaneDeleteUi planeDeleteUi = new PlaneDeleteUi(planeService);
            planeDeleteUi.showPlaneDeleteUi();
        });
        panel.add(deleteButton);
        // BOTON DE CONSULTAR INFORMACION: 
        JButton getButton = createMenuButton("Consultar Información de Avión");
        getButton.addActionListener(e -> {
            //System.out.println(" Consultar informacion de avion");
            PlaneDetailsUi planeDetailsUi = new PlaneDetailsUi(planeService, airlineService, modelService, statusService);
            planeDetailsUi.showPlaneDetailsUi();
        });
        panel.add(getButton);
        //
        return panel;
    }

    private static JPanel createCrewPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.add(createMenuButton("Asignar Tripulación"));
        panel.add(createMenuButton("Consultar Asignación de Tripulación"));
        return panel;
    }

    private static JPanel createClientPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.add(createMenuButton("Registrar Cliente"));
        panel.add(createMenuButton("Consultar Información de Cliente"));
        panel.add(createMenuButton("Actualizar Información de Cliente"));
        return panel;
    }

    private static JPanel createReservationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.add(createMenuButton("Crear Reserva de Vuelo"));
        panel.add(createMenuButton("Consultar Reserva de Vuelo"));
        panel.add(createMenuButton("Eliminar Reserva de Vuelo"));
        return panel;
    }

    private static JPanel createMaintenancePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.add(createMenuButton("Registrar Revisión de Mantenimiento"));
        panel.add(createMenuButton("Consultar Historial de Revisiones de Avión"));
        panel.add(createMenuButton("Actualizar Información de Revisión"));
        panel.add(createMenuButton("Eliminar Revisión de Mantenimiento"));
        return panel;
    }

    private static JPanel createAirportPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.add(createMenuButton("Registrar Aeropuerto"));
        panel.add(createMenuButton("Consultar Información de Aeropuerto"));
        panel.add(createMenuButton("Actualizar Información de Aeropuerto"));
        panel.add(createMenuButton("Eliminar Aeropuerto"));
        return panel;
    }

    private static JPanel createRoutePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.add(createMenuButton("Consultar Información de Trayecto"));
        panel.add(createMenuButton("Actualizar Información de Trayecto"));
        panel.add(createMenuButton("Eliminar Trayecto"));
        panel.add(createMenuButton("Consultar Escalas de un Trayecto"));
        panel.add(createMenuButton("Actualizar Información de Escala"));
        panel.add(createMenuButton("Eliminar Escala"));
        return panel;
    }

    private static JPanel createFarePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.add(createMenuButton("Registrar Tarifa de Vuelo"));
        panel.add(createMenuButton("Consultar Tarifa de Vuelo"));
        panel.add(createMenuButton("Actualizar Información de Tarifa de Vuelo"));
        panel.add(createMenuButton("Eliminar Tarifa de Vuelo"));
        return panel;
    }

    private static JPanel createDocumentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.add(createMenuButton("Registrar Tipo de Documento"));
        panel.add(createMenuButton("Consultar Tipo de Documento"));
        panel.add(createMenuButton("Actualizar Tipo de Documento"));
        panel.add(createMenuButton("Eliminar Tipo de Documento"));
        return panel;
    }

    private static JButton createMenuButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 50));
        return button;
    }
}
