package com.aerolinea.flightfares.domain.entity;

public class FlightFares {
    private int id;
    private String description;
    private String details;
    private double value;
    
    public FlightFares() {
    }
    public FlightFares(int id, String description, String details, double value) {
        this.id = id;
        this.description = description;
        this.details = details;
        this.value = value;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    
}
