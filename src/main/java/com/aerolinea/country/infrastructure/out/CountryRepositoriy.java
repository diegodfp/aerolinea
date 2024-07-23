package com.aerolinea.country.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aerolinea.common.infrastructure.config.DatabaseConfig;
import com.aerolinea.country.domain.entity.Country;
import com.aerolinea.country.domain.service.CountryService;

public class CountryRepositoriy implements CountryService {

    @Override
    public List<Country> getAllCountrys() {
        String sql = "SELECT id, name FROM countries";
        List<Country> countries = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Country country = new Country();
                country.setId(resultSet.getString("id"));
                country.setName(resultSet.getString("name"));
                countries.add(country);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    @Override
    public Country getCountryById(String countryId) {
        String sql = "SELECT id, name FROM countries WHERE id = ?";
        Country country = null;
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, countryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    country = new Country();
                    country.setId(resultSet.getString("id"));
                    country.setName(resultSet.getString("name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
    }

}
