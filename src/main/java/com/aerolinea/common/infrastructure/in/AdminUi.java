package com.aerolinea.common.infrastructure.in;

import javax.swing.*;

import com.aerolinea.airline.domain.service.AirlineService;
import com.aerolinea.airport.domain.service.AirportService;
import com.aerolinea.airport.infrastructure.in.AirportRegisterUi;
import com.aerolinea.airport.infrastructure.in.AirportUpdateUi;
import com.aerolinea.city.domain.service.CityService;
import com.aerolinea.country.domain.service.CountryService;
import com.aerolinea.model.domain.service.ModelService;
//import com.aerolinea.plane.application.PlaneUseCase;
import com.aerolinea.plane.domain.service.PlaneService;
import com.aerolinea.plane.infrastructure.in.PlaneDeleteUi;
import com.aerolinea.plane.infrastructure.in.PlaneDetailsUi;
import com.aerolinea.plane.infrastructure.in.PlaneRegisterUi;
import com.aerolinea.plane.infrastructure.in.PlaneUpdateUi;
import com.aerolinea.status.domain.service.StatusService;
import com.aerolinea.tripCrew.domain.service.TripCrewService;
import com.aerolinea.tripCrew.infrastructure.in.AssignCrewToTripUi;
import com.aerolinea.tripCrew.infrastructure.in.DetailsCrewToTripUi;

import java.awt.*;

public class AdminUi {

    private final PlaneService planeService;
    private final AirlineService airlineService;
    private final ModelService modelService;
    private final StatusService statusService;
    private final TripCrewService tripCrewService;
    private final CountryService countryService;
    private final CityService cityService;
    private final AirportService airportService;

    public AdminUi(PlaneService planeService, AirlineService airlineService, ModelService modelService,
            StatusService statusService, TripCrewService tripCrewService, CountryService countryService,
            CityService cityService, AirportService airportService) {
        this.planeService = planeService;
        this.airlineService = airlineService;
        this.modelService = modelService;
        this.statusService = statusService;
        this.tripCrewService = tripCrewService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.airportService = airportService;
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
            PlaneRegisterUi planeRegisterUi = new PlaneRegisterUi(planeService, airlineService, modelService,
                    statusService);
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
            // System.out.println(" eliminar avion");
            PlaneDeleteUi planeDeleteUi = new PlaneDeleteUi(planeService);
            planeDeleteUi.showPlaneDeleteUi();
        });
        panel.add(deleteButton);
        // BOTON DE CONSULTAR INFORMACION:
        JButton getButton = createMenuButton("Consultar Información de Avión");
        getButton.addActionListener(e -> {
            // System.out.println(" Consultar informacion de avion");
            PlaneDetailsUi planeDetailsUi = new PlaneDetailsUi(planeService, airlineService, modelService,
                    statusService);
            planeDetailsUi.showPlaneDetailsUi();
        });
        panel.add(getButton);
        //
        return panel;
    }

    private JPanel createCrewPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        // BOTON PARA ASIGNAR TRIPULACION
        JButton assignCrewButton = createMenuButton("Asignar Tripulación");
        assignCrewButton.addActionListener(e -> {
            AssignCrewToTripUi assignCrewToTripUi = new AssignCrewToTripUi(tripCrewService);
            assignCrewToTripUi.showAssignCrewToTripUi();
        });
        panel.add(assignCrewButton);
        // BOTON DE CONSULTAR TRIPULACION
        JButton crewDetails = createMenuButton("Consultar Consultar Asignación de Tripulación");
        crewDetails.addActionListener(e -> {
            DetailsCrewToTripUi detailsCrewToTrip = new DetailsCrewToTripUi(tripCrewService);
            detailsCrewToTrip.showDetailsCrewToTripUi();
        });
        panel.add(crewDetails);
        // panel.add(createMenuButton("Consultar Asignación de Tripulación"));
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

    private JPanel createAirportPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        // boton de crear aeropuerto
        JButton addAirport = createMenuButton("Registrar Aeropuerto");
        addAirport.addActionListener(e -> {
            AirportRegisterUi airportRegisterUi = new AirportRegisterUi(airportService, countryService, cityService);
            airportRegisterUi.showAirportRegisterUi();
        });
        panel.add(addAirport);
        //

        // boton de consultar aeropuerto
        JButton getAirport = createMenuButton("Actualizar Informacion de Aeropuero");
        getAirport.addActionListener(e -> {
            AirportUpdateUi airportRegisterUi = new AirportUpdateUi(airportService, countryService, cityService);
            airportRegisterUi.showAirportUpdateUi();
        });
        panel.add(getAirport);
        //
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
