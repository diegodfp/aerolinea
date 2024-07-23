package com.aerolinea.trip.domain.entity;

import java.sql.Date;

public class Trip {
    private int id;
    private Date date;
    private double price;
    private String originAirport;
    private String destinationAirport;
    
    public Trip() {
    }

    public Trip(int id, Date date, double price, String originAirport, String destinationAirport) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }
    

    
}
