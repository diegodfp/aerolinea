package com.aerolinea;


import com.aerolinea.airline.domain.service.AirlineService;
import com.aerolinea.airline.infrastructure.out.AirlineRepository;
import com.aerolinea.airport.domain.service.AirportService;
import com.aerolinea.airport.infrastructure.out.AirportRepository;
import com.aerolinea.city.domain.service.CityService;
import com.aerolinea.city.infrastructure.out.CityRepository;
import com.aerolinea.common.infrastructure.in.MainUi;
import com.aerolinea.country.domain.service.CountryService;
import com.aerolinea.country.infrastructure.out.CountryRepositoriy;
import com.aerolinea.flightConnections.domain.service.FlightConnectionService;
import com.aerolinea.flightConnections.infrastructure.out.FlightConnectionRepository;
import com.aerolinea.flightfares.domain.service.FlightFaresService;
import com.aerolinea.flightfares.infrastructure.out.FlightFaresRepository;
import com.aerolinea.model.domain.service.ModelService;
import com.aerolinea.model.infrastructure.out.ModelRepository;
import com.aerolinea.plane.domain.service.PlaneService;
import com.aerolinea.plane.infrastructure.out.PlaneRepository;
import com.aerolinea.status.domain.service.StatusService;
import com.aerolinea.status.infrastructure.out.StatusRepository;
import com.aerolinea.trip.domain.service.TripService;
import com.aerolinea.trip.infrastructure.out.TripRepository;
//import com.aerolinea.tripCrew.application.TripCrewUseCase;
import com.aerolinea.tripCrew.domain.service.TripCrewService;
//import com.aerolinea.tripCrew.infrastructure.in.AssignCrewToTripUi;
import com.aerolinea.tripCrew.infrastructure.out.TripCrewRepository;
//  import com.aerolinea.users.application.UserUseCase;
import com.aerolinea.users.domain.service.UserLoginUseCase;
import com.aerolinea.users.domain.service.UserService;
import com.aerolinea.users.infrastructure.out.LoginUiAdapter;
import com.aerolinea.users.infrastructure.out.UserRepository;
import com.aerolinea.customer.domain.service.CustomerService;
import com.aerolinea.customer.infrastructure.out.CustomerRepository;
import com.aerolinea.documenttype.domain.service.DocumenttypeService;
import com.aerolinea.documenttype.infrastructure.out.DocumenttypeRepository;

public class Main {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserLoginUseCase loginUseCase = new LoginUiAdapter(userRepository, userRepository);
        UserService userService = new UserRepository();
        PlaneService planeService = new PlaneRepository();
        AirlineService airlineService = new AirlineRepository();
        ModelService modelService = new ModelRepository();
        StatusService statusService = new StatusRepository();
        TripCrewService tripCrewService = new TripCrewRepository();
        CountryService countryService = new CountryRepositoriy();
        CityService cityService = new CityRepository();
        AirportService airportService = new AirportRepository();
        TripService tripService = new TripRepository();
        FlightConnectionService flightConnectionService = new FlightConnectionRepository();
        CustomerService customerService = new CustomerRepository();
        DocumenttypeService documenttypeService = new DocumenttypeRepository();
        FlightFaresService flightFaresService = new FlightFaresRepository();
        MainUi mainWindow = new MainUi(loginUseCase,userService, planeService, airlineService,
         modelService ,statusService, tripCrewService, countryService, cityService, airportService, 
         tripService, flightConnectionService , customerService, documenttypeService,flightFaresService);
        mainWindow.showMainUi();


    }
}