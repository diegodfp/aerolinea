package com.aerolinea.users.domain.entity;

public class User {
    private int id;
    private String nombre;
    private String email;
    private String password;
    private int idRol;
    
    public User() {
    }
    public User(int id, String nombre, String email, String password, int idRol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.idRol = idRol;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getIdRol() {
        return idRol;
    }
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
}
