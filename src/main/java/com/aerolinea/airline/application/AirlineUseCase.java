package com.aerolinea.airline.application;

import java.util.List;

import com.aerolinea.airline.domain.entity.Airline;
import com.aerolinea.airline.domain.service.AirlineService;

public class AirlineUseCase {
    private final AirlineService airlineService;

    public AirlineUseCase(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    public List<Airline> getAllAirlines(){
        return airlineService.getAllAirlines();
    }
}
