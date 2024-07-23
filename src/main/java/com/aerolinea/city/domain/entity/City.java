package com.aerolinea.city.domain.entity;

public class City {
    private String id;
    private String name;
    private String idCountry;

    public City() {
    }

    public City(String id, String name, String idCountry) {
        this.id = id;
        this.name = name;
        this.idCountry = idCountry;
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

    public String getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(String idCountry) {
        this.idCountry = idCountry;
    }

}
