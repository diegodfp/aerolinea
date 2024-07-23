package com.aerolinea.airport.application;

import java.util.List;

import com.aerolinea.airport.domain.service.AirportService;
import com.aerolinea.airport.domain.entity.Airport;

public class AirportUseCase {
    private final AirportService airportService;

    public AirportUseCase(AirportService airportService) {
        this.airportService = airportService;
    }

        public void createAirport(Airport airport){
        airportService.createAirport(airport);
    }

    public void updateAirport(Airport airportUpdate, String id){
        airportService.updateAirport(airportUpdate, id);
    }

    public void deleteAirport(String id){
        airportService.deleteAirport(id);
    }

    public Airport findAirportById(String id){
        return airportService.findAirportById(id);
    }

    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }
}
