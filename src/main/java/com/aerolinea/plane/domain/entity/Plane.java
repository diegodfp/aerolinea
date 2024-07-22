package com.aerolinea.plane.domain.entity;

import java.sql.Date;

public class Plane {
    private int id;
    private String plates;
    private int capacity;
    private Date fabricationDate;
    private int idStatus;
    private int idModel;
    private int idAerolinea;
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(Date fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    // Constructor (assuming no default constructor)
    public Plane(String plates, int capacity, Date fabricationDate, int idStatus, int idModel, int idAerolinea) {
        this.plates = plates;
        this.capacity = capacity;
        this.fabricationDate = fabricationDate;
        this.idStatus = idStatus;
        this.idModel = idModel;
        this.idAerolinea = idAerolinea;
    }

    public Plane() {
    }

    public int getIdAerolinea() {
        return idAerolinea;
    }

    public void setIdAerolinea(int idAerolinea) {
        this.idAerolinea = idAerolinea;
    }

    
}
