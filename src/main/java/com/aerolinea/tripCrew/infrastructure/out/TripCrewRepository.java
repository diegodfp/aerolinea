package com.aerolinea.tripCrew.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aerolinea.common.infrastructure.config.DatabaseConfig;
import com.aerolinea.employee.domain.entity.Employee;
import com.aerolinea.tripCrew.domain.entity.TripConnectionInfo;
import com.aerolinea.tripCrew.domain.entity.TripCrew;
import com.aerolinea.tripCrew.domain.service.TripCrewService;

public class TripCrewRepository implements TripCrewService {

    @Override
    public void assignCrewToTrip(String employeeId, int flightConnectionId) {
        String sql = "INSERT INTO tripCrews (idEmployee, idConnection) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employeeId);
            statement.setInt(2, flightConnectionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TripCrew> getAllAssignedCrews() {
        List<TripCrew> crews = new ArrayList<>();
        String sql = "SELECT * FROM tripCrews";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                TripCrew crew = new TripCrew();
                crew.setIdEmployee(resultSet.getString("idEmployee"));
                crew.setIdConnection(resultSet.getInt("idConnection"));
                crews.add(crew);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crews;
    }

    @Override
    public List<Employee> getAvailableEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT id, name FROM employees";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getString("id"));
                employee.setName(resultSet.getString("name"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
public List<TripConnectionInfo> getAllFlightConnections() {
    List<TripConnectionInfo> connections = new ArrayList<>();
    String sql = "SELECT fc.id, fc.numConnection, fc.idTrip, t.date as tripDate, t.price as tripPrice, " +
                 "t.originAirport, t.destinationAirport, fc.idPlane, " +
                 "fc.departureAirport, fc.arrivalAirport, fc.departureTime, fc.arrivalTime, " +
                 "fc.orderNumber, fc.connectionType " +
                 "FROM flightConnections fc " +
                 "JOIN trips t ON fc.idTrip = t.id";
    try (Connection connection = DatabaseConfig.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            TripConnectionInfo info = new TripConnectionInfo();
            info.setConnectionId(resultSet.getInt("id"));
            info.setNumConnection(resultSet.getString("numConnection"));
            info.setTripId(resultSet.getInt("idTrip"));
            info.setTripDate(resultSet.getString("tripDate"));
            info.setTripPrice(resultSet.getDouble("tripPrice"));
            info.setOriginAirport(resultSet.getString("originAirport"));
            info.setDestinationAirport(resultSet.getString("destinationAirport"));
            info.setPlaneId(resultSet.getInt("idPlane"));
            info.setDepartureAirport(resultSet.getString("departureAirport"));
            info.setArrivalAirport(resultSet.getString("arrivalAirport"));
            info.setDepartureTime(resultSet.getString("departureTime"));
            info.setArrivalTime(resultSet.getString("arrivalTime"));
            info.setOrderNumber(resultSet.getInt("orderNumber"));
            info.setConnectionType(resultSet.getString("connectionType"));
            connections.add(info);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return connections;
}

    @Override
    public boolean isEmployeeAssignedToConnection(String employeeId, int connectionId) {
        String sql = "SELECT COUNT(*) FROM tripCrews WHERE idEmployee = ? and idConnection = ? ";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employeeId);
            statement.setInt(2, connectionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Employee> getAssignedEmployees(int flightConnectionId) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT id, name FROM employees e " +
                     "JOIN tripCrews tp ON tp.idEmployee = e.id " +
                     "WHERE tp.idConnection = ?";
        
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, flightConnectionId);  // Mueve esta línea dentro del try-with-resources
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setId(resultSet.getString("id"));
                    employee.setName(resultSet.getString("name"));
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    
    

}
