package com.aerolinea.trip.domain.entity;

import java.sql.Date;

public class Trip {
    private int id;
    private Date date;
    private double price;
    public Trip() {
    }
    public Trip(int id, Date date, double price) {
        this.id = id;
        this.date = date;
        this.price = price;
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

    
}
