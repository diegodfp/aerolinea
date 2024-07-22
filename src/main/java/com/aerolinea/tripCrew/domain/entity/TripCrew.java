package com.aerolinea.tripCrew.domain.entity;

public class TripCrew {
    private String idEmployee;
    private int idConnection;

    
    public TripCrew() {
    }
    
    public TripCrew(String idEmployee, int idConnection) {
        this.idEmployee = idEmployee;
        this.idConnection = idConnection;
    }

    public String getIdEmployee() {
        return idEmployee;
    }
    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }
    public int getIdConnection() {
        return idConnection;
    }
    public void setIdConnection(int idConnection) {
        this.idConnection = idConnection;
    }

    
}
