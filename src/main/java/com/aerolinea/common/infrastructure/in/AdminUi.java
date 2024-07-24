package com.aerolinea.common.infrastructure.in;

import javax.swing.*;

import com.aerolinea.airline.domain.service.AirlineService;
import com.aerolinea.airport.domain.service.AirportService;
import com.aerolinea.airport.infrastructure.in.AirportDeleteUi;
import com.aerolinea.airport.infrastructure.in.AirportDetailsUi;
import com.aerolinea.airport.infrastructure.in.AirportRegisterUi;
import com.aerolinea.airport.infrastructure.in.AirportUpdateUi;
import com.aerolinea.city.domain.service.CityService;
import com.aerolinea.country.domain.service.CountryService;
import com.aerolinea.customer.domain.service.CustomerService;
import com.aerolinea.customer.infrastructure.in.CustomerDetailsUi;
import com.aerolinea.customer.infrastructure.in.CustomerRegisterUi;
import com.aerolinea.customer.infrastructure.in.CustomerUpdateUi;
import com.aerolinea.documenttype.domain.service.DocumenttypeService;
import com.aerolinea.documenttype.infrastructure.in.DocumenttypeDeleteUi;
import com.aerolinea.documenttype.infrastructure.in.DocumenttypeDetailsUi;
import com.aerolinea.documenttype.infrastructure.in.DocumenttypeRegisterUi;
import com.aerolinea.documenttype.infrastructure.in.DocumenttypeUpdateUi;
import com.aerolinea.flightConnections.domain.service.FlightConnectionService;
import com.aerolinea.flightConnections.infrastructure.in.FlightConnectionDeleteUi;
import com.aerolinea.flightConnections.infrastructure.in.FlightConnectionDetailsUi;
import com.aerolinea.flightConnections.infrastructure.in.FlightConnectionUpdateUi;
import com.aerolinea.model.domain.service.ModelService;
//import com.aerolinea.plane.application.PlaneUseCase;
import com.aerolinea.plane.domain.service.PlaneService;
import com.aerolinea.plane.infrastructure.in.PlaneDeleteUi;
import com.aerolinea.plane.infrastructure.in.PlaneDetailsUi;
import com.aerolinea.plane.infrastructure.in.PlaneRegisterUi;
import com.aerolinea.plane.infrastructure.in.PlaneUpdateUi;
import com.aerolinea.status.domain.service.StatusService;
import com.aerolinea.trip.domain.service.TripService;
import com.aerolinea.trip.infrastructure.in.TripDeleteUi;
import com.aerolinea.trip.infrastructure.in.TripDetailsUi;
import com.aerolinea.trip.infrastructure.in.TripUpdateUi;
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
    private final TripService tripService;
    private final FlightConnectionService flightConnectionService;
    private final CustomerService customerService;
    private final DocumenttypeService documenttypeService;



    public AdminUi(PlaneService planeService, AirlineService airlineService, ModelService modelService,
            StatusService statusService, TripCrewService tripCrewService, CountryService countryService,
            CityService cityService, AirportService airportService, TripService tripService,
            FlightConnectionService flightConnectionService, CustomerService customerService,
            DocumenttypeService documenttypeService) {
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

    private JPanel createClientPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        JButton registerButton = createMenuButton("Registrar Cliente");
        registerButton.addActionListener(e -> {
            CustomerRegisterUi customerRegisterUi = new CustomerRegisterUi(customerService, documenttypeService);
            customerRegisterUi.showCustomerRegisterUi();
        });
        panel.add(registerButton);
        JButton consultButton = createMenuButton("Consultar Información de Cliente");
        consultButton.addActionListener(e -> {
            CustomerDetailsUi customerDetailsUi = new CustomerDetailsUi(customerService);
            customerDetailsUi.showCustomerDetailsUi();
        });
        panel.add(consultButton);

        JButton updateButton = createMenuButton("Actualizar Información de Cliente");
        updateButton.addActionListener(e -> {
            CustomerUpdateUi customerUpdateUi = new CustomerUpdateUi(customerService, documenttypeService);
            customerUpdateUi.showCustomerUpdateUi();
        });
        panel.add(updateButton);
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

        // boton de actualizar aeropuerto
        JButton updateAirport = createMenuButton("Actualizar Informacion de Aeropuero");
        updateAirport.addActionListener(e -> {
            AirportUpdateUi airportRegisterUi = new AirportUpdateUi(airportService, countryService, cityService);
            airportRegisterUi.showAirportUpdateUi();
        });
        panel.add(updateAirport);
        //

         // boton de consultar aeropuerto
         JButton getAirport = createMenuButton("Consultar Informacion de Aeropuero");
         getAirport.addActionListener(e -> {
             AirportDetailsUi airportDetailsUi = new AirportDetailsUi(airportService, countryService, cityService);
             airportDetailsUi.showAirportDetailsUi();
         });
         panel.add(getAirport);
         //

         // boton de eliminar aeropuerto
         JButton deleteAirport = createMenuButton("Eliminar Aeropuero");
         deleteAirport.addActionListener(e -> {
             AirportDeleteUi airportDeleteUi = new AirportDeleteUi(airportService);
             airportDeleteUi.showAirportDeleteUi();
         });
         panel.add(deleteAirport);
         //

        //panel.add(createMenuButton("Eliminar Aeropuerto"));
        return panel;
    }

    private  JPanel createRoutePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

          // boton de crear aeropuerto
          JButton getTrayecto = createMenuButton("Consultar Información de Trayecto");
          getTrayecto.addActionListener(e -> {
              TripDetailsUi tripDetailsUi = new TripDetailsUi(tripService);
              tripDetailsUi.showTripDetailsUi();
          });
          panel.add(getTrayecto);
          //
          // boton de actualizar  Trayecto
          JButton updateTrayecto = createMenuButton("Actualizar Información de Trayecto");
          updateTrayecto.addActionListener(e -> {
              TripUpdateUi tripUpdateUi = new TripUpdateUi(tripService , airportService);
              tripUpdateUi.showTripUpdateUi();
          });
          panel.add(updateTrayecto);
          //

          // boton de Eliminar  Trayecto
          JButton deleteTrayecto = createMenuButton("Eliminar Trayecto");
          deleteTrayecto.addActionListener(e -> {
              TripDeleteUi tripDeleteUi = new TripDeleteUi(tripService);
              tripDeleteUi.showTripDeleteUi();
          });
          panel.add(deleteTrayecto);
          //
          // boton de Consultar Escalas de  Trayecto
          JButton consultEscalasTrayecto = createMenuButton("Consultar Escalas de un Trayecto");
          consultEscalasTrayecto.addActionListener(e -> {
              FlightConnectionDetailsUi flightConnectionDetailsUi = new FlightConnectionDetailsUi(flightConnectionService);
              flightConnectionDetailsUi.showFlightConnectionDetailsUi();
          });
          panel.add(consultEscalasTrayecto);
          //
          // boton de actualizar Escalas de  Trayecto
          JButton updateFlightConnection = createMenuButton("Actualizar Escalas de un Trayecto");
          updateFlightConnection.addActionListener(e -> {
              FlightConnectionUpdateUi flightConnectionUpdateUi = new FlightConnectionUpdateUi(flightConnectionService);
              flightConnectionUpdateUi.showFlightConnectionUpdateUi();
          });
          panel.add(updateFlightConnection);
          //
              // boton de eliminar Escalas de  Trayecto
              JButton deleteFlightConnection = createMenuButton("Eliminar Escalas de un Trayecto");
              deleteFlightConnection.addActionListener(e -> {
                  FlightConnectionDeleteUi flightConnectionDeleteUi = new FlightConnectionDeleteUi(flightConnectionService);
                  flightConnectionDeleteUi.showFlightConnectionDeleteUi();
              });
              panel.add(deleteFlightConnection);
              //
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

    private JPanel createDocumentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JButton registerButton = createMenuButton("Registrar Tipo de Documento");
        registerButton.addActionListener(e -> {
            DocumenttypeRegisterUi documenttypeRegisterUi = new DocumenttypeRegisterUi(documenttypeService);
            documenttypeRegisterUi.showDocumenttypeRegisterUi();
        });
        panel.add(registerButton);

        JButton consultButton = createMenuButton("Consultar Tipo de Documento");
        consultButton.addActionListener(e -> {
            DocumenttypeDetailsUi documenttypeDetailsUi = new DocumenttypeDetailsUi(documenttypeService);
            documenttypeDetailsUi.showDocumenttypeDetailsUi();
        });
        panel.add(consultButton);

        JButton updateButton = createMenuButton("Actualizar Tipo de Documento");
        updateButton.addActionListener(e -> {
            DocumenttypeUpdateUi documenttypeUpdateUi = new DocumenttypeUpdateUi(documenttypeService);
            documenttypeUpdateUi.showDocumenttypeUpdateUi();
        });
        panel.add(updateButton);

        JButton deleteButton = createMenuButton("Eliminar Tipo de Documento");
        deleteButton.addActionListener(e -> {
            DocumenttypeDeleteUi documenttypeDeleteUi = new DocumenttypeDeleteUi(documenttypeService);
            documenttypeDeleteUi.showDocumenttypeDeleteUi();
        });
        panel.add(deleteButton);

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
