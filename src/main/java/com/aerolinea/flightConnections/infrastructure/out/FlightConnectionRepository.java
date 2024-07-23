package com.aerolinea.flightConnections.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aerolinea.common.infrastructure.config.DatabaseConfig;
import com.aerolinea.flightConnections.domain.entity.FlightConnection;
import com.aerolinea.flightConnections.domain.service.FlightConnectionService;

public class FlightConnectionRepository implements FlightConnectionService {

    @Override
    public List<FlightConnection> getConnectionsByTripId(int tripId) {
        String sql = "SELECT * FROM flightConnections WHERE idTrip = ?";
        List<FlightConnection> connections = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, tripId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                FlightConnection flightConnection = new FlightConnection(
                        resultSet.getInt("id"),
                        resultSet.getString("numConnection"),
                        resultSet.getInt("idTrip"),
                        resultSet.getInt("idPlane"),
                        resultSet.getString("departureAirport"),
                        resultSet.getString("arrivalAirport"),
                        resultSet.getTimestamp("departureTime").toLocalDateTime(),
                        resultSet.getTimestamp("arrivalTime").toLocalDateTime(),
                        resultSet.getInt("orderNumber"),
                        FlightConnection.ConnectionType.valueOf(resultSet.getString("connectionType")));
                connections.add(flightConnection);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connections;
    }

    @Override
    public List<Integer> getAllTripIds() {
        String sql = "SELECT DISTINCT idTrip FROM flightConnections";
        List<Integer> tripIds = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                tripIds.add(resultSet.getInt("idTrip"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tripIds;
    }

    @Override
    public void updateFlightConnection(FlightConnection flightConnection) {
        String sql = "UPDATE flightConnections SET numConnection = ?, idTrip = ?, idPlane = ?, departureAirport = ?, arrivalAirport = ?, departureTime = ?, arrivalTime = ?, orderNumber = ?, connectionType = ? WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, flightConnection.getNumConnection());
            statement.setInt(2, flightConnection.getIdTrip());
            statement.setInt(3, flightConnection.getIdPlane());
            statement.setString(4, flightConnection.getDepartureAirport());
            statement.setString(5, flightConnection.getArrivalAirport());
            statement.setTimestamp(6, java.sql.Timestamp.valueOf(flightConnection.getDepartureTime()));
            statement.setTimestamp(7, java.sql.Timestamp.valueOf(flightConnection.getArrivalTime()));
            statement.setInt(8, flightConnection.getOrderNumber());
            statement.setString(9, flightConnection.getConnectionType().toString());
            statement.setInt(10, flightConnection.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFlightConnection(int connectionId) {
        String sql = "DELETE FROM flightConnections WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, connectionId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
