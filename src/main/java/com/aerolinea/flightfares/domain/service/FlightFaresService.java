package com.aerolinea.flightfares.domain.service;

import java.util.List;

import com.aerolinea.flightfares.domain.entity.FlightFares;

public interface FlightFaresService {
    void createFlightFare(FlightFares flightFares);

    FlightFares findFlightFares(int id);

    void updateFlightFare(FlightFares flightFares, int id);

    void deleteFlightFare(int id);

    boolean isFlightFareExists(int id);
    
    List<FlightFares> getAllFlightFares(); 
}
