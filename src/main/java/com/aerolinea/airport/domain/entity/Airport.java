package com.aerolinea.airport.domain.entity;

public class Airport {
    private String id;
    private String name;
    private String idCity;

    
    public Airport() {
    }


    public Airport(String id, String name, String idCity) {
        this.id = id;
        this.name = name;
        this.idCity = idCity;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getIdCity() {
        return idCity;
    }


    public void setIdCity(String idCity) {
        this.idCity = idCity;
    }
    
    
}
