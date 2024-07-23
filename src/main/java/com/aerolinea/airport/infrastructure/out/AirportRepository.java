package com.aerolinea.airport.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aerolinea.common.infrastructure.config.DatabaseConfig;
import com.aerolinea.airport.domain.entity.Airport;
import com.aerolinea.airport.domain.service.AirportService;

public class AirportRepository implements AirportService {

    @Override
    public void createAirport(Airport airport) {
        String sql = "INSERT INTO airports (id, name, idCity) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, airport.getId());
            statement.setString(2, airport.getName());
            statement.setString(3, airport.getIdCity());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Airport findAirportById(String id) {
        String sql = "SELECT id, name, idCity FROM airports WHERE id = ?";
        Airport airport = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    airport = new Airport();
                    airport.setId(resultSet.getString("id"));
                    airport.setName(resultSet.getString("name"));
                    airport.setIdCity(resultSet.getString("idCity"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airport;
    }

    @Override
    public void updateAirport(Airport airportUpdate, String originalId) {
        String sql = "UPDATE airports SET id = ?, name = ?, idCity = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, airportUpdate.getId());
            statement.setString(2, airportUpdate.getName());
            statement.setString(3, airportUpdate.getIdCity());
            statement.setString(4, originalId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAirport(String id) {
        String query = "DELETE FROM airports WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<>();
        String sql = "SELECT * FROM airports";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Airport airport = new Airport();
                airport.setId(resultSet.getString("id"));
                airport.setName(resultSet.getString("name"));
                airport.setIdCity(resultSet.getString("idCity"));
                airports.add(airport);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airports;
    }

    @Override
    public boolean isAirportIdExists(String id) {
        String sql = "SELECT COUNT(*) FROM airports WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
