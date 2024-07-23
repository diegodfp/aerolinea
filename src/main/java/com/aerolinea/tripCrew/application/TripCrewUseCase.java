package com.aerolinea.tripCrew.application;

import java.util.List;

import com.aerolinea.employee.domain.entity.Employee;
import com.aerolinea.tripCrew.domain.entity.TripConnectionInfo;
import com.aerolinea.tripCrew.domain.entity.TripCrew;
import com.aerolinea.tripCrew.domain.service.TripCrewService;

public class TripCrewUseCase {
    private final TripCrewService tripCrewService;

    public TripCrewUseCase(TripCrewService tripCrewService) {
        this.tripCrewService = tripCrewService;
    }

    public void assignCrewToTrip(String employeeId, int flightConnectionId) {
        tripCrewService.assignCrewToTrip(employeeId, flightConnectionId);
    }

    public List<TripCrew> getAllAssignedCrews() {
        return tripCrewService.getAllAssignedCrews();
    }

    public List<Employee> getAvailableEmployees() {
        return tripCrewService.getAvailableEmployees();
    }

    public List<TripConnectionInfo> getAllFlightConnections() {
        return tripCrewService.getAllFlightConnections();
    }

    public List<Employee> getAssignedEmployees(int flightConnectionId){
        return tripCrewService.getAssignedEmployees(flightConnectionId);
    }

}
