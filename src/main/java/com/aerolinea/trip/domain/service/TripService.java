package com.aerolinea.trip.domain.service;

import java.util.List;

import com.aerolinea.trip.domain.entity.Trip;

public interface TripService {

    List<Trip> getAllTrips();
    void updateTrip(Trip trip, int originalId);
    void deleteTrip(int tripId);
}
