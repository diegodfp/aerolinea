package com.aerolinea.trip.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aerolinea.common.infrastructure.config.DatabaseConfig;
import com.aerolinea.trip.domain.entity.Trip;
import com.aerolinea.trip.domain.service.TripService;

public class TripRepository implements TripService {

    @Override
    public List<Trip> getAllTrips() {
        String sql = "SELECT id, date, price,originAirport ,destinationAirport  FROM trips";
        List<Trip> trips = new ArrayList<>();

          try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Trip trip = new Trip();
                trip.setId(resultSet.getInt("id"));
                trip.setDate(resultSet.getDate("date"));
                trip.setPrice(resultSet.getDouble("price"));
                trip.setOriginAirport(resultSet.getString("originAirport"));
                trip.setDestinationAirport(resultSet.getString("destinationAirport"));
                trips.add(trip);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }   

    @Override
    public void updateTrip(Trip trip, int originalId) {
        String sql = "UPDATE trips SET date = ?, price = ?, originAirport = ?, destinationAirport = ? WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDate(1, trip.getDate());
            statement.setDouble(2, trip.getPrice());
            statement.setString(3, trip.getOriginAirport());
            statement.setString(4, trip.getDestinationAirport());
            statement.setInt(5, originalId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteTrip(int tripId) {
        String sql = "DELETE FROM trips WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, tripId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
