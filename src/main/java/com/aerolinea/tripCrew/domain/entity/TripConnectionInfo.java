package com.aerolinea.tripCrew.domain.entity;

public class TripConnectionInfo {
    private int connectionId;
    private int tripId;
    private String airportName;
    private String planePlates;
    public TripConnectionInfo() {
    }
    public TripConnectionInfo(int connectionId, int tripId, String airportName, String planePlates) {
        this.connectionId = connectionId;
        this.tripId = tripId;
        this.airportName = airportName;
        this.planePlates = planePlates;
    }
    public int getConnectionId() {
        return connectionId;
    }
    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }
    public int getTripId() {
        return tripId;
    }
    public void setTripId(int tripId) {
        this.tripId = tripId;
    }
    public String getAirportName() {
        return airportName;
    }
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
    public String getPlanePlates() {
        return planePlates;
    }
    public void setPlanePlates(String planePlates) {
        this.planePlates = planePlates;
    }
    
    
}
