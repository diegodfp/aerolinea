package com.aerolinea.common.domain.entity;

public class Passenger {
    private String name;
    private int age;
    private String documentType;
    private String documentNumber;
    private String assignedSeat;

    public Passenger(String name, int age, String documentType, String documentNumber) {
        this.name = name;
        this.age = age;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getAssignedSeat() {
        return assignedSeat;
    }

    public void setAssignedSeat(String assignedSeat) {
        this.assignedSeat = assignedSeat;
    }

    @Override
    public String toString() {
        return name + " (Edad: " + age + ", " + documentType + ": " + documentNumber + 
               (assignedSeat != null ? ", Asiento: " + assignedSeat : "") + ")";
    }
}
