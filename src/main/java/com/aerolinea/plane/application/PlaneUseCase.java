package com.aerolinea.plane.application;

import java.util.List;

import com.aerolinea.plane.domain.entity.Plane;
import com.aerolinea.plane.domain.service.PlaneService;

public class PlaneUseCase {
    private final PlaneService planeService;

    public PlaneUseCase(PlaneService planeService) {
        this.planeService = planeService;
    }
    
    public void createPlane(Plane plane){
        planeService.createPlane(plane);
    }

    public void updatePlane(Plane planeUpdate, String originalPlate){
        planeService.updatePlane(planeUpdate, originalPlate);
    }

    public void deletePlane(String plate){
        planeService.deletePlane(plate);
    }

    public Plane findPlaneById(String id){
        return planeService.findPlaneById(id);
    }

    public List<Plane> getAllPlanes() {
        return planeService.getAllPlanes();
    }
}
