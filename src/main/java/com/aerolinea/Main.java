package com.aerolinea;


import com.aerolinea.airline.domain.service.AirlineService;
import com.aerolinea.airline.infrastructure.out.AirlineRepository;
import com.aerolinea.common.infrastructure.in.MainUi;
import com.aerolinea.model.domain.service.ModelService;
import com.aerolinea.model.infrastructure.out.ModelRepository;
import com.aerolinea.plane.domain.service.PlaneService;
import com.aerolinea.plane.infrastructure.out.PlaneRepository;
import com.aerolinea.status.domain.service.StatusService;
import com.aerolinea.status.infrastructure.out.StatusRepository;
import com.aerolinea.tripCrew.application.TripCrewUseCase;
import com.aerolinea.tripCrew.infrastructure.in.AssignCrewToTripUi;
import com.aerolinea.tripCrew.infrastructure.out.TripCrewRepository;
//  import com.aerolinea.users.application.UserUseCase;
import com.aerolinea.users.domain.service.UserLoginUseCase;
import com.aerolinea.users.domain.service.UserService;
import com.aerolinea.users.infrastructure.out.LoginUiAdapter;
import com.aerolinea.users.infrastructure.out.UserRepository;

public class Main {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserLoginUseCase loginUseCase = new LoginUiAdapter(userRepository, userRepository);
        UserService userService = new UserRepository();
        PlaneService planeService = new PlaneRepository();
        AirlineService airlineService = new AirlineRepository();
        ModelService modelService = new ModelRepository();
        StatusService statusService = new StatusRepository();
        MainUi mainWindow = new MainUi(loginUseCase,userService, planeService, airlineService, modelService, statusService);
        mainWindow.showMainUi();


    }
}