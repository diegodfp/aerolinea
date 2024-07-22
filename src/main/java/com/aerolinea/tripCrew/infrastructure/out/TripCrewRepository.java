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
        String sql = "SELECT fc.id, t.id as tripId, a.name as airportName, p.plates as planePlates " +
                "FROM flightConnections fc " +
                "JOIN trips t ON fc.idTrip = t.id " +
                "JOIN airports a ON fc.idAirport = a.id " +
                "JOIN planes p ON fc.idPlan = p.id";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                TripConnectionInfo info = new TripConnectionInfo();
                info.setConnectionId(resultSet.getInt("id"));
                info.setTripId(resultSet.getInt("tripId"));
                info.setAirportName(resultSet.getString("airportName"));
                info.setPlanePlates(resultSet.getString("planePlates"));
                connections.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connections;
    }

}
