package com.aerolinea.flightfares.application;

import java.util.List;

import com.aerolinea.flightfares.domain.entity.FlightFares;
import com.aerolinea.flightfares.domain.service.FlightFaresService;

public class FlightUseCase {
    private final FlightFaresService flightFaresService;

    public FlightUseCase(FlightFaresService flightFaresService) {
        this.flightFaresService = flightFaresService;
    }

    public void createFlightFare(FlightFares flightFares) {
        flightFaresService.createFlightFare(flightFares);
    }

    public FlightFares findFlightFares(int id) {
        return flightFaresService.findFlightFares(id);
    }

    public void updateFlightFare(FlightFares flightFares, int id) {
        flightFaresService.updateFlightFare(flightFares, id);
    }

    public void deleteFlightFare(int id) {
        flightFaresService.deleteFlightFare(id);
    }

    public List<FlightFares> execute() {
        return flightFaresService.getAllFlightFares();
    }

}
