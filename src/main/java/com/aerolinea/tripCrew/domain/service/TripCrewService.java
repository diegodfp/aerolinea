package com.aerolinea.tripCrew.domain.service;

import java.util.List;

import com.aerolinea.employee.domain.entity.Employee;
import com.aerolinea.tripCrew.domain.entity.TripConnectionInfo;
import com.aerolinea.tripCrew.domain.entity.TripCrew;

public interface TripCrewService {
    void assignCrewToTrip(String employeeId, int flightConnectionId);
    List<TripCrew> getAllAssignedCrews();
    List<Employee> getAvailableEmployees();
    List<TripConnectionInfo> getAllFlightConnections();
    boolean isEmployeeAssignedToConnection(String employeeId, int connectionId);
    List<Employee> getAssignedEmployees(int flightConnectionId);

}
