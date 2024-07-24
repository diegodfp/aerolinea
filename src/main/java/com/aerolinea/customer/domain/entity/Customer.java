package com.aerolinea.customer.domain.entity;

public class Customer {
    private String id;
    private String name;
    private int age;
    private int idDocType;
    private String documentNumber; // Añadir este campo

    // Getters y setters

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdDocType() {
        return idDocType;
    }

    public void setIdDocType(int idDocType) {
        this.idDocType = idDocType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
