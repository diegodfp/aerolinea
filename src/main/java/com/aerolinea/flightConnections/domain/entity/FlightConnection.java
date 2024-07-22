package com.aerolinea.flightConnections.domain.entity;

public class FlightConnection {
    private int id;
    private String numConnection;
    private int idTrip;
    private int idPlane;
    private String idAirport;

    

    public FlightConnection() {
    }

    
    public FlightConnection(int id, String numConnection, int idTrip, int idPlane, String idAirport) {
        this.id = id;
        this.numConnection = numConnection;
        this.idTrip = idTrip;
        this.idPlane = idPlane;
        this.idAirport = idAirport;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNumConnection() {
        return numConnection;
    }
    public void setNumConnection(String numConnection) {
        this.numConnection = numConnection;
    }
    public int getIdTrip() {
        return idTrip;
    }
    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }
    public int getIdPlane() {
        return idPlane;
    }
    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }
    public String getIdAirport() {
        return idAirport;
    }
    public void setIdAirport(String idAirport) {
        this.idAirport = idAirport;
    }

    
}
