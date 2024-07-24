package com.aerolinea.plane.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aerolinea.common.infrastructure.config.DatabaseConfig;
import com.aerolinea.plane.domain.entity.Plane;
import com.aerolinea.plane.domain.service.PlaneService;

public class PlaneRepository implements PlaneService {

    @Override
    public void createPlane(Plane plane) {
        String sql = "INSERT INTO planes (plates, capacity, fabricationDate, idStatus, idModel, idAirline ) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, plane.getPlates());
            statement.setInt(2, plane.getCapacity());
            statement.setDate(3, plane.getFabricationDate());
            statement.setInt(4, plane.getIdStatus());
            statement.setInt(5, plane.getIdModel());
            statement.setInt(6, plane.getIdAerolinea());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    plane.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Plane findPlaneById(String plate) {
        String sql = "SELECT plates, capacity, fabricationDate, idStatus, idModel, idAirline FROM planes WHERE plates = ?";
        Plane plane = null;

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, plate);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    plane = new Plane();
                    plane.setPlates(resultSet.getString("plates"));
                    plane.setCapacity(resultSet.getInt("capacity"));
                    plane.setFabricationDate(resultSet.getDate("fabricationDate"));
                    plane.setIdStatus(resultSet.getInt("idStatus"));
                    plane.setIdModel(resultSet.getInt("idModel"));
                    plane.setIdAerolinea(resultSet.getInt("idAirline"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plane;
    }

    

    @Override
    public void updatePlane(Plane planeUpdate, String originalPlates) {
        String sql = "UPDATE planes SET plates = ?, capacity = ?, fabricationDate = ?, idStatus = ?, idModel = ?, idAirline = ? WHERE plates = ?";
    try (Connection connection = DatabaseConfig.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, planeUpdate.getPlates());
        statement.setInt(2, planeUpdate.getCapacity());
        statement.setDate(3, planeUpdate.getFabricationDate());
        statement.setInt(4, planeUpdate.getIdStatus());
        statement.setInt(5, planeUpdate.getIdModel());
        statement.setInt(6, planeUpdate.getIdAerolinea());
        statement.setString(7, originalPlates);
        
        statement.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    @Override
    public void deletePlane(String plates) {
        String query = "DELETE FROM planes  WHERE plates = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, plates);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Plane> getAllPlanes() {
        List<Plane> planes = new ArrayList<>();
        String sql = "SELECT * FROM planes";
        
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                Plane plane = new Plane();
                plane.setId(resultSet.getInt("id"));
                plane.setPlates(resultSet.getString("plates"));
                plane.setCapacity(resultSet.getInt("capacity"));
                plane.setFabricationDate(resultSet.getDate("fabricationDate"));
                plane.setIdStatus(resultSet.getInt("idStatus"));
                plane.setIdModel(resultSet.getInt("idModel"));
                plane.setIdAerolinea(resultSet.getInt("idAirline"));
                planes.add(plane);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planes;
    }

    @Override
    public boolean isPlanePlatesExists(String plates) {
        String sql = "SELECT COUNT(*) FROM planes WHERE plates = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, plates);
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
    public Plane findPlaneById(int id) {
        String sql = "SELECT plates, capacity, fabricationDate, idStatus, idModel, idAirline FROM planes WHERE id = ?";
        Plane plane = null;

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    plane = new Plane();
                    plane.setPlates(resultSet.getString("plates"));
                    plane.setCapacity(resultSet.getInt("capacity"));
                    plane.setFabricationDate(resultSet.getDate("fabricationDate"));
                    plane.setIdStatus(resultSet.getInt("idStatus"));
                    plane.setIdModel(resultSet.getInt("idModel"));
                    plane.setIdAerolinea(resultSet.getInt("idAirline"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plane;
    }

}
