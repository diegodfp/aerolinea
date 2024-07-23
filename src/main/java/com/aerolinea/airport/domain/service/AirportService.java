package com.aerolinea.airport.domain.service;

import java.util.List;

import com.aerolinea.airport.domain.entity.Airport;


public interface AirportService {
    void createAirport(Airport airport);

    Airport findAirportById(String id);

    void updateAirport(Airport airportUpdate, String id);

    void deleteAirport(String id);

    List<Airport> getAllAirports();

    boolean isAirportIdExists(String id); 
}
