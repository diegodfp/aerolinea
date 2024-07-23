package com.aerolinea.trip.application;

import java.util.List;

import com.aerolinea.trip.domain.entity.Trip;
import com.aerolinea.trip.domain.service.TripService;

public class TripUseCase {

    private final TripService tripService;

    public TripUseCase(TripService tripService) {
        this.tripService = tripService;
    }

    public List<Trip> getAllTrips(){
        return tripService.getAllTrips();
    }

    public void updateTrip(Trip trip, int originalId) {
        tripService.updateTrip(trip, originalId);
    }

    public void deleteTrip(int tripId) {
        tripService.deleteTrip(tripId);
    }

}
