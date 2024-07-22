package com.aerolinea.plane.domain.service;

import java.util.List;

import com.aerolinea.plane.domain.entity.Plane;

public interface PlaneService {
    void createPlane(Plane plane);

    Plane findPlaneById(String id);

    void updatePlane(Plane planeUpdate, String originalPlate);

    void deletePlane(String plate);

    List<Plane> getAllPlanes();

    boolean isPlanePlatesExists(String plates);
}
