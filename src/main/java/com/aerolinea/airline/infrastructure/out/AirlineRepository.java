package com.aerolinea.airline.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aerolinea.airline.domain.entity.Airline;
import com.aerolinea.airline.domain.service.AirlineService;
import com.aerolinea.common.infrastructure.config.DatabaseConfig;

public class AirlineRepository implements AirlineService {

    @Override
    public List<Airline> getAllAirlines() {
         String sql = "SELECT id, name FROM airlines";
        List<Airline> airlines = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Airline airline = new Airline();
                airline.setId(resultSet.getInt("id"));
                airline.setName(    resultSet.getString("name"));
                airlines.add(airline);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airlines;
    }

}
