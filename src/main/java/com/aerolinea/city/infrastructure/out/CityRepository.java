package com.aerolinea.city.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aerolinea.city.domain.entity.City;
import com.aerolinea.city.domain.service.CityService;
import com.aerolinea.common.infrastructure.config.DatabaseConfig;

public class CityRepository implements CityService {

    @Override
    public List<City> getAllCitys(String idCountry) {
        String sql = "SELECT id, name , idCountry  FROM cities WHERE idCountry = ?";
        List<City> cities = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, idCountry); // Mueve esta línea dentro del try-with-resources
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    City city = new City();
                    city.setId(resultSet.getString("id"));
                    city.setName(resultSet.getString("name"));
                    city.setIdCountry(resultSet.getString("idCountry"));
                    cities.add(city);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }
    @Override
    public City getCityById(String cityId) {
        String sql = "SELECT id, name, idCountry FROM cities WHERE id = ?";
        City city = null;
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cityId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    city = new City();
                    city.setId(resultSet.getString("id"));
                    city.setName(resultSet.getString("name"));
                    city.setIdCountry(resultSet.getString("idCountry"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return city;
    }

}
