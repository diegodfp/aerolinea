package com.aerolinea.flightConnections.application;

import java.util.List;

import com.aerolinea.flightConnections.domain.entity.FlightConnection;
import com.aerolinea.flightConnections.domain.service.FlightConnectionService;

public class FlightConnectionUseCase {
    private final FlightConnectionService flightConnectionService;

    public FlightConnectionUseCase(FlightConnectionService flightConnectionService) {
        this.flightConnectionService = flightConnectionService;
    }

    public List<FlightConnection> getConnectionsByTripId(int tripId) {
        return flightConnectionService.getConnectionsByTripId(tripId);
    }

    public void updateFlightConnection(FlightConnection flightConnection) {
        flightConnectionService.updateFlightConnection(flightConnection);
    }

    public void deleteFlightConnection(int connectionId) {
        flightConnectionService.deleteFlightConnection(connectionId);
    }
}
