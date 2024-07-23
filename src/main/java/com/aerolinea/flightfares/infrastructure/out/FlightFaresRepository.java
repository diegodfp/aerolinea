package com.aerolinea.flightfares.infrastructure.out;

import com.aerolinea.common.infrastructure.config.DatabaseConfig;
import com.aerolinea.flightfares.domain.entity.FlightFares;
import com.aerolinea.flightfares.domain.service.FlightFaresService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class FlightFaresRepository implements FlightFaresService {

    @Override
    public void createFlightFare(FlightFares flightFares) {
        String sql = "INSERT INTO flightFares (description, details, value) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, flightFares.getDescription());
            statement.setString(2, flightFares.getDetails());
            statement.setDouble(3, flightFares.getValue());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    flightFares.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FlightFares findFlightFares(int id) {
        String sql = "SELECT id, description, details, value FROM flightFares WHERE id = ?";
        FlightFares flightFares = null;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    flightFares = new FlightFares();
                    flightFares.setId(resultSet.getInt("id"));
                    flightFares.setDescription(resultSet.getString("description"));
                    flightFares.setDetails(resultSet.getString("details"));
                    flightFares.setValue(resultSet.getDouble("value"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flightFares;
    }

    @Override
    public void updateFlightFare(FlightFares flightFares, int id) {
        String sql = "UPDATE flightFares SET description = ?, details = ?, value = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, flightFares.getDescription());
            statement.setString(2, flightFares.getDetails());
            statement.setDouble(3, flightFares.getValue());
            statement.setInt(4, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFlightFare(int id) {
        String sql = "DELETE FROM flightFares WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isFlightFareExists(int id) {
        String sql = "SELECT COUNT(*) FROM flightFares WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<FlightFares> getAllFlightFares() {
        List<FlightFares> flightFaresList = new ArrayList<>();
        String sql = "SELECT id, description, details, value FROM flightFares";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FlightFares flightFares = new FlightFares();
                flightFares.setId(resultSet.getInt("id"));
                flightFares.setDescription(resultSet.getString("description"));
                flightFares.setDetails(resultSet.getString("details"));
                flightFares.setValue(resultSet.getDouble("value"));
                flightFaresList.add(flightFares);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flightFaresList;
    }
}
