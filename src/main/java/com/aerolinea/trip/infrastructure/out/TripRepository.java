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
        String sql = "SELECT id, name FROM airlines";
        List<Trip> trips = new ArrayList<>();

          try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Trip trip = new Trip();
                trip.setId(resultSet.getInt("id"));
                trip.setDate(resultSet.getDate("date"));
                trip.setPrice(resultSet.getDouble("price"));
                trips.add(trip);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }   

}
