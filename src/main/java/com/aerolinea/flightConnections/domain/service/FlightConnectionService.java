package com.aerolinea.flightConnections.domain.service;

import java.util.List;

import com.aerolinea.flightConnections.domain.entity.FlightConnection;

public interface FlightConnectionService {
    List<FlightConnection> getConnectionsByTripId(int tripId);
    List<Integer> getAllTripIds();
    void updateFlightConnection(FlightConnection flightConnection);
    void deleteFlightConnection(int connectionId);
}
